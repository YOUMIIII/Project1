package Pjt_1_Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Pjt_1_Home.HomeFrame;

public class LoginFrame implements ActionListener {
	JFrame fLog, fSign;
	JLabel lId, lPw, lChk;
	JTextField tId;
	JPasswordField tPw;
	JButton btnLog, btnFindId, btnFindPw, btnSign;
	JPanel p, np, sp;
	MyFont mf = new MyFont();

	LoginFrame() {
		fLog = new JFrame("로그인");
		p = new JPanel();
		np = new JPanel();
		sp = new JPanel();
		lId = new JLabel("아이디");
		lId.setFont(mf.f2);
		tId = new JTextField(20);
		lPw = new JLabel("비밀번호");
		lPw.setFont(mf.f2);
		tPw = new JPasswordField(20);
//		lChk = new JLabel("아이디 또는 비밀번호를 확인해주세요!");
		btnLog = new JButton("로그인");
		btnLog.setFont(mf.f2);
		btnFindId = new JButton("아이디찾기");
		btnFindPw = new JButton("비밀번호찾기");
		btnSign = new JButton("회원가입");
	}

	void loginOpen() {
		fLog.add(p);
		fLog.add(np, "North");
//		np.add(lChk);
		p.add(lId);
		p.add(tId);
		p.add(lPw);
		p.add(tPw);
		p.add(btnLog);
		btnLog.addActionListener(this);// 로그인 버튼 리스너추가
		fLog.add(sp, "South");
		sp.add(btnFindId);
		sp.add(btnFindPw);
		sp.add(btnSign);
		btnSign.addActionListener(this); // 회원가입 버튼 리스너추가

		fLog.setSize(400, 250);
		fLog.setResizable(false);
		fLog.setLocationRelativeTo(null);
		fLog.setVisible(true);
		fLog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
				
				String getpwd = "";
				for (int i = 0; i < list.size(); i++) {
					LoginVo data = (LoginVo) list.get(i);
					
					getpwd = data.getMempwd();
					
					if (getpwd.equals(String.valueOf(tPw.getPassword()))) {
//						System.out.println("로그인 되었습니다.");
						fLog.setVisible(false);
						HomeFrame hf = new HomeFrame();
						hf.homeOpen();
						break;
					} else {
						JOptionPane.showMessageDialog(null, "입력하신 비밀번호가 틀렸습니다.","비밀번호 오류",JOptionPane.ERROR_MESSAGE);
						System.out.println("입력하신 비밀번호가 틀렸습니다.");
					}
				}
			} catch(Exception ex) {
//				JOptionPane.showMessageDialog(this, "로그인 실패");
				System.out.println("입력하신 id나 pwd가 틀렸습니다.");

			}
		}
	}
}
