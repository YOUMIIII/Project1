package Pjt_1_Login;

public class MemVo {
	String id;
	String pw;
	String repw;
	String birth;
	String email;

	public MemVo() {

	}

	public MemVo(String id, String pw, String repw, int birthy, int birthm, int birthd, String email) {
		this.id = id; // set
		this.pw = pw; // set
		this.repw = repw; // set
		this.birth = birthy + "-" + birthm + "-" + birthd;
		this.email = email;
	}

	public String getId() { // get
		return id;
	}

	public String getPw() {
		return pw;
	}

	public String getRepw() {
		return repw;
	}
	
	public String getBirth() {
		return birth;
	}
	
	public String getEmail() {
		return email;
	}

}
