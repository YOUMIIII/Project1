package Pjt_1_Home;

import java.awt.CardLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Pjt_1_ConnectServer.ConnectTest;
import Pjt_1_ConnectServer.PanelGroup;
import Pjt_1_Fridge.FridgeFrame;
import Pjt_1_Login.LoginDao;
import Pjt_1_Login.LoginFrame;
import Pjt_1_Login.LoginVo;

public class HomeFrame extends WindowAdapter implements ActionListener {

	ConnectTest con = new ConnectTest();

	Calendar now = Calendar.getInstance();
	String format = "MM월 dd일(E)";
	SimpleDateFormat sdf = new SimpleDateFormat(format);

	MyFont font = new MyFont();

	PanelGroup pg = new PanelGroup();

	CardLayout card = new CardLayout();

	JFrame fHome;
	Frame fBaby, fMypage, fAskPass;
	JPanel pBar, pLabel, pAverage, pBaby;
	JLabel lDate, lDateY, lDateYY, lMenuB, lMenuL, lMenuD, lMenu1, lMenu2, lYellow, lYellow2, lOrange, lOrange2,
			lGreen, lBlue, lBlue2, lNa, lNa2, lAverage, imageLogo, lBName, lBAge, lMypageEx, lMypageEx2, lName, lBirth,
			lPhoto, lSex, lpwCheck, limageB, lpw, lemail;
	JButton bMenuB, bFrid, bLogout, bMypage;
	RoundedButton bEdit, bCheck, bPlusP;
	String id, pw, email, name, photo, birth, sex, imageDirectory;
	JPanel[] pYellow, pGreen, pOrange, pBlue, pNa;
	MenuFrame mToday, mYesterday, mYYesterday;
	JTextField tName, tBirth, tPlusP, tpwCheck, tpw, temail;
	Choice cMonth, cDay;
	FileDialog fileOpen;

	Color geul = new Color(93, 93, 93);

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

		// 아이추가
		imageLogo = new JLabel();
		limageB = new JLabel();
		pBaby = new RoundedPanel(15, Color.white);
		lBName = new JLabel();
		lBAge = new JLabel();
		

		// 메인전체
		pBar = new JPanel();
		bMenuB = new JButton("식단추가");
		bFrid = new JButton("냉장고보기");
		bLogout = new JButton("로그아웃");
		bMypage = new JButton("정보수정");
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

		pLabel = new JPanel();
		lMenuB = new JLabel("아침");
		lMenuL = new JLabel("점심");
		lMenuD = new JLabel("저녁");
		lMenu1 = new JLabel("간식");
		lMenu2 = new JLabel("간식");

		// 마이페이지 관련
		fMypage = new Frame("정보수정");
		fAskPass = new Frame("비밀번호 확인");
		lpwCheck = new JLabel();
		tpwCheck = new JPasswordField();
		bCheck = new RoundedButton("확인");

		lMypageEx = new JLabel();
		lMypageEx2 = new JLabel();
		lName = new JLabel("아기이름(애칭)");
		tName = new JTextField();
		lpw = new JLabel("비밀번호");
		tpw = new JPasswordField();
		lemail = new JLabel("이메일");
		temail = new JTextField();
		lPhoto = new JLabel("아기사진");
		tPlusP = new JTextField();
		bPlusP = new RoundedButton("파일추가");
		fileOpen = new FileDialog(fMypage, "파일찾기", FileDialog.LOAD);
		bEdit = new RoundedButton("수정");

		LoginDao dao = new LoginDao(id);
		ArrayList<LoginVo> listMain = dao.listMain();
		for (int i = 0; i < listMain.size(); i++) {
			LoginVo data = (LoginVo) listMain.get(i);
			pw = data.getMempwd();
			email = data.getMememail();
			name = data.getMemname();
			photo = data.getMemphoto();
			birth = data.getMembirth();
			sex = data.getMemSex();
		}

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
		pBar.setBounds(0, 0, 200, 800);

