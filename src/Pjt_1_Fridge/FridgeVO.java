package Pjt_1_Fridge;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class FridgeVO {
	Calendar now = Calendar.getInstance();
	String format = "YYYY-MM-dd";
	SimpleDateFormat sdf = new SimpleDateFormat(format);
	String today = sdf.format(now.getTime());

	String id;
	String ingredient;
	String quantity;
	String date;
	String beforedate;
	String n = null;

	public FridgeVO(String id, String ingredient, String quantity, String date) {
		this.id = id;
		this.ingredient = ingredient;
		this.quantity = quantity;
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public String getIngredient() {
		return ingredient;
	}

	public String getQuantity() {
		return quantity;
	}

	public String getDate() {
		if (date==null) {
			return n;
		} else {
			String beforedate = date.substring(0, 10);
			return beforedate;
		}
	}
}
