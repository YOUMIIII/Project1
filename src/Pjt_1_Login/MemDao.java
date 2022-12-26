package Pjt_1_Login;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
//import java.sql.SQLException;
import java.sql.Statement;

public class MemDao {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@127.0.0.1:1521/xe";
	String user = "C##green";
	String password = "green1234";

	private Connection con;
	private Statement stmt;
//	private ResultSet rs;

	Login_SignFrame ls = new Login_SignFrame();

	MemDao(String sql) {
		try {
			connDB();
			boolean b = stmt.execute(sql);
			if (!b) {
				System.out.println("Insert sucess.\n");
				ls.signClose();
			} else {
				System.out.println("Insert fail.\n");
			}

		}
		catch(Exception e1) {
			ls.msg.setText("입력을 완료하지 않았습니다!");
			ls.error.setSize(290, 110);
			ls.d = ls.error.getSize();
			ls.error.setLocation((ls.screenSize.width - (int) (ls.d.getWidth())) / 2,
					(ls.screenSize.height - (int) (ls.d.getHeight())) / 2);
			ls.error.setLayout(new FlowLayout());
			ls.error.add(ls.msg);
			ls.error.add(ls.ok);
			ls.ok.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ls.error.dispose();
				}
			});
			ls.error.setVisible(true);
			
			e1.getStackTrace();
			System.out.println(e1);
		}
//		catch (SQLException e) {
//				Login_SignFrame ls = new Login_SignFrame();
//				ls.msg.setText("이미 사용중인 아이디 입니다!");
//				ls.error.setSize(290, 110);
//				ls.d = ls.error.getSize();
//				ls.error.setLocation((ls.screenSize.width - (int) (ls.d.getWidth())) / 2,
//						(ls.screenSize.height - (int) (ls.d.getHeight())) / 2);
//				ls.error.setLayout(new FlowLayout());
//				ls.error.add(ls.msg);
//				ls.error.add(ls.ok);
//				ls.ok.addActionListener(new ActionListener() {
//					public void actionPerformed(ActionEvent e) {
//						ls.error.dispose();
//					}
//				});
//				ls.error.setVisible(true);
//				System.out.println(e);
//		}
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
