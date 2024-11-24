package expressionTrees;


import java.util.Scanner;


/**
 * A basic Expressions tree class
 * @author Gossett/Albing
 * @author Matthew Washburn
 * @version 23.11
 *
 */

public class 
ExpressionTree
{
	
	/**
	 * Basic Binary Tree Node class. 
	 *
	 * @param <T>
	 */
	private class 
	BTNode<T>
	{
	    public T data;
	    public BTNode<T> left;
	    public BTNode<T> right;
		
	    public BTNode(T data, BTNode<T> left, BTNode<T> right){
		this.data = data;
		this.left = left;
		this.right = right;
	    }
		
	} // class BTNode

	// ---------------------------------------------------------
	
	/** Root of tree.  If tree is empty, will be equal to null */
	private BTNode<String> root;
	
	
	/**
	 * Construct a tree from the given expression
	 * @param expression: infix expression with parentheses around all sub expressions 
	 *                    and spaces between all tokens
	 * @throws RuntimeException For malformed infix expressions
	 */
	public 
	ExpressionTree(String expression) 
	    throws RuntimeException
	{
	    Scanner in = new Scanner(expression);
	    root = buildTree(in);
	    if(in.hasNext()){
		throw new RuntimeException("Symbols encountered after end of expression: " +
						 in.next());
	    }
		
	} // ExpressionTree constructor
	
	/**
	 * Build left and right sub-trees recursively, operator in parent node
	 * @param in - A scanner advanced to the appropriate point in the expression
	 * @return  The root node of the current (sub) tree
	 * @throws  RuntimeException for illegal expressions
	 */
	private BTNode<String> buildTree(Scanner in) throws RuntimeException {
	    BTNode<String> root = new BTNode<String>(null, null, null);
	    if (in.hasNextDouble()) {
	    	root.data = in.next();
	    	return root;
	    }
	    if (openParen(in)) {
	    	root.left = buildTree(in);
	    	if (closeParen(in)) {
	    		return root.left;
	    	}
	    	root.data = in.next();
	    	if (! isValidOperator(root.data)) {
	    		throw new RuntimeException("Invalid Operator");
	    	}
	    	
	    	root.right = buildTree(in);
	    	if (! closeParen(in)) {
	    		throw new RuntimeException("closing paren not found");
	    	}
	    	
	    	return root;
	    }
	    return root;

	} // buildTree
	
	/**
	 * Detect and consume (if present) a closing parenthesis from the input expression
	 * @param in A scanner that has been parsed to the appropriate point in the exception
	 * @return true if the closing paren was detected and consumed, false otherwise
	 */
	private boolean 
	closeParen(Scanner in)
	{
	    if (in.hasNext("\\)")) {
	    	in.next();
	    	return true;
	    }
	    return false;

	} // closeParen
	
	/**
	 * Detect and consume (if present) an opening parenthesis from the input expression
	 * @param in A scanner that has been parsed to the appropriate point in the exception
	 * @return true if the opening paren was detected and consumed, false otherwise
	 */
	private boolean
	openParen(Scanner in)
	{
		if (in.hasNext("\\(")) {
	    	in.next();
	    	return true;
	    }
	    return false;

	}  // openParen
	
	/**
	 * Check to see if symbol contains +, -, *, or /
	 * @param symbol 
	 * @return true if symbol is one of the four allowed operators, false otherwise
	 */
	private boolean
	isValidOperator(String symbol)
	{
	    if (symbol.equals("+") || symbol.equals("-") || symbol.equals("*") || symbol.equals("/")) {
	    	return true;
	    }
	    return false;

	} // isValidOperator
	
	// ----------------------------------------------------------------------

	public static void
	main(String[] args)
	{
	    ExpressionTree et = new ExpressionTree("( ( 2 + ( 4 * 6 ) ) - ( 8 / 2 ) ) ");
	    // System.out.println(et + "= " + et.evaluateTree());
	    System.out.println(et.toStringPostFix());
	    System.out.println(et.toStringInFix());
	} // main

	@Override
	public String toString(){
		return toStringInFix();
	}
	
	/** 
	 * HOMEWORK
	 * Infix = in-order traversal
	 * @return String representation of tree
	 */
	public String 
	toStringInFix()
	{
	    return inFixIt(root);
	    
	}  // toStringInFix
	
	private String inFixIt(BTNode<String> root) {
		if (root.left == null) {
			return root.data + " ";
		}
		return "( " + inFixIt(root.left) + root.data + " " + inFixIt(root.right) + ") ";

	}
	
	/** 
	 * HOMEWORK
	 * @return PostFix representation of tree
	 */
	public String
	toStringPostFix()
	{
	    return postFixIt(root);
	   
	} // toStringPostFix()
	
	private String postFixIt(BTNode<String>  root) {
		if (root.left == null) {
			return root.data + " ";
		}
		return postFixIt(root.left) + postFixIt(root.right) + root.data + " ";
	}
	
	/**
	 * HOMEWORK
	 * @return value that this tree evaluates to
	 */
	public double
	evaluateTree()
	{
	  return evaluateIt(root);
	} // evaluateTree
	
	public double evaluateIt(BTNode<String> root) {
		  if(root.left == null)
		    {
		    return Double.valueOf(root.data);
		    }
			return(applyOperator(root.data, evaluateIt(root.left), evaluateIt(root.right)));
		  }
	/**
	 * HOMEWORK
	 * @param operator 
	 * @param left left operand
	 * @param right right operand
	 * @return result of applying operator to left and right operands
	 * @throws RuntimeException for anything other than +, -, *, /
	 */
	private double
	applyOperator(String operator, double left, double right)
		throws RuntimeException
	{
		switch(operator) {
		  case "/":
		    return left / right;
		  case "*":
			  return left * right;
		  case "+":  
			  return left + right;
		  case "-":  
			  return left - right;
		  default:
			  return 0;
		}
	} // applyOperator

} // class ExpressionTree