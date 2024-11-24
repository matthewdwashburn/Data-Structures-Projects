package Wardrobe;
/**
 * @author Matthew Washburn
 * @version 1
 */
public class Shirt extends Apparel {
	//Variables
	public enum Size{S, M, L, XL};
	private Size size;
	private String text;
	//Constructor
	public Shirt(String color, double price, Condition condition, Size size, String text) throws IllegalArgumentException {
		super(color, price, condition);
		setSize(size);
		setText(text);
	}
	// Getters
	public Size getSize() {
		return size;
	}
	public String getText() {
		return text;
	}
	// Setters
	public void setSize(Size size) throws IllegalArgumentException {
		if (size == null) {
			throw new IllegalArgumentException("Size can't be Empty!");
		}
		else {
			this.size = size; 

		}
	}
	public void setText(String text) {
		this.text = text; 
	}
	// String Override
	public String toString() {
		String shirtinfo = "This " + getColor() + " shirt is size " + size + ". It cost " + getPrice() + " and is in " + getCondition() + 
		" condition.";
		if(text != null && !text.isEmpty()) {
			shirtinfo += " It contain the text " + "\"" + text + "\"" + ".";
		}
		return shirtinfo;
	}


}

