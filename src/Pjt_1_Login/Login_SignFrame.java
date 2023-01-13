package Pjt_1_Login;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Pjt_1_Home.RoundedButton;

public class Login_SignFrame extends WindowAdapter implements ActionListener {
	Frame fSign;
	JLabel lId, lPw, lRePw, lBirth, lEmail, lEx;
	JTextField tId, tPw, tRePw, tYear, tEmail;
	RoundedButton btnSign, btnCheckId;
	Choice cMonth, cDay;
	String id, pw, repw, email, birth;
	int year, month, day;
	Dialog error = new Dialog(fSign, "회원가입 오류", true);
	JLabel msg = new JLabel("");
	MyFont font = new MyFont();

	Login_SignFrame() {
		fSign = new Frame("회원가입");
		lEx = new JLabel();
		lId = new JLabel("아이디"); // (영문 소문자, 숫자, 특수문자 혼합가능 6~12자 사용)
		lPw = new JLabel("비밀번호"); // 영문 소문자, 숫자, 특수문자 혼합가능 6~12자 사용
		tId = new JTextField(25);
		tPw = new JPasswordField(25);
		lRePw = new JLabel("비밀번호 재확인");
		tRePw = new JPasswordField(25);
		lBirth = new JLabel("생년월일");
		tYear = new JTextField(4);
		cMonth = new Choice();
		for (int i = 1; i <= 12; i++) {
			cMonth.add(i + "");
		}
		cDay = new Choice();
		lEmail = new JLabel("본인 확인 이메일");
		tEmail = new JTextField(50);
		btnSign = new RoundedButton("가입하기");
		btnCheckId = new RoundedButton("중복확인");

		signOpen();
	}

	void signOpen() {
		fSign.setLayout(null);
		fSign.setBackground(Color.white);
		lEx.setText("|  회원가입  |");
		lEx.setFont(font.f20);
		lEx.setBounds(50, 65, 400, 30);
		lId.setBounds(50, 120, 65, 35); // Y축 65씩
		tId.setBounds(50, 155, 250, 35); // label, tf y차이 35
		btnCheckId.setBounds(320, 157, 75, 30);
		lPw.setBounds(50, 195, 65, 35);
		tPw.setBounds(50, 230, 350, 35);
		lRePw.setBounds(50, 270, 200, 35);
		tRePw.setBounds(50, 305, 350, 35);
		lBirth.setBounds(50, 345, 200, 35);
		tYear.setBounds(50, 385, 70, 35);
		cMonth.setBounds(130, 388, 60, 30);
		cDay.setBounds(200, 388, 70, 30);
		lEmail.setBounds(50, 425, 200, 35);
		tEmail.setBounds(50, 460, 350, 35);
		btnSign.setBounds(180, 530, 90, 40);

		lId.setFont(font.f17);
		lPw.setFont(font.f17);
		lRePw.setFont(font.f17);
		lBirth.setFont(font.f17);
		lEmail.setFont(font.f17);
		btnSign.setFont(font.f15);
		btnCheckId.setFont(font.f15);

		fSign.add(lEx);
		fSign.add(lId);
		fSign.add(tId);
		fSign.add(btnCheckId);
		btnCheckId.addActionListener(this);
		fSign.add(lPw);
		fSign.add(tPw);
		fSign.add(lRePw);
		fSign.add(tRePw);
		fSign.add(lBirth);
		fSign.add(tYear);
		fSign.add(cMonth);
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
		fSign.add(cDay);
		fSign.add(lEmail);
		fSign.add(tEmail);
		fSign.add(btnSign);
		btnSign.addActionListener(this);
		fSign.setSize(450, 610);
		fSign.addWindowListener(this);
		fSign.setLocationRelativeTo(null);
		fSign.setVisible(true);
	}

	public void windowClosing(WindowEvent e) { // 회원가입 창 종료하면 다시 로그인 창 오픈
		fSign.setVisible(false);
		new LoginFrame();

	}

	public void signClose() {
		fSign.setVisible(false);
		new LoginFrame();
	}

	public boolean isEmail(String str) {
		return Pattern.matches("^[a-z0-9A-Z._-]*@[a-z0-9A-Z]*.[a-zA-Z.]*$", str);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("중복확인")) {
			new IdCheckDao(tId.getText());
		}else if(e.getActionCommand().equals("가입하기")) {
			try {
				id = tId.getText();
				pw = tPw.getText();
				repw = tRePw.getText();
				year = Integer.parseInt(tYear.getText());
				month = Integer.parseInt(cMonth.getSelectedItem());
				day = Integer.parseInt(cDay.getSelectedItem());
				birth = year + "-" + month + "-" + day;
				email = tEmail.getText();

				if (id.length() < 6 || id.length() > 12) {
					JOptionPane.showMessageDialog(null, "아이디의 글자수를 한번 더 확인해주세요!", "잠깐만요!",
							JOptionPane.ERROR_MESSAGE);
				} else if (pw.length() < 6 || pw.length() > 12) {
					JOptionPane.showMessageDialog(null, "비밀번호의 글자수를 한번 더 확인해주세요!", "잠깐만요!",
							JOptionPane.ERROR_MESSAGE);
				} else if (!(pw.equals(repw))) {
					JOptionPane.showMessageDialog(null, "입력하신 비밀번호가 일치하지 않습니다!", "잠깐만요!",
							JOptionPane.ERROR_MESSAGE);
				} else if (!(isEmail(email))) {
					JOptionPane.showMessageDialog(null, "입력하신 이메일의 형식이 올바르지 않습니다!", "잠깐만요!",
							JOptionPane.ERROR_MESSAGE);
				} else if (year < 1000 || tYear.getText().equals(null)) {
					JOptionPane.showMessageDialog(null, "입력하신 생일 형식이 올바르지 않습니다!", "잠깐만요!",
							JOptionPane.ERROR_MESSAGE);
				} else {
					String sql = "insert into member values ('" + id + "','" + pw + "','" + birth + "','" + email
							+ "')";
					new MemDao(sql);
					fSign.setVisible(false);
				}
			} catch (NumberFormatException ee) { // 작성이 안 된 부분이 있으면 경고창
//				System.out.println("아직 작성 덜됨");
				JOptionPane.showMessageDialog(null, "작성을 완료 후 가입하기 버튼을 클릭해주세요!", "잠깐만요!",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
