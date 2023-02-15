package Pjt_1_Fridge;

import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import Pjt_1_ConnectServer.ConnectTest;
import Pjt_1_Home.MyFont;
import Pjt_1_Home.RoundedButton;

public class FridgeFrame extends WindowAdapter implements ActionListener {
	Calendar now = Calendar.getInstance();
	String format = "YYYY-MM-dd";
	SimpleDateFormat sdf = new SimpleDateFormat(format);
	String today = sdf.format(now.getTime());

	Frame fFridge, fRecipe, fShowRecipe;
	JLabel lExplain, lExRecipe,lExRecipe2, lExClick;
	JTable tFridge, tRecipe;
	JTextField tInput;
	JTextArea taRecipe;
	JScrollPane sRecipe;
	
	RoundedButton bAdd, bUse, bRecipe, bSearch, bShow;
	String id;
	ConnectTest con = new ConnectTest();

//	String[] fridge;

	MyFont font = new MyFont();
	

	public FridgeFrame(String id) {
		this.id = id;
//		System.out.println(id); // 아이디 확인

		fFridge = new Frame("나의 냉장고");
		lExplain = new JLabel("냉장고의 현재 재고상태입니다");
		bAdd = new RoundedButton("재고 추가");
		bUse = new RoundedButton("재고 사용");
		bRecipe = new RoundedButton("추천레시피");
		fRecipe = new Frame("추천레시피");
		lExRecipe = new JLabel("|   레시피 검색   |");
		lExRecipe2 = new JLabel("사용할 재료");
		tInput = new JTextField();
		lExClick = new JLabel("👨🏻‍🍳 원하는 메뉴를 찾아 레시피를 따라해보세요");
		bSearch = new RoundedButton("검색");
		bShow = new RoundedButton("레시피보기");
		fShowRecipe = new Frame();
		
		taRecipe = new JTextArea(400, 400);
		
		
		
		fFridge.setLayout(null);
		lExplain.setBounds(35, 40, 500, 30);
		lExplain.setFont(font.f18);

		String sql = String.format("select * from myfridge where id = '%s'", id);
		Vector<String> listField = new Vector<String>();
		listField.addElement("식재료");
		listField.addElement("남은양");
		listField.addElement("유통기한");

		DefaultTableModel model = new DefaultTableModel(listField, 0) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1812600666358922399L;

			public boolean isCellEditable(int r, int c) {
				return (c != 0) ? true : false;
			}
		};

