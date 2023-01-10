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
	JButton bMenuSearch, bInput, bSearch, bAdd;
	JLabel lName;
	JTextField tName;
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

		openPlusFood();
	}

	void openPlusFood() {
		//
		System.out.println(id);

		fPFood.setLayout(null);
		pNorth.setBounds(0, 0, 450, 60);
		pNorth.setBackground(Color.lightGray);
		pNorth.setLayout(null);
		bMenuSearch.setBounds(0, 23, 100, 40);
		bMenuSearch.addActionListener(this);
		bMenuSearch.setFont(font.f15);
		bInput.setBounds(95, 23, 100, 40);
		bInput.setFont(font.f15);
		bInput.addActionListener(this);

		pCard.setLayout(card);
		pCard.setBounds(0, 60, 450, 440);

		pSearch.setLayout(null);
		lName.setBounds(20, 20, 200, 40);
		lName.setFont(font.f17);
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

		pSearch.add(lName);
		pSearch.add(tName);
		pSearch.add(bSearch);
		pSearch.add(scrollT);
		pSearch.add(bAdd);

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
			System.out.println("검색");
			DefaultTableModel model = (DefaultTableModel) tbResult.getModel();
			String sql = "select name from nutrition where name like '%" + tName.getText() + "%'";
			ArrayList<String> menu = cont.bringMenuList(sql);
			for (int i = 0; i < menu.size(); i++) {
//				System.out.println(menu.get(i));
				Vector<String> vector = new Vector<String>();
				vector.add(menu.get(i));
				model.addRow(vector);
			}
		}else if(e.getActionCommand().equals("추가")) {
			int row = tbResult.getSelectedRow();
			int column = tbResult.getSelectedColumn();
			String value = tbResult.getValueAt(row, column).toString();
			String sql = "select * from nutrition where name = '" + value + "'"; // nutrition테이블에서 선택값 찾
			cont.plusList(sql, id);
			new PlusMenuFrameB(id);
			fPFood.setVisible(false);
		}
	}

	public void windowClosing(WindowEvent e) {
		new PlusMenuFrameB(id);
		fPFood.setVisible(false);
	}
}
