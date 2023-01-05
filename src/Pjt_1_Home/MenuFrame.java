package Pjt_1_Home;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import Pjt_1_ConnectServer.ConnectTest;
import Pjt_1_ConnectServer.PanelGroup;

public class MenuFrame {
	JPanel pMenu, pMenuB, pMenuBB, pMenuL, pMenuLL, pMenuD, pMenu1, pMenuS1, pMenu2, pDate, pNutriBox;
	JLabel[] lMenuB, lMenuL, lMenuD, lMenu1, lMenu2;
	int[] nutrinum;
	int[] n= {0,0,0,0,0,0,0};
	JPanel[] pNutri;

	MyFont font = new MyFont();
	ConnectTest con = new ConnectTest();
	PanelGroup pg = new PanelGroup();
	PanelGroup pg2 = new PanelGroup();
	PanelGroup pg3 = new PanelGroup();

	LineBorder bb = new LineBorder(Color.gray, 1, false);
	String id, date;

	public MenuFrame(String id, String date) {
		this.id = id;
		this.date = date;

		
		pDate = new JPanel();
		pNutriBox = new JPanel();
		pNutri = new JPanel[7];
		pMenu = new JPanel();
		pMenuB = new JPanel();
		pMenuBB = new JPanel();
		pMenuL = new JPanel();
		pMenuLL = new JPanel();
		pMenuD = new JPanel();
		pMenu1 = new JPanel();
		pMenuS1 = new JPanel();
		pMenu2 = new JPanel();
//		System.out.println(id);
//		System.out.println(date);

		String sqlb = String.format(
				"select menu_name from todaymenu where id = '%s' and today = '%s' and today_when = '%d'", id, date, 1);
		String[] menu = con.bringMenu(sqlb);
		lMenuB = new JLabel[menu.length];
		for (int i = 0; i < menu.length; i++) {
			lMenuB[i] = new JLabel();
			lMenuB[i].setText(menu[i]);
			lMenuB[i].setFont(font.f18);
		}

		String sqls1 = String.format(
				"select menu_name from todaymenu where id = '%s' and today = '%s' and today_when = '%d'", id, date, 2);
		String[] menu2 = con.bringMenu(sqls1);
		lMenu1 = new JLabel[menu2.length];
		for (int i = 0; i < menu2.length; i++) {
			lMenu1[i] = new JLabel();
			lMenu1[i].setText(menu2[i]);
			lMenu1[i].setFont(font.f18);
		}

		String sqll = String.format(
				"select menu_name from todaymenu where id = '%s' and today = '%s' and today_when = '%d'", id, date, 3);
		String[] menu3 = con.bringMenu(sqll);
		lMenuL = new JLabel[menu3.length];
		for (int i = 0; i < menu3.length; i++) {
			lMenuL[i] = new JLabel();
			lMenuL[i].setText(menu3[i]);
			lMenuL[i].setFont(font.f18);
		}

		String sqls2 = String.format(
				"select menu_name from todaymenu where id = '%s' and today = '%s' and today_when = '%d'", id, date, 4);
		String[] menu4 = con.bringMenu(sqls2);
		lMenu2 = new JLabel[menu4.length];
		for (int i = 0; i < menu4.length; i++) {
			lMenu2[i] = new JLabel();
			lMenu2[i].setText(menu4[i]);
			lMenu2[i].setFont(font.f18);
		}

		String sqld = String.format(
				"select menu_name from todaymenu where id = '%s' and today = '%s' and today_when = '%d'", id, date, 5);
		String[] menu5 = con.bringMenu(sqld);
		lMenuD = new JLabel[menu5.length];
		for (int i = 0; i < menu5.length; i++) {
			lMenuD[i] = new JLabel();
			lMenuD[i].setText(menu5[i]);
			lMenuD[i].setFont(font.f18);
		}

		pMenu.setLayout(null);
		pDate.setBounds(0, 0, 230, 70);
		pDate.setBackground(Color.WHITE);
		pDate.setLayout(new BorderLayout());
		pNutriBox.setLayout(null);

		// 영양소패널 위치지정
		pNutriBox.setBounds(0, 70, 230, 33);
		for (int i = 0; i < pNutri.length; i++) {
			pNutri[i] = new JPanel();
			pNutri[i].setBounds((i * 33), 0, 33, 33);

		}

		// 하루 식단 종합해서 각 영양소 존재하는지 계산
		String sql = String.format("select menu_name from todaymenu where id = '%s' and today = '%s'", id, date);
		String[] menuAll = con.bringMenu(sql);

		for (int i = 0; i < menuAll.length; i++) {
			String sqlAll = String.format("select * from food where id = '%s' and food_name = '%s'", id, menuAll[i]);
			nutrinum = con.bringNutri(sqlAll); // 영양소 번호가 적혀있는 int 배열
			for(int j = 0; j<n.length; j++) {
				if(nutrinum[j]==1) {
					n[j]++;
				}
			}
		}
		
		// 영양소 존재하면 색깔 배정
		for(int i = 0; i<n.length; i++) {
			if(n[i]>0) {
				pNutri[i].setBackground(pg.cNutri[i]);
			}
		}

		pMenu.setBackground(Color.black);
		pMenuB.setLayout(null);
		pMenuB.setBackground(Color.WHITE);
		pMenuB.setBounds(0, 103, 230, 130);
		pMenuB.setBorder(bb);
		pMenuBB.setLayout(new FlowLayout());
		pMenuBB.setBounds(50, 10, 140, 80);
		pMenuBB.setBackground(Color.white);
		pMenu1.setBounds(0, 233, 230, 80);
		pMenu1.setBackground(Color.WHITE);
		pMenu1.setBorder(bb);
		pMenuL.setBackground(Color.WHITE);
		pMenuL.setBounds(0, 313, 230, 130);
		pMenuL.setBorder(bb);
		pMenuL.setLayout(null);
		pMenuLL.setLayout(new FlowLayout());
		pMenuLL.setBounds(50, 10, 140, 80);
		pMenuLL.setBackground(Color.white);
		pMenu2.setBounds(0, 443, 230, 80);
		pMenu2.setBackground(Color.WHITE);
		pMenu2.setBorder(bb);
		pMenuD.setBackground(Color.WHITE);
		pMenuD.setBounds(0, 523, 230, 130);
		pMenuD.setBorder(bb);

		pMenu.add(pDate);
		pMenu.add(pNutriBox);
		for (int i = 0; i < pNutri.length; i++) {
			pNutriBox.add(pNutri[i]);
		}
		pMenuB.add(pMenuBB);
		for (int i = 0; i < menu.length; i++) {
			pMenuBB.add(lMenuB[i]);
		}
		for (int i = 0; i < menu2.length; i++) {
			pMenu1.add(lMenu1[i]);
		}
		pMenuL.add(pMenuLL);
		for (int i = 0; i < menu3.length; i++) {
			pMenuLL.add(lMenuL[i]);
		}
		for (int i = 0; i < menu4.length; i++) {
			pMenu2.add(lMenu2[i]);
		}
		for (int i = 0; i < menu5.length; i++) {
			pMenuD.add(lMenuD[i]);
		}
		pMenu.add(pMenuB);
		pMenu.add(pMenuL);
		pMenu.add(pMenuD);
		pMenu.add(pMenu1);
		pMenu.add(pMenu2);
	}
	
	int getN1() {
		return n[0];
	}
	
	int getN2() {
		return n[1];
	}
	
	int getN3() {
		return n[2];
	}

}
