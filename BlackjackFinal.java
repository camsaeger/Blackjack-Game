/*
* CS 120 Final
* Cam Saeger
* Ayden Vang
* 5/8/2026
* Blackjack
*/



import java.util.Random;
import java.util.Scanner;

public class BlackjackFinal {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        
        // Welcome message and entering starting money
        
        System.out.println("Welcome to the Blackjack Table!");
        System.out.print("How much money do you want to bring to the table? ");
        int money = in.nextInt();
        // Validating starting money
        while(money < 1) {
            System.out.print("Starting balance must be at least 1. Enter starting balance again: ");
            money = in.nextInt();
        }
        
        // Loop for game
        while (money>0) {
            System.out.printf("You currently have $%d\n", money);

            // Validating Bet
            System.out.println("How much do you want to bet? ");
            int bet = in.nextInt();
            while (bet < 1 || bet > money) {
                System.out.printf("Bet must be between 1 and %d. Enter bet again: ", money);
                bet = in.nextInt();
            }
        
            // Create and shuffle deck
            Deck deck = new Deck();

            // Deal hands
            Card[] playerHand = new Card[2];
            playerHand[0] = deck.draw();
            playerHand[1] = deck.draw();

            Card[] dealerHand = new Card[2];
            dealerHand[0] = deck.draw();
            dealerHand[1] = deck.draw();

            // Starting messages for each hand
            System.out.print("\nYour hand: ");
            printHand(playerHand);
            System.out.printf("\nTotal: %d\n", calculateHandValue(playerHand));

            System.out.println("Dealer shows: " +  dealerHand[0]);

            // Player Turn
            boolean playerBust = false;
            while (askHitOrStay()) {
                playerHand = addCard(playerHand, deck);
                System.out.print("Your hand: ");
                printHand(playerHand);
                int total = calculateHandValue(playerHand);
                System.out.printf("\nTotal: %d", total);

                if (total > 21) {
                    System.out.println("\nYou Busted!");
                    System.out.println("Dealer Wins!");
                    playerBust = true;
                    break;
                }
            }

            //Dealer Turn
            boolean dealerBust = false;
            if (!playerBust) {
                System.out.print("\nDealer's Hand: \n");
                printHand(dealerHand);
                System.out.printf("Total: %d\n", calculateHandValue(dealerHand));

                while (calculateHandValue(dealerHand) < 17) {
                    dealerHand = addCard(dealerHand, deck);
                    System.out.print("Dealer Hits: ");
                    printHand(dealerHand);
                    System.out.printf("\nTotal: %d", calculateHandValue(dealerHand));
                }

                if (calculateHandValue(dealerHand) > 21) {
                    System.out.println("\nDealer Busts!");
                    System.out.println("\nYou win!!!!!!");
                    dealerBust = true;
                }
            }

            // Who wins
            int playerTotal = calculateHandValue(playerHand);
            int dealerTotal = calculateHandValue(dealerHand);

            if (playerBust) {
                System.out.printf("\nYou lose $%d \n", bet);
                money -= bet;      
            }
            else if (dealerBust) {
                System.out.printf("\nYou win $%d \n", bet);
                money += bet;   
            }
            else if (playerTotal > dealerTotal) {
                System.out.printf("\nYou win $%d \n", bet);
                money += bet;
            }
            else if (playerTotal < dealerTotal) {
                System.out.printf("\nYou lose $%d \n", bet);
                money -= bet;   
            }
            else {
                System.out.println("\nPush, no money won or lost");
            }
            if (money <= 0) {
                System.out.println("\nYou ran out of money....");
                break;
            }

            // Validate Play another hand
            System.out.print("Do you want to play another hand? (1 = yes, 2 = no): ");
            int again = in.nextInt();
            while (again !=1 && again != 2) {
                System.out.print("Invalid Choice. Enter 1 to play or 2 to stop: ");
                again = in.nextInt();
            }

            if (again == 2) {
                System.out.println("Thank you for playing Blackjack!\n");
                System.out.printf("You are leaving the table with: $%d", money);
                break;
            }
        }



        // Initializing player and dealer total and hand
        int playerTotal = 0;
        int dealerTotal = 0;
        
        Card [] hand = new Card[2]; 
        

      
    }
    
    // Method asking player if they want to hit or stay
    public static boolean askHitOrStay() {
        Scanner in = new Scanner(System.in);
        System.out.print("\nDo you want to hit (1) or stay (2)?  ");
        int hitOrStay = in.nextInt();
        while(hitOrStay < 1 || hitOrStay > 2) {
            System.out.print("\nDo you want to hit (1) or stay (2)?  ");
            hitOrStay = in.nextInt();
        }
        return hitOrStay == 1;
    }
    
    // Method for drawing a card from Deck
    public static Card[] addCard(Card[] hand, Deck deck) {
        Card [] temp = new Card[hand.length + 1];
        for(int i = 0; i < hand.length; i++) {
            temp[i] = hand[i];
        }
        temp[temp.length - 1] = deck.draw();
        return temp;
    }    
    
    // Method for calculating hand value of player and dealer
    public static int calculateHandValue(Card[] hand) {
      int total = 0;
      int aces = 0;

      for(int i = 0; i < hand.length; i++) {
          int value = hand[i].getValue();
          total += value;
          if(hand[i].getRank() == 14) {
              aces++;
          }
      }
      while(total > 21 && aces > 0) {
            total -= 10;
            aces --;
       }
        return total;
    }
    
    // Method for printing player and dealer hand
    public static void printHand(Card[] hand) {
        for(int i = 0; i < hand.length; i++) {
            System.out.printf("%s ", hand[i]);
        }
    }
    
    

    
}
