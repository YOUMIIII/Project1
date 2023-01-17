package Pjt_1_Home;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Pjt_1_ConnectServer.ConnectTest;

public class PlusMenuFrameB extends WindowAdapter{
	Calendar now = Calendar.getInstance();
	String format = "MM월 dd일(E)";
	SimpleDateFormat sdf = new SimpleDateFormat(format);

	MyFont font = new MyFont();
	Frame fPMenu;
	JLabel lMent, lMenu, lTime;
	JPanel pMenu, pMain, pTime;
	JTable tbFood;
	JScrollPane scrollT;
	JButton bPMenu, bEnterMenu;
	String id, today;
	int when;
	JRadioButton rbTime1, rbTime2, rbTime3, rbTime4, rbTime5;
	ConnectTest cont = new ConnectTest();


	PlusMenuFrameB(String id) {
		fPMenu = new Frame("식단추가");
		lMent = new JLabel();
		pTime = new JPanel();
		lTime = new JLabel();
		rbTime1 = new JRadioButton("아침");
		rbTime2 = new JRadioButton("오전간식");
		rbTime3 = new JRadioButton("점심");
		rbTime4 = new JRadioButton("오후간식");
		rbTime5 = new JRadioButton("저녁");
		
		pMenu = new JPanel();
		lMenu = new JLabel();
		
		
		ButtonGroup jb = new ButtonGroup();
		jb.add(rbTime1);
		jb.add(rbTime2);
		jb.add(rbTime3);
		jb.add(rbTime4);
		jb.add(rbTime5);
		
		bPMenu = new JButton("메뉴 추가");
		bEnterMenu = new JButton("식단 등록");

		this.id = id; // 전달받은 id 지역변수로 삽입
		openPlusMenuB();
	}

	void openPlusMenuB() {
		// 받은 id 제대로 출력됨 확인
//		System.out.println(id);
		fPMenu.setLayout(null);
		fPMenu.setBackground(Color.white);
		// 상단 멘트
		lMent.setBounds(30, 55, 400, 30);
		lMent.setText("|  오늘의 식단을 등록해주세요  |");
		lMent.setFont(font.f20);
		
		// 시간설정
		pTime.setBounds(0, 100, 500, 80);
		pTime.setLayout(null);
		pTime.setBackground(Color.white);
		lTime.setText("1. 식단시간을 선택해주세요");
		lTime.setBounds(30, 0, 200, 40);
		lTime.setFont(font.f17);
		rbTime1.setFont(font.f16);
		rbTime2.setFont(font.f16);
		rbTime3.setFont(font.f16);
		rbTime4.setFont(font.f16);
		rbTime5.setFont(font.f16);
		rbTime1.setBounds(30, 30, 100, 50);
		rbTime2.setBounds(110, 30, 100, 50);
		rbTime3.setBounds(210, 30, 100, 50);
		rbTime4.setBounds(290, 30, 100, 50);
		rbTime5.setBounds(390, 30, 100, 50);
		
		// 메뉴리스트 테이블
		pMenu.setBounds(0, 180, 500, 270);
		pMenu.setBackground(Color.white);
		pMenu.setLayout(null);
		lMenu.setBounds(30, 0, 500, 40);
		lMenu.setText("2. 등록할 식단을 선택해주세요");
		lMenu.setFont(font.f17);
		
		Vector<String> listField = new Vector<String>();
		listField.addElement("");
		DefaultTableModel model = new DefaultTableModel(listField, 0);
		String sql = "select name from food where id = '" + id + "'";
		ArrayList<String> list = cont.getList(sql);
		for (int i = 0; i<list.size(); i++) {
			Vector<String> vector = new Vector<String>();
			vector.add(list.get(i));
			model.addRow(vector);
		}
		tbFood = new JTable(model);
		scrollT = new JScrollPane(tbFood);
		scrollT.setBounds(30, 50, 440, 200);
		tbFood.setRowHeight(25);
		tbFood.setFont(font.f16);

	

		bPMenu.setFont(font.f16);
		bPMenu.setBounds(140, 460, 100, 40);
		bPMenu.addActionListener(new ActionListener() { // 메뉴 추가 버튼 리스너
			public void actionPerformed(ActionEvent arg0) {
				new PlusFoodFrame(id);
				fPMenu.setVisible(false);
			}
		});
		bEnterMenu.setFont(font.f16);
		bEnterMenu.setBounds(250, 460, 100, 40);
		bEnterMenu.addActionListener(new ActionListener() { // 식단 등록 버튼 리스너
			public void actionPerformed(ActionEvent arg0) {
				today = sdf.format(now.getTime());
				if(rbTime1.isSelected()) {
					when = 1;
				}else if(rbTime2.isSelected()) {
					when = 2;
				}else if(rbTime3.isSelected()) {
					when = 3;
				}else if(rbTime4.isSelected()) {
					when = 4;
				}else if(rbTime5.isSelected()){
					when = 5;
				}else {
					JOptionPane.showMessageDialog(null, "식단시간을 선택해주세요!", "잠깐만요!", JOptionPane.ERROR_MESSAGE);
				}
				int[] row = tbFood.getSelectedRows();
				String value[] = new String[row.length];
				for(int i = 0; i<row.length; i++) {
					value[i] = tbFood.getValueAt(row[i], 0).toString();
					String sql = "insert into todaymenu values('" + id + "','" + today + "','" + when + "','" + value[i]
							+ "')";
					ConnectTest cont = new ConnectTest();
					cont.plusMenu(sql);
				}
				HomeFrame hf = new HomeFrame(id);
				hf.setId(id);
				hf.homeOpen();
				fPMenu.setVisible(false);
			}
		});
		
		pTime.add(lTime);
		pTime.add(rbTime1);
		pTime.add(rbTime2);
		pTime.add(rbTime3);
		pTime.add(rbTime4);
		pTime.add(rbTime5);
		pMenu.add(lMenu);
		pMenu.add(scrollT);
		
		
		fPMenu.add(lMent);
		fPMenu.add(pTime);
		fPMenu.add(pMenu);
		fPMenu.add(bPMenu);
		fPMenu.add(bEnterMenu);
		
		

		fPMenu.addWindowListener(this);
		fPMenu.setSize(500, 540);
		fPMenu.setLocationRelativeTo(null);
		fPMenu.setResizable(false);
		fPMenu.setVisible(true);

	}

	public void windowClosing(WindowEvent e) { // 회원가입 창 종료하면 다시 로그인 창 오픈
		HomeFrame hf = new HomeFrame(id);
		hf.setId(id);
		hf.homeOpen();
		fPMenu.setVisible(false);

	}
}
