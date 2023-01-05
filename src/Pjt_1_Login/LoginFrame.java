package Pjt_1_Login;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Pjt_1_Home.HomeFrame;
import Pjt_1_Home.PlusFoodFrame;
import Pjt_1_Home.PlusFoodTest;
import Pjt_1_Home.RoundedButton;

public class LoginFrame implements ActionListener {
	JFrame fLog, fSign;
	JLabel lId, lPw, lChk;
	JTextField tId;
	JPasswordField tPw;
	RoundedButton btnLog, btnFindId, btnFindPw, btnSign;
	JPanel p;
	MyFont mf = new MyFont();

	public LoginFrame() {
		fLog = new JFrame("로그인");
		p = new JPanel();
		lId = new JLabel("아이디");
		tId = new JTextField();
		lPw = new JLabel("비밀번호");
		tPw = new JPasswordField();
		btnLog = new RoundedButton("로그인");
		btnFindId = new RoundedButton("아이디찾기");
		btnFindPw = new RoundedButton("비밀번호찾기");
		btnSign = new RoundedButton("회원가입");
		
		loginOpen();
	}

	void loginOpen() {
		
		p.setBackground(Color.white);
		
		lId.setFont(mf.fLogLabel);
		lPw.setFont(mf.fLogLabel);
		btnLog.setFont(mf.f2);
		btnFindId.setFont(mf.f2);
		btnFindPw.setFont(mf.f2);
		btnSign.setFont(mf.f2);
		
		p.setLayout(null);
		lId.setHorizontalAlignment(JLabel.RIGHT);
		lPw.setHorizontalAlignment(JLabel.RIGHT);
		lId.setBounds(40, 50, 60, 20);
		lPw.setBounds(40, 80, 60, 20);
		tId.setBounds(110, 50, 150, 20);
		tPw.setBounds(110, 80, 150, 20);
		btnLog.setBounds(270, 50, 70, 50);
		btnFindId.setBounds(45, 140, 90, 25);
		btnFindPw.setBounds(145, 140, 90, 25);
		btnSign.setBounds(245, 140, 90, 25);
		
		btnLog.addActionListener(this);// 로그인 버튼 리스너추가
		btnSign.addActionListener(this); // 회원가입 버튼 리스너추가
		
		p.add(lId);
		p.add(tId);
		p.add(lPw);
		p.add(tPw);
		p.add(btnLog);
		p.add(btnFindId);
		p.add(btnFindPw);
		p.add(btnSign);
		fLog.add(p);

		fLog.setSize(400, 250);
		fLog.setResizable(false);
		fLog.setLocationRelativeTo(null);
		fLog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fLog.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("회원가입")) {
			Login_SignFrame ls = new Login_SignFrame();
			ls.signOpen();
			fLog.setVisible(false);
		}
		if (e.getActionCommand().equals("로그인")) {
			try{
				LoginDao dao = new LoginDao(tId.getText());
				ArrayList<LoginVo> list = dao.list();
				
				String getid = "", getpwd = "";
				for (int i = 0; i < list.size(); i++) {
					LoginVo data = (LoginVo) list.get(i);
					getid = data.getMemid();
					getpwd = data.getMempwd();
					
					if (getpwd.equals(String.valueOf(tPw.getPassword()))) {
//						System.out.println("로그인 되었습니다.");
						HomeFrame hf = new HomeFrame();
						hf.setId(getid);
						hf.homeOpen();
//						PlusFoodFrame pf = new PlusFoodFrame();
//						pf.getId(getid);
//						
						fLog.setVisible(false);
						break;
					} else {
						JOptionPane.showMessageDialog(null, "입력하신 비밀번호가 틀렸습니다.","비밀번호 오류",JOptionPane.ERROR_MESSAGE);
						System.out.println("입력하신 비밀번호가 틀렸습니다.");
					}
				}
			} catch(Exception ex) {
//				JOptionPane.showMessageDialog(this, "로그인 실패");
				System.out.println("입력하신 id나 pwd가 틀렸습니다.");
				System.out.println(ex);

			}
		}
	}
}
