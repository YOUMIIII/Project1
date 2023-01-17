package Pjt_1_Login;

public class LoginVo {
	private String memid;
	private String mempwd;
	private String mememail;
	private String babyname;
	private String babybirth;
	private String babysex;
	private String babyphoto;
	
	public LoginVo() {
	}
	
	public LoginVo(String memid, String mempwd) {
		this.memid = memid;
		this.mempwd = mempwd;
	}
	public LoginVo(String id, String pwd, String email, String name, String birth, String sex, String photo) {
		this.memid = id;
		this.mempwd = pwd;
		this.mememail = email;
		this.babyname = name;
		this.babybirth = birth;
		this.babysex = sex;
		this.babyphoto = photo;
		
	}
	
	public String getMemid() {
		return memid;
	}
	
	public String getMempwd() {
		return mempwd;
	}
	public String getMememail() {
		return mememail;
	}
	public String getMemname() {
		return babyname;
	}
	public String getMemphoto() {
		return babyphoto;
	}
	public String getMemSex() {
		return babysex;
	}
	public String getMembirth() {
		return babybirth;
	}
}
