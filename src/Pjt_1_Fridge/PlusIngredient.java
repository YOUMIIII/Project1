package Pjt_1_Fridge;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class PlusIngredient extends WindowAdapter{
	Frame fPlusIn;
	String id;
	
	PlusIngredient(String id){
		this.id = id;
		fPlusIn = new Frame("재료추가");
	}
	
	public void openPlus(){
		System.out.println(id);
		fPlusIn.addWindowListener(this);
		
		fPlusIn.setSize(500, 520);
		fPlusIn.setLocationRelativeTo(null);
		fPlusIn.setResizable(false);
		fPlusIn.setVisible(true);
	}
	
	public void windowClosing(WindowEvent e) { // 회원가입 창 종료하면 다시 로그인 창 오픈
		fPlusIn.setVisible(false);
		new FridgeFrame(id);

	}
}
