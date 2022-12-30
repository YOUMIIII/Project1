package Pjt_1_Home;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import Pjt_1_Login.LoginVo;

public class PlusFoodFrame implements ActionListener {
	//
	HomeFrame hf;
	
	MyFont font = new MyFont();
	JFrame fPFood;
	JPanel pFood;
	JCheckBox[] checkMS;
	JRadioButton rbMain, rbSide;
	JLabel lEx, lSurvey1, lSurvey2, lSurvey3, lSurvey4, lSurvey5;
	JTextField tFood, tIngredient1, tIngredient2;
	String[] nutri = { "탄수화물", "단백질", "채소", "칼슘", "지방", "과일", "기타" };
	RoundedButton bPlus;
	int main = 0;
	String id;

	public PlusFoodFrame(String id) { // 주/사이드, 메뉴이름, 영양소선택, 주 식재료1, 주 식재료2
		//
		this.id = id;

		fPFood = new JFrame("메뉴 추가");
		pFood = new JPanel();
		lEx = new JLabel("리스트에 추가하실 메뉴를 작성해주세요:)");
		lSurvey1 = new JLabel("ㆍ메뉴");
		lSurvey2 = new JLabel("ㆍ메뉴이름");
		lSurvey3 = new JLabel("ㆍ영양소");
		lSurvey4 = new JLabel("ㆍ주 식재료1");
		lSurvey5 = new JLabel("ㆍ주 식재료2");
		rbMain = new JRadioButton("메인메뉴(밥, 면, 덮밥 등)");
		rbSide = new JRadioButton("그 외(반찬, 간식 등)");
		tFood = new JTextField(15);
		checkMS = new JCheckBox[7];
		for (int i = 0; i < checkMS.length; i++) {
			checkMS[i] = new JCheckBox(nutri[i]);
		}
		tIngredient1 = new JTextField();
		tIngredient2 = new JTextField();
		bPlus = new RoundedButton("추가");

		// 라디오버튼 그룹설정
		ButtonGroup jb = new ButtonGroup();
		jb.add(rbMain);
		jb.add(rbSide);

	}

	void openPlusFood() {
		//
		System.out.println(id);
		
		pFood.setLayout(null);

		pFood.setBackground(Color.white);
		lEx.setBounds(30, 10, 300, 30);
		lEx.setFont(font.fExLabel);

		// 작성1
		lSurvey1.setBounds(30, 50, 200, 30);
		rbMain.setBounds(40, 80, 200, 30);
		rbSide.setBounds(260, 80, 150, 30);
		lSurvey1.setFont(font.fPlusLabel);
		rbMain.setFont(font.f2p);
		rbMain.setBackground(Color.white);
		rbMain.addActionListener(this); // 메인메뉴버튼 리스너
		rbSide.setFont(font.f2p);
		rbSide.setBackground(Color.white);

		// 작성2
		lSurvey2.setBounds(30, 110, 200, 30);
		lSurvey2.setFont(font.fPlusLabel);
		tFood.setBounds(40, 145, 100, 25);

		// 작성3
		lSurvey3.setBounds(30, 175, 200, 30);
		lSurvey3.setFont(font.fPlusLabel);
		checkMS[0].setBounds(40, 205, 80, 30);
		checkMS[0].setFont(font.f2p);
		checkMS[1].setBounds(130, 205, 80, 30);
		checkMS[1].setFont(font.f2p);
		checkMS[2].setBounds(220, 205, 60, 30);
		checkMS[2].setFont(font.f2p);
		checkMS[3].setBounds(290, 205, 80, 30);
		checkMS[3].setFont(font.f2p);
		checkMS[4].setBounds(370, 205, 80, 30);
		checkMS[4].setFont(font.f2p);
		checkMS[5].setBounds(40, 235, 80, 30);
		checkMS[5].setFont(font.f2p);
		checkMS[6].setBounds(130, 235, 80, 30);
		checkMS[6].setFont(font.f2p);
		for (int i = 0; i < checkMS.length; i++) {
			checkMS[i].setBackground(Color.white);
			checkMS[i].addActionListener(this);
		}

		// 작성4
		lSurvey4.setBounds(30, 265, 200, 30);
		lSurvey4.setFont(font.fPlusLabel);
		tIngredient1.setBounds(40, 300, 100, 25);

		// 작성5
		lSurvey5.setBounds(30, 330, 200, 30);
		lSurvey5.setFont(font.fPlusLabel);
		tIngredient2.setBounds(40, 360, 100, 25);

		bPlus.setBounds(210, 405, 50, 30);
		bPlus.setFont(font.f2);
		bPlus.addActionListener(this); // 추가버튼 리스너

		pFood.add(lEx);
		pFood.add(lSurvey1);
		pFood.add(rbSide);
		pFood.add(rbMain);
		pFood.add(lSurvey2);
		pFood.add(tFood);
		pFood.add(lSurvey3);
		for (int i = 0; i < checkMS.length; i++) {
			pFood.add(checkMS[i]);
		}
		pFood.add(lSurvey4);
		pFood.add(tIngredient1);
		pFood.add(lSurvey5);
		pFood.add(tIngredient2);
		pFood.add(bPlus);

		fPFood.add(pFood);

		// 메뉴추가 프레임
		fPFood.setSize(500, 520);
		fPFood.setLocationRelativeTo(null);
		fPFood.setResizable(false);
		fPFood.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		fPFood.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		if (s.equals("추가")) {
			int main = 0;
			if (rbMain.isSelected()) {
				main = 1;
			}
			String menu = tFood.getText();
			int[] nutri = new int[7];
			for (int i = 0; i < checkMS.length; i++) {
				if (checkMS[i].isSelected()) {
					nutri[i] = 1;
				} else {
					nutri[i] = 0;
				}
			}
			String ingredient1 = tIngredient1.getText();
			String ingredient2 = tIngredient2.getText();
			
			String sql = "insert into food values('" + id + "','" + main + "','" + menu + "','" + nutri[0] + "','"
					+ nutri[1] + "','" + nutri[2] + "','" + nutri[3] + "','" + nutri[4] + "','" + nutri[5] + "','"
					+ nutri[6] + "','" + ingredient1 + "','" + ingredient2 + "')";
			new MenuDao(sql);
		}
	}
}
