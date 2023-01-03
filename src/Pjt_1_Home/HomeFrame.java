package Pjt_1_Home;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import Pjt_1_ConnectServer.ConnectTest;
import Pjt_1_Login.LoginFrame;
import Pjt_1_Refridge.RefridgeFrame;

public class HomeFrame {
//	Date today = new Date();
//	SimpleDateFormat dateFormat1 = new SimpleDateFormat("YY년 MM월 dd일(E)", Locale.KOREA);
	ConnectTest con = new ConnectTest();
	
	Calendar now = Calendar.getInstance();
	String format = "YY년 MM월 dd일(E)";
	SimpleDateFormat sdf = new SimpleDateFormat(format);
	
	MyFont font = new MyFont();
	MenuFrame mToday = new MenuFrame();
	MenuFrame mYesterday = new MenuFrame();
	MenuFrame mYYesterday = new MenuFrame();
	JFrame fHome;
	JPanel pDate, pBar, pLabel, pNorthBar;
	JLabel lId, lDate, lDateY, lDateYY, lMenuB, lMenuL, lMenuD, lMenu1, lMenu2;
	TextField tfh, tfw;
	RoundedButton bMenuB, bMenuL, bMenuD, bMenuS1, bMenuS2, bFrid, bLogout, bEditB;
	String id;
	String[] nutri = { "탄수화물", "단백질", "채소", "칼슘", "지방", "과일", "기타" };
	JPanel[] pNutri;
	Color[] cNutri;
	JLabel[] lNutri, lMenu;

	LineBorder bb = new LineBorder(Color.gray, 1, false);
	
	CardLayout card = new CardLayout();
	Panel card1 = new Panel();
	Panel card2 = new Panel();


	public HomeFrame() {
//		System.out.println(id); // 아이디 null
		fHome = new JFrame(); // 메인페이지 프레임
		fHome.setTitle("식단");
		
		// 날짜 레이블 설정
		String date = sdf.format(now.getTime()); 
		lDate = new JLabel(date);
		// 어제날짜 레이블
		now.add(Calendar.DATE, -1);
		date = sdf.format(now.getTime());
		lDateY = new JLabel(date);
		// 그제날짜 레이블
		now.add(Calendar.DATE, -2);
		date = sdf.format(now.getTime());
		lDateYY = new JLabel(date);
		
		lMenuB = new JLabel("아침");
		lMenuL = new JLabel("점심");
		lMenuD = new JLabel("저녁");
		lMenu1 = new JLabel("간식");
		lMenu2 = new JLabel("간식");
		bMenuB = new RoundedButton("식단추가");
		bMenuL = new RoundedButton("식단추가");
		bMenuD = new RoundedButton("식단추가");
		bMenuS1 = new RoundedButton("식단추가");
		bMenuS2 = new RoundedButton("식단추가");
		bEditB = new RoundedButton("식단수정");
		pBar = new JPanel();
		pNorthBar = new JPanel();
		lId = new JLabel();
		bFrid = new RoundedButton("냉장고보기");
		pLabel = new JPanel();
	
		bLogout = new RoundedButton("로그아웃");
		pNutri = new JPanel[7];
		lNutri = new JLabel[7];
		cNutri = new Color[7];
		cNutri[0] = new Color(255,243,138);
		cNutri[1] = new Color(255,155,5);
		cNutri[2] = new Color(42,255,102);
		cNutri[3] = new Color(46,155,255);
		cNutri[4] = new Color(186,0,252);
		cNutri[5] = new Color(255,137,187);
		cNutri[6] = new Color(2,79,151);
		lMenu = new JLabel[5];
		

	}

	public void setId(String id) { // loginframe에서 id 받아서 this.id에 셋팅
		this.id = id;
	}

	public String getId() { // setId에서 id 설정한 값 return
		return id;
	}

