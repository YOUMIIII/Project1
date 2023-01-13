package Pjt_1_Home;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
//import Pjt_1_Login.LoginVo;
import javax.swing.table.DefaultTableModel;

import Pjt_1_ConnectServer.ConnectTest;

public class PlusFoodFrame extends WindowAdapter implements ActionListener {
	//
	HomeFrame hf;
	Frame fPFood;
	JPanel pNorth, pCard, pSearch, pInput;
	JButton bMenuSearch, bInput, bSearch, bAdd, bAdd2;
	JLabel lName, lName2, lEx, lNutri, lNutriEx;
	JLabel[] lNutrif;
	JTextField tName, tName2;
	JTextField[] tNutri;
	JTable tbResult;
	JScrollPane scrollT;
	CardLayout card = new CardLayout();
	ConnectTest cont = new ConnectTest();

	MyFont font = new MyFont();

	String id;

	public PlusFoodFrame(String id) { // 주/사이드, 메뉴이름, 영양소선택, 주 식재료1, 주 식재료2
		//
		this.id = id;

		fPFood = new Frame("메뉴 추가");
		pNorth = new JPanel();
		bMenuSearch = new JButton("메뉴검색");
		bInput = new JButton("직접입력");
		pCard = new JPanel();
		pSearch = new JPanel();
		pInput = new JPanel();

		lName = new JLabel("메뉴이름");
		tName = new JTextField(20);
		bSearch = new JButton("검색");
		tbResult = new JTable();
		bAdd = new JButton("추가");

		lEx = new JLabel("추가하실 메뉴를 직접 입력해주세요:)");
		lName2 = new JLabel("메뉴이름");
		tName2 = new JTextField(20);
		lNutri = new JLabel("영양성분");
		lNutriEx = new JLabel("없는 영양성분은 빈칸으로 둬주세요");
		lNutrif = new JLabel[7];
		tNutri = new JTextField[7];
		bAdd2 = new JButton("추가");

		openPlusFood();
	}

