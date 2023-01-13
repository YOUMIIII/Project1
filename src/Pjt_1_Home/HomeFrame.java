package Pjt_1_Home;

import java.awt.CardLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import Pjt_1_ConnectServer.ConnectTest;
import Pjt_1_ConnectServer.PanelGroup;
import Pjt_1_Fridge.FridgeFrame;
import Pjt_1_Login.LoginFrame;

public class HomeFrame extends WindowAdapter{
	
	ConnectTest con = new ConnectTest();

	Calendar now = Calendar.getInstance();
	String format = "MM월 dd일(E)";
	SimpleDateFormat sdf = new SimpleDateFormat(format);

	MyFont font = new MyFont();

	PanelGroup pg = new PanelGroup();
	
	CardLayout card = new CardLayout();

	JFrame fHome;
	Frame fBaby;
	JPanel pBar, pLabel, pNorthBar, pNorthNutri, pAverage, pBaby, pBaby1, pBaby2, pBaby3;
	JLabel lId, lDate, lDateY, lDateYY, lMenuB, lMenuL, lMenuD, lMenu1, lMenu2, lYellow, lYellow2, lOrange, lOrange2, lGreen, lBlue, lBlue2, lNa, lNa2,
			lAverage, imageLogo, lBName, lBAge, lPlusEx, lName, lBirth, lPhoto, lSex, limageB,lBName2, lBAge2 ,limageB2;
	JButton bMenuB, bFrid, bLogout, bPlusP, bPlusB, bPlus;
	String id = "test";
	String imageDirectory = "";
	String[] nutri = { "탄수화물", "단백질", "채소", "칼슘", "지방", "나트륨" };
	JPanel[] pNutri, pYellow, pGreen, pOrange, pBlue, pNa;
	JLabel[] lNutri;
	MenuFrame mToday, mYesterday, mYYesterday;
	JTextField tName, tBirth, tPlusP;
	Choice cMonth, cDay;
	JRadioButton rbMale, rbFemale;
	ButtonGroup rbGroup;
	FileDialog fileOpen;
	Boolean check;

	LineBorder bb = new LineBorder(Color.gray, 1, false);
	Color geul = new Color(93,93,93);

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
		
		// 아이추가 해보는중
		imageLogo = new JLabel();
		limageB = new JLabel();
		limageB2 = new JLabel();
		pBaby = new RoundedPanel(15, pg.back);
		pBaby1 = new RoundedPanel(15, pg.back);
		pBaby2 = new RoundedPanel(15, Color.white);
		pBaby3 = new RoundedPanel(15, Color.white);
		fBaby = new Frame("아기추가");
		lBName = new JLabel();
		lBAge = new JLabel();
		lBName2 = new JLabel();
		lBAge2 = new JLabel();
		bPlusB = new JButton("아기 추가");
		lPlusEx = new JLabel();
		lName = new JLabel();
		tName = new JTextField();
		lBirth = new JLabel();
		tBirth = new JTextField();
		lPhoto = new JLabel();
		cMonth = new Choice();
		for (int i = 1; i <= 12; i++) {
			cMonth.add(i + "");
		}
		cDay = new Choice();
		lSex = new JLabel();
		rbGroup = new ButtonGroup();
		rbMale = new JRadioButton("남자");
		rbFemale = new JRadioButton("여자");
		rbGroup.add(rbMale);
		rbGroup.add(rbFemale);
		tPlusP = new JTextField();
		bPlusP = new JButton("파일추가");
		fileOpen = new FileDialog(fBaby,"파일찾기", FileDialog.LOAD);
		bPlus = new JButton("추가");
		
		pBar = new JPanel();
		lId = new JLabel();
		bMenuB = new JButton("식단추가");
		bFrid = new JButton("냉장고보기");
		bLogout = new JButton("로그아웃");
		pAverage = new RoundedPanel(15, Color.white);
		lAverage = new JLabel("오늘의 필요영양소");
		lYellow = new JLabel("탄수화물");
		lYellow2 = new JLabel();
		pYellow = new JPanel[10];
		lOrange = new JLabel("단백질");
		lOrange2 = new JLabel();
		pOrange = new JPanel[10];
		lGreen = new JLabel("채소");
		pGreen = new JPanel[4];
		lBlue = new JLabel("지방");
		lBlue2 = new JLabel();
		pBlue = new JPanel[10];
		lNa = new JLabel("나트륨");
		lNa2 = new JLabel();
		pNa = new JPanel[10];

