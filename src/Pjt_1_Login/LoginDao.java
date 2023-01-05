package Pjt_1_Login;

import java.sql.DriverManager;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Pjt_1_ConnectServer.ConnectTest;

public class LoginDao {

	ConnectTest cont = new ConnectTest();
	String query;
	
	public LoginDao(){
		
	}

	LoginDao(String id) {
		query = String.format("Select * from member where id = '%s'", id);
	}

	public ArrayList<LoginVo> list() {
		ArrayList<LoginVo> list = new ArrayList<LoginVo>();
		try {
			connDB();
			cont.rs = cont.stmt.executeQuery(query);

			if(cont.rs.next()) {
				String memid = cont.rs.getString("ID");
				String mempwd = cont.rs.getString("PW");
				LoginVo data = new LoginVo(memid, mempwd);
				list.add(data);
				
			} else {
				JOptionPane.showMessageDialog(null, "입력하신 아이디가 존재하지않습니다.","아이디 오류",JOptionPane.ERROR_MESSAGE);
				System.out.println("입력하신 아이디가 틀렸습니다.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void connDB() {
		try {
			Class.forName(cont.driver);
			System.out.println("jdbc driver loading success");
			cont.con = DriverManager.getConnection(cont.url, cont.user, cont.password);
			System.out.println("oracle connection success.");
			cont.stmt = cont.con.createStatement();
			System.out.println("statement create success.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
