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
	int quantity;
	String date;

	public FridgeVO(String id, String ingredient, int quantity, String date) {
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

	public int getQuantity() {
		return quantity;
	}

	public String getDate() {
		if ((int)(date.charAt(6)) < (int)(today.charAt(6))) {
			return "** "+ date;
		} else if((int)(date.charAt(8)) <= (int)(today.charAt(8))) {
			if ((int)(date.charAt(9)) < (int)(today.charAt(9))) {
				return "** " + date;
			}else {
				return date;
			}
		} else {
			return date;
		}
	}
}
