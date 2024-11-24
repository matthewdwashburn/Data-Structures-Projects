package datafinal;

import java.util.Stack;

/**
 * COS212 Final Project
 * 
 * Suitcase holds a stack of type Item
 * 
 * @author Matthew Washburn
 * @version 2
 */

public class Suitcase {
	//--------------Attributes--------------
	private Stack<Item> suitcaseStack;

	//--------------Constructor--------------
	public Suitcase() {
		suitcaseStack = new Stack<>();
	}
	
	//--------------Setter and Getter--------------
	public void setStack(Stack<Item> stack) {
		suitcaseStack = stack;
	}
	
	public Stack<Item> getStack(){
		return suitcaseStack;
	}
}