package Pjt_1_Refridge;

import javax.swing.JFrame;

public class RefridgeFrame {
	JFrame fRefridge;
	
	public RefridgeFrame() {
		fRefridge = new JFrame("나의 냉장고");
		fRefridge.setSize(500, 520);
		fRefridge.setLocationRelativeTo(null);
		fRefridge.setResizable(false);
		fRefridge.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		fRefridge.setVisible(true);
		
	}
	
	
	
	
}
