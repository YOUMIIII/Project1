package Pjt_1_Home;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import Pjt_1_ConnectServer.ConnectTest;
import Pjt_1_ConnectServer.PanelGroup;

public class MenuFrame {
	JPanel pMenu, pMenuB, pMenuBB, pMenuL, pMenuLL, pMenuD, pMenu1, pMenuS1, pMenu2, pDate, pNutriBox;
	JLabel[] lMenuB, lMenuL, lMenuD, lMenu1, lMenu2;
	Double[] nutrinum;
	int[] n= {0,0,0,0,0};
	JPanel[] pNutri;
	JTable bTable, sTable, lTable, s2Table, dTable;

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
		pNutri = new JPanel[5];
		pMenu = new JPanel();
		pMenuB = new JPanel();
		pMenuBB = new JPanel();
		pMenuL = new JPanel();
		pMenuLL = new JPanel();
		pMenuD = new JPanel();
		pMenu1 = new JPanel();
		pMenuS1 = new JPanel();
		pMenu2 = new JPanel();
		

		String sqlb = String.format(
				"select name from todaymenu where id = '%s' and today = '%s' and today_when = '%d'", id, date, 1);
		ArrayList<String> menu1 = con.bringMenuList(sqlb);
		Vector<String> listField = new Vector<String>();
		listField.addElement("");
		DefaultTableModel model = new DefaultTableModel(listField,0);
		for (int i = 0; i<menu1.size(); i++) {
			Vector<String> vector = new Vector<String>();
			vector.add(menu1.get(i));
			model.addRow(vector);
		}
		bTable = new JTable(model);
		bTable.setRowHeight(24);
		bTable.setFont(font.f17);
		tableCellCenter(bTable);
		
		String sqls1 = String.format(
				"select name from todaymenu where id = '%s' and today = '%s' and today_when = '%d'", id, date, 2);
		ArrayList<String> menu2 = con.bringMenuList(sqls1);
		DefaultTableModel model2 = new DefaultTableModel(listField,0);
		for (int i = 0; i<menu2.size(); i++) {
			Vector<String> vector = new Vector<String>();
			vector.add(menu2.get(i));
			model2.addRow(vector);
		}
		sTable = new JTable(model2);
		sTable.setRowHeight(24);
		sTable.setFont(font.f17);
		tableCellCenter(sTable);
		
		String sqll = String.format(
				"select name from todaymenu where id = '%s' and today = '%s' and today_when = '%d'", id, date, 3);
		ArrayList<String> menu3 = con.bringMenuList(sqll);
		DefaultTableModel model3 = new DefaultTableModel(listField,0);
		for (int i = 0; i<menu3.size(); i++) {
			Vector<String> vector = new Vector<String>();
			vector.add(menu3.get(i));
			model3.addRow(vector);
		}
		lTable = new JTable(model3);
		lTable.setRowHeight(24);
		lTable.setFont(font.f17);
		tableCellCenter(lTable);
		
		String sqls2 = String.format(
				"select name from todaymenu where id = '%s' and today = '%s' and today_when = '%d'", id, date, 4);
		ArrayList<String> menu4 = con.bringMenuList(sqls2);
		DefaultTableModel model4 = new DefaultTableModel(listField,0);
		for (int i = 0; i<menu4.size(); i++) {
			Vector<String> vector = new Vector<String>();
			vector.add(menu4.get(i));
			model4.addRow(vector);
		}
		s2Table = new JTable(model4);
		s2Table.setRowHeight(24);
		s2Table.setFont(font.f17);
		tableCellCenter(s2Table);
		
		String sqld = String.format(
				"select name from todaymenu where id = '%s' and today = '%s' and today_when = '%d'", id, date, 5);
		ArrayList<String> menu5 = con.bringMenuList(sqld);
		DefaultTableModel model5 = new DefaultTableModel(listField,0);
		for (int i = 0; i<menu5.size(); i++) {
			Vector<String> vector = new Vector<String>();
			vector.add(menu5.get(i));
			model5.addRow(vector);
		}
		dTable = new JTable(model5);
		dTable.setRowHeight(24);
		dTable.setFont(font.f17);
		tableCellCenter(dTable);
		
