package Pjt_1_Login;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Pjt_1_Home.HomeFrame;
import Pjt_1_Home.RoundedButton;

public class LoginFrame implements ActionListener {
	JFrame fLog;
	JLabel lId, lPw, imageLogo;
	JTextField tId;
	JPasswordField tPw;
	RoundedButton btnLog;
	JButton btnFindId, btnFindPw, btnSign;
	JPanel p;
	MyFont mf = new MyFont();

	public LoginFrame() {
		imageLogo = new JLabel();
		
		fLog = new JFrame("로그인");
		p = new JPanel();
		lId = new JLabel("아이디");
		tId = new JTextField();
		lPw = new JLabel("비밀번호");
		tPw = new JPasswordField();
		btnLog = new RoundedButton("로그인");
		btnFindId = new JButton("아이디찾기");
		btnFindPw = new JButton("비밀번호찾기");
		btnSign = new JButton("회원가입");
		
		loginOpen();
	}

	void loginOpen() {
		
		p.setBackground(Color.white);
		
		ImageIcon logo = new ImageIcon(LoginFrame.class.getResource("/Pjt_1_Image/Image/yummmy_c.png"));
//		ImageIcon logo = new ImageIcon(LoginFrame.class.getResource("/Pjt_1_Image/Image/yummmy.png"));
//		ImageIcon logo = new ImageIcon(LoginFrame.class.getResource("/Pjt_1_Image/Image/yummy_circlec.png"));
		Image img = logo.getImage();
		Image updateImg = img.getScaledInstance(140, 140, Image.SCALE_SMOOTH);
        ImageIcon updateIcon = new ImageIcon(updateImg);
		imageLogo.setIcon(updateIcon);
		
		lId.setFont(mf.f17);
		lPw.setFont(mf.f17);
		btnLog.setFont(mf.f15);
		btnFindId.setFont(mf.f15);
		btnFindPw.setFont(mf.f15);
		btnSign.setFont(mf.f15);
		
		p.setLayout(null);
		
		/* 프레임 가로긴 버전(450,260)
		imageLogo.setBounds(0,40,140,140);
		lId.setHorizontalAlignment(JLabel.RIGHT);
		lPw.setHorizontalAlignment(JLabel.RIGHT);
		lId.setBounds(130, 65, 60, 20);
		lPw.setBounds(130,95, 60, 20);
		tId.setBounds(195, 65, 150, 20);
		tPw.setBounds(195, 95, 150, 20);
		btnLog.setBounds(350, 65, 70, 50);
		btnFindId.setBounds(130, 130, 90, 25);
		btnFindPw.setBounds(230, 130, 90, 25);
		btnSign.setBounds(330, 130, 90, 25);
		*/

		imageLogo.setBounds(125,10,160,160);
		lId.setHorizontalAlignment(JLabel.RIGHT);
		lPw.setHorizontalAlignment(JLabel.RIGHT);
		lId.setBounds(50, 170, 60, 20);
		lPw.setBounds(50, 200, 60, 20);
		tId.setBounds(120, 170, 150, 20);
		tPw.setBounds(120, 200, 150, 20);
		btnLog.setBounds(280, 170, 70, 50);
		btnFindId.setBounds(40, 245, 110, 33);
		btnFindPw.setBounds(150, 245, 110, 33);
		btnSign.setBounds(260, 245, 100, 33);
		
		btnLog.addActionListener(this);// 로그인 버튼 리스너추가
		btnSign.addActionListener(this); // 회원가입 버튼 리스너추가
		btnFindId.addActionListener(this); // 회원가입 버튼 리스너추가
		btnFindPw.addActionListener(this); // 회원가입 버튼 리스너추가
		
		p.add(lId);
		p.add(tId);
		p.add(lPw);
		p.add(tPw);
		p.add(btnLog);
		p.add(btnFindId);
		p.add(btnFindPw);
		p.add(btnSign);
		p.add(imageLogo);
		fLog.add(p);

		fLog.setSize(400, 360);
//		fLog.setSize(450, 260);
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
						JOptionPane.showMessageDialog(null, "안녕하세요, " + getid +"님!\n오늘도 건강한 유아식 식단을 도와드릴게요! :)","로그인 성공",JOptionPane.PLAIN_MESSAGE);
						HomeFrame hf = new HomeFrame(getid);
						hf.setId(getid);
						hf.homeOpen();
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
		} if(e.getSource().equals(btnFindId)) {
			FindIdPw find = new FindIdPw();
			find.findId();
		}if(e.getSource().equals(btnFindPw)) {
			FindIdPw find = new FindIdPw();
			find.findPw();
		}
	}
}
