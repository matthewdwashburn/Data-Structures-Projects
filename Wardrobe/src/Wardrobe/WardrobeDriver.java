package Wardrobe;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * COS212 Project 2
 * 
 * 
 * @author Albing, based on N. Gossett
 * @author Matthew Washburn
 * @version 24
 */

public class
WardrobeDriver
{
	/** Store the list of clothes in the wardrobe */
	private ArrayList<Apparel> closet;
	
	/**
	 * 
	 */
	public
	WardrobeDriver()
	{
		super();
		closet = new ArrayList<Apparel>();

	} // constructor

	/**
	 * A method to hard-code some example clothes
	 */
	private void
	populate()
	{
	    Scanner in = new Scanner(System.in);
	    Shirt myShirt = null;
	    while (true) {
	    	try {
	    	System.out.print("What color is the shirt?");
	    	String color = in.next();
	    	System.out.print("How much is the shirt?");
	    	double price = in.nextDouble();
	    	myShirt = new Shirt(color,price, Apparel.Condition.GOOD, Shirt.Size.M, "YOLO");
	    	closet.add(myShirt);
	    	break;
	    	}
	    	catch (IllegalArgumentException iae) {
	    		System.out.println(iae.getMessage());
	    		System.out.println("Try Again!");
	    	}
	    	catch (InputMismatchException jie) {
		    	System.out.println("Bad Input. Try again!");
		    		in.nextLine();
	    	}
	    }
	    
	 
	    closet.add(new Shirt("white", 9.99, Apparel.Condition.POOR, Shirt.Size.M, null));
	    closet.add(new Shirt("green", 99.99, Apparel.Condition.POOR, Shirt.Size.M, null));
	    closet.add(new Pants("blue", 29.99, Apparel.Condition.NEW, 30, 32));
	    closet.add(new Pants("black", 39.99, Apparel.Condition.TRASHED, 44, 35));
	    closet.add(new Pants("khaki", 42.99, Apparel.Condition.GOOD, 40, 38));
		
	    /* 
	     * Your Apparel class should implement Comparable<Apparel> interface,
	     * allowing Apparel objects to be compared
	     * which then makes you compatible with a call to sort()
	     */
	     Collections.sort(closet);

	} // populate
	
	/**
	 * Override the default toString to print a list of the clothes
	 * in the wardrobe
	 */
	public String toString(){
		String myString = "";
		for(Apparel a : closet){
			myString += a + "\n";
		}
		return myString;

	} // toString

	/**
	 * 
	 * @param args
	 */
	public static void
	main(String [] args)
	{
	    WardrobeDriver myWardrobe = new WardrobeDriver();
	    myWardrobe.populate();
	    System.out.print(myWardrobe);
		
	} // main
	
} // class WardrobeDriver
