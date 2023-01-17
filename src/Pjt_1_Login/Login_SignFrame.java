package Pjt_1_Login;

import java.awt.CardLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Pjt_1_Home.RoundedButton;

public class Login_SignFrame extends WindowAdapter implements ActionListener {
	Frame fSign;
	JPanel p, pSign, pBaby;
	JLabel lId, lPw, lRePw, lBirth, lEmail, lEx, lEx2, lBabyInfo, lBabyInfo2, lBName, lBSex, lPhoto;
	JTextField tId, tPw, tRePw, tYear, tEmail, tPlusP, tBName;
	JRadioButton rbBoy, rbGirl;
	ButtonGroup rbGroup;
	RoundedButton btnSign, btnCheckId, bNext, bPrevious, bPlusP;
	Choice cMonth, cDay;
	String id, pw, repw, email, birth, month, day, name, sex, photo,imageDirectory;
	int year;
	FileDialog fileOpen;
	MyFont font = new MyFont();
	CardLayout card = new CardLayout();

	Login_SignFrame() {
		fSign = new Frame("회원가입");
		p = new JPanel();
		pSign = new JPanel();
		pBaby = new JPanel();
		lEx = new JLabel();
		lEx2 = new JLabel();
		lId = new JLabel("아이디"); // (영문 소문자, 숫자, 특수문자 혼합가능 6~12자 사용)
		lPw = new JLabel("비밀번호"); // 영문 소문자, 숫자, 특수문자 혼합가능 6~12자 사용
		tId = new JTextField(25);
		tPw = new JPasswordField(25);
		lRePw = new JLabel("비밀번호 재확인");
		tRePw = new JPasswordField(25);
		lEmail = new JLabel("본인 확인 이메일");
		tEmail = new JTextField(50);
		btnCheckId = new RoundedButton("중복확인");
		bNext = new RoundedButton(">>다음단계");

		lBabyInfo = new JLabel();
		lBabyInfo2 = new JLabel();
		lBName = new JLabel("아이 이름 또는 애칭");
		tBName = new JTextField();
		lBSex = new JLabel("성별");
		rbGroup = new ButtonGroup();
		rbBoy = new JRadioButton("남아");
		rbGirl = new JRadioButton("여아");
		rbGroup.add(rbBoy);
		rbGroup.add(rbGirl);
		lBirth = new JLabel("생년월일");
		tYear = new JTextField(4);
		cMonth = new Choice();
		for (int i = 1; i <= 12; i++) {
			cMonth.add(i + "");
		}
		cDay = new Choice();
		lPhoto = new JLabel("아이사진");
		tPlusP = new JTextField();
		bPlusP = new RoundedButton("파일추가");
		fileOpen = new FileDialog(fSign,"파일찾기", FileDialog.LOAD);

		bPrevious = new RoundedButton("<<이전단계");

//		fileOpen = new FileDialog(fBaby,"파일찾기", FileDialog.LOAD);
		btnSign = new RoundedButton("가입");

//		signOpen();
	}

