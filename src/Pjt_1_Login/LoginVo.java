package Pjt_1_Login;

public class LoginVo {
	private String memid;
	private String mempwd;
	
	public LoginVo() {
	}
	
	public LoginVo(String memid, String mempwd) {
		this.memid = memid;
		this.mempwd = mempwd;
	}
	
	public String getMemid() {
		return memid;
	}
	
	public String getMempwd() {
		return mempwd;
	}
}
