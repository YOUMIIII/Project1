package Pjt_1_ConnectServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Pjt_1_Fridge.FridgeVO;

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
	int[] nutrinum;

	public void connDB() {
		try {
			Class.forName(driver);
//			System.out.println("jdbc driver loading success");
			con = DriverManager.getConnection(url, user, password);
//			System.out.println("oracle connection success.");
			stmt = con.createStatement();
//			System.out.println("statement create success.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 메뉴 데이터베이스에 추가해주는 메소드
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
			while (rs.next()) {
				list.add(rs.getString("FOOD_NAME"));
			}
			Collections.sort(list);
		} catch (SQLException e) {
			System.out.println(e);
		}
//		System.out.println(list);
//		System.out.println(list.toString());
//		list.toString();
		StringTokenizer st = new StringTokenizer(list.toString(), "[|, |]");
		int countTokens = st.countTokens();
		mainlist = new String[countTokens];
		for (int i = 0; i < countTokens; i++) {
			mainlist[i] = st.nextToken();
		}
		return mainlist;
	}
	
	public ArrayList<FridgeVO> getFridge(String sql) {
		ArrayList<FridgeVO> list = new ArrayList<FridgeVO>();
		try{
			connDB();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String id = rs.getString("ID");
				String ingredient = rs.getString("INGREDIENT");
				String quantity = rs.getString("QUANTITY");
				String date = rs.getString("BEST_BEFORE");
				
				FridgeVO data = new FridgeVO(id, ingredient, quantity, date);
				list.add(data);
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
		
		return list;
	}

	// 선택한 메뉴를 오늘 식단에 추가해주는 메소드
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

	//
	public ArrayList<String> bringMenuList(String sql) {
		ArrayList<String> list = new ArrayList<String>();
		try {
			connDB();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				list.add(rs.getString("MENU_NAME"));
			}
			
		} catch (SQLException e) {
			System.out.println(e);
		}
		return list;
	}
	
	public String[] bringMenu(String sql) {
		try {
			ArrayList<String> list = new ArrayList<String>();
			connDB();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				list.add(rs.getString("MENU_NAME"));
			}
			int i = 0;
			menu = new String[list.size()];
			for (String value : list) {
				menu[i] = value;
				i++;
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return menu;
	}
	
	// food(t)에서 그 음식의 영양소 가져와서 list로
	public int[] bringNutri(String sql) {
		try {
			ArrayList<Integer> list = new ArrayList<Integer>();
			connDB();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				for (int i = 1; i <= 7; i++) {
					list.add(rs.getInt("NUTRITION"+i));
				}
				nutrinum = new int[list.size()];
				int i = 0;
				for(Integer value : list) {
					nutrinum[i] = value;
					i++;
				}
			}

		} catch (SQLException e) {
			System.out.println(e);
		}
		return nutrinum;
	}
	
	public void insertFridge(String sql) {
		try {
			connDB();
			boolean b = stmt.execute(sql);
			if (!b) {
				System.out.println("Insert sucess.\n");
			} else {
				System.out.println("Insert fail.\n");
			}
		} catch (SQLException e) {
			System.out.println(e);
			System.out.println("여기?");
		}
	}
	
	public void updateFridge(String sql) {
		try {
			connDB();
			boolean b = stmt.execute(sql);
			if (!b) {
				System.out.println("Update sucess.\n");
				JOptionPane.showMessageDialog(null, "재고량이 수정되었습니다:)", "수정완료", JOptionPane.PLAIN_MESSAGE);
			} else {
				System.out.println("Update fail.\n");
			}
		}catch (SQLException e) {
			System.out.println(e);
			System.out.println("저기?");
		}
	}
}
