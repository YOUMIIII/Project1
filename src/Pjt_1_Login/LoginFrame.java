package Pjt_1_Login;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class LoginFrame extends WindowAdapter implements ActionListener {
	Frame fLog, fSign;
	Label lId, lPw, lChk;
	TextField tId, tPw;
	Button btnLog, btnFindId, btnFindPw, btnSign;
	Panel p, np, sp;
	Toolkit tk = Toolkit.getDefaultToolkit();
	Dimension screenSize = tk.getScreenSize();
	Dimension d;

	LoginFrame() {
		fLog = new Frame("로그인");
		p = new Panel();
		np = new Panel();
		sp = new Panel();
		lId = new Label("아이디");
		tId = new TextField(30);
		lPw = new Label("비밀번호");
		tPw = new TextField(30);
		tPw.setEchoChar('*');
		lChk = new Label("아이디 또는 비밀번호를 확인해주세요!");
		btnLog = new Button("로그인");
		btnFindId = new Button("아이디찾기");
		btnFindPw = new Button("비밀번호찾기");
		btnSign = new Button("회원가입");
	}

	void loginOpen() {
		fLog.add(p);
		fLog.add(np, "North");
		np.add(lChk);
		p.add(lId);
		p.add(tId);
		p.add(lPw);
		p.add(tPw);
		p.add(btnLog);
		fLog.add(sp, "South");
		sp.add(btnFindId);
		sp.add(btnFindPw);
		sp.add(btnSign);
		btnSign.addActionListener(this);

		fLog.setSize(845, 423);
		fLog.setResizable(false);
		d = fLog.getSize();
		fLog.setLocation((screenSize.width - (int) (d.getWidth())) / 2,
				(screenSize.height - (int) (d.getHeight())) / 2);
		fLog.setVisible(true);
		fLog.addWindowListener(this);
	}

	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Login_SignFrame ls = new Login_SignFrame();
		ls.signOpen();
		fLog.setVisible(false);
	}
}
