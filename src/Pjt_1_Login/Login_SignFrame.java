package Pjt_1_Login;

import java.awt.Choice;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login_SignFrame extends WindowAdapter {
	Toolkit tk = Toolkit.getDefaultToolkit();
	Dimension screenSize = tk.getScreenSize();
	Dimension d;
	Frame fSign;
	JLabel lId, lPw, lRePw, lBirth, lEmail;
	JTextField tId, tPw, tRePw, tYear, tEmail;
	JButton btnSign, ok;
	Choice cMonth, cDay;
	String id, pw, repw, email, birth;
	int year, month, day;
	Dialog error = new Dialog(fSign, "회원가입 오류", true);
	JLabel msg = new JLabel("");
//	boolean off;

	Login_SignFrame() {
		fSign = new Frame("회원가입");
		fSign.addWindowListener(this);
		fSign.setSize(495, 510);
		d = fSign.getSize();
		fSign.setLocationRelativeTo(null);;

		lId = new JLabel("아이디(영문 소문자, 숫자, 특수문자 혼합가능 6~12자 사용)");
		lPw = new JLabel("비밀번호(영문 소문자, 숫자, 특수문자 혼합가능 6~12자 사용)");
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
		btnSign = new JButton("가입하기");
		ok = new JButton("확인");

	}

	void signOpen() {
		fSign.setLayout(new FlowLayout());
		fSign.add(lId);
		fSign.add(tId);
		fSign.add(lPw);
		fSign.add(tPw);
//		tPw.setEchoChar('*');
		fSign.add(lRePw);
//		tRePw.setEchoChar('*');
		fSign.add(tRePw);
		fSign.add(lBirth);
		fSign.add(tYear);
		fSign.add(cMonth);
		cMonth.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				try {
					Calendar birth = Calendar.getInstance();
					birth.set(Calendar.MONTH, (int) (cMonth.getSelectedItem().charAt(0)) - 49);
					birth.set(Calendar.YEAR, Integer.parseInt(tYear.getText()));
					for (int i = 1; i <= birth.getActualMaximum(Calendar.DATE); i++) {
						cDay.add(i + "");
					}
				} catch (Exception ee) {
					error.setSize(290, 110);
					d = error.getSize();
					error.setLocation((screenSize.width - (int) (d.getWidth())) / 2,
							(screenSize.height - (int) (d.getHeight())) / 2);
					error.setLayout(new FlowLayout());
					msg.setText("연도를 먼저 입력해주세요!");
					error.add(msg);
					error.add(ok);
					ok.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							error.dispose();
						}
					});
					error.setVisible(true);
				}
			}
		});
		fSign.add(cDay);
		fSign.add(lEmail);
		fSign.add(tEmail);
		fSign.add(btnSign);
		btnSign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				id = tId.getText();
				pw = tPw.getText();
				repw = tRePw.getText();
				year = Integer.parseInt(tYear.getText());
				month = Integer.parseInt(cMonth.getSelectedItem());
				day = Integer.parseInt(cDay.getSelectedItem());
				birth = year + "-" + month + "-" + day;
				email = tEmail.getText();
				error.setSize(290, 110);
				d = error.getSize();
				error.setLocation((screenSize.width - (int) (d.getWidth())) / 2,
						(screenSize.height - (int) (d.getHeight())) / 2);
				error.setLayout(new FlowLayout());

				if (id.length() < 6 || id.length() > 12) {
					msg.setText("아이디의 글자수를 한번 더 확인해 주세요!");
					error.add(msg);
					error.add(ok);
					ok.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							error.dispose();
						}
					});
					error.setVisible(true);
				} else if (pw.length() < 6 || pw.length() > 12) {
					msg.setText("비밀번호의 글자수를 한번 더 확인해 주세요!");
					error.add(msg);
					error.add(ok);
					ok.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							error.dispose();
						}
					});
					error.setVisible(true);
				} else if (!(pw.equals(repw))) {
					msg.setText("입력하신 비밀번호가 일치하지 않습니다!");
					error.add(msg);
					error.add(ok);
					ok.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							error.dispose();
						}
					});
					error.setVisible(true);
				} else if (!(isEmail(email))) {
					msg.setText("입력하신 이메일의 형식이 올바르지 않습니다!");
					error.add(msg);
					error.add(ok);
					ok.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							error.dispose();
						}
					});
					error.setVisible(true);
				} else if (year < 1000 || tYear.getText().equals(null)) {
					msg.setText("입력하신 생일 형식이 올바르지 않습니다!");
					error.add(msg);
					error.add(ok);
					ok.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							error.dispose();
						}
					});
					error.setVisible(true);
				}
				else {
					String sql = "insert into member values ('" + id + "','" + pw + "','" + birth + "','" + email
							+ "')";
					new MemDao(sql);
					
				}
			}
		});

		fSign.setVisible(true);
	}

	public void windowClosing(WindowEvent e) {
		fSign.setVisible(false);
		LoginFrame lf = new LoginFrame();
		lf.loginOpen();
	}
	
	void signClose() {
		fSign.setVisible(false);
		LoginFrame lf = new LoginFrame();
		lf.loginOpen();
	}
	
	public boolean isEmail(String str) {
	    return Pattern.matches("^[a-z0-9A-Z._-]*@[a-z0-9A-Z]*.[a-zA-Z.]*$", str);
	}
}
