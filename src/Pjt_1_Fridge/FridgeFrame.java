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

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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

	Frame fFridge;
	JLabel lExplain;
	JList listFridge;
	JTable tFridge;
	RoundedButton bAdd, bUse, bRecipe;
	String id;
	ConnectTest con = new ConnectTest();
	int[] count;

	String[] fridge;

	MyFont font = new MyFont();

	public FridgeFrame(String id) {
		this.id = id;
//		System.out.println(id); // 아이디 확인

		fFridge = new Frame("나의 냉장고");
		lExplain = new JLabel("냉장고의 현재 재고상태입니다 :)");
		bAdd = new RoundedButton("재고 추가");
		bUse = new RoundedButton("재고 사용");
		bRecipe = new RoundedButton("추천레시피");

		fFridge.setLayout(null);
		lExplain.setBounds(35, 40, 500, 30);
		lExplain.setFont(font.f18);

		String sql = String.format("select * from myfridge where id = '%s'", id);
		Vector<String> listField = new Vector<String>();
		listField.addElement("식재료");
		listField.addElement("남은양");
		listField.addElement("유통기한");

		DefaultTableModel model = new DefaultTableModel(listField, 0) {
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
		
//		tFridge.setSelectionBackground(Color.blue);
//		tFridge.setSelectionForeground(Color.white);
		
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
	}

	public void windowClosing(WindowEvent e) {
		fFridge.setVisible(false);
	}
}