		for (int i = 0; i < pYellow.length; i++) {
			pYellow[i] = new JPanel();
		}
		for (int i = 0; i < pOrange.length; i++) {
			pOrange[i] = new JPanel();
		}
		for (int i = 0; i < pGreen.length; i++) {
			pGreen[i] = new JPanel();
		}
		for (int i = 0; i < pBlue.length; i++) {
			pBlue[i] = new JPanel();
		}
		for (int i = 0; i < pNa.length; i++) {
			pNa[i] = new JPanel();
		}

		pNorthBar = new JPanel();
		lNutri = new JLabel[6];
		pNutri = new JPanel[6];
		pNorthNutri = new JPanel();

		pLabel = new JPanel();
		lMenuB = new JLabel("아침");
		lMenuL = new JLabel("점심");
		lMenuD = new JLabel("저녁");
		lMenu1 = new JLabel("간식");
		lMenu2 = new JLabel("간식");
		
//		String idCheck = "select * from baby where id = '" + id + "'";
//		check = con.idCheck(idCheck);
		
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

		// 왼쪽 바 패널 세팅
		pBar.setLayout(null);
		pBar.setBackground(pg.back);
		pBar.setBounds(0, 0, 180, 800);
		
		//홈에 로고사용시
		pBaby.setLayout(card);
		pBaby.setBounds(20, 60, 140, 180);
		String idCheck = "select * from baby where id = '" + id + "'";
		boolean check = con.idCheck(idCheck);

