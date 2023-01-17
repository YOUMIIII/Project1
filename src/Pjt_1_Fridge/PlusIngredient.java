package Pjt_1_Fridge;

import java.awt.Choice;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Pjt_1_ConnectServer.ConnectTest;
import Pjt_1_Home.MyFont;


public class PlusIngredient extends WindowAdapter implements ActionListener{
	MyFont font = new MyFont();
	
	Frame fPlusIn;
	String id, beforeDate;
	JLabel lPlus, lQuantity, lBefore;
	JTextField tPlus, tQuantity, tBeforeY;
	Choice cMonth, cDay;
	JButton bPlus;
	
	
	PlusIngredient(String id){
		this.id = id;
		fPlusIn = new Frame("재고추가");
		lPlus = new JLabel("추가할 재고");
		lQuantity = new JLabel("재고량");
		lBefore = new JLabel("유통기한");
		tPlus = new JTextField(15);
		tQuantity = new JTextField(15);
		tBeforeY = new JTextField(4);
		cMonth = new Choice();
		for (int i = 1; i <= 12; i++) {
			cMonth.add(i + "");
		}
		cDay = new Choice();
		bPlus = new JButton("추가하기");
		openPlus();
	}
	
	public void openPlus(){
		System.out.println(id);
		
		fPlusIn.setLayout(null);
		lPlus.setBounds(35, 50, 300, 40);
		lPlus.setFont(font.f17);
		tPlus.setBounds(30, 80, 300, 33);
		lQuantity.setBounds(35, 120, 300, 40);
		lQuantity.setFont(font.f17);
		tQuantity.setBounds(30, 150, 300, 33);
		lBefore.setBounds(35, 190, 300, 40);
		lBefore.setFont(font.f17);
		tBeforeY.setBounds(30, 225, 80, 33);
		cMonth.setBounds(110, 225, 80, 40);
		cMonth.setFont(font.f17);
		cMonth.addItemListener(new ItemListener() { // choice 월 부분에 listener 삽입
			@Override
			public void itemStateChanged(ItemEvent e) {
				try {
					Calendar before = Calendar.getInstance(); // Calendar클래스 인스턴스
					if(tBeforeY.getText()==null) {
						beforeDate = null;
					}else {
						before.set(Calendar.YEAR, Integer.parseInt(tBeforeY.getText())); // tYear에 입력한 년도를 calendar.year에 입력
						before.set(Calendar.MONTH, Integer.parseInt(cMonth.getSelectedItem())); // choice 선택한 월을
						// calendar.month에 입력
						for (int i = 1; i <= before.getActualMaximum(Calendar.DATE); i++) { // calendar의 제일 마지막날을 구해서 반복문으로 일
							// choice 작성
							cDay.add(i + "");
						}
						beforeDate = tBeforeY.getText() + "-" + cMonth.getSelectedItem() + "-" + cDay.getSelectedItem();
					}
				} catch (Exception ee) { // 년도를 입력하지 않고 바로 월이나 일을 선택할 경우
					JOptionPane.showMessageDialog(null, "년도를 먼저 입력해야합니다!", "잠깐만요!", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		cDay.setBounds(190, 225, 80, 40);
		cDay.setFont(font.f17);
		bPlus.setBounds(130, 290, 100, 40);
		bPlus.setFont(font.f15);
		bPlus.addActionListener(this);
		
		
		
		fPlusIn.add(lPlus);
		fPlusIn.add(tPlus);
		fPlusIn.add(lQuantity);
		fPlusIn.add(tQuantity);
		fPlusIn.add(lBefore);
		fPlusIn.add(tBeforeY);
		fPlusIn.add(cMonth);
		fPlusIn.add(cDay);
		fPlusIn.add(bPlus);
		fPlusIn.setSize(360, 360);
		fPlusIn.addWindowListener(this);
		fPlusIn.setLocationRelativeTo(null);
		fPlusIn.setResizable(false);
		fPlusIn.setVisible(true);
	}
	
	public void windowClosing(WindowEvent e) { 
		fPlusIn.setVisible(false);
		new FridgeFrame(id);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("추가하기")) {
			String ingredient = tPlus.getText();
			Double quantity = Double.parseDouble(tQuantity.getText());
			String sql;
			if(beforeDate != null) {
				sql = "insert into myfridge values('" + id + "','" + ingredient + "','" + quantity + "','" + beforeDate + "')";
			}else {
				beforeDate = "null";
				sql = "insert into myfridge values('" + id + "','" + ingredient + "','" + quantity + "'," + beforeDate + ")";
			}
			ConnectTest cont = new ConnectTest();
			cont.insertSql(sql);
			new FridgeFrame(id);
			fPlusIn.setVisible(false);
		}
		
	}
}
