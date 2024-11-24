package machineLearning;

import java.util.Scanner;
/**
 * @author Matthew Washburn
 * @version 1
 */
public class AnimalGuessingGame {
    private static class AnimalNode {
        String data;
        AnimalNode yesNode;
        AnimalNode noNode;

        AnimalNode(String data) {
            this.data = data;
        } // Animal node method
    } // Animal node class

    private AnimalNode root; //root of the tree
    private Scanner scanner; //user input scanner

    public AnimalGuessingGame() {
        root = new AnimalNode("Can your animal fly?");
        root.yesNode = new AnimalNode("eagle");
        root.noNode = new AnimalNode("pig");
        scanner = new Scanner(System.in);
    } // Tree setup with root and left and right for yes and no

    public void playGame() {
        System.out.println("Think of an animal. Let's see if I can guess it!");
        while (true) {
            root = playRound(root);
            System.out.print("Let's play again! ");
        }
    } // repeat the game forever

    private AnimalNode playRound(AnimalNode current) {
        if (current.yesNode == null && current.noNode == null) { //when node is a leaf
            System.out.print("You were thinking of: " + current.data + ". Am I correct? (Y/N): "); //ask if last animal is correct
            String response = scanner.nextLine().trim().toLowerCase();
            if (response.equals("y")) {
                System.out.println("Hurray! I got it!"); //correct answer
            } else {
                System.out.print("Sigh. What animal were you thinking of? "); 
                String newAnimal = scanner.nextLine().trim();
                System.out.print("What is a question that has an answer of yes for " + current.data + " and no for " + newAnimal + "? ");
                String newQuestion = scanner.nextLine().trim(); //grab new question
                return addQuestion(current, newQuestion, current.data, newAnimal); //add new data to tree
            }
        } else { // when node is not a leaf
            System.out.print(current.data + " (Y/N): ");
            String response = scanner.nextLine().trim().toLowerCase();
            if (response.equals("y")) {
                current.yesNode = playRound(current.yesNode); //yes answer
            } else {
                current.noNode = playRound(current.noNode); //no answer
            }
        }
        return current;
    } //play round

    private AnimalNode addQuestion(AnimalNode parent, String question, String yesAnswer, String noAnswer) { 
        AnimalNode newNode = new AnimalNode(question);
        newNode.yesNode = new AnimalNode(yesAnswer);
        newNode.noNode = new AnimalNode(noAnswer);
        return newNode;
    } //add question 

    public static void main(String[] args) {
        AnimalGuessingGame game = new AnimalGuessingGame();
        game.playGame();
    } // main
} // animal guessing game
