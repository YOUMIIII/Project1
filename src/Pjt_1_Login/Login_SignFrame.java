package Pjt_1_Login;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

public class Login_SignFrame extends WindowAdapter{
	Toolkit tk = Toolkit.getDefaultToolkit();
	Dimension screenSize = tk.getScreenSize();
	Dimension d;
	Frame fSign;
	Label lId, lPw, lRePw, lBirth, lEmail;
	TextField tId, tPw, tRePw, tYear, tEmail;
	Button btnSign;
	Choice cMonth, cDay;

	Login_SignFrame() {
		fSign = new Frame("회원가입");
		fSign.addWindowListener(this);
		fSign.setSize(495, 510);
		d = fSign.getSize();
		fSign.setLocation((screenSize.width - (int) (d.getWidth())) / 2,
				(screenSize.height - (int) (d.getHeight())) / 2);

		lId = new Label("아이디(영문 소문자, 숫자, 특수문자 혼합가능 6~12자 사용)");
		lPw = new Label("회원가입(영문 소문자, 숫자, 특수문자 혼합가능 6~12자 사용)");
		tId = new TextField(50);
		tPw = new TextField(50);
		lRePw = new Label("비밀번호 재확인");
		tRePw = new TextField(50);
		lBirth = new Label("생년월일");
		tYear = new TextField(4);
		cMonth = new Choice();
		for (int i = 1; i <= 12; i++) {
			cMonth.add(i + "월");
		}
		cDay = new Choice();
		lEmail = new Label("본인 확인 이메일");
		tEmail = new TextField(50);
		btnSign = new Button("가입하기");

	}

	void signOpen() {
		fSign.setLayout(new FlowLayout());
		fSign.add(lId);
		fSign.add(tId);
		fSign.add(lPw);
		fSign.add(tPw);
		tPw.setEchoChar('*');
		fSign.add(lRePw);
		tRePw.setEchoChar('*');
		fSign.add(tRePw);
		fSign.add(lBirth);
		fSign.add(tYear);
		fSign.add(cMonth);
		cMonth.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				Calendar birth = Calendar.getInstance();
				birth.set(Calendar.MONTH, (int) (cMonth.getSelectedItem().charAt(0))-49);
				birth.set(Calendar.YEAR, Integer.parseInt(tYear.getText()));
				for (int i = 1; i <= birth.getActualMaximum(Calendar.DATE); i++) {
					cDay.add(i + "일");
				}
			}
		});
		fSign.add(cDay);
		fSign.add(lEmail);
		fSign.add(tEmail);
		fSign.add(btnSign);
		btnSign.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				System.out.println("ddd");
			}
		});

		fSign.setVisible(true);
	}

	public void windowClosing(WindowEvent e) {
		System.exit(0);
		LoginFrame lf = new LoginFrame();
		lf.loginOpen();
	}
}
