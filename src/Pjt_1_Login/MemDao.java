package Pjt_1_Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class MemDao {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@127.0.0.1:1521/xe";
	String user = "C##green";
	String password = "green1234";

	private Connection con;
	private Statement stmt;

	Login_SignFrame ls = new Login_SignFrame();

	MemDao(String sql) {
		try {
			connDB();
			boolean b = stmt.execute(sql);
			if (!b) {
				System.out.println("Insert sucess.\n");
				ls.signClose();
				JOptionPane.showMessageDialog(null, "가입완료! 환영합니다:) 가입하신 아이디로 로그인해주세요!","가입완료", JOptionPane.PLAIN_MESSAGE);
			} else {
				System.out.println("Insert fail.\n");
			}
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "이미 사용중인 아이디입니다!", "잠깐만요!",
					JOptionPane.ERROR_MESSAGE);
				System.out.println(e);
		}
	}

	void connDB() {
		try {
			Class.forName(driver);
//			System.out.println("jdbc driver loading success.");
			con = DriverManager.getConnection(url, user, password);
//			System.out.println("oracle connection success.");
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
//			System.out.println("statement create success.");
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}
