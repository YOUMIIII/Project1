package Pjt_1_Home;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import Pjt_1_ConnectServer.ConnectTest;
import Pjt_1_ConnectServer.PanelGroup;
import Pjt_1_Fridge.FridgeFrame;
import Pjt_1_Login.LoginFrame;

public class HomeFrame {
	Date today = new Date();
	SimpleDateFormat dateFormat1 = new SimpleDateFormat("YY년 MM월 dd일(E)", Locale.KOREA);
	ConnectTest con = new ConnectTest();

	Calendar now = Calendar.getInstance();
	String format = "YY년 MM월 dd일(E)";
	SimpleDateFormat sdf = new SimpleDateFormat(format);

	MyFont font = new MyFont();

	PanelGroup pg = new PanelGroup();

	JFrame fHome;
	JPanel pBar, pLabel, pNorthBar, pNorthNutri, pAverage;
	JLabel lId, lDate, lDateY, lDateYY, lMenuB, lMenuL, lMenuD, lMenu1, lMenu2, lYellow, lOrange, lGreen, lAverage;
	JButton bMenuB, bFrid, bLogout, bEditB;
	String id = "test";
	String[] nutri = { "탄수화물", "단백질", "채소", "칼슘", "지방", "과일", "기타" };
	JPanel[] pNutri, pYellow, pGreen, pOrange;
	JLabel[] lNutri;
	MenuFrame mToday, mYesterday, mYYesterday;

	LineBorder bb = new LineBorder(Color.gray, 1, false);

	public void setId(String id) { // loginframe에서 id 받아서 this.id에 셋팅
		this.id = id;
	}

	public String getId() { // setId에서 id 설정한 값 return
		return id;
	}

	public HomeFrame(String id) {
//		System.out.println(id); // 아이디 null..
		fHome = new JFrame(); // 메인페이지 프레임
		fHome.setTitle("식단");

		pBar = new JPanel();
		lId = new JLabel();
		bMenuB = new JButton("식단추가");
		bEditB = new JButton("식단수정");
		bFrid = new JButton("냉장고보기");
		bLogout = new JButton("로그아웃");
		pAverage = new RoundedPanel(15, Color.white);
		lAverage = new JLabel("오늘의 필요영양소");
		lYellow = new JLabel("탄수화물");
		pYellow = new JPanel[3];
		lOrange = new JLabel("단백질");
		pOrange = new JPanel[2];
		lGreen = new JLabel("채소");
		pGreen = new JPanel[4];
		for (int i = 0; i < pYellow.length; i++) {
			pYellow[i] = new JPanel();
		}
		for (int i = 0; i < pOrange.length; i++) {
			pOrange[i] = new JPanel();
		}
		for (int i = 0; i < pGreen.length; i++) {
			pGreen[i] = new JPanel();
		}

		pNorthBar = new JPanel();
		lNutri = new JLabel[7];
		pNutri = new JPanel[7];
		pNorthNutri = new JPanel();

		pLabel = new JPanel();
		lMenuB = new JLabel("아침");
		lMenuL = new JLabel("점심");
		lMenuD = new JLabel("저녁");
		lMenu1 = new JLabel("간식");
		lMenu2 = new JLabel("간식");
	}

