package Pjt_1_Home;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import Pjt_1_ConnectServer.ConnectTest;

public class PlusMenuFrameB {
	Calendar now = Calendar.getInstance();
	String format = "YY년 MM월 dd일(E)";
	SimpleDateFormat sdf = new SimpleDateFormat(format);

	MyFont font = new MyFont();
	JFrame fPMenu;
	JLabel lMent, lMain, lSide, lTime;
	JPanel pMenu, pMain, pSide, pButton, pTime;
	JList listMain, listSide;
	RoundedButton bPMenu, bEnterMenu;
	String id, sqlm, sqls, today, menuName;
	int when;
	JScrollPane scrollMain, scrollSide;
	String[] mains, sides, menu;
	JRadioButton rbTime1, rbTime2, rbTime3, rbTime4, rbTime5;
	ConnectTest conTest = new ConnectTest();

	PlusMenuFrameB(String id) {
		lMent = new JLabel();
		lMain = new JLabel();
		lSide = new JLabel();
		fPMenu = new JFrame("식단추가");
		pMenu = new JPanel();
		pMain = new JPanel();
		pSide = new JPanel();
		pButton = new JPanel();
		sqlm = String.format("select food_name from food where id = '%s' and main = '%d'", id, 1);
		mains = conTest.getList(sqlm);
		sqls = String.format("select food_name from food where id = '%s' and main = '%d'", id, 0);
		sides = conTest.getList(sqls);
		listMain = new JList(mains);
		listSide = new JList(sides);
		bPMenu = new RoundedButton("메뉴 추가");
		bEnterMenu = new RoundedButton("식단 등록");
		scrollMain = new JScrollPane(listMain);
		scrollSide = new JScrollPane(listSide);
		rbTime1 = new JRadioButton("아침");
		rbTime2 = new JRadioButton("오전간식");
		rbTime3 = new JRadioButton("점심");
		rbTime4 = new JRadioButton("오후간식");
		rbTime5 = new JRadioButton("저녁");
		pTime = new JPanel();
		lTime = new JLabel();
		
		ButtonGroup jb = new ButtonGroup();
		jb.add(rbTime1);
		jb.add(rbTime2);
		jb.add(rbTime3);
		jb.add(rbTime4);
		jb.add(rbTime5);

		this.id = id; // 전달받은 id 지역변수로 삽입
		openPlusMenuB();
	}

	void openPlusMenuB() {
		// 받은 id 제대로 출력됨 확인
//		System.out.println(id);

		// 상단 멘트
		lMent.setText("식단을 등록해주세요:)");
		lMent.setFont(font.f3);
		
		// 시간설정
		pTime.setBounds(0, 10, 500, 30);
		lTime.setText("식단시간을 선택해주세요");
		lTime.setFont(font.fPlusLabel);
		rbTime1.setFont(font.f2p);
		rbTime2.setFont(font.f2p);
		rbTime3.setFont(font.f2p);
		rbTime4.setFont(font.f2p);
		rbTime5.setFont(font.f2p);

		// 메인메뉴 레이블
		pMenu.setLayout(null);
		pMain.setBounds(0, 45, 500, 180);
//		pMain.setBackground(Color.yellow);
		pMain.setLayout(new BorderLayout());
		lMain.setText("ㆍ메인메뉴");
		lMain.setFont(font.fPlusLabel);

		// 메인메뉴 리스트
		listMain.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listMain.setFont(font.fPlusMenuList);
		scrollMain.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));

		// 사이드메뉴 레이블
//		pSide.setBackground(Color.cyan);
		pSide.setBounds(0, 235, 500, 180);
		pSide.setLayout(new BorderLayout());
		lSide.setText("ㆍ그 외");
		lSide.setFont(font.fPlusLabel);

		// 사이드메뉴 리스트
		listSide.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listSide.setFont(font.fPlusMenuList);
		scrollSide.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 20));

		// 버튼 패널
//		pButton.setBackground(Color.white);
		pButton.setBounds(0, 425, 500, 80);
		bPMenu.setFont(font.f2);
		bPMenu.addActionListener(new ActionListener() { // 메뉴 추가 버튼 리스너
			public void actionPerformed(ActionEvent arg0) {
				PlusFoodFrame pf = new PlusFoodFrame(id);
				pf.openPlusFood();
				fPMenu.setVisible(false);
			}
		});
		bEnterMenu.setFont(font.f2);
		bEnterMenu.addActionListener(new ActionListener() { // 식단 등록 버튼 리스너
			public void actionPerformed(ActionEvent arg0) {
				today = sdf.format(now.getTime());
				if(rbTime1.isSelected()) {
					when = 1;
				}else if(rbTime2.isSelected()) {
					when = 2;
				}else if(rbTime3.isSelected()) {
					when = 3;
				}else if(rbTime4.isSelected()) {
					when = 4;
				}else {
					when = 5;
				}
				menuName = (String) listMain.getSelectedValue();
				//메인메뉴는 단일선택, 사이드는 다중선택이라 선택값 불러오는게 달라서 따로 쿼리작성함.
				String sql = "insert into todaymenu values('" + id + "','" + today + "','" + when + "','" + menuName
						+ "')";
				ConnectTest con = new ConnectTest();
				con.plusMenu(sql);

				List<String> ls = listSide.getSelectedValuesList();
				for (String value : ls) {
					sql = "insert into todaymenu values('" + id + "','" + today + "','" + when + "','" + value + "')";
					con = new ConnectTest();
					con.plusMenu(sql);
				}
				
//				String sqlb = String.format("select menu_name from todaymenu where id = '%s' and today = '%s' and today_when = '%d'", id, today, 1);
//				menu = con.bringMenu(sqlb);
				HomeFrame hf = new HomeFrame();
				hf.setId(id);
				hf.homeOpen();
				fPMenu.setVisible(false);
			}
		});

		// 아침 식단 추가 프레임
		fPMenu.add(lMent, "North");
		fPMenu.add(pMenu, "Center");
		pMenu.add(pTime);
		pTime.add(lTime);
		pTime.add(rbTime1);
		pTime.add(rbTime2);
		pTime.add(rbTime3);
		pTime.add(rbTime4);
		pTime.add(rbTime5);
		pMenu.add(pMain);
		pMain.add(lMain, "North");
		pMain.add(scrollMain, "Center");
		pMenu.add(pSide);
		pSide.add(lSide, "North");
		pSide.add(scrollSide, "Center");
		pMenu.add(pButton);
		pButton.add(bPMenu);
		pButton.add(bEnterMenu);

		fPMenu.setSize(500, 520);
		fPMenu.setLocationRelativeTo(null);
		fPMenu.setResizable(false);
		fPMenu.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		fPMenu.setVisible(true);

	}

//	void getId(String id) {
//		this.id = id;
//	}
}
