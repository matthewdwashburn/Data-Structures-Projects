package balancedSymbols;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

/**
 * Starting point for Project 6
 * 
 * @author Nathan Gossett
 * @author Matthew Washburn
 * @version Spring 2021
 *
 */

public class BalancedSymbolChecker {

	// stack declarations 
	
	Stack<Character> stack = new Stack<Character>();
	Stack<String> stringStack = new Stack<String>();
	/** String of text containing the code we're parsing */
	private String text;

	public BalancedSymbolChecker(String filename) {

		text = "";
		try {
			BufferedReader bir = new BufferedReader(new FileReader(filename));
			while (bir.ready()) {
				text += bir.readLine() + "\n";
			}

		} catch (FileNotFoundException e) {
			System.err.println("File not found: " + filename);
		} catch (IOException e) {
			System.err.println("Error reading from file: " + e.getMessage());
		}
		Balanced(text);
	}

	public void Balanced(String text) {
		// loop through string
		for (int i = 0; i < text.length(); i++) {
			System.out.print(text.charAt(i));
			// check for "//" comments
			if (i + 1 < text.length() && text.charAt(i) == '/' && text.charAt(i + 1) == '/') {
				stringStack.push(String.valueOf(text.charAt(i)) + String.valueOf(text.charAt(i + 1)));
				i++;

				// if found, loop until new line "\n"
				for (int j = i; j < text.length(); j++) {
					System.out.print(text.charAt(j));
					if (text.charAt(j) == '\n') {
						stringStack.pop();
						i = j;
						break;
					}
				}
				// check for "/*" comment
			} else if (i + 1 < text.length() && text.charAt(i) == '/' && text.charAt(i + 1) == '*') {
				stringStack.push(String.valueOf(text.charAt(i)) + String.valueOf(text.charAt(i + 1)));
				i++;
				// if found, loop until "*/"
				for (int j = i; j < text.length(); j++) {
					System.out.print(text.charAt(j));
					if (j + 1 < text.length() && (text.charAt(j) == '*' && text.charAt(j + 1) == '/')) {
						stringStack.pop();
						i = j;
						break;
					}
				}
				// check if all symbols are closed
				if (stringStack.isEmpty() == false) {
					System.out.print("ERROR: Unmatched opening characters: ");
					while (stringStack.isEmpty() == false) {
						System.out.print(stringStack.pop() + " ");
					}
					return;
				}
				// check for "
				else if (text.charAt(i) == '\"') {
					stringStack.push(String.valueOf(text.charAt(i)));
					i++;
					// if found, loop until another " symbol without \ before it
					for (int j = i; j < text.length(); j++) {
						System.out.print(text.charAt(j));
						if (text.charAt(j) == '\"' && text.charAt(j - 1) != '\\') {
							stringStack.pop();
							i = j;
							break;
						}
					}
				}
				// check if all symbols are closed again
				if (stringStack.isEmpty() == false) {
					System.out.print("ERROR: Unmatched opening characters: ");
					while (stringStack.isEmpty() == false) {
						System.out.print(stringStack.pop() + " ");
					}
					return;
				}

			// check for open symbols
			} else if (text.charAt(i) == '(') {
				stack.push(text.charAt(i));
			} else if (text.charAt(i) == '{') {
				stack.push(text.charAt(i));
			} else if (text.charAt(i) == '[') {
				stack.push(text.charAt(i));
			}
			// check for closed symbols and if the stack is empty
			else if (text.charAt(i) == ')') {
				if (!stack.empty() && stack.peek() == '(') {
					stack.pop();
				} else if (stack.empty()) {
					System.out.println("ERROR: Unmatched closing character: " + text.charAt(i));
					System.out.println("Not Balanced!");
					return;

				} else {
					System.out.println("ERROR: Cannot use " + text.charAt(i) + " to close " + stack.peek());
					System.out.println("Not Balanced!");
					return;
				}
			} else if (text.charAt(i) == '}') {
				if (!stack.empty() && stack.peek() == '{') {
					stack.pop();
				} else if (stack.empty()) {
					System.out.println("ERROR: Unmatched closing character: " + text.charAt(i));
					System.out.println("Not Balanced!");
					return;

				} else {
					System.out.println("ERROR: Cannot use " + text.charAt(i) + " to close " + stack.peek());
					System.out.println("Not Balanced!");
					return;
				}
			} else if (text.charAt(i) == ']') {
				if (!stack.empty() && stack.peek() == '[') {
					stack.pop();
				} else if (stack.empty()) {
					System.out.println("ERROR: Unmatched closing character: " + text.charAt(i));
					System.out.println("Not Balanced!");
					return;

				} else {
					System.out.println("ERROR: Cannot use " + text.charAt(i) + " to close " + stack.peek());
					System.out.println("Not Balanced!");
					return;
				}
			}

		} // end of loop 
		
		// check if stack is empty for extra opening symbols
		if (!(stack.isEmpty())) {
			System.out.println("ERROR: Unmatched opening characters: ");
			while (stack.empty() == false) {
				System.out.println(stack.pop() + " ");
			}
			return;
		}
		// print balanced if no errors are thrown
		System.out.println("Balanced!");
	}

	// run checking process
	public static void main(String[] args) {
		new BalancedSymbolChecker("ValidClass.java");
	}
}
