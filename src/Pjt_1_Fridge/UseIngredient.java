package Pjt_1_Fridge;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Pjt_1_ConnectServer.ConnectTest;
import Pjt_1_Home.MyFont;

public class UseIngredient extends WindowAdapter implements ActionListener{
	Frame fUseIn;
	String id, name;
	JLabel lEx, lUse;
	JTextField tUse;
	JButton bUse;
	MyFont font = new MyFont();
	
	UseIngredient(String id, String name){
		this.id = id;
		this.name = name;
		fUseIn = new Frame("재료사용");
		lEx = new JLabel("재고를 모두 사용한 경우 0을 입력해주세요!");
		lUse = new JLabel("재고량");
		tUse = new JTextField(10);
		bUse = new JButton("등록");
		openUse();
	}
	
	public void openUse(){
//		System.out.println(id);
		
		fUseIn.setLayout(null);
		
	    lEx.setBounds(35, 30, 300, 40);
	    lEx.setFont(font.f18);
	    lUse.setBounds(35, 50, 300, 40);
		lUse.setFont(font.f17);
		tUse.setBounds(30, 80, 300, 33);
		bUse.setBounds(130, 130, 100, 40);
		bUse.setFont(font.f15);
		bUse.addActionListener(this);
		
		fUseIn.add(lUse);
		fUseIn.add(tUse);
		fUseIn.add(bUse);
		
		fUseIn.addWindowListener(this);
		
		fUseIn.setSize(360, 200);
		fUseIn.setLocationRelativeTo(null);
		fUseIn.setResizable(false);
		fUseIn.setVisible(true);
	}
	
	public void windowClosing(WindowEvent e) {
		fUseIn.setVisible(false);
		new FridgeFrame(id);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("등록")) {
			Double quantity = Double.parseDouble(tUse.getText());
			String sql;
			if(quantity > 0) {
				sql = "update myfridge set quantity = " + quantity +"where ingredient = '" + name + "'";
			} else {
				sql = "delete from myfridge where ingredient = '" + name + "'";
			}
			ConnectTest cont = new ConnectTest();
			cont.updateFridge(sql);
			new FridgeFrame(id);
			fUseIn.setVisible(false);
		}
	}
}
