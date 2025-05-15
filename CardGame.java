//package linkedLists;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
import java.util.Scanner;

public class CardGame {
	
	private static LinkList cardList = new LinkList();  // make list

	public static void main(String[] args) {

        String fileName = "cards.txt"; // Ensure the file is in the working directory or specify the full path

        // Read the file and create Card objects
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line into components
                String[] details = line.split(","); // Assuming comma-separated values
                if (details.length == 4) {
                    // Parse card details
                    String suit = details[0].trim();
                    String name = details[1].trim();
                    int value = Integer.parseInt(details[2].trim());
                    String pic = details[3].trim();

                    // Create a new Card object
                    Card card = new Card(suit, name, value, pic);

                    // Add the Card object to the list
                    cardList.add(card);
                } else {
                    System.err.println("Invalid line format: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        // Print the loaded cards
        System.out.println("Cards loaded:");
        cardList.displayList();
		
		Card[] playerHand = new Card[5];
		for(int i = 0; i < playerHand.length; i++)
			playerHand[i] = cardList.getFirst();
		
		System.out.println("players hand");
		for(int i = 0; i < playerHand.length; i++)
			System.out.println(playerHand[i]);
		
		System.out.println();
		System.out.println("the deck");
		cardList.displayList();

        // *** GAME IMPLEMENTATION STARTS HERE ***
        System.out.println("\n=== WAR CARD GAME ===");
        System.out.println("Each player draws a card. Higher card wins!");
        playWarGame();

	}//end main
    
    // Simple War game implementation
    private static void playWarGame() {
        Scanner scanner = new Scanner(System.in);
        int playerScore = 0;
        int computerScore = 0;
        int rounds = 0;
        
        while (true) {
            System.out.print("\nPress Enter to play a round or 'q' to quit: ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("q")) break;
            
            // Draw cards
            Card playerCard = cardList.getFirst();
            Card computerCard = cardList.getFirst();
            
            // Check if we're out of cards
            if (playerCard == null || computerCard == null) {
                System.out.println("No more cards left. Game over!");
                break;
            }
            
            rounds++;
            System.out.println("\nRound " + rounds + ":");
            System.out.println("You drew: " + playerCard);
            System.out.println("Computer drew: " + computerCard);
            
            // Compare cards
            if (playerCard.getCardValue() > computerCard.getCardValue()) {
                System.out.println("You win this round!");
                playerScore++;
            } else if (computerCard.getCardValue() > playerCard.getCardValue()) {
                System.out.println("Computer wins this round!");
                computerScore++;
            } else {
                System.out.println("It's a tie!");
            }
            
            System.out.println("Score - You: " + playerScore + " | Computer: " + computerScore);
        }
        
        // Final results
        System.out.println("\n=== FINAL SCORE ===");
        System.out.println("Rounds played: " + rounds);
        System.out.println("Your score: " + playerScore);
        System.out.println("Computer's score: " + computerScore);
        
        if (playerScore > computerScore) {
            System.out.println("You win the game!");
        } else if (computerScore > playerScore) {
            System.out.println("Computer wins the game!");
        } else {
            System.out.println("The game ends in a tie!");
        }
        
        scanner.close();
    }
}//end class
