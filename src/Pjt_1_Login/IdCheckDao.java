package Pjt_1_Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class IdCheckDao {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521/xe";
	String user = "c##green";
	String password = "green1234";

	private Connection con;
	private Statement stmt;
	ResultSet rs;
	String query;

	public IdCheckDao() {

	}

	IdCheckDao(String id) {
		query = String.format("Select * from member where id = '%s'", id);

		try {
			connDB();
			rs = stmt.executeQuery(query);

			if (rs.next()) {
				String memid = rs.getString("ID");
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