		// 아기정보패널
		pBaby.setLayout(null);
		pBaby.setBounds(30, 50, 140, 180);

		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(birth.substring(0, 4)), Integer.parseInt(birth.substring(5, 7)),
				Integer.parseInt(birth.substring(8, 10)));
		long dDay = cal.getTimeInMillis(); // 1000분의 1초로 계산
		long now = System.currentTimeMillis();
		long result = (now - dDay) / 1000 / 60 / 60 / 24 / 30;
		String sexKor = "";
		if (sex.equals("male")) {
			sexKor = "(남아)";
		} else if (sex.equals("female")) {
			sexKor = "(여아)";
		}
		String imageP;
		if (photo.length() > 0) {
			imageP = photo;
		} else {
			imageP = "yummmy_c.png";
		}
		ImageIcon image = new ImageIcon(LoginFrame.class.getResource("/Pjt_1_Image/Image/" + imageP));
		Image img = image.getImage();
		Image updateImg = img.getScaledInstance(110, 110, Image.SCALE_SMOOTH);
		ImageIcon updateIcon = new ImageIcon(updateImg);
		limageB.setIcon(updateIcon);
		limageB.setBounds(15, 10, 110, 110);
		lBName.setText(name + sexKor);
		lBName.setHorizontalAlignment(JLabel.CENTER);
		lBAge.setText(result + "개월");
		lBName.setBounds(28, 125, 90, 30);
		lBName.setFont(font.f15);
		lBAge.setBounds(50, 145, 90, 30);
		lBAge.setFont(font.f15);

		// 하루필요영양소 패널 세팅

		pAverage.setBounds(30, 250, 140, 290);
		pAverage.setBackground(pg.back);
		pAverage.setLayout(null);
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
		int orangenum;
		if(result>=12&&result <=24) {
			orangenum = 20;
		} else if(result >24 && result <=60) {
			orangenum = 25;
		} else if(result > 60 && result <= 96) {
			orangenum = 35;
		} else if(result > 96 && result <= 132) {
			if(sexKor.equals("(남아)")) {
				orangenum = 50;
			} else {
				orangenum = 45;
			}
		} else if(result>132 && result <= 168) {
			if(sexKor.equals("(남아)")) {
				orangenum = 60;
			} else {
				orangenum = 55;
			}
		}else{
			if(sexKor.equals("(남아)")) {
				orangenum = 65;
			} else {
				orangenum = 55;
			}
		}
		String orange = String.format("%d / %dg", n2, orangenum);
		lOrange2.setText(orange);
		for (int i = 0; i < pOrange.length; i++) {
			pOrange[i].setBounds(10 + (i * 12), 110, 12, 20);
		}
		if (n2 / (orangenum/10) > 0 && n2 / (orangenum/10) <= 10) {
			for (int i = 0; i < n2 / 3; i++) {
				pOrange[i].setBackground(pg.cNutri[1]);
			}
		} else if (n2 / (orangenum/10) > 10) {
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
		for (int i = 0; i < pBlue.length; i++) {
			pBlue[i].setBounds(10 + (i * 12), 200, 12, 20);
//			pBlue[i].setBackground(Color.black);
		}
		if (n4 / 5 > 0 && n4 / 5 <= 10) {
			for (int i = 0; i < n4 / 5; i++) {
				pBlue[i].setBackground(pg.cNutri[3]);
			}
		} else if (n4 / 5 > 10) {
			for (int i = 0; i < pBlue.length; i++) {
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
		int nanum;
		if(result>=12&&result <=24) {
			nanum = 810;
		} else if(result >24 && result <=60) {
			nanum = 1000;
		} else if(result > 60 && result <= 96) {
			nanum = 1200;
		}else{
			nanum = 1500;
		}
		String na = String.format("%d / %dmg", n5,nanum);
		lNa2.setText(na);
		for (int i = 0; i < pNa.length; i++) {
			pNa[i].setBounds(10 + (i * 12), 245, 12, 20);
		}
		if (n5 / 100 > 0 && n5 / 100 <= 10) {
			for (int i = 0; i < n5 / 100; i++) {
				pNa[i].setBackground(pg.cNutri[4]);
			}
		} else if (n5 / 100 > 10) {
			for (int i = 0; i < pNa.length; i++) {
				pNa[i].setBackground(pg.cNutri[4]);
			}
		}

		bMenuB.setBounds(9, 555, 90, 35);
		bFrid.setBounds(101, 555, 90, 35);
		bMypage.setBounds(9, 590, 90, 35);
		bLogout.setBounds(101, 590, 90, 35);

		// 마이페이지-비번확인 세팅
		fAskPass.setLayout(null);
		lpwCheck.setText("비밀번호를 입력해주세요");
		lpwCheck.setBounds(35, 40, 200, 30);
		lpwCheck.setFont(font.f16);
		tpwCheck.setBounds(30, 75, 200, 35);
		bCheck.setBounds(90, 120, 90, 30);
		bCheck.setFont(font.f15);
		bCheck.addActionListener(this);

		// 마이페이지 프레임 세팅
		fMypage.setLayout(null);
		fMypage.setBackground(Color.white);
		lMypageEx.setBounds(40, 45, 400, 30);
		lMypageEx.setFont(font.f20);
		lMypageEx.setText("|  정보수정  |");
		lMypageEx2.setBounds(43, 85, 400, 20);
		lMypageEx2.setFont(font.f15);
		lMypageEx2.setText("정보를 수정하신 후 수정버튼을 클릭해주세요.");
		lpw.setBounds(43, 125, 65, 35);
		lpw.setFont(font.f17);
		tpw.setBounds(40, 155, 350, 35);
		tpw.setText(pw);
		lemail.setBounds(43, 195, 65, 35);
		lemail.setFont(font.f17);
		temail.setBounds(40, 225, 350, 35);
		temail.setText(email);
		lName.setBounds(43, 265, 200, 35);
		lName.setFont(font.f17);
		tName.setBounds(40, 295, 350, 35);
		tName.setText(name);
		lPhoto.setBounds(43, 335, 200, 35);
		lPhoto.setFont(font.f17);
		tPlusP.setBounds(40, 365, 250, 35);
		tPlusP.setText(photo);
		bPlusP.setBounds(320, 365, 75, 30);
		bPlusP.setFont(font.f15);
		bPlusP.addActionListener(this);
		bEdit.setBounds(180, 440, 90, 35);
		bEdit.setFont(font.f15);
		bEdit.addActionListener(this);

		fMypage.setSize(450, 520);
		fMypage.setLocationRelativeTo(null);
		fMypage.addWindowListener(this);
		fAskPass.add(lpwCheck);
		fAskPass.add(tpwCheck);
		fAskPass.add(bCheck);
		fMypage.add(lMypageEx);
		fMypage.add(lMypageEx2);
		fMypage.add(lpw);
		fMypage.add(tpw);
		fMypage.add(lemail);
		fMypage.add(temail);
		fMypage.add(lName);
		fMypage.add(tName);
		fMypage.add(lPhoto);
		fMypage.add(tPlusP);
		fMypage.add(bPlusP);
		fMypage.add(bEdit);
		fAskPass.setSize(260, 180);
		fAskPass.setLocationRelativeTo(null);
		fAskPass.addWindowListener(this);

//		bMenuB.setBounds(45, 555, 90, 35);
//		bFrid.setBounds(45, 595, 90, 35);
//		bMypage.setBounds(45, 635, 90, 35);
//		bLogout.setBounds(45, 675, 90, 35);

		bMenuB.setFont(font.f15);
		bMenuB.addActionListener(new ActionListener() { // 식단추가 리스너
			public void actionPerformed(ActionEvent e) {
				new PlusMenuFrameB(id); // id를 넣어서 다음프레임으로 값 전달
				fHome.setVisible(false);
			}
		});
		bFrid.setFont(font.f15);
		bFrid.addActionListener(new ActionListener() { // 냉장고보기 리스너
			public void actionPerformed(ActionEvent arg0) {
				new FridgeFrame(id);
			}
		});
		bMypage.setFont(font.f15);
		bMypage.addActionListener(new ActionListener() { // 마이페이지 리스너
			public void actionPerformed(ActionEvent arg0) {
				fAskPass.setVisible(true);
			}
		});
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
		mToday.pMenu.setBounds(310, 10, 230, 653);
		mYesterday.pMenu.setBounds(590, 10, 230, 653);
		mYYesterday.pMenu.setBounds(870, 10, 230, 653);

		// 라벨 바 패널 세팅
		pLabel.setLayout(null);
		pLabel.setBackground(Color.WHITE);
		pLabel.setBounds(225, 60, 95, 885);
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

		pBaby.add(limageB);
		pBaby.add(lBName);
		pBaby.add(lBAge);
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
		pBar.add(bMypage);
		pBar.add(bLogout);
		fHome.add(mToday.pMenu);
		fHome.add(mYesterday.pMenu);
		fHome.add(mYYesterday.pMenu);
		fHome.add(pBar);
		fHome.add(pLabel);

		// 프레임 사이즈, 위치 설정
		fHome.setSize(1200, 730);
		fHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fHome.setLocationRelativeTo(null); // 모니터 가운데로 위치시켜주는 JFrame메소드
		fHome.setVisible(true);
	}

	public void windowClosing(WindowEvent e) { // 회원가입 창 종료하면 다시 로그인 창 오픈
		if (e.getSource().equals(fBaby)) {
			fBaby.setVisible(false);
		} else if (e.getSource().equals(fMypage)) {
			homeOpen();
			fMypage.setVisible(false);
		} else if (e.getSource().equals(fAskPass)) {
			fAskPass.setVisible(false);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(bCheck)) {
			if (pw.equals(tpwCheck.getText())) {
				tpwCheck.setText("");
				fMypage.setVisible(true);
				fAskPass.setVisible(false);
				fHome.setVisible(false);
			} else {
				JOptionPane.showMessageDialog(null, "비밀번호를 한번 더 확인해주세요!", "비밀번호확인오류!", JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource().equals(bPlusP)) {
			fileOpen.setDirectory("/Users/youmi/git/repository/src/Pjt_1_Image/Image");
			fileOpen.setVisible(true);
			imageDirectory = fileOpen.getFile();
			tPlusP.setText(imageDirectory);
		}else if(e.getSource().equals(bEdit)) {
			String newpw = tpw.getText();
			String newemail = temail.getText();
			String newname = tName.getText();
			String newphoto = tPlusP.getText();
			String sql;
			if(newpw.length()>0 && !(newpw.equals(pw))) {
				pw = newpw;
				sql = "update member set pw = '" + pw + "' where id = '" + id +"'";
				con.insertSql(sql);
			}
			if(newemail.length()>0 && !(newemail.equals(email))) {
				email = newemail;
				sql = "update member set email = '" + email + "' where id = '" + id +"'";
				con.insertSql(sql);
			}
			if(newname.length()>0 && !(newname.equals(name))) {
				name = newname;
				sql = "update member set baby_name = '" + name + "' where id = '" + id +"'";
				con.insertSql(sql);
			}
			if(newphoto.length()>0 && !(newphoto.equals(photo))) {
				photo = newphoto;
				sql = "update member set baby_photo = '" + photo + "' where id = '" + id +"'";
				con.insertSql(sql);
			}
			JOptionPane.showMessageDialog(null, "정보가 수정되었습니다!", "정보수정", JOptionPane.PLAIN_MESSAGE);
			homeOpen();
			fMypage.setVisible(false);
		}
	}
}
