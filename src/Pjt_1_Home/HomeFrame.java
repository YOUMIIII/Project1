package Pjt_1_Home;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomeFrame{
	Date today = new Date();
	SimpleDateFormat dateFormat1 = new SimpleDateFormat("YY년 MM월 dd일(E)", Locale.KOREA);

	JFrame fHome;
	JPanel pDate, pMenu, pMenuB, pMenuL, pMenuD;
	JLabel lDate, lMenuB, lMenuL, lMenuD;
	Font f1, f2;
	TextField tfh, tfw;
	JButton bMenuB, bMenuL, bMenuD;
	

	public HomeFrame() {
		fHome = new JFrame(); // 메인페이지 프레임
		fHome.setTitle("식단");
		pDate = new JPanel();
		lDate = new JLabel(dateFormat1.format(today), JLabel.LEFT);
		pMenu = new JPanel();
		pMenuB = new JPanel();
		pMenuL = new JPanel();
		pMenuD = new JPanel();
		lMenuB = new JLabel("아침");
		lMenuL = new JLabel("점심");
		lMenuD = new JLabel("저녁");
		bMenuB = new JButton("식단추가");
		bMenuL = new JButton("식단추가");
		bMenuD = new JButton("식단추가");
	}

	public void homeOpen() { // 메인페이지 오픈
		fHome.setLayout(null);
		fHome.add(pDate);
		fHome.setBackground(Color.white);
		pDate.setLayout(new GridLayout(1,3));
		pDate.setBounds(40, 120, 1340, 90);
		pDate.setBackground(Color.cyan);
		pDate.add(lDate);
		lDate.setFont(f1);
		fHome.add(pMenu);
		pMenu.setBounds(40, 210, 1340, 260);
		pMenu.setBackground(Color.yellow);
		
		pMenu.add(pMenuB);
		pMenuB.add(lMenuB);
		pMenuB.add(bMenuB);
		bMenuB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new PlusMenuFrame();
			}
		});
		
		pMenu.add(pMenuL);
		pMenuL.add(lMenuL);
		pMenuL.add(bMenuL);

		pMenu.add(pMenuD);
		pMenuD.add(lMenuD);
		pMenuD.add(bMenuD);
		JPanel pNuB[] = new JPanel[5];
		for(int i = 0; i<pNuB.length; i++) {
			pNuB[i] = new JPanel();
		}
		JPanel pNuL[] = new JPanel[5];
		for(int i = 0; i<pNuL.length; i++) {
			pNuL[i] = new JPanel();
		}
		JPanel pNuD[] = new JPanel[5];
		for(int i = 0; i<pNuD.length; i++) {
			pNuD[i] = new JPanel();
		}
		for(int i = 0; i<pNuB.length; i++) {
			pMenuB.add(pNuB[i]);
		}
		for(int i = 0; i<pNuL.length; i++) {
			pMenuL.add(pNuL[i]);
		}
		for(int i = 0; i<pNuD.length; i++) {
			pMenuD.add(pNuD[i]);
		}
		
		fHome.setSize(1440, 1024);
		fHome.setVisible(true);
		fHome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fHome.setVisible(true);
		fHome.setLocationRelativeTo(null);
	}


}
