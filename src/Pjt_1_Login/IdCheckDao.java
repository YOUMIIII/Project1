package Pjt_1_Login;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;

import javax.swing.JOptionPane;

import Pjt_1_ConnectServer.ConnectTest;

public class IdCheckDao {
//	String driver = "oracle.jdbc.driver.OracleDriver";
//	String url = "jdbc:oracle:thin:@localhost:1521/xe";
//	String user = "c##green";
//	String password = "green1234";
//
//	private Connection con;
//	private Statement stmt;
//	ResultSet rs;
	ConnectTest cont = new ConnectTest();
	String query;

	public IdCheckDao() {

	}

	public IdCheckDao(String id) {
		query = String.format("Select * from member where id = '%s'", id);

		try {
			cont.connDB();
			cont.rs = cont.stmt.executeQuery(query);

			if (cont.rs.next()) {
				String memid = cont.rs.getString("ID");
				if (memid.equals(id)) {
					JOptionPane.showMessageDialog(null, "이미 사용중인 아이디 입니다.", "중복확인", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				if (id.length() < 6 || id.length() > 12) {
					JOptionPane.showMessageDialog(null, "아이디의 글자수를 한번 더 확인해주세요!", "잠깐만요!", JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "사용가능한 아이디 입니다.", "중복확인", JOptionPane.PLAIN_MESSAGE);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
