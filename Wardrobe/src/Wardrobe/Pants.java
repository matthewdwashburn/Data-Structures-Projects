package Wardrobe;

import Wardrobe.Apparel.Condition;
import Wardrobe.Shirt.Size;
/**
 * @author Matthew Washburn
 * @version 1
 */
public class Pants extends Apparel {
	//Variables
	private int waistMeasurement;
	private int inseamMeasurement;
	//Constructor
	public Pants(String color, double price, Condition condition, 
			int waistMeasurement, int inseamMeasurement) throws IllegalArgumentException{
		super(color, price, condition);
		setwaistMeasurement(waistMeasurement);
		setinseamMeasurement(inseamMeasurement);
	}
	//Getters
	public int getwaistMeasurement() {
		return waistMeasurement; 
		}
	public int getinseamMeasurement() {
		return inseamMeasurement; 
	}
	//Setters
	public void setwaistMeasurement(int waistMeasurement) throws IllegalArgumentException {
		if (waistMeasurement < 0 || waistMeasurement == 0) {
			throw new IllegalArgumentException("Waist Measurement must be Positive!");
		} else {
			this.waistMeasurement = waistMeasurement;
		}
	}
	public void setinseamMeasurement(int inseamMeasurement) throws IllegalArgumentException {
		if (inseamMeasurement < 0 || inseamMeasurement == 0) {
			throw new IllegalArgumentException("Inseam Measurement must be Positive!");
		}
		else {
			this.inseamMeasurement = inseamMeasurement; 

		}
	}
	//String Override
	public String toString() {
		String pantsinfo = "This " + getColor() + " pair of pants has a " 
				+ waistMeasurement + " inch waist and a " + inseamMeasurement + " inch inseam. " +
				"It costs " + getPrice() + " and is in " + getCondition() + " condition.";
		return pantsinfo;
	}

}
