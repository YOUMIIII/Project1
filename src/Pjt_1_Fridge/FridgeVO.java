package Pjt_1_Fridge;

public class FridgeVO {
	String id;
	String ingredient;
	int quantity;
	String date;
	
	public FridgeVO(String id, String ingredient, int quantity, String date){
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
		return date;
	}
}
