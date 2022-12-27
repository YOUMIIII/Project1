package Pjt_1_Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class LoginDao {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521/xe";
	String user = "c##green";
	String password = "green1234";

	private Connection con;
	private Statement stmt;
	ResultSet rs;
	String query;

	LoginDao(String id) {
		query = String.format("Select * from member where id = '%s'", id);
	}

	public ArrayList<LoginVo> list() {
		ArrayList<LoginVo> list = new ArrayList<LoginVo>();
		try {
			connDB();
			rs = stmt.executeQuery(query);

			if(rs.next()) {
				String memid = rs.getString("ID");
				String mempwd = rs.getString("PW");
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
			Class.forName(driver);
			System.out.println("jdbc driver loading success");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("oracle connection success.");
			stmt = con.createStatement();
			System.out.println("statement create success.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
