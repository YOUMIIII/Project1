package Pjt_1_ConnectServer;

import java.awt.Color;

import javax.swing.JPanel;


public class PanelGroup {
	public JPanel[] pNutri;
	public Color[] cNutri;
	public JPanel p;
	public Color back;
	
	public PanelGroup() {
		back = new Color(228, 227, 227);
		cNutri = new Color[7];
		cNutri[1] = new Color(255,135,135);  //탄수화물 빨강
//		cNutri[0] = new Color(255,243,138);  //탄수화물 노랑일
		cNutri[0] = new Color(254, 208, 73); //단백질
//		cNutri[1] = new Color(250,171,120);  //단백질 주황
		cNutri[2] = new Color(173,231,146);  //채소
		cNutri[3] = new Color(127,181,255);  //칼슘
		cNutri[4] = new Color(2,79,151);  //지방
		cNutri[5] = new Color(255,187,187);  //과일
//		cNutri[5] = new Color(255,137,187);  //과일
		cNutri[6] = new Color(2,79,151);  //기타
	
		pNutri = new JPanel[7];
		for(int i = 0; i<pNutri.length; i++) {
			pNutri[i] = new JPanel();
			pNutri[i].setBackground(cNutri[i]);
		}
	}
}
