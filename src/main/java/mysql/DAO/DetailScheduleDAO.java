package mysql.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import murach.model.DetailSchedule;
import murach.model.Instructor;


public class DetailScheduleDAO {
	private String URL = "jdbc:mysql://localhost:3306/QL_TKB";
	private String username = "root";
	private String password = "842641";

	private static final String SELECT_SCHEDULE_INSTRUCTOR_SQL = "select tkb.MaTKB, tkb.thu, mh.MaMH, ct.MaNHP, ct.SL, mh.TenMH, tkb.GioVaoHoc,tkb.GioTanHoc,gv.TenGV,tkb.Phong "
			+ "from GiangVien gv join ThoiKhoaBieu tkb on gv.MaGV=tkb.MaGV"
			+ "	 join MonHoc mh on mh.MaMH=tkb.MaMH"
			+ "     join chitiettkb ct on ct.MaTKB=tkb.MaTKB"
			+ " where gv.MaGV = ? and tkb.TrangThai='Chưa diễn ra'";
	
	private static final String SELECT_INSTRUCTOR_BY_ID_SQL = "select * from GiangVien where MaGV = ?";
	
	private static final String SELECT_DAY_OFF_SQL = "select tkb.MaTKB, tkb.thu, mh.MaMH, ct.MaNHP, ct.SL, mh.TenMH, tkb.GioVaoHoc,tkb.GioTanHoc,tkb.Phong"
			+ " from GiangVien gv join ThoiKhoaBieu tkb on gv.MaGV=tkb.MaGV"
			+ "	 join MonHoc mh on mh.MaMH=tkb.MaMH"
			+ "     join chitiettkb ct on ct.MaTKB=tkb.MaTKB"
			+ " where gv.MaGV=? and tkb.TrangThai='Chưa diễn ra' and DAYOFWEEK(?)=tkb.thu";
	
	private static final String UPDATE_STATUS_SCHEDULE_SQL = "UPDATE ThoiKhoaBieu tkb"
			+ " JOIN GiangVien gv ON tkb.MaGV = gv.MaGV"
			+ " JOIN MonHoc mh ON tkb.MaMH = mh.MaMH"
			+ " JOIN chitiettkb ct ON tkb.MaTKB = ct.MaTKB"
			+ " SET tkb.TrangThai = 'Đăng ký nghỉ'"
			+ " WHERE gv.MaGV=?"
			+ "    AND DAYOFWEEK(?)=tkb.thu"
			+ "    AND tkb.TrangThai='Chưa diễn ra'";
	
	private static final String UPDATE_SCHEDULE_SQL = "UPDATE ThoiKhoaBieu tkb"
			+ " JOIN GiangVien gv ON tkb.MaGV = gv.MaGV"
			+ " JOIN MonHoc mh ON tkb.MaMH = mh.MaMH"
			+ " JOIN chitiettkb ct ON tkb.MaTKB = ct.MaTKB"
			+ " SET tkb.thu=?, tkb.gioVaoHoc=?, tkb.gioTanHoc=?, tkb.phong=?"
			+ " WHERE tkb.MaTKB=?";
	
	private static final String SELECT_SCHEDULE_BY_ID_SQL = "select tkb.MaTKB, tkb.thu, mh.MaMH, ct.MaNHP, ct.SL, mh.TenMH, tkb.GioVaoHoc,tkb.GioTanHoc,gv.TenGV,tkb.Phong"
			+ "	from GiangVien gv join ThoiKhoaBieu tkb on gv.MaGV=tkb.MaGV"
			+ "	join MonHoc mh on mh.MaMH=tkb.MaMH"
			+ "	join chitiettkb ct on ct.MaTKB=tkb.MaTKB"
			+ " where tkb.MaTKB=?";
	
	
	public int updateSchedule (DetailSchedule schedule) throws SQLException {
		Connection con = getConnection();
		PreparedStatement st = con.prepareStatement(UPDATE_SCHEDULE_SQL);
		st.setString(1,schedule.getThu());
		st.setString(2,schedule.getGioVao());
		st.setString(3,schedule.getGioRa());
		st.setString(4,schedule.getPhong());
		st.setInt(5,schedule.getMaTKB());
		int row = st.executeUpdate();
		
		st.close();
		con.close();
		return row;
	}
	
	
	protected Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(URL,username,password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	
	
	public Instructor showInfoInstructor (String maGV) throws SQLException {
		Instructor instructor = new Instructor();
		Connection con = getConnection();
		PreparedStatement st = con.prepareStatement(SELECT_INSTRUCTOR_BY_ID_SQL);
		
		st.setString(1,maGV);
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			instructor.setMaGV(rs.getString("MaGV"));
			instructor.setTenGV(rs.getString("TenGV"));
			instructor.setEmail(rs.getString("Email"));
		}
		
		rs.close();
		st.close();
		con.close();
		
		return instructor;
	}