		pMenu.setLayout(null);
		pDate.setBounds(0, 0, 230, 60);
		pDate.setBackground(Color.WHITE);
		pDate.setLayout(null);
		pNutriBox.setLayout(null);

		// 영양소패널 위치지정
		pNutriBox.setBounds(0, 60, 230, 10);
		for (int i = 0; i < pNutri.length; i++) {
			pNutri[i] = new JPanel();
			pNutri[i].setBounds((i * 46), 0, 46, 10); //영양소 7개일땐 33

		}

//		 하루 식단 종합해서 각 영양소 존재하는지 계산
		String sql = String.format("select name from todaymenu where id = '%s' and today = '%s'", id, date);
		String[] menuAll = con.bringMenu(sql);

		for (int i = 0; i < menuAll.length; i++) {
			String sqlAll = String.format("select * from food where id = '%s' and name = '%s'", id, menuAll[i]);
			nutrinum = con.bringNutri(sqlAll); // 영양소 번호가 적혀있는 int 배열
			for(int j = 0; j<n.length; j++) {
				if(nutrinum[j]>0) {
					n[j] += nutrinum[j];
				}
			}
		}
		
		// 영양소 존재하면 색깔 배정
		for(int i = 0; i<n.length; i++) {
			if(n[i]>0) {
				pNutri[i].setBackground(pg.cNutri[i]);
			}
		}

		pMenu.setBackground(Color.white);
		pMenuB.setLayout(null);
		pMenuB.setBackground(Color.WHITE);
		pMenuB.setBounds(0, 70, 230, 130);
		pMenuB.setBorder(bb);
		bTable.setBounds(5, 15, 220, 110);
		pMenu1.setBounds(0, 200, 230, 80);
		pMenu1.setBackground(Color.WHITE);
		pMenu1.setBorder(bb);
		sTable.setBounds(5, 5, 220, 70);
		pMenuL.setBackground(Color.WHITE);
		pMenuL.setBounds(0, 280, 230, 130);
		pMenuL.setBorder(bb);
		pMenuL.setLayout(null);
		lTable.setBounds(5, 5, 220, 115);
		pMenu2.setBounds(0, 410, 230, 80);
		pMenu2.setBackground(Color.WHITE);
		pMenu2.setBorder(bb);
		s2Table.setBounds(5, 5, 220, 70);
		pMenuD.setLayout(null);
		pMenuD.setBackground(Color.WHITE);
		pMenuD.setBounds(0, 490, 230, 130);
		pMenuD.setBorder(bb);
		dTable.setBounds(5, 15, 220, 110);

		pMenu.add(pDate);
		pMenu.add(pNutriBox);
		for (int i = 0; i < pNutri.length; i++) {
			pNutriBox.add(pNutri[i]);
		}

		pMenuB.add(bTable);
		pMenu1.add(sTable);
		pMenuL.add(lTable);
		pMenu2.add(s2Table);
		pMenuD.add(dTable);
		pMenu.add(pMenuB);
		pMenu.add(pMenuL);
		pMenu.add(pMenuD);
		pMenu.add(pMenu1);
		pMenu.add(pMenu2);
	}
	
	int getN1() {
		return (int)n[0];
	}
	
	int getN2() {
		return (int)n[1]/2;
	}
	
	int getN3() {
		return (int)n[2];
	}
	
	int getN4() {
		return (int)n[3];
	}
	
	int getN5() {
		return (int)n[4]/3;
	}
	
	// 테이블 가운데 정렬 메소드
	public void tableCellCenter(JTable t) { 
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = t.getColumnModel();
		tcm.getColumn(0).setCellRenderer(dtcr);
	}

}
