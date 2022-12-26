package Pjt_1_Home;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.ImageObserver;

public class HomeFrame extends WindowAdapter implements ImageObserver {
	Toolkit tk = Toolkit.getDefaultToolkit();
	Dimension screenSize = tk.getScreenSize();
	Dimension d;
	Frame fHome;
	Panel pWest1, pWest2, pWest3, pWest4, pWest5, pWest6;
	Label lbn1, lbn2, lbn3, lbh, lbw;
	Font f1, f2;
	TextField tfh, tfw;
	Button bEdit;
	Image iBaby1 = tk.getImage("D:\\work-java\\Project1\\src\\iMage\\baby1.png");
	Image iBaby2 = tk.getImage("D:\\work-java\\Project1\\src\\iMage\\baby2.png");
	Image iBaby3 = tk.getImage("D:\\work-java\\Project1\\src\\iMage\\baby3.png");

//	void paint(Graphics g) {
//		g.drawImage(iBaby1, 126, 84, this);
//	}

	HomeFrame() {
		fHome = new Frame("홈");
		f1 = new Font("Inter", Font.PLAIN, 30);
		f2 = new Font("Inter", Font.PLAIN, 15);
		pWest1 = new Panel()
		{
			public void paint(Graphics g) {
				g.drawImage(iBaby1, 125, 40, 127, 127, this);
			}
		};

		pWest2 = new Panel() {
			public void paint(Graphics g) {
				g.drawImage(iBaby2, 125, 40, 127, 127, this);
			}
		};
		pWest3 = new Panel() {
			public void paint(Graphics g) {
				g.drawImage(iBaby3, 125, 40, 127, 127, this);
			}
		};
		pWest4 = new Panel();
		pWest5 = new Panel();
		pWest6 = new Panel();
		lbn1 = new Label("이름1");
		lbh = new Label("키  : ");
		lbw = new Label("몸무게  : ");
		tfh = new TextField(6);
		tfw = new TextField(6);
		bEdit = new Button("  수정  ");
//		lBName2 = new Label("이름2");
//		lBName3 = new Label("이름3");

	}

	void homeOpen() {
		fHome.setLayout(null);
		fHome.add(pWest1);
		fHome.add(pWest2);
		fHome.add(pWest3);
		fHome.add(pWest4);
		fHome.add(pWest5);
		fHome.add(pWest6);
		
		pWest1.setBounds(0, 30, 370, 170);
		pWest1.setBackground(Color.darkGray);
		pWest4.setBounds(0, 200, 370, 170);
		pWest4.setBackground(Color.darkGray);
//		pWest1.setLayout(null);
		pWest4.add(lbn1);
		lbn1.setForeground(Color.white);
		lbn1.setFont(f1);
		pWest4.add(lbh);
		lbh.setForeground(Color.white);
		lbh.setFont(f2);
		pWest4.add(tfh);
		pWest4.add(lbw);
		lbw.setForeground(Color.white);
		lbw.setFont(f2);
		pWest4.add(tfw);
		pWest4.add(bEdit);
		
		pWest2.setBounds(0, 375, 370, 170);
		pWest2.setBackground(Color.darkGray);
		pWest5.setBounds(0, 545, 370, 170);
		pWest5.setBackground(Color.darkGray);
		
		pWest3.setBounds(0, 720, 370, 170);
		pWest3.setBackground(Color.darkGray);
		pWest6.setBounds(0, 890, 370, 170);
		pWest6.setBackground(Color.darkGray);

		d = fHome.getSize();
		fHome.setSize(1440, 1024);
		fHome.setLocation(0, 0);
		fHome.setVisible(true);
		fHome.addWindowListener(this);
		fHome.setVisible(true);
	}

	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}

}
