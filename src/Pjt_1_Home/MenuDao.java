package Pjt_1_Home;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class MenuDao {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521/xe";
	String user = "c##green";
	String password = "green1234";

	private Connection con;
	private Statement stmt;
	ResultSet rs;
//	String query;

	MenuDao(String sql) {
		try {
			connDB();
			boolean b = stmt.execute(sql);
			if (!b) {
				System.out.println("Insert sucess.\n");
				JOptionPane.showMessageDialog(null, "작성해주신 메뉴가 리스트에 추가되었습니다!", "추가완료", JOptionPane.PLAIN_MESSAGE);
			} else {
				System.out.println("Insert fail.\n");
			}
		} catch (SQLException e) {
			System.out.println(e);
			JOptionPane.showMessageDialog(null, "이미 작성하신 메뉴이거나 메뉴이름을 확인해주세요!", "잠깐만요!", JOptionPane.ERROR_MESSAGE);
		}
	}

	void connDB() {
		try {
			Class.forName(driver);
			System.out.println("jdbc driver loading success.");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("oracle connection success.");
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			System.out.println("statement create success.");
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}