	public void homeOpen() { // 메인페이지 오픈

		// 날짜 레이블 설정
		String date = sdf.format(now.getTime());
		lDate = new JLabel(date);
		mToday = new MenuFrame(id, date);
		// 어제날짜 레이블
		now.add(Calendar.DATE, -1);
		date = sdf.format(now.getTime());
		lDateY = new JLabel(date);
		mYesterday = new MenuFrame(id, date);
		// 그제날짜 레이블
		now.add(Calendar.DATE, -1);
		date = sdf.format(now.getTime());
		lDateYY = new JLabel(date);
		mYYesterday = new MenuFrame(id, date);
		fHome.setLayout(null);
//		System.out.println(id);

		// 상단 바 패널 설정
		pNorthBar.setBounds(170, 0, 1050, 40);
		pNorthBar.setBackground(pg.back);
		pNorthBar.setLayout(null);
		pNorthNutri.setBounds(500, 5, 500, 30);
		pNorthNutri.setBackground(pg.back);

		for (int i = 0; i < nutri.length; i++) {
			lNutri[i] = new JLabel();
			pNutri[i] = new JPanel();
			lNutri[i].setText(nutri[i]); // 상단 바 영양소레이블 설정
			lNutri[i].setFont(font.fNorthLabel); // 상단 바 영양소레이블 설정
			lNutri[i].setForeground(Color.white);
			pNutri[i].setBackground(pg.cNutri[i]); // 상단 바 영양소패널 색깔 설정

		}

		// 왼쪽 바 패널 세팅
		pBar.setLayout(null);
		pBar.setBackground(pg.back);
		pBar.setBounds(0, 0, 180, 800);
		lId.setText(
				"<html><body><br><br>안녕하세요,<br>" + id + "님!<br>오늘도 건강한 <br> 유아식 식단을<br>도와드릴게요:)<br><br></body></html>");
		lId.setFont(font.f3);
		lId.setForeground(Color.white); // 레이블 색 설정 메소드
		lId.setBounds(40, 0, 140, 200);

		pAverage.setBounds(20, 200, 140, 200);
		pAverage.setBackground(pg.back);
		pAverage.setLayout(null);

		lAverage.setBounds(10, 0, 140, 60);
		lAverage.setFont(font.f16b);
		lYellow.setBounds(10, 25, 140, 60);
		lYellow.setFont(font.f15);
		pYellow[0].setBounds(10, 65, 40, 20);
		pYellow[1].setBounds(50, 65, 40, 20);
		pYellow[2].setBounds(90, 65, 40, 20);

		// 하루필요영양소 패널 세팅
		int n = mToday.getN1();
		if (n > 0 && n < 3) {
			for (int i = 0; i < n; i++) {
				pYellow[i].setBackground(pg.cNutri[0]);
			}
		} else if (n >= 3) {
			for (int i = 0; i < pYellow.length; i++) {
				pYellow[i].setBackground(pg.cNutri[0]);
			}
		}

		lOrange.setBounds(10, 70, 140, 60);
		lOrange.setFont(font.f15);
		pOrange[0].setBounds(10, 110, 60, 20);
		pOrange[1].setBounds(70, 110, 60, 20);
		int n2 = mToday.getN2();
		if (n2 > 0 && n2 < 2) {
			for (int i = 0; i < n2; i++) {
				pOrange[i].setBackground(pg.cNutri[1]);
			}
		} else if (n2 >= 2) {
			for (int i = 0; i < pOrange.length; i++) {
				pOrange[i].setBackground(pg.cNutri[1]);
			}
		}

		lGreen.setBounds(10, 115, 140, 60);
		lGreen.setFont(font.f15);
		pGreen[0].setBounds(10, 155, 30, 20);
		pGreen[1].setBounds(40, 155, 30, 20);
		pGreen[2].setBounds(70, 155, 30, 20);
		pGreen[3].setBounds(100, 155, 30, 20);
		int n3 = mToday.getN3();
		if (n3 > 0 && n3 < 4) {
			for (int i = 0; i < n3; i++) {
				pGreen[i].setBackground(pg.cNutri[2]);
			}
		} else if (n3 >= 4) {
			for (int i = 0; i < pGreen.length; i++) {
				pGreen[i].setBackground(pg.cNutri[2]);
			}
		}

		bMenuB.setBounds(40, 595, 100, 40);
		bMenuB.setFont(font.f15);
		bMenuB.addActionListener(new ActionListener() { // 식단추가 리스너
			public void actionPerformed(ActionEvent arg0) {
				new PlusMenuFrameB(id); // id를 넣어서 다음프레임으로 값 전달
				fHome.setVisible(false);
			}
		});
		bFrid.setBounds(40, 635, 100, 40);
		bFrid.setFont(font.f15);
		bFrid.addActionListener(new ActionListener() { // 냉장고보기 리스너
			public void actionPerformed(ActionEvent arg0) {
				new FridgeFrame(id);
			}
		});
		bLogout.setBounds(40, 675, 100, 40);
		bLogout.setFont(font.f15);
		bLogout.addActionListener(new ActionListener() { // 로그아웃 리스너 - 홈프레임 닫고 로그인창으로
			public void actionPerformed(ActionEvent arg0) {
				fHome.setVisible(false);
				new LoginFrame();
			}
		});

		// 날짜레이블 세팅
		fHome.getContentPane().setBackground(Color.white); // JFrame은 배경색 지정시 getContentPane 메소드 필요
		lDate.setFont(font.f1);
		lDate.setVerticalAlignment(JLabel.CENTER);
		lDate.setHorizontalAlignment(JLabel.CENTER);
		lDateY.setFont(font.f1);
		lDateY.setVerticalAlignment(JLabel.CENTER);
		lDateY.setHorizontalAlignment(JLabel.CENTER);
		lDateYY.setFont(font.f1);
		lDateYY.setVerticalAlignment(JLabel.CENTER);
		lDateYY.setHorizontalAlignment(JLabel.CENTER);

		// 식단패널 세팅
		mToday.pMenu.setBounds(310, 50, 230, 653);
		mYesterday.pMenu.setBounds(590, 50, 230, 653);
		mYYesterday.pMenu.setBounds(870, 50, 230, 653);

		// 라벨 바 패널 세팅
		pLabel.setLayout(null);
		pLabel.setBackground(Color.WHITE);
		pLabel.setBounds(225, 130, 95, 885);
		lMenuB.setFont(font.f1);
		lMenuB.setBounds(30, 60, 90, 50);
		lMenu1.setFont(font.f1);
		lMenu1.setBounds(30, 170, 90, 50);
		lMenuL.setFont(font.f1);
		lMenuL.setBounds(30, 270, 90, 50);
		lMenu2.setFont(font.f1);
		lMenu2.setBounds(30, 380, 90, 50);
		lMenuD.setFont(font.f1);
		lMenuD.setBounds(30, 480, 90, 50);

		// add
		pNorthBar.add(pNorthNutri);
		for (int i = 0; i < nutri.length; i++) {
			pNorthNutri.add(pNutri[i]);
			pNorthNutri.add(lNutri[i]);
		}

		mToday.pDate.add(lDate, "Center");
		mYesterday.pDate.add(lDateY, "Center");
		mYYesterday.pDate.add(lDateYY, "Center");

		pLabel.add(lMenuB);
		pLabel.add(lMenu1);
		pLabel.add(lMenuL);
		pLabel.add(lMenu2);
		pLabel.add(lMenuD);
		pBar.add(lId);
		pBar.add(pAverage);
		pAverage.add(lAverage);
		pAverage.add(lYellow);
		pAverage.add(lOrange);
		pAverage.add(lGreen);
		for (int i = 0; i < pYellow.length; i++) {
			pAverage.add(pYellow[i]);
		}
		for (int i = 0; i < pOrange.length; i++) {
			pAverage.add(pOrange[i]);
		}
		for (int i = 0; i < pGreen.length; i++) {
			pAverage.add(pGreen[i]);
		}
		pBar.add(bFrid);
		pBar.add(bMenuB);
		pBar.add(bLogout);
		fHome.add(mToday.pMenu);
		fHome.add(mYesterday.pMenu);
		fHome.add(mYYesterday.pMenu);
		fHome.add(pBar);
		fHome.add(pNorthBar);
		fHome.add(pLabel);

		// 프레임 사이즈, 위치 설정
		fHome.setSize(1200, 800);
		fHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fHome.setLocationRelativeTo(null); // 모니터 가운데로 위치시켜주는 JFrame메소드
		fHome.setVisible(true);
	}
}