	void openPlusFood() {
		//
		System.out.println(id);

		fPFood.setLayout(null);
		pNorth.setBounds(0, 0, 450, 60);
		pNorth.setBackground(new Color(255, 188, 158));
		pNorth.setLayout(null);
		bMenuSearch.setBounds(0, 24, 100, 40);
		bMenuSearch.addActionListener(this);
		bMenuSearch.setFont(font.f15);
		bInput.setBounds(95, 24, 100, 40);
		bInput.setFont(font.f15);
		bInput.addActionListener(this);

		pCard.setLayout(card);
		pCard.setBounds(0, 60, 450, 440);

		// 메뉴검색 카드패널
		pSearch.setLayout(null);
		pSearch.setBackground(Color.white);
		lName.setBounds(20, 20, 200, 40);
		lName.setFont(font.f16);
		tName.setBounds(15, 55, 310, 40);
		bSearch.setBounds(335, 55, 100, 40);
		bSearch.setFont(font.f15);
		bSearch.addActionListener(this);

		Vector<String> listField = new Vector<String>();
		listField.addElement("");
		DefaultTableModel model = new DefaultTableModel(listField, 0);
		tbResult = new JTable(model);
		scrollT = new JScrollPane(tbResult);
		scrollT.setBounds(20, 120, 410, 210);
		tbResult.setRowHeight(20);
		tbResult.setFont(font.f15);

		bAdd.setBounds(175, 360, 100, 40);
		bAdd.setFont(font.f15);
		bAdd.addActionListener(this);

		// 직접입력 카드패널
		pInput.setLayout(null);
		pInput.setBackground(Color.white);
		lEx.setBounds(20, 20, 400, 40);
		lEx.setFont(font.f18);
		lName2.setBounds(20, 60, 200, 40);
		lName2.setFont(font.f17);
		tName2.setBounds(15, 95, 310, 35);
		lNutri.setBounds(20, 135, 200, 40);
		lNutri.setFont(font.f17);
		lNutriEx.setBounds(20, 160, 400, 40);
		lNutriEx.setFont(font.f15);
		for (int i = 0; i < lNutrif.length; i++) {
			lNutrif[i] = new JLabel();
		}
		for (int i = 0; i < tNutri.length; i++) {
			tNutri[i] = new JTextField();
		}
		lNutrif[0].setBounds(20, 195, 80, 40);
		lNutrif[0].setText("탄수화물(g): ");
		lNutrif[0].setFont(font.f14);
		tNutri[0].setBounds(95, 200, 60, 35);
		lNutrif[1].setBounds(170, 195, 80, 40);
		lNutrif[1].setText("단백질(g): ");
		lNutrif[1].setFont(font.f14);
		tNutri[1].setBounds(235, 200, 60, 35);
		lNutrif[2].setBounds(315, 195, 80, 40);
		lNutrif[2].setText("채소(g): ");
		lNutrif[2].setFont(font.f14);
		tNutri[2].setBounds(370, 200, 60, 35);
		lNutrif[3].setBounds(20, 235, 80, 40);
		lNutrif[3].setText("칼슘(mg): ");
		lNutrif[3].setFont(font.f14);
		tNutri[3].setBounds(85, 240, 60, 35);
		lNutrif[4].setBounds(165, 235, 80, 40);
		lNutrif[4].setText("지방(g): ");
		lNutrif[4].setFont(font.f14);
		tNutri[4].setBounds(220, 240, 60, 35);
		lNutrif[5].setBounds(295, 235, 80, 40);
		lNutrif[5].setText("나트륨(mg): ");
		lNutrif[5].setFont(font.f14);
		tNutri[5].setBounds(375, 240, 60, 35);
		bAdd2.setBounds(175, 360, 100, 40);
		bAdd2.setFont(font.f15);
		bAdd2.addActionListener(this);

		pSearch.add(lName);
		pSearch.add(tName);
		pSearch.add(bSearch);
		pSearch.add(scrollT);
		pSearch.add(bAdd);

		pInput.add(lEx);
		pInput.add(lName2);
		pInput.add(tName2);
		pInput.add(lNutri);
		pInput.add(lNutriEx);
		pInput.add(bAdd2);
		for (int i = 0; i < lNutrif.length; i++) {
			pInput.add(lNutrif[i]);
		}
		for (int i = 0; i < tNutri.length; i++) {
			pInput.add(tNutri[i]);
		}

		pCard.add(pSearch, "1");
		pCard.add(pInput, "2");

		pNorth.add(bMenuSearch);
		pNorth.add(bInput);
		fPFood.add(pNorth);
		fPFood.add(pCard);

		// 메뉴추가 프레임
		fPFood.setSize(450, 500);
		fPFood.setLocationRelativeTo(null);
		fPFood.setResizable(false);
		fPFood.addWindowListener(this);
		fPFood.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("메뉴검색")) {
			card.show(pCard, "1");
		} else if (e.getActionCommand().equals("직접입력")) {
			card.show(pCard, "2");
		} else if (e.getActionCommand().equals("검색")) {
//			System.out.println("검색");
			DefaultTableModel model = (DefaultTableModel) tbResult.getModel();
			String sql = "select name from nutrition where name like '%" + tName.getText() + "%'";
			ArrayList<String> menu = cont.bringMenuList(sql);
			if (menu.size() > 0) {
				for (int i = 0; i < menu.size(); i++) {
//					System.out.println(menu.get(i));
					Vector<String> vector = new Vector<String>();
					vector.add(menu.get(i));
					model.addRow(vector);
				}
			} else{
				JOptionPane.showMessageDialog(null, "해당메뉴에 대한 자료가 없습니다! 메뉴를 직접입력해주세요!", "자료없음",
						JOptionPane.ERROR_MESSAGE);

			}
		} else if (e.getSource() == bAdd) {
			int row = tbResult.getSelectedRow();
			int column = tbResult.getSelectedColumn();
			String value = tbResult.getValueAt(row, column).toString();
			String sql = "select * from nutrition where name = '" + value + "'"; // nutrition테이블에서 선택값 찾
			cont.plusList(sql, id);
			new PlusMenuFrameB(id);
			fPFood.setVisible(false);
		} else if (e.getSource() == bAdd2) {
			String name = tName2.getText();
			String[] nutri = new String[tNutri.length];
			for (int i = 0; i < nutri.length; i++) {
				String str = tNutri[i].getText();
				if (str == null || str.length() < 1) {
					nutri[i] = "null";
				} else {
					nutri[i] = str;
				}
			}
			String sql = "insert into food values( '" + id + "','" + name + "', '0','" + nutri[0] + "','" + nutri[1]
					+ "','" + nutri[4] + "','null','" + nutri[5] + "','" + nutri[3] + "','" + nutri[2] + "')";
			cont.plusMenu(sql);
			new PlusMenuFrameB(id);
			fPFood.setVisible(false);
		}
	}

	public void windowClosing(WindowEvent e) {
		new PlusMenuFrameB(id);
		fPFood.setVisible(false);
	}
}
