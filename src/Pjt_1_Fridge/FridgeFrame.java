package Pjt_1_Fridge;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JList;

import Pjt_1_ConnectServer.ConnectTest;
import Pjt_1_Home.MyFont;
import Pjt_1_Home.RoundedButton;

public class FridgeFrame extends WindowAdapter implements ActionListener {
	Frame fFridge;
	JLabel lExplain;
	JList listFridge;
	RoundedButton bAdd, bUse, bRecipe;
	String id;
	ConnectTest con = new ConnectTest();

	String[] fridge;

	MyFont font = new MyFont();

	public FridgeFrame(String id) {
		this.id = id;
//		System.out.println(id); // 아이디 확인

		fFridge = new Frame("나의 냉장고");
		lExplain = new JLabel("냉장고의 현재 재고상태입니다 :)");
		listFridge = new JList();
		bAdd = new RoundedButton("재고 추가");
		bUse = new RoundedButton("재고 사용");
		bRecipe = new RoundedButton("추천레시피");

		fFridge.setLayout(null);
		lExplain.setBounds(10, 20, 500, 30);
		lExplain.setFont(font.f18);
		listFridge.setBounds(10, 60, 280, 400);
		listFridge.setFont(font.f18);

		String sql = String.format("select * from myfridge where id = '%s'", id);
		ArrayList<FridgeVO> list = con.getFridge(sql);
		fridge = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			FridgeVO vo = list.get(i);
			fridge[i] = vo.getIngredient()  + "\t" + "\t" + "\t" + "\t" + "\t" + vo.getQuantity() + "\t"
					+ "\t" + "\t" + "\t" + "\t" + "\t" + vo.getDate();
		}
		listFridge.setListData(fridge);

		bAdd.setBounds(350, 170, 100, 30);
		bAdd.setFont(font.f16);
		bAdd.addActionListener(this);
		bUse.setBounds(350, 220, 100, 30);
		bUse.setFont(font.f16);
		bAdd.addActionListener(this);
		bRecipe.setBounds(350, 270, 100, 30);
		bRecipe.setFont(font.f16);

		fFridge.add(lExplain);
		fFridge.add(listFridge);
		fFridge.add(bAdd);
		fFridge.add(bUse);
		fFridge.add(bRecipe);
		fFridge.setSize(500, 520);
		fFridge.setLocationRelativeTo(null);
		fFridge.setResizable(false);
		fFridge.addWindowListener(this);
		fFridge.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("재고 추가")) {
			PlusIngredient pi = new PlusIngredient(id);
			pi.openPlus();
			fFridge.setVisible(false);
		}
		if(e.getActionCommand().equals("재고 사용")) {
			UseIngredient ui = new UseIngredient(id);
			ui.openUse();
			fFridge.setVisible(false);
		}
	}
	
	public void windowClosing(WindowEvent e) { 
		fFridge.setVisible(false);
	}
}