	public void homeOpen() { // 메인페이지 오픈
		fHome.setLayout(null);
		System.out.println(id);

		// 상단 바 패널 설정
		pNorthBar.setBounds(170, 0, 1266, 40);
		pNorthBar.setBackground(Color.gray);
		
		for(int i = 0; i< nutri.length; i++) {
			lNutri[i] = new JLabel();
			pNutri[i] = new JPanel();
			lNutri[i].setText(nutri[i]); // 상단 바 영양소레이블 설정
			lNutri[i].setFont(font.fNorthLabel); // 상단 바 영양소레이블 설정
			pNutri[i].setBackground(cNutri[i]); // 상단 바 영양소패널 색깔 설정
		}
		

		// 왼쪽 바 패널 설정
		pBar.setBackground(Color.GRAY);
		pBar.setBounds(0, 0, 180, 1024);
		lId.setText("<html><body><br><br>안녕하세요,<br>" + id + "님!<br>오늘도 멋진 <br> 유아식 식단을<br>도와드릴게요:)<br><br></body></html>");
		lId.setFont(font.f3);
		lId.setForeground(Color.white); // 레이블 색 설정 메소드
		bFrid.setSize(100, 30);
		bFrid.setFont(font.f2);
		bFrid.addActionListener(new ActionListener() { // 냉장고보기 리스너
			public void actionPerformed(ActionEvent arg0) {
				new RefridgeFrame();
			}
		});
		bLogout.setSize(100, 30);
		bLogout.setFont(font.f2);
		bLogout.addActionListener(new ActionListener() { // 로그아웃 리스너 - 홈프레임 닫고 로그인창으로
			public void actionPerformed(ActionEvent arg0) {
				fHome.setVisible(false);
				new LoginFrame();
			}
		});

		// 날짜 패널설정
		fHome.getContentPane().setBackground(Color.white); // JFrame은 배경색 지정시 getContentPane 메소드 필요
//		pDate.setLayout(new BorderLayout());
//		pDate.setBounds(270, 0, 330, 150);
//		pDate.setBackground(Color.white);
		lDate.setFont(font.f1);
		lDate.setVerticalAlignment(JLabel.CENTER);
		lDate.setHorizontalAlignment(JLabel.CENTER);
		lDateY.setFont(font.f1);
		lDateY.setVerticalAlignment(JLabel.CENTER);
		lDateY.setHorizontalAlignment(JLabel.CENTER);
		lDateYY.setFont(font.f1);
		lDateYY.setVerticalAlignment(JLabel.CENTER);
		lDateYY.setHorizontalAlignment(JLabel.CENTER);

		//
		mToday.pMenu.setBounds(270, 40, 330, 1000);
		mYesterday.pMenu.setBounds(650, 40, 330, 1000);
		mYYesterday.pMenu.setBounds(1030, 40, 330, 1000);
		
		bMenuB.setFont(font.f2);
		
		//
		
		String sqlb = String.format("select menu_name from todaymenu where id = '%s' and today = '%s' and today_when = '%d'", id, lDate.getText(), 1);
		String[] menu = con.bringMenu(sqlb);
		System.out.println(menu);
		for(int i = 0; i<menu.length; i++) {
			lMenu[i] = new JLabel();
			lMenu[i].setText(menu[i]);
			lMenu[i].setFont(font.fPlusMenuList);
		}
//		mToday.pMenuB.setLayout(card);
//		card1.setBackground(Color.LIGHT_GRAY);
//		card2.setBackground(Color.orange);
//		card2.add(bEditB);
//		mToday.pMenuB.add(card1,"1");
//		mToday.pMenuB.add(card2,"2");
//		card1.add(bMenuB);
		
		bMenuB.addActionListener(new ActionListener() { // 일단 아침,간식,점심,저녁 따로 프레임 만들어서 연결
			public void actionPerformed(ActionEvent arg0) {
				new PlusMenuFrameB(id); // id를 넣어서 다음프레임으로 값 전달
				fHome.setVisible(false);
			}
		});
		
//		mToday.pMenuB.add(bMenuB);
		bMenuS1.setFont(font.f2);
		bMenuS1.addActionListener(new ActionListener() { // 일단 아침,간식,점심,저녁 따로 프레임 만들어서 연결
			public void actionPerformed(ActionEvent arg0) {
				new PlusMenuFrameB(id); // id를 넣어서 다음프레임으로 값 전달
			}
		});
		bMenuL.setFont(font.f2);
		bMenuL.addActionListener(new ActionListener() { // 일단 아침,간식,점심,저녁 따로 프레임 만들어서 연결
			public void actionPerformed(ActionEvent arg0) {
				new PlusMenuFrameB(id); // id를 넣어서 다음프레임으로 값 전달
			}
		});
		bMenuS2.setFont(font.f2);
		bMenuS2.addActionListener(new ActionListener() { // 일단 아침,간식,점심,저녁 따로 프레임 만들어서 연결
			public void actionPerformed(ActionEvent arg0) {
				new PlusMenuFrameB(id); // id를 넣어서 다음프레임으로 값 전달
			}
		});
		bMenuD.setFont(font.f2);
		bMenuD.addActionListener(new ActionListener() { // 일단 아침,간식,점심,저녁 따로 프레임 만들어서 연결
			public void actionPerformed(ActionEvent arg0) {
				new PlusMenuFrameB(id); // id를 넣어서 다음프레임으로 값 전달
			}
		});
		
		
		
//		// 식단 패널설정
//		pMenu.setBounds(270, 80, 330, 1000);
//		pMenu.setBackground(Color.blue);
//		pMenu.setLayout(null);
//		// 식단 패널_아침식단
//		pMenuB.setBounds(0, 80, 330, 250);
//		pMenuB.setBackground(Color.pink);
//		pMenuB.setBorder(bb);
//		bMenuB.setFont(font.f2);
//		bMenuB.addActionListener(new ActionListener() { // 일단 아침,간식,점심,저녁 따로 프레임 만들어서 연결
//			public void actionPerformed(ActionEvent arg0) {
//				new PlusMenuFrameB(id); // id를 넣어서 다음프레임으로 값 전달
//			}
//		});
//
//		// 식단 패널_간식1식단
//		pMenuS1.setBounds(0, 250, 330, 65);
//		pMenuS1.setBackground(Color.white);
//		pMenuS1.setBorder(bb);
//		bMenuS1.setFont(font.f2);
//		bMenuS1.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				new PlusMenuFrameB(id); // id를 넣어서 다음프레임으로 값 전달
//			}
//		});
//
//		// 식단 패널_점심식단
//		pMenuL.setBounds(0, 315, 330, 250);
//		pMenuL.setBackground(Color.white);
//		pMenuL.setBorder(bb);
//		bMenuL.setFont(font.f2);
//		bMenuL.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				new PlusMenuFrameB(id); // id를 넣어서 다음프레임으로 값 전달
//			}
//		});
//
//		// 식단 패널_간식2식단
//		pMenuS2.setBounds(0, 565, 330, 65);
//		pMenuS2.setBackground(Color.white);
//		pMenuS2.setBorder(bb);
//		bMenuS2.setFont(font.f2);
//		bMenuS2.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				new PlusMenuFrameB(id); // id를 넣어서 다음프레임으로 값 전달
//			}
//		});
//
//		// 식단 패널_저녁식단
//		pMenuD.setBounds(0, 630, 330, 250);
//		pMenuD.setBackground(Color.white);
//		pMenuD.setBorder(bb);
//		bMenuD.setFont(font.f2);
//		bMenuD.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				new PlusMenuFrameB(id); // id를 넣어서 다음프레임으로 값 전달
//			}
//		});

		// 라벨 바 패널 설정
		pLabel.setLayout(null);
		pLabel.setBackground(Color.WHITE);
		pLabel.setBounds(175, 110, 95, 885);
		lMenuB.setFont(font.f1);
		lMenuB.setBounds(30, 0, 90, 50);
		lMenu1.setFont(font.f1);
		lMenu1.setBounds(30, 230, 90, 50);
		lMenuL.setFont(font.f1);
		lMenuL.setBounds(30, 315, 90, 50);
		lMenu2.setFont(font.f1);
		lMenu2.setBounds(30, 545, 90, 50);
		lMenuD.setFont(font.f1);
		lMenuD.setBounds(30, 630, 90, 50);

//		JPanel pNuB[] = new JPanel[5];
//		for (int i = 0; i < pNuB.length; i++) {
//			pNuB[i] = new JPanel();
//			pNuB[i].setBackground(Color.BLUE);
//		}
//		JPanel pNuL[] = new JPanel[5];
//		for (int i = 0; i < pNuL.length; i++) {
//			pNuL[i] = new JPanel();
//		}
//		JPanel pNuD[] = new JPanel[5];
//		for (int i = 0; i < pNuD.length; i++) {
//			pNuD[i] = new JPanel();
//		}
//		for (int i = 0; i < pNuB.length; i++) {
//			pMenuB.add(pNuB[i]);
//		}
//		for (int i = 0; i < pNuL.length; i++) {
//			pMenuL.add(pNuL[i]);
//		}
//		for (int i = 0; i < pNuD.length; i++) {
//			pMenuD.add(pNuD[i]);
//		}
		// 여기까지 체크
		
		
		for(int i = 0 ; i<nutri.length; i++) {
			pNorthBar.add(pNutri[i]);
			pNorthBar.add(lNutri[i]);
		}
		for(int i = 0; i<menu.length; i++) {
			mToday.pMenuB.add(lMenu[i]);
		}

		mToday.pDate.add(lDate, "Center");
		mYesterday.pDate.add(lDateY, "Center");
		mYYesterday.pDate.add(lDateYY, "Center");
		
		mToday.pMenuB.add(bMenuB);
		mToday.pMenu1.add(bMenuS1);
		mToday.pMenuL.add(bMenuL);
		mToday.pMenu2.add(bMenuS2);
		mToday.pMenuD.add(bMenuD);
//		pDate.add(lDate, "Center");
		
		pLabel.add(lMenuB);
		pLabel.add(lMenu1);
		pLabel.add(lMenuL);
		pLabel.add(lMenu2);
		pLabel.add(lMenuD);
		pBar.add(lId);
		pBar.add(bFrid);
		pBar.add(bLogout);
		fHome.add(mToday.pMenu);
		fHome.add(mYesterday.pMenu);
		fHome.add(mYYesterday.pMenu);
//		fHome.add(pMenu);
		fHome.add(pBar);
		fHome.add(pNorthBar);
		fHome.add(pLabel);
//		fHome.add(pDate);
		// 프레임 사이즈, 위치 설정
		fHome.setSize(1440, 1024);
		fHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fHome.setLocationRelativeTo(null); // 모니터 가운데로 위치시켜주는 JFrame메소드
		fHome.setVisible(true);
	}
}
