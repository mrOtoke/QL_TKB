package murach.com;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import murach.model.DetailSchedule;
import mysql.DAO.DetailScheduleDAO;
import java.io.IOException;
import java.sql.SQLException;


public class ChangeRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		DetailSchedule dtS = new DetailSchedule();
		
		dtS.setThu(request.getParameter("thu"));
		dtS.setGioVao(request.getParameter("gioVao"));
		dtS.setGioRa(request.getParameter("gioRa"));
		dtS.setPhong(request.getParameter("phong"));
		
		String message1 = request.getParameter("message1");
		
		
		DetailScheduleDAO dtSchedule = new DetailScheduleDAO();
		int row;
		try {
			row = dtSchedule.updateSchedule(dtS);
			if(row>0) {
				message1 = "Cập nhật thành công";
			}
			else {
				message1 = "Chưa có cập nhật cái nào hết";
			}
			session.setAttribute("message1",message1);
			request.getRequestDispatcher("changeRoom.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
