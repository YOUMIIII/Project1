package Pjt_1_ConnectServer;

import java.awt.Color;

import javax.swing.JPanel;


public class PanelGroup {
	public JPanel[] pNutri;
	public Color[] cNutri;
	public JPanel p;
	public Color back;
	
	public PanelGroup() {
		back = new Color(129, 129, 129);
		cNutri = new Color[7];
		cNutri[0] = new Color(255,243,138);  //탄수화물
		cNutri[1] = new Color(255,155,5);  //단백질
		cNutri[2] = new Color(42,255,102);  //채소
		cNutri[3] = new Color(46,155,255);  //칼슘
		cNutri[4] = new Color(186,0,252);  //지방
		cNutri[5] = new Color(255,137,187);  //과일
		cNutri[6] = new Color(2,79,151);  //기타
	
		pNutri = new JPanel[7];
		for(int i = 0; i<pNutri.length; i++) {
			pNutri[i] = new JPanel();
			pNutri[i].setBackground(cNutri[i]);
		}
	}
}
