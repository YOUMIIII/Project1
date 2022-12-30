package Pjt_1_Home;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class PlusMenuFrameB {
	MyFont font = new MyFont();
	JFrame fPMenu;
	JLabel lMent, lMain, lSide;
	JPanel pMenu, pMain, pSide, pButton;
	JList listMain, listSide;
	RoundedButton bPMenu, bEnterMenu;
	String id;
	
	
	PlusMenuFrameB(String id){
		lMent = new JLabel();
		lMain = new JLabel();
		lSide = new JLabel();
		fPMenu = new JFrame("식단추가");
		pMenu = new JPanel();
		pMain = new JPanel();
		pSide = new JPanel();
		pButton = new JPanel();
		listMain = new JList();
		listSide = new JList();
		bPMenu = new RoundedButton("메뉴 추가");
		bEnterMenu = new RoundedButton("식단 등록");
		
		this.id = id; // 전달받은 id 지역변수로 삽입
		openPlusMenuB();
	}
	
	void openPlusMenuB(){
		System.out.println(id); //받은 id 제대로 출력됨
		//상단 멘트
		fPMenu.add(lMent, "North");
		lMent.setText("아침식단을 입력해 주세요!");
		lMent.setFont(font.f3);
		
		//메인메뉴 레이블
		fPMenu.add(pMenu,"Center");
		pMenu.setLayout(null);
		pMenu.add(pMain);
		pMain.setBounds(0, 10, 500, 180);
//		pMain.setBackground(Color.yellow);
		pMain.setLayout(new BorderLayout());
		pMain.add(lMain, "North");
		lMain.setText("ㆍ메인메뉴");
		lMain.setFont(font.f3);
		
		//메인메뉴 리스트
		pMain.add(listMain, "Center");
		
		//사이드메뉴 레이블
		pMenu.add(pSide);
//		pSide.setBackground(Color.cyan);
		pSide.setBounds(0, 190, 500, 180);
		pSide.setLayout(new BorderLayout());
		pSide.add(lSide, "North");
		lSide.setText("ㆍ그 외");
		lSide.setFont(font.f3);
		
		//사이드메뉴 리스트
		pSide.add(listSide, "Center");
		
		//버튼 패널
		pMenu.add(pButton);
//		pButton.setBackground(Color.white);
		pButton.setBounds(0, 385, 500, 80);
		pButton.add(bPMenu);
		bPMenu.setFont(font.f2);
		bPMenu.addActionListener(new ActionListener() { // 메뉴 추가 버튼 리스너
			public void actionPerformed(ActionEvent arg0) {
				PlusFoodFrame pf = new PlusFoodFrame(id);
				pf.openPlusFood();
			}
		});
		pButton.add(bEnterMenu);
		bEnterMenu.setFont(font.f2);
		
		
		
		//아침 식단 추가 프레임
		fPMenu.setSize(500, 520);
		fPMenu.setLocationRelativeTo(null);
		fPMenu.setVisible(true);
		fPMenu.setResizable(false);
		fPMenu.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
	}
	
	void getId(String id) {
		this.id = id;
	}
}
