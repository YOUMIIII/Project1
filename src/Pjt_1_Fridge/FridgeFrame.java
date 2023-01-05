package Pjt_1_Fridge;

import javax.swing.JFrame;

public class FridgeFrame {
	JFrame fRefridge;
	
	public FridgeFrame() {
		fRefridge = new JFrame("나의 냉장고");
		fRefridge.setSize(500, 520);
		fRefridge.setLocationRelativeTo(null);
		fRefridge.setResizable(false);
		fRefridge.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		fRefridge.setVisible(true);
		
	}
	
	
	
	
}
