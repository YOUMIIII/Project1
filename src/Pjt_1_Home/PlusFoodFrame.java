package Pjt_1_Home;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class PlusFoodFrame {
	MyFont font = new MyFont();
	JFrame fPFood;
	JPanel pFood1, pFood2, pFood3, pFood4, pFood5, pRadio;
	JCheckBox checkMS;
	JRadioButton rbMain, rbSide;
	JLabel lSurvey1, lSurvey2, lSurvey3, lSurvey4, lSurvey5;
	JTextField tFood;
	
	PlusFoodFrame(){ // 주/사이드, 메뉴이름, 영양소선택, 주 식재료1, 주 식재료2
		
		fPFood = new JFrame("메뉴 추가");
		pFood1 = new JPanel();
		pFood2 = new JPanel();
		pFood3 = new JPanel();
		pFood4 = new JPanel();
		pFood5 = new JPanel();
		pRadio = new JPanel();
		lSurvey1 = new JLabel("주메뉴 / 반찬");
		lSurvey2 = new JLabel("메뉴이름");
		lSurvey3 = new JLabel("영양소");
		lSurvey4 = new JLabel();
		lSurvey5 = new JLabel();
		rbMain = new JRadioButton("주메뉴");
		rbSide = new JRadioButton("반찬");
		tFood = new JTextField(15);
		
		//버튼 그룹설정
		ButtonGroup jb = new ButtonGroup();
		jb.add(rbMain);
		jb.add(rbSide);
		
		openPlusFood();
	}
	
	void openPlusFood() {
		pFood1.setLayout(null);
		
		//작성1
		lSurvey1.setBounds(10, 10, 100, 30);
		rbMain.setBounds(10, 40, 100, 30);
		rbSide.setBounds(100, 40, 100, 30);
		lSurvey1.setFont(font.f2);
		rbMain.setFont(font.f2p);
		rbSide.setFont(font.f2p);
		
		//작성2
		lSurvey2.setBounds(10, 80, 100, 30);
		tFood.setBounds(10, 110, 100, 30);
		lSurvey2.setFont(font.f2);
		
		//작성3
		lSurvey3.setBounds(10, 150, 100, 30);
		lSurvey3.setFont(font.f2);
		
		//작성4
		
		
		
		pFood1.add(rbSide);
		pFood1.add(rbMain);
		pFood1.add(lSurvey1);
		pFood1.add(lSurvey2);
		pFood1.add(lSurvey3);
		pFood1.add(tFood);

		
		
//		pFood2.setBounds(0, 87, 500, 87);
//		fPFood.add(pFood2);
//		pFood2.add(lSurvey2);
//		pFood2.add(tFood);
//		
//		fPFood.add(pFood3);
//		fPFood.add(pFood4);
//		fPFood.add(pFood5);
		//
		fPFood.add(pFood1);
		
		//메뉴추가 프레임
		fPFood.setSize(500, 520);
		fPFood.setLocationRelativeTo(null);
		fPFood.setResizable(false);
		fPFood.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		fPFood.setVisible(true);
	}
}