		if(check == true) {
			card.show(pBaby, "3");
			pBaby3.setLayout(null);
			pBaby3.setBounds(0, 0, 140, 180);
			String[] baby = con.bringBaby(idCheck);
			ImageIcon imagee = new ImageIcon(LoginFrame.class.getResource("/Pjt_1_Image/Image/" + baby[3]));
			Image img3 = imagee.getImage();
			Image updateImg3 = img3.getScaledInstance(110, 110, Image.SCALE_SMOOTH);
			ImageIcon updateIcon3 = new ImageIcon(updateImg3);
			limageB2.setIcon(updateIcon3);
			if(baby[2].equals("male")) {
				lBName2.setText(baby[0]+"(남아)");
			}else {
				lBName2.setText(baby[0]+"(여아)");
			}
			Calendar cal2 = Calendar.getInstance();
			cal2.set(Integer.parseInt(baby[1].substring(0, 4)),(int)(baby[1].charAt(4)),Integer.parseInt(baby[1].substring(5, 7)));
			long dDay2 = cal2.getTimeInMillis(); // 1000분의 1초로 계산
			long now2 = System.currentTimeMillis();
			long result2 = (dDay2 - now2)/1000 / 60 / 60 / 24/30;
			lBAge2.setText(result2+"개월");
			
			limageB2.setBounds(15, 10, 110, 110);
			lBName2.setBounds(38, 125, 90, 30);
			lBName2.setFont(font.f15);
			lBAge2.setBounds(50, 145, 90, 30);
			lBAge2.setFont(font.f15);
			pBaby.add(pBaby3, "3");
		} else if(check == false) {
			card.show(pBaby, "1");
			pBaby1.setLayout(null);
			pBaby1.setBounds(0, 0, 140, 180);
			ImageIcon logo = new ImageIcon(LoginFrame.class.getResource("/Pjt_1_Image/Image/yummmy_c.png"));
			Image img = logo.getImage();
			Image updateImg = img.getScaledInstance(130, 130, Image.SCALE_SMOOTH);
			ImageIcon updateIcon = new ImageIcon(updateImg);
			imageLogo.setIcon(updateIcon);
			imageLogo.setBounds(5, 0, 130, 130);
			bPlusB.setBounds(25, 140, 90, 30);
			bPlusB.setFont(font.f14);
			bPlusB.addActionListener(new ActionListener() { //아이추가 리스너
				public void actionPerformed(ActionEvent e) {
					fBaby.setVisible(true);
				}
			});
			
			lPlusEx.setText("|  아이의 정보를 입력해주세요  |");
			lPlusEx.setFont(font.f17);
			lPlusEx.setBounds(30, 55, 400, 30);
			lName.setText("아이 이름 또는 애칭");
			lName.setFont(font.f16);
			lName.setBounds(45, 110, 400, 30);
			tName.setBounds(40, 135, 200, 35);
			lBirth.setText("생년월일");
			lBirth.setFont(font.f16);
			lBirth.setBounds(45, 180, 400, 30);
			tBirth.setBounds(40, 205, 90, 35);
			cMonth.setBounds(130, 203, 80, 40);
			cDay.setBounds(205, 203, 80, 40);
			cMonth.addItemListener(new ItemListener() { // choice 월 부분에 listener 삽입
				@Override
				public void itemStateChanged(ItemEvent e) {
					try {
						Calendar birth = Calendar.getInstance(); // Calendar클래스 인스턴스
						birth.set(Calendar.YEAR, Integer.parseInt(tBirth.getText())); // tYear에 입력한 년도를 calendar.year에 입력
						birth.set(Calendar.MONTH, Integer.parseInt(cMonth.getSelectedItem())); // choice 선택한 월을
						// calendar.month에 입력
						for (int i = 1; i <= birth.getActualMaximum(Calendar.DATE); i++) { // calendar의 제일 마지막날을 구해서 반복문으로 일
							// choice 작성
							cDay.add(i + "");
						}
					} catch (Exception ee) { // 년도를 입력하지 않고 바로 월이나 일을 선택할 경우
						JOptionPane.showMessageDialog(null, "년도를 먼저 입력해야합니다!", "잠깐만요!", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});
			lSex.setBounds(45, 250, 400, 30);
			lSex.setFont(font.f16);
			lSex.setText("아이의 성별을 선택해주세요");
			rbMale.setBounds(40, 280, 60, 30);
			rbFemale.setBounds(110, 280, 60, 30);
			rbMale.setFont(font.f15);
			rbFemale.setFont(font.f15);
			
			lPhoto.setBounds(45, 320, 400, 30);
			lPhoto.setText("아이의 사진을 추가해주세요");
			lPhoto.setFont(font.f16);
			tPlusP.setBounds(40, 350, 250, 35);
			bPlusP.setBounds(290, 350, 80, 35);
			bPlusP.addActionListener(new ActionListener() { // 사진추가 리스너
				public void actionPerformed(ActionEvent e) {
					fileOpen.setDirectory("/Users/youmi/git/repository/src/Pjt_1_Image/Image");
					fileOpen.setVisible(true);
					imageDirectory = fileOpen.getFile();
					tPlusP.setText(imageDirectory);
				}
			});
			bPlus.setBounds(150, 420, 100, 40);
			bPlus.setFont(font.f15);
			
			fBaby.setLayout(null);
			fBaby.addWindowListener(this);
			fBaby.setBackground(Color.white);
			fBaby.setSize(400, 500);
			fBaby.setLocationRelativeTo(null);
			
			pBaby2.setBounds(0, 0, 140, 180);
			pBaby2.setLayout(null);
			lBName.setBounds(38, 125, 90, 30);
			lBName.setFont(font.f15);
			lBAge.setBounds(50, 145, 90, 30);
			lBAge.setFont(font.f15);
			
			bPlus.addActionListener(new ActionListener() { // 추가버튼 리스너
				public void actionPerformed(ActionEvent e) {
					Calendar cal = Calendar.getInstance();
					cal.set(Integer.parseInt(tBirth.getText()),Integer.parseInt(cMonth.getSelectedItem()),Integer.parseInt(cDay.getSelectedItem()));
//					System.out.println(cal);
					long dDay = cal.getTimeInMillis(); // 1000분의 1초로 계산
					long now = System.currentTimeMillis();
					long result = (now - dDay)/1000 / 60 / 60 / 24/30;
					String name = tName.getText();
					String birth = tBirth.getText() + cMonth.getSelectedItem() + cDay.getSelectedItem();
					String sex = "";
					String sexKor = "";
					if(rbMale.isSelected()) {
						sex = "male";
						sexKor = "(남아)";
					} else if(rbFemale.isSelected()) {
						sex = "female";
						sexKor = "(여아)";
					}
					
					String sql = "insert into baby values('" + id + "','" + name + "','" + birth + "','" + sex + "','" +imageDirectory +"')";
					con.plusMenu(sql);
					ImageIcon image = new ImageIcon(LoginFrame.class.getResource("/Pjt_1_Image/Image/" + imageDirectory));
					Image img2 = image.getImage();
					Image updateImg2 = img2.getScaledInstance(110, 110, Image.SCALE_SMOOTH);
					ImageIcon updateIcon2 = new ImageIcon(updateImg2);
					limageB.setIcon(updateIcon2);
					limageB.setBounds(15, 10, 110, 110);
					lBName.setText(name + sexKor);
					lBAge.setText(result + "개월");
					card.show(pBaby, "2");
				
					fBaby.setVisible(false);
				}
			});
			pBaby.add(pBaby1, "1");
			pBaby.add(pBaby2, "2");
		}
		

		pAverage.setBounds(20, 270, 140, 290);
		pAverage.setBackground(pg.back);
		pAverage.setLayout(null);

		// 하루필요영양소 패널 세팅
		lAverage.setBounds(10, 0, 140, 60);
		lAverage.setFont(font.f16b);
		lAverage.setForeground(geul);
		lYellow.setBounds(10, 25, 140, 60);
		lYellow.setFont(font.f15);
		lYellow.setForeground(geul);
		lYellow2.setBounds(75, 25, 80, 60);
		lYellow2.setFont(font.f10);
		lYellow2.setForeground(geul);
		int n = mToday.getN1();
		String yellow = String.format("%d / 130g", n);
		lYellow2.setText(yellow);
		for (int i = 0; i < pYellow.length; i++) {
			pYellow[i].setBounds(10 + (i * 12), 65, 12, 20);
		}
		if (n / 13 <= 10 && n / 13 > 0) {
			for (int i = 0; i < n / 13; i++) {
				pYellow[i].setBackground(pg.cNutri[0]);
			}
		} else if (n / 13 > 10) {
			for (int i = 0; i < pYellow.length; i++) {
				pYellow[i].setBackground(pg.cNutri[0]);
			}
		}

		lOrange.setBounds(10, 70, 140, 60);
		lOrange.setFont(font.f15);
		lOrange.setForeground(geul);
		int n2 = mToday.getN2();
		lOrange2.setBounds(80, 70, 80, 60);
		lOrange2.setFont(font.f10);
		lOrange2.setForeground(geul);
		String orange = String.format("%d / 25g", n2);
		lOrange2.setText(orange);
		for(int i = 0; i<pOrange.length; i++) {
			pOrange[i].setBounds(10 + (i*12), 110, 12, 20);
		}
		if (n2/3 > 0 && n2/3 <= 10) {
			for (int i = 0; i < n2/3; i++) {
				pOrange[i].setBackground(pg.cNutri[1]);
			}
		} else if (n2/3 > 10) {
			for (int i = 0; i < pOrange.length; i++) {
				pOrange[i].setBackground(pg.cNutri[1]);
			}
		}

		lGreen.setBounds(10, 115, 140, 60);
		lGreen.setFont(font.f15);
		lGreen.setForeground(geul);
		pGreen[0].setBounds(10, 155, 30, 20);
//		pGreen[0].setBackground(pg.cNutri[2]);
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
		
		lBlue.setBounds(10, 160, 140, 60);
		lBlue.setFont(font.f15);
		lBlue.setForeground(geul);
		int n4 = mToday.getN4();
		lBlue2.setBounds(80, 160, 80, 60);
		lBlue2.setFont(font.f10);
		lBlue2.setForeground(geul);
		String blue = String.format("%d / 51g", n4);
		lBlue2.setText(blue);
		for(int i = 0; i<pBlue.length; i++) {
			pBlue[i].setBounds(10 + (i*12), 200, 12, 20);
//			pBlue[i].setBackground(Color.black);
		}
		if(n4/5 > 0 && n4/5 <=10) {
			for(int i = 0; i<n4/5; i++) {
				pBlue[i].setBackground(pg.cNutri[3]);
			}
		}else if(n4/5 > 10) {
			for(int i = 0; i<pBlue.length; i++) {
				pBlue[i].setBackground(pg.cNutri[3]);
			}
		}
		
		lNa.setBounds(10, 205, 140, 60);
		lNa.setFont(font.f15);
		lNa.setForeground(geul);
		int n5 = mToday.getN5();
		lNa2.setBounds(60, 205, 90, 60);
		lNa2.setFont(font.f10);
		lNa2.setForeground(geul);
		String na = String.format("%d / 1000mg", n5);
		lNa2.setText(na);
		for(int i = 0; i<pNa.length; i++) {
			pNa[i].setBounds(10 +(i*12), 245, 12, 20);
		}
		if(n5/100 > 0 && n5/100 <=10) {
			for(int i = 0; i<n5/100; i++) {
				pNa[i].setBackground(pg.cNutri[4]);
			}
		}else if(n5/100 > 10) {
			for(int i = 0; i<pNa.length; i++) {
				pNa[i].setBackground(pg.cNutri[4]);
			}
		}
		

		bMenuB.setBounds(40, 595, 100, 40);
		bMenuB.setFont(font.f15);
		bMenuB.addActionListener(new ActionListener() { // 식단추가 리스너
			public void actionPerformed(ActionEvent e) {
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
		lDate.setForeground(geul);
		lDate.setVerticalAlignment(JLabel.CENTER);
		lDate.setHorizontalAlignment(JLabel.CENTER);
		lDateY.setFont(font.f1);
		lDateY.setVerticalAlignment(JLabel.CENTER);
		lDateY.setHorizontalAlignment(JLabel.CENTER);
		lDateY.setForeground(geul);
		lDateYY.setFont(font.f1);
		lDateYY.setVerticalAlignment(JLabel.CENTER);
		lDateYY.setHorizontalAlignment(JLabel.CENTER);
		lDateYY.setForeground(geul);

		// 식단패널 세팅
		mToday.pMenu.setBounds(310, 50, 230, 653);
		mYesterday.pMenu.setBounds(590, 50, 230, 653);
		mYYesterday.pMenu.setBounds(870, 50, 230, 653);

		// 라벨 바 패널 세팅
		pLabel.setLayout(null);
		pLabel.setBackground(Color.WHITE);
		pLabel.setBounds(225, 90, 95, 885);
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
		
		lDate.setBounds(15, 20, 200, 50);
		lDateY.setBounds(15, 20, 200, 50);
		lDateYY.setBounds(15, 20, 200, 50);
		mToday.pDate.add(lDate);
		mYesterday.pDate.add(lDateY);
		mYYesterday.pDate.add(lDateYY);
		
		pLabel.add(lMenuB);
		pLabel.add(lMenu1);
		pLabel.add(lMenuL);
		pLabel.add(lMenu2);
		pLabel.add(lMenuD);
		
		//
	
		pBaby3.add(limageB2);
		pBaby3.add(lBName2);
		pBaby3.add(lBAge2);
		pBaby1.add(imageLogo);
		pBaby1.add(bPlusB);
		pBaby2.add(limageB);
		
	
		fBaby.add(lPlusEx);
		fBaby.add(lName);
		fBaby.add(tName);
		fBaby.add(lBirth);
		fBaby.add(tBirth);
		fBaby.add(cMonth);
		fBaby.add(cDay);
		fBaby.add(lSex);
		fBaby.add(rbMale);
		fBaby.add(rbFemale);
		fBaby.add(lPhoto);
		fBaby.add(tPlusP);
		fBaby.add(bPlusP);
		fBaby.add(bPlus);
		
		pBaby2.add(lBName);
		pBaby2.add(lBAge);
		pBar.add(pBaby);
		pBar.add(pAverage);
		pAverage.add(lAverage);
		pAverage.add(lYellow);
		pAverage.add(lYellow2);
		pAverage.add(lOrange);
		pAverage.add(lOrange2);
		pAverage.add(lGreen);
		pAverage.add(lBlue);
		pAverage.add(lBlue2);
		pAverage.add(lNa);
		pAverage.add(lNa2);
		for (int i = 0; i < pYellow.length; i++) {
			pAverage.add(pYellow[i]);
		}
		for (int i = 0; i < pOrange.length; i++) {
			pAverage.add(pOrange[i]);
		}
		for (int i = 0; i < pGreen.length; i++) {
			pAverage.add(pGreen[i]);
		}
		for (int i = 0; i < pBlue.length; i++) {
			pAverage.add(pBlue[i]);
		}
		for (int i = 0; i < pNa.length; i++) {
			pAverage.add(pNa[i]);
		}
		
		pBar.add(bFrid);
		pBar.add(bMenuB);
		pBar.add(bLogout);
		fHome.add(mToday.pMenu);
		fHome.add(mYesterday.pMenu);
		fHome.add(mYYesterday.pMenu);
		fHome.add(pBar);
//		fHome.add(pNorthBar);
		fHome.add(pLabel);

		// 프레임 사이즈, 위치 설정
		fHome.setSize(1200, 800);
		fHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fHome.setLocationRelativeTo(null); // 모니터 가운데로 위치시켜주는 JFrame메소드
		fHome.setVisible(true);
	}
	
	public void windowClosing(WindowEvent e) { // 회원가입 창 종료하면 다시 로그인 창 오픈
		fBaby.setVisible(false);
	}
}
