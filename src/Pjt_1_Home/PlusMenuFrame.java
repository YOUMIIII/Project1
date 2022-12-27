package Pjt_1_Home;

import javax.swing.JFrame;

public class PlusMenuFrame {
	JFrame fPMenu;
	
	PlusMenuFrame(){
		fPMenu = new JFrame("식단추가");
		fPMenu.setSize(500, 520);
		fPMenu.setLocationRelativeTo(null);
		fPMenu.setVisible(true);
		fPMenu.setResizable(false);
		fPMenu.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
	}
}
