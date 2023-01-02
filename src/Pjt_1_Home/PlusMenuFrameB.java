package Pjt_1_Home;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import Pjt_1_ConnectServer.ConnectTest;

public class PlusMenuFrameB {
	MyFont font = new MyFont();
	JFrame fPMenu;
	JLabel lMent, lMain, lSide;
	JPanel pMenu, pMain, pSide, pButton;
	JList listMain, listSide;
	RoundedButton bPMenu, bEnterMenu;
	String id, sqlm, sqls;
	JScrollPane scrollMain, scrollSide;
	String[] mains, sides;
	ConnectTest conTest = new ConnectTest();
	
	
	
	PlusMenuFrameB(String id){
		lMent = new JLabel();
		lMain = new JLabel();
		lSide = new JLabel();
		fPMenu = new JFrame("식단추가");
		pMenu = new JPanel();
		pMain = new JPanel();
		pSide = new JPanel();
		pButton = new JPanel();
		sqlm = String.format("select food_name from food where id = '%s' and main = '%d'", id, 1);
		mains = conTest.getList(sqlm);
		sqls = String.format("select food_name from food where id = '%s' and main = '%d'", id, 0);
		sides = conTest.getList(sqls);
		listMain = new JList(mains);
		listSide = new JList(sides);
		bPMenu = new RoundedButton("메뉴 추가");
		bEnterMenu = new RoundedButton("식단 등록");
		scrollMain = new JScrollPane(listMain);
		scrollSide = new JScrollPane(listSide);
		
		this.id = id; // 전달받은 id 지역변수로 삽입
		openPlusMenuB();
	}
	
	void openPlusMenuB(){
		//받은 id 제대로 출력됨 확인
//		System.out.println(id);
		
		//상단 멘트
		lMent.setText("식단을 등록해주세요:)");
		lMent.setFont(font.f3);
		
		//메인메뉴 레이블
		pMenu.setLayout(null);
		pMain.setBounds(0, 10, 500, 180);
//		pMain.setBackground(Color.yellow);
		pMain.setLayout(new BorderLayout());
		lMain.setText("ㆍ메인메뉴");
		lMain.setFont(font.fPlusLabel);
		
		//메인메뉴 리스트
		listMain.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listMain.setFont(font.fPlusMenuList);
		scrollMain.setBorder(BorderFactory.createEmptyBorder(0,0,0,20));
		
		//사이드메뉴 레이블
//		pSide.setBackground(Color.cyan);
		pSide.setBounds(0, 190, 500, 180);
		pSide.setLayout(new BorderLayout());
		lSide.setText("ㆍ그 외");
		lSide.setFont(font.fPlusLabel);
		
		//사이드메뉴 리스트
		listSide.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listSide.setFont(font.fPlusMenuList);
		scrollSide.setBorder(BorderFactory.createEmptyBorder(0,0,0,20));
		
		//버튼 패널
//		pButton.setBackground(Color.white);
		pButton.setBounds(0, 385, 500, 80);
		bPMenu.setFont(font.f2);
		bPMenu.addActionListener(new ActionListener() { // 메뉴 추가 버튼 리스너
			public void actionPerformed(ActionEvent arg0) {
				PlusFoodFrame pf = new PlusFoodFrame(id);
				pf.openPlusFood();
				fPMenu.setVisible(false);
			}
		});
		bEnterMenu.setFont(font.f2);
		
		
		
		//아침 식단 추가 프레임
		pMain.add(lMain, "North");
		fPMenu.add(lMent, "North");
		fPMenu.add(pMenu,"Center");
		pMenu.add(pMain);
		pMain.add(scrollMain, "Center");
		pMenu.add(pSide);
		pSide.add(lSide, "North");
		pSide.add(scrollSide,"Center");
		pMenu.add(pButton);
		pButton.add(bPMenu);
		pButton.add(bEnterMenu);
		
		fPMenu.setSize(500, 520);
		fPMenu.setLocationRelativeTo(null);
		fPMenu.setResizable(false);
		fPMenu.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		fPMenu.setVisible(true);
		
		
	}
	
//	void getId(String id) {
//		this.id = id;
//	}
}
