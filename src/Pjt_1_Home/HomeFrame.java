package Pjt_1_Home;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class HomeFrame {
	Date today = new Date();
	MyFont font = new MyFont();
	SimpleDateFormat dateFormat1 = new SimpleDateFormat("YY년 MM월 dd일(E)", Locale.KOREA);
	JFrame fHome;
	JPanel pDate, pMenu, pMenuB, pMenuL, pMenuD, pMenuS1, pMenuS2, pBar, pLabel;
	JLabel lId, lDate, lMenuB;
	TextField tfh, tfw;
	RoundedButton bMenuB, bMenuL, bMenuD, bMenuS1, bMenuS2, bFrid;
	String id;
	LineBorder bb = new LineBorder(Color.gray,1,false);

	public HomeFrame() {
		fHome = new JFrame(); // 메인페이지 프레임
		fHome.setTitle("식단");
		pDate = new JPanel();
		lDate = new JLabel(dateFormat1.format(today));
		pMenu = new JPanel();
		pMenuB = new JPanel();
		pMenuL = new JPanel();
		pMenuD = new JPanel();
		lMenuB = new JLabel("<html><body>아침<br><br><br><br><br><br><br><br>간식<br><br>점심<br><br><br><br><br><br><br><br>간식<br><br>저녁");
		bMenuB = new RoundedButton("식단추가");
		bMenuL = new RoundedButton("식단추가");
		bMenuD = new RoundedButton("식단추가");
		bMenuS1 = new RoundedButton("식단추가");
		bMenuS2 = new RoundedButton("식단추가");
		pBar = new JPanel();
		lId = new JLabel();
		bFrid = new RoundedButton("냉장고보기");
		pLabel = new JPanel();
		pMenuS1 = new JPanel();
		pMenuS2 = new JPanel();
	}

	public void getId(String id) {
		this.id = id;
	}

	public void homeOpen() { // 메인페이지 오픈
		fHome.setLayout(null);

		// 날짜 패널설정
		fHome.add(pDate);
		fHome.getContentPane().setBackground(Color.white); // JFrame은 배경색 지정시 getContentPane 메소드 필요
		pDate.setLayout(new BorderLayout());
		pDate.setBounds(270, 0, 330, 75);
		pDate.setBackground(Color.white);
		lDate.setFont(font.f1);
		lDate.setVerticalAlignment(JLabel.CENTER);
		lDate.setHorizontalAlignment(JLabel.CENTER);
		pDate.add(lDate, "Center");

		// 식단 패널설정
		fHome.add(pMenu);
		pMenu.setBounds(270, 80, 330, 885);
		pMenu.setBackground(Color.WHITE);
		pMenu.setLayout(null);
		// 식단 패널_아침식단
		pMenuB.setBounds(0, 0, 330, 250);
		pMenuB.setBackground(Color.white);
		pMenuB.setBorder(bb);
		bMenuB.setFont(font.f2);
		bMenuB.addActionListener(new ActionListener() { // 일단 아침,간식,점심,저녁 따로 프레임 만들어서 연결
			public void actionPerformed(ActionEvent arg0) {
				new PlusMenuFrameB();
			}
		});
		pMenu.add(pMenuB);
		pMenuB.add(bMenuB);
		// 식단 패널_간식1식단
		pMenuS1.setBounds(0, 250, 330, 65);
		pMenuS1.setBackground(Color.white);
		pMenuS1.setBorder(bb);
		bMenuS1.setFont(font.f2);
		bMenuS1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new PlusMenuFrameB();
			}
		});
		pMenuS1.add(bMenuS1);
		pMenu.add(pMenuS1);
		// 식단 패널_점심식단
		pMenuL.setBounds(0, 315, 330, 250);
		pMenuL.setBackground(Color.white);
		pMenuL.setBorder(bb);
		bMenuL.setFont(font.f2);
		bMenuL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new PlusMenuFrameB();
			}
		});
		pMenuL.add(bMenuL);
		pMenu.add(pMenuL);
		// 식단 패널_간식2식단
		pMenuS2.setBounds(0, 565, 330, 65);
		pMenuS2.setBackground(Color.white);
		pMenuS2.setBorder(bb);
		bMenuS2.setFont(font.f2);
		bMenuS2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new PlusMenuFrameB();
			}
		});
		pMenuS2.add(bMenuS2);
		pMenu.add(pMenuS2);
		// 식단 패널_저녁식단
		pMenuD.setBounds(0, 630, 330, 250);
		pMenuD.setBackground(Color.white);
		pMenuD.setBorder(bb);
		bMenuD.setFont(font.f2);
		bMenuD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new PlusMenuFrameB();
			}
		});
		pMenuD.add(bMenuD);
		pMenu.add(pMenuD);
		
		// 왼쪽 바 패널 설정
		fHome.add(pBar);
		pBar.setBackground(Color.gray);
		pBar.setBounds(0, 0, 180, 1024);
		lId.setText("<html><body><br>안녕하세요,<br>" + id + "님!<br>오늘도 좋은하루되세요:)<br><br></body></html>");
		lId.setFont(font.f3);
		pBar.add(lId);
		pBar.add(bFrid);
		bFrid.setFont(font.f2);
		
		//라벨 바 패널 설정
		pLabel.setBackground(Color.white);
		pLabel.setBounds(175,80, 95, 885);
		fHome.add(pLabel);
		lMenuB.setFont(font.f1);
		lMenuB.setLocation(200, 150);
		pLabel.add(lMenuB);

		
		
		
		
	
		
		
		JPanel pNuB[] = new JPanel[5];
		for (int i = 0; i < pNuB.length; i++) {
			pNuB[i] = new JPanel();
		}
		JPanel pNuL[] = new JPanel[5];
		for (int i = 0; i < pNuL.length; i++) {
			pNuL[i] = new JPanel();
		}
		JPanel pNuD[] = new JPanel[5];
		for (int i = 0; i < pNuD.length; i++) {
			pNuD[i] = new JPanel();
		}
		for (int i = 0; i < pNuB.length; i++) {
			pMenuB.add(pNuB[i]);
		}
		for (int i = 0; i < pNuL.length; i++) {
			pMenuL.add(pNuL[i]);
		}
		for (int i = 0; i < pNuD.length; i++) {
			pMenuD.add(pNuD[i]);
		}
		
		// 프레임 사이즈, 위치 설정
		fHome.setSize(1440, 1024);
		fHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fHome.setVisible(true);
		fHome.setLocationRelativeTo(null); // 모니터 가운데로 위치시켜주는 JFrame메소드
	}

}