	public List<DetailSchedule> showSchedule (String maGV) throws SQLException {
		Connection con = getConnection();
		PreparedStatement st = con.prepareStatement(SELECT_SCHEDULE_INSTRUCTOR_SQL);

		List<DetailSchedule> list = new ArrayList<>();
		try {
			st.setString(1,maGV);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				DetailSchedule schedule = new DetailSchedule();
				schedule.setMaTKB(rs.getInt("MaTKB"));
				schedule.setThu(rs.getString("thu"));
				schedule.setMaHP(rs.getString("MaMH"));
				schedule.setMaNHP(rs.getString("MaNHP"));
				schedule.setSoLuong(rs.getInt("SL"));
				schedule.setTenHP(rs.getString("TenMH"));
				schedule.setGioVao(rs.getString("GioVaoHoc"));
				schedule.setGioRa(rs.getString("GioTanHoc"));
				schedule.setPhong(rs.getString("Phong"));
				
				list.add(schedule);
			}
			
			rs.close();
			st.close();
			con.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<DetailSchedule> showDayOff (String maGV,String date) throws SQLException {
		Connection con = getConnection();
		PreparedStatement st = con.prepareStatement(SELECT_DAY_OFF_SQL);
		List<DetailSchedule> list = new ArrayList<>();
		try {
			st.setString(1,maGV);
			st.setString(2,date);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				DetailSchedule schedule = new DetailSchedule();
				schedule.setMaTKB(rs.getInt("MaTKB"));
				schedule.setThu(rs.getString("thu"));
				schedule.setMaHP(rs.getString("MaMH"));
				schedule.setMaNHP(rs.getString("MaNHP"));
				schedule.setSoLuong(rs.getInt("SL"));
				schedule.setTenHP(rs.getString("TenMH"));
				schedule.setGioVao(rs.getString("GioVaoHoc"));
				schedule.setGioRa(rs.getString("GioTanHoc"));
				schedule.setPhong(rs.getString("Phong"));
				
				list.add(schedule);
			}
			
			rs.close();
			st.close();
			con.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int update_StatusSchedule (String maGV,String day) throws SQLException {
		
		Connection con = getConnection();
		PreparedStatement st = con.prepareStatement(UPDATE_STATUS_SCHEDULE_SQL);
		st.setString(1,maGV);
		st.setString(2,day);
		
		int row = st.executeUpdate();
		
		st.close();
		con.close();
		
		return row;
	}


	public DetailSchedule getScheduleByID(int maTKB) throws SQLException {
		// TODO Auto-generated method stub
		DetailSchedule dtS = new DetailSchedule();
		Connection con = getConnection();
		PreparedStatement st = con.prepareStatement(SELECT_SCHEDULE_BY_ID_SQL);
		st.setInt(1, maTKB);
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			dtS.setMaTKB(rs.getInt("MaTKB"));
			dtS.setThu(rs.getString("thu"));
			dtS.setMaHP(rs.getString("MaMH"));
			dtS.setMaNHP(rs.getString("MaNHP"));
			dtS.setSoLuong(rs.getInt("SoLuong"));
			dtS.setTenHP(rs.getString("TenHP"));
			dtS.setGioVao(rs.getString("GioVaoHoc"));
			dtS.setGioRa(rs.getString("GioTanHoc"));
			dtS.setTenGV(rs.getString("TenGV"));
			dtS.setPhong(rs.getString("Phong"));
		}
		
		rs.close();
		st.close();
		con.close();
		
		return dtS;
	}
	
	
}
