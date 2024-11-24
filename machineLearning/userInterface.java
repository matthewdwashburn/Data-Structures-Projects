package machineLearning;

import java.util.Scanner;
/**
 * @author Matthew Washburn
 * @version 1
 */
public class userInterface {
	public static void main(String[] args) {
		System.out.println("Think of an animal. Let's see if I can guess it!");
		System.out.println("Can your animal fly? (Y/N)");
		Scanner response = new Scanner(System.in);

		String answer = response.nextLine();
		System.out.println(answer);
	}
}
