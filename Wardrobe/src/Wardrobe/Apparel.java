package Wardrobe;
/**
 * @author Matthew Washburn
 * @version 1
 */
public abstract class Apparel implements Comparable<Apparel>
{
		private String color;
		private double price;
		private Condition condition;
		/** Allowed Conditions of the clothing */
		enum Condition{
			NEW("new"), 
			GOOD("good"), 
			POOR("poor"), 
			TRASHED("trashed");
			private final String mCond;
			Condition(String cond){
				mCond = cond;
			}
			public String toString(){ return mCond; }
		};
		//Constructor	
		public Apparel(String color, double price, Condition condition) throws IllegalArgumentException {
			setPrice(price);
			setColor(color);
			setCondition(condition);
		}
		//Setters
		public void setColor(String color) throws IllegalArgumentException {
			//Don't allow nulls
			if (color == null || color == "") {
				throw new IllegalArgumentException("Can't be Null or Empty!");
			}
			else { 
				this.color = color; 
				}
		}
		
		public void setPrice(double price) throws IllegalArgumentException {
			//Don't allow negatives
			if (price < 0) {
				throw new IllegalArgumentException("Can't be Negative!");
			}
			else { 
				this.price = price;
			}
		}

	    public void setCondition(Condition condition) throws IllegalArgumentException {
	        if (condition == null) {
	        	throw new IllegalArgumentException("Can't be Empty!");
	        }
	        else {
	        	 this.condition = condition;
	        }
	    }
	    //Getters
	    public Condition getCondition() {
	        return condition;
	    }
		
		public String getColor() {
			return color; 
		}
		public double getPrice() {
			return price; 
		}
		@Override
		// Allow comparison using sort() in WardrobeDriver
		public int compareTo(Apparel other){
		    	int conditionComparison = this.condition.compareTo(other.condition);
		    	if (conditionComparison != 0) {
			    	// if conditions are different, return the result of the comparison
		    		return conditionComparison;
		    	}
		    	// if conditions are the same, return based on price
		    	else {
		    		if (this.price < other.price) {
		    			return -1; //this apparel is cheaper
		    		}
		    		else if (this.price > other.price) {
		    			return 1; // this apparel is more expensive
		    		}
		    		else { 
		    			return 0; // this apparel is the same price
		    			}
		    		}
		}
}

