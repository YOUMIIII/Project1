package Pjt_1_Fridge;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class UseIngredient extends WindowAdapter{
	Frame fUseIn;
	String id;
	
	UseIngredient(String id){
		this.id = id;
		fUseIn = new Frame("재료사용");
	}
	
	public void openUse(){
//		System.out.println(id);
		fUseIn.addWindowListener(this);
		
		fUseIn.setSize(500, 520);
		fUseIn.setLocationRelativeTo(null);
		fUseIn.setResizable(false);
		fUseIn.setVisible(true);
	}
	
	public void windowClosing(WindowEvent e) { // 회원가입 창 종료하면 다시 로그인 창 오픈
		fUseIn.setVisible(false);
		new FridgeFrame(id);

	}
}
