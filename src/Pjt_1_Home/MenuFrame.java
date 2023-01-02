package Pjt_1_Home;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class MenuFrame{
	JPanel pMenu, pMenuB, pMenuL, pMenuD, pMenu1, pMenu2, pDate;
	LineBorder bb = new LineBorder(Color.gray, 1, false);
	
	public MenuFrame() {
		pMenu = new JPanel();
		pMenuB = new JPanel();
		pMenuL = new JPanel();
		pMenuD = new JPanel();
		pMenu1 = new JPanel();
		pMenu2 = new JPanel();
		pDate = new JPanel();
		
		pMenu.setLayout(null);
		pDate.setBounds(0, 0, 330, 70);
		pDate.setBackground(Color.WHITE);
		pDate.setLayout(new BorderLayout());
		pMenu.setBackground(Color.black);
		pMenuB.setBackground(Color.WHITE);
		pMenuB.setBounds(0, 70, 330, 230);
		pMenuB.setBorder(bb);
		pMenu1.setBounds(0, 300, 330, 90);
		pMenu1.setBackground(Color.WHITE);
		pMenu1.setBorder(bb);
		pMenuL.setBackground(Color.WHITE);
		pMenuL.setBounds(0, 390, 330, 230);
		pMenuL.setBorder(bb);
		pMenu2.setBounds(0, 620, 330, 90);
		pMenu2.setBackground(Color.WHITE);
		pMenu2.setBorder(bb);
		pMenuD.setBackground(Color.WHITE);
		pMenuD.setBounds(0, 710, 330, 230);
		pMenuD.setBorder(bb);
		
		pMenu.add(pDate);
		pMenu.add(pMenuB);
		pMenu.add(pMenuL);
		pMenu.add(pMenuD);
		pMenu.add(pMenu1);
		pMenu.add(pMenu2);
	}
	
	
}
