/*
* Cam and Ayden
* Deck class for Blackjack
*/


import java.util.*;


// Deck class
public class Deck {
    private Card[] cards;
    private int size;

    public Deck() {
        int[] suits = {0, 1, 2, 3};
        int[] ranks = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

        this.cards = new Card[52];
        int index = 0;

        for (int i = 0; i < suits.length; i++) {
            for (int j = 0; j < ranks.length; j++) {
                cards[index] = new Card(suits[i], ranks[j]);
                index++;
            }
        }
        this.size = 52;
        shuffle();
    }

    public boolean isEmpty() {
        if(size > 0) {
            return false;
        }
        else {
            return true;
        }
    }


     // Shuffle the deck of cards 

    public void shuffle() {
        Random rand = new Random();
        for (int i = cards.length - 1; i > 0; i--) {
            int randomIndexToSwap = rand.nextInt(i + 1);
            Card temp = cards[i];
            cards[i] = cards[randomIndexToSwap];
            cards[randomIndexToSwap] = temp;
        }
    }

    public Card draw() {
        if (size == 0){
            return null;
        }
        // return the top card (last one in the pile)
        Card retval = cards[size - 1];
        size--;
        return retval;
    }
}
