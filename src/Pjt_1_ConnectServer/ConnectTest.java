package Pjt_1_ConnectServer;

import java.awt.Color;
import java.awt.Label;
import java.awt.Panel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

//import Pjt_1_Login.LoginVo;

public class ConnectTest {
	public String driver = "oracle.jdbc.driver.OracleDriver";
	public String url = "jdbc:oracle:thin:@localhost:1521/xe";
	public String user = "c##green";
	public String password = "green1234";

	public Connection con;
	public Statement stmt;
	public ResultSet rs;
	String[] menu;
	
	
	
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
	
	public void plusList(String sql) {
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
	
	// 식단리스트 가져오는 메소드
	public String[] getList(String sql) {
		ArrayList<String> list = new ArrayList<String>();
		String[] mainlist;
		try {
			connDB();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				list.add(rs.getString("FOOD_NAME"));
			} 
			Collections.sort(list);
		} catch(SQLException e) {
			System.out.println(e);
		}
//		System.out.println(list);
//		System.out.println(list.toString());
//		list.toString();
		StringTokenizer st = new StringTokenizer(list.toString(), "[|, |]");
		int countTokens = st.countTokens();
		mainlist = new String[countTokens];
		for(int i = 0; i < countTokens; i++) {
			mainlist[i] = st.nextToken();
		}
		return mainlist;
	}
	
	public void plusMenu(String sql) {
		try {
			connDB();
			boolean b = stmt.execute(sql);
			if (!b) {
				System.out.println("Insert sucess.\n");
//				JOptionPane.showMessageDialog(null, "작성해주신 메뉴가 리스트에 추가되었습니다!", "추가완료", JOptionPane.PLAIN_MESSAGE);
			} else {
				System.out.println("Insert fail.\n");
			}
		} catch (SQLException e) {
			System.out.println(e);
//			JOptionPane.showMessageDialog(null, "이미 작성하신 메뉴이거나 메뉴이름을 확인해주세요!", "잠깐만요!", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public String[] bringMenu(String sql) {
		try {
			ArrayList<String> list = new ArrayList<String>();
			connDB();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				list.add(rs.getString("MENU_NAME"));
			}
			int i = 0;
			menu = new String[list.size()];
			for(String value : list) {
				menu[i] = value;
				i++;
			}
//			Label[]	lMenu = new Label[list.size()];
//			for(int j = 0; j<list.size(); j++) {
//				lMenu[j] = new Label();
//				lMenu[j].setText(menu[j]);
//				System.out.println(menu[j]);
//			}
		}catch(SQLException e) {
			System.out.println(e);
		}
		return menu;
	}
	
	
}
