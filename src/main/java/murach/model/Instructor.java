package murach.model;

public class Instructor {
	
	private String maGV,tenGV,email;

	public Instructor(String maGV, String tenGV, String email) {

		this.maGV = maGV;
		this.tenGV = tenGV;
		this.email = email;
	}

	public Instructor() {

	}

	public String getMaGV() {
		return maGV;
	}

	public void setMaGV(String maGV) {
		this.maGV = maGV;
	}

	public String getTenGV() {
		return tenGV;
	}

	public void setTenGV(String tenGV) {
		this.tenGV = tenGV;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