	void signOpen() {
		fSign.setLayout(card);
		fSign.setBackground(Color.white);

		pSign.setBounds(0, 0, 450, 560);
		pSign.setLayout(null);
		pSign.setBackground(Color.white);
		lEx.setText("|  회원가입  |");
		lEx.setBounds(40, 45, 400, 30);
		lEx2.setBounds(43, 85, 400, 20);
		lEx2.setText("얌미 가입을 위한 아래 인적사항을 빠짐없이 입력해주세요.");
		lId.setBounds(43, 125, 65, 35); // Y축 65씩
		tId.setBounds(40, 155, 250, 35); // label, tf y차이 35
		btnCheckId.setBounds(320, 155, 75, 30);
		lPw.setBounds(43, 195, 65, 35);
		tPw.setBounds(40, 225, 350, 35);
		lRePw.setBounds(43, 265, 200, 35);
		tRePw.setBounds(40, 295, 350, 35);
		lEmail.setBounds(43, 335, 200, 35);
		tEmail.setBounds(40, 365, 350, 35);
		bNext.setBounds(180, 440, 90, 35);

		pBaby.setLayout(null);
		pBaby.setBounds(0, 0, 450, 560);
		pBaby.setBackground(Color.white);
		lBabyInfo.setBounds(40, 45, 400, 30);
		lBabyInfo.setText("|  아기정보  |");
		lBabyInfo2.setBounds(43, 85, 400, 20);
		lBabyInfo2.setText("아이의 정보를 정확하게 입력해주세요.");
		lBName.setBounds(43, 125, 400, 35);
		tBName.setBounds(40, 155, 250, 35);
		lBSex.setBounds(43, 195, 400, 35);
		rbBoy.setBounds(43, 225, 90, 35);
		rbGirl.setBounds(130, 225, 90, 35);
		lBirth.setBounds(43, 265, 400, 35);
		tYear.setBounds(40, 295, 70, 35);
		cMonth.setBounds(120, 295, 60, 35);
		cDay.setBounds(190, 295, 70, 35);
		lPhoto.setBounds(43, 335, 400, 35);
		tPlusP.setBounds(40, 365, 250, 35);
		bPlusP.setBounds(320, 365, 75, 30);
		bPrevious.setBounds(130, 440, 90, 35);
		btnSign.setBounds(230, 440, 90, 35);

		lEx.setFont(font.f20);
		lEx2.setFont(font.f15);
		lId.setFont(font.f17);
		lPw.setFont(font.f17);
		lRePw.setFont(font.f17);
		lBirth.setFont(font.f17);
		lEmail.setFont(font.f17);
		bNext.setFont(font.f15);
		btnCheckId.setFont(font.f15);
		lBabyInfo.setFont(font.f20);
		lBabyInfo2.setFont(font.f15);
		lBName.setFont(font.f17);
		lBSex.setFont(font.f17);
		lBirth.setFont(font.f17);
		lPhoto.setFont(font.f17);
		bPlusP.setFont(font.f15);
		bPrevious.setFont(font.f15);
		btnSign.setFont(font.f15);

		btnCheckId.addActionListener(this);
		bNext.addActionListener(this);
		btnSign.addActionListener(this);
		bPrevious.addActionListener(this);

//		tYear.setBounds(50, 385, 70, 35);
//		cMonth.setBounds(130, 388, 60, 30);
//		cDay.setBounds(200, 388, 70, 30);
		fSign.add(pSign, "1");
		fSign.add(pBaby, "2");
		pSign.add(lEx);
		pSign.add(lEx2);
		pSign.add(lId);
		pSign.add(tId);
		pSign.add(btnCheckId);
		pSign.add(lPw);
		pSign.add(tPw);
		pSign.add(lRePw);
		pSign.add(tRePw);
		pSign.add(lEmail);
		pSign.add(tEmail);
		pSign.add(bNext);

		pBaby.add(lBabyInfo);
		pBaby.add(lBabyInfo2);
		pBaby.add(lBName);
		pBaby.add(tBName);
		pBaby.add(lBSex);
		pBaby.add(rbBoy);
		pBaby.add(rbGirl);
		pBaby.add(lBirth);
		pBaby.add(tYear);
		pBaby.add(cMonth);
		pBaby.add(cDay);
		pBaby.add(lPhoto);
		pBaby.add(tPlusP);
		pBaby.add(bPlusP);
		pBaby.add(bPrevious);
		pBaby.add(btnSign);

//		pSign.add(lBirth);
//		pSign.add(tYear);
//		fSign.add(cMonth);
		cMonth.addItemListener(new ItemListener() { // choice 월 부분에 listener 삽입
			@Override
			public void itemStateChanged(ItemEvent e) {
				try {
					Calendar birth = Calendar.getInstance(); // Calendar클래스 인스턴스
					birth.set(Calendar.YEAR, Integer.parseInt(tYear.getText())); // tYear에 입력한 년도를 calendar.year에 입력
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
//		fSign.add(cDay);
//		pSign.add(btnSign);
		fSign.setSize(450, 560);
		fSign.addWindowListener(this);
		fSign.setLocationRelativeTo(null);
		fSign.setVisible(true);
	}

	public void windowClosing(WindowEvent e) { // 회원가입 창 종료하면 다시 로그인 창 오픈
		signClose();

	}

	public void signClose() { // 가입완료 후 종료를 위한 메소드
		new LoginFrame();
		fSign.setVisible(false);
	}

	boolean isEmail(String str) {
		return Pattern.matches("^[a-z0-9A-Z._-]*@[a-z0-9A-Z]*.[a-zA-Z.]*$", str);
	}

	/**
	 *
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("중복확인")) {
			new IdCheckDao(tId.getText());
		} else if (e.getActionCommand().equals("가입")) {
			try {
				id = tId.getText();
				pw = tPw.getText();
				repw = tRePw.getText();
				year = Integer.parseInt(tYear.getText());
				email = tEmail.getText();
				if (Integer.parseInt(cMonth.getSelectedItem()) < 10) {
					month = "0" + cMonth.getSelectedItem();
				} else {
					month = cMonth.getSelectedItem();
				}
				if (Integer.parseInt(cDay.getSelectedItem()) < 10) {
					day = "0" + cDay.getSelectedItem();
				} else {
					day = cDay.getSelectedItem();
				}
				birth = year + "-" + month + "-" + day;
				name = tBName.getText();
				if (rbBoy.isSelected()) {
					sex = "male";
				} else if (rbGirl.isSelected()) {
					sex = "female";
				}
				photo = tPlusP.getText();

				if (id.length() < 6 || id.length() > 12) {
					JOptionPane.showMessageDialog(null, "아이디의 글자수를 한번 더 확인해주세요!", "잠깐만요!", JOptionPane.ERROR_MESSAGE);
				} else if (pw.length() < 6 || pw.length() > 12) {
					JOptionPane.showMessageDialog(null, "비밀번호의 글자수를 한번 더 확인해주세요!", "잠깐만요!", JOptionPane.ERROR_MESSAGE);
				} else if (!(pw.equals(repw))) {
					JOptionPane.showMessageDialog(null, "입력하신 비밀번호가 일치하지 않습니다!", "잠깐만요!", JOptionPane.ERROR_MESSAGE);
				} else if (!(isEmail(email))) {
					JOptionPane.showMessageDialog(null, "입력하신 이메일의 형식이 올바르지 않습니다!", "잠깐만요!", JOptionPane.ERROR_MESSAGE);
				} else if (year < 1000 || tYear.getText().equals(null)) {
					JOptionPane.showMessageDialog(null, "년도를 4자리로 입력해주세요!", "잠깐만요!", JOptionPane.ERROR_MESSAGE);
				} else {
					String sql = "insert into member values ('" + id + "','" + pw + "','" + birth + "','" + email
							+ "','" + name + "','" + sex + "','" + photo + "')";
					new MemDao(sql);
//					new LoginFrame();
					fSign.setVisible(false);
				}
			} catch (NumberFormatException ee) { // 작성이 안 된 부분이 있으면 경고창
//				System.out.println("아직 작성 덜됨");
				JOptionPane.showMessageDialog(null, "작성을 완료 후 가입하기 버튼을 클릭해주세요!", "잠깐만요!", JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource().equals(bNext)) {
			card.show(fSign, "2");
		} else if (e.getSource().equals(bPrevious)) {
			card.show(fSign, "1");
		}else if (e.getSource().equals(bPlusP)) {
				fileOpen.setDirectory("/Users/youmi/git/repository/src/Pjt_1_Image/Image");
				fileOpen.setVisible(true);
				imageDirectory = fileOpen.getFile();
				tPlusP.setText(imageDirectory);
			
		}
	}
}
