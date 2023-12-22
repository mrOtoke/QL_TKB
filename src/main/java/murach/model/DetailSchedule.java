package murach.model;

public class DetailSchedule {
	private int maTKB;
	private String thu, maHP, maNHP;
	private int soLuong;
	private String tenHP, gioVao, gioRa, tenGV, phong;
	public DetailSchedule() {
		
	}
	public DetailSchedule(int maTKB, String thu, String maHP, String maNHP, int soLuong, String tenHP, String gioVao,
			String gioRa, String tenGV, String phong) {
		this.maTKB = maTKB;
		this.thu = thu;
		this.maHP = maHP;
		this.maNHP = maNHP;
		this.soLuong = soLuong;
		this.tenHP = tenHP;
		this.gioVao = gioVao;
		this.gioRa = gioRa;
		this.tenGV = tenGV;
		this.phong = phong;
	}
	public int getMaTKB() {
		return maTKB;
	}
	public void setMaTKB(int maTKB) {
		this.maTKB = maTKB;
	}
	public String getThu() {
		return thu;
	}
	public void setThu(String thu) {
		this.thu = thu;
	}
	public String getMaHP() {
		return maHP;
	}
	public void setMaHP(String maHP) {
		this.maHP = maHP;
	}
	public String getMaNHP() {
		return maNHP;
	}
	public void setMaNHP(String maNHP) {
		this.maNHP = maNHP;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public String getTenHP() {
		return tenHP;
	}
	public void setTenHP(String tenHP) {
		this.tenHP = tenHP;
	}
	public String getGioVao() {
		return gioVao;
	}
	public void setGioVao(String gioVao) {
		this.gioVao = gioVao;
	}
	public String getGioRa() {
		return gioRa;
	}
	public void setGioRa(String gioRa) {
		this.gioRa = gioRa;
	}
	public String getTenGV() {
		return tenGV;
	}
	public void setTenGV(String tenGV) {
		this.tenGV = tenGV;
	}
	public String getPhong() {
		return phong;
	}
	public void setPhong(String phong) {
		this.phong = phong;
	}
	
}
