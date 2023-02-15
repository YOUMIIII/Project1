package Pjt_1_Login;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;

import Pjt_1_Home.RoundedButton;
import Pjt_1_Mail.MailTest;

public class FindIdPw extends WindowAdapter implements ActionListener {
	Frame f;
	JLabel lEx, lEx2, lId, lMail;
	JTextField tInput,tInputId, tInputMail;
	RoundedButton bSubmit, bSubmit2;

	MyFont font = new MyFont();

	FindIdPw() {
		f = new Frame();
		lEx = new JLabel();
		lEx2 = new JLabel();
		lId = new JLabel("🌼 아이디");
		lMail = new JLabel("🌼 이메일");
		tInput = new JTextField();
		tInputId = new JTextField();
		tInputMail = new JTextField();
		bSubmit = new RoundedButton("아이디찾기");
		bSubmit2 = new RoundedButton("비밀번호찾기");

		f.setLayout(null);
		lEx.setBounds(40, 70, 400, 30);
		lEx.setFont(font.f20);
		lEx2.setBounds(43, 105, 400, 60);
		lEx2.setFont(font.f16);
		bSubmit.setFont(font.f15);
		bSubmit2.setFont(font.f15);
		bSubmit.addActionListener(this);
		bSubmit2.addActionListener(this);
		f.setBackground(Color.white);
		f.addWindowListener(this);
	}

	void findId() {

		lEx.setText("|  아이디찾기  |");
		lEx2.setText("<html><body>가입시 입력했던 메일주소를 작성하시면<br>아이디를 확인하실 수 있습니다.</body></html>");
		lMail.setBounds(43, 180, 300, 30);
		lMail.setFont(font.f17);
		tInput.setBounds(43, 210, 320, 35);
		bSubmit.setBounds(150, 280, 100, 40);
		f.add(lEx);
		f.add(lEx2);
		f.add(lMail);
		f.add(tInput);
		f.add(bSubmit);
		f.setSize(400, 400);
		f.setTitle("아이디찾기");
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

	void findPw() {
		lEx.setText("|  비밀번호찾기  |");
		lEx2.setText("<html><body>가입시 입력했던 아이디와 메일주소를 작성하시면<br>아이디를 확인하실 수 있습니다.</body></html>");
		lId.setBounds(43, 160, 300, 30);
		lId.setFont(font.f17);
		tInputId.setBounds(43, 190, 320, 35);
		lMail.setBounds(43, 230, 300, 30);
		lMail.setFont(font.f17);
		tInputMail.setBounds(43, 260, 320, 35);
		bSubmit2.setBounds(150, 320, 100, 40);
		f.add(lEx);
		f.add(lEx2);
		f.add(lId);
		f.add(tInputId);
		f.add(lMail);
		f.add(tInputMail);
		f.add(bSubmit2);
		f.setSize(400, 420);
		f.setTitle("비밀번호찾기");
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

	public void windowClosing(WindowEvent e) { // 회원가입 창 종료하면 다시 로그인 창 오픈
		if (e.getSource().equals(f)) {
			f.setVisible(false);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(bSubmit)) {
			String email = tInput.getText();
			String sql = "select id from member where email = '" + email + "'";
			LoginDao ld = new LoginDao();
			ld.findId(sql);
			f.setVisible(false);
		} else if (e.getSource().equals(bSubmit2)) {
			String id = tInputId.getText();
			String email = tInputMail.getText();
			String sql = "select pw from member where email = '" + email + "' and id = '" + id + "'";
			LoginDao ld = new LoginDao();
			String pw = ld.findPw(sql);
//			String toMail = tInput.getText();
			String toName = "얌미";
			String subject = "얌미에서 보내드리는 비밀번호확인 메일입니다";
			String content = "<html><head><meta charset='utf-8'/></head><body><font size='4em'>" +id +"님의 비밀번호는 </font><font size='4em', color = 'blue'>" + pw + "</font> <font size='4em'>입니다!</font>";

			MailTest mI = new MailTest();
			mI.sendMail(email, toName, subject, content);
			f.setVisible(false);
		}
	}

}