		ArrayList<FridgeVO> list = con.getFridge(sql);
		for (FridgeVO data : list) {
			Vector<String> vector = new Vector<String>();
			vector.add(data.getIngredient());
			vector.add(data.getQuantity());
			vector.add(data.getDate());
			model.addRow(vector);
		}
		//
		tFridge = new JTable(model) {
			private static final long serialVersionUID = 1L;

			public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
				Component comp = super.prepareRenderer(renderer, row, col);
				Object value = getModel().getValueAt(row, col);
				Color red = new Color(255, 135, 135);
				if (value == null) {
//					
				} else if (value.toString().length() > 9) {
					int date1 = Integer.parseInt(value.toString().substring(5, 7));
					int date2 = Integer.parseInt(today.substring(5, 7));
					int date3 = Integer.parseInt(value.toString().substring(8, 10));
					int date4 = Integer.parseInt(today.substring(8, 10));
					if (date1 < date2) {
						comp.setBackground(red);
					} else if (date1 == date2) {
						if (date3 < date4) {
							comp.setBackground(red);
						}
					} 
				} else if(value.toString().length()>0) {
					comp.setBackground(gridColor);
				}
				return comp;
			}// Override
		};
		

		tFridge.setUpdateSelectionOnSort(true);
		JScrollPane scroll = new JScrollPane(tFridge);

		scroll.setBounds(35, 80, 300, 400);
		scroll.setFont(font.f18);
		
		bAdd.setBounds(365, 180, 100, 30);
		bAdd.setFont(font.f16);
		bAdd.addActionListener(this);
		bUse.setBounds(365, 230, 100, 30);
		bUse.setFont(font.f16);
		bUse.addActionListener(this);
		bRecipe.setBounds(365, 280, 100, 30);
		bRecipe.setFont(font.f16);
		bRecipe.addActionListener(this);
		
		
		Vector<String> listField2 = new Vector<String>();
		listField2.addElement("");
		DefaultTableModel model2 = new DefaultTableModel(listField2, 0);
		tRecipe = new JTable(model2);
		sRecipe = new JScrollPane(tRecipe);
		sRecipe.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		sRecipe.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		fRecipe.setLayout(null);
		fRecipe.setBackground(Color.white);
		lExRecipe.setBounds(40, 45, 400, 30);		
		lExRecipe.setFont(font.f20);
		lExRecipe2.setBounds(43, 95, 400, 20);
		lExRecipe2.setFont(font.f17);
		tInput.setBounds(40, 120, 190, 35);
		lExClick.setBounds(40, 170, 320, 30);
		lExClick.setFont(font.f16);
		sRecipe.setBounds(40, 210, 310, 200);
		tRecipe.setFont(font.f17);
		tRecipe.setRowHeight(23);
		bSearch.setBounds(260, 123, 90, 30);
		bSearch.setFont(font.f15);
		bSearch.addActionListener(this);
		bShow.setBounds(155, 440, 90, 30);
		bShow.setFont(font.f15);
		bShow.addActionListener(this);
		
		fRecipe.add(lExRecipe);
		fRecipe.add(lExRecipe2);
		fRecipe.add(tInput);
		fRecipe.add(sRecipe);
		fRecipe.add(bSearch);
		fRecipe.add(lExClick);
		fRecipe.add(bShow);
		fRecipe.setSize(400, 500);
		fRecipe.setLocationRelativeTo(null);
		fRecipe.addWindowListener(this);
		
		
		fFridge.add(lExplain);
		fFridge.add(scroll);
		fFridge.add(bAdd);
		fFridge.add(bUse);
		fFridge.add(bRecipe);
		fFridge.setSize(500, 520);
		fFridge.setLocationRelativeTo(null);
		fFridge.setResizable(false);
		fFridge.addWindowListener(this);
		fFridge.setVisible(true);
		
		fShowRecipe.add(taRecipe);
		fShowRecipe.addWindowListener(this);
		fShowRecipe.setSize(400,450);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("재고 추가")) {
			new PlusIngredient(id);
			fFridge.setVisible(false);
		}
		if (e.getActionCommand().equals("재고 사용")) {
			try {
				String use = (String) tFridge.getValueAt(tFridge.getSelectedRow(), 0);
				new UseIngredient(id, use);
				fFridge.setVisible(false);
			}catch(Exception ee) {
//				System.out.println(ee);
				JOptionPane.showMessageDialog(null, "재고량을 변경할 재고를 먼저 선택해주세요!", "잠깐만요!", JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource().equals(bRecipe)) {
			fRecipe.setVisible(true);
		}
		if(e.getSource().equals(bSearch)) {
			DefaultTableModel model2 = (DefaultTableModel) tRecipe.getModel();
			model2.setRowCount(0);
			String sql = "select distinct name from recipe where info like '%" + tInput.getText() + "%'";
			ArrayList<String> menu = con.bringMenuList(sql);
			if (menu.size() > 0) {
				for (int i = 0; i < menu.size(); i++) {
//					System.out.println(menu.get(i));
					Vector<String> vector = new Vector<String>();
					vector.add(menu.get(i));
					model2.addRow(vector);
				}
			} else{
				JOptionPane.showMessageDialog(null, "해당메뉴에 대한 자료가 없습니다! 메뉴를 직접입력해주세요!", "자료없음",
						JOptionPane.ERROR_MESSAGE);

			}
			
		}
		if(e.getSource().equals(bShow)) {
			String show = tRecipe.getValueAt(tRecipe.getSelectedRow(), 0).toString();
			String sql = "Select distinct * from recipe where name = '" + show + "'";
			String recipe = con.getRecipe(sql);
			taRecipe.setText(recipe);
			taRecipe.setLineWrap(true);
			taRecipe.setFont(font.f16);
			fShowRecipe.setTitle(show + "레시피");
			fShowRecipe.setLocationRelativeTo(null);	
			fShowRecipe.setVisible(true);
		}
	}

	public void windowClosing(WindowEvent e) {
		if(e.getSource().equals(fFridge)) {
			fFridge.setVisible(false);
		}
		if(e.getSource().equals(fRecipe)) {
			fRecipe.setVisible(false);
		}
		if(e.getSource().equals(fShowRecipe)) {
			fShowRecipe.setVisible(false);
		}
	}
}
