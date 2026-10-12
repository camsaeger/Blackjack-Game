/**
 * Ayden and Cam
 * 
 * Card class for blackjack
 */

 public class Card {
    private int suit;
    private int rank;

    public Card(int suit, int rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public String toString() {
        final char [] suitSymbols = {'S', 'H', 'D', 'C'};
        final char [] faceCards = {'J', 'Q', 'K', 'A'};
        
        if(rank > 10) {
            return String.format("%c%c", faceCards[this.rank - 11], suitSymbols[this.suit]);
        }
        else {
            return String.format("%d%c", this.rank, suitSymbols[this.suit]);
        }
    }

    public int getRank() {
        return this.rank;
    }

    public int getSuit() {
        return this.suit;
    }


     public int getValue() {
         if(rank >= 11 && rank <= 13) {
             return 10;
         }
         else if(rank == 14) {
            return 11;
         }
         else {
            return rank;
         }
     }

     // Sets the new suit
     
    public void setSuit(int newSuit) {
        if(newSuit < 0 || newSuit > 3) {
            return;
        }
        else {
            this.suit = newSuit;
        }
    }

    public boolean matches(Card c) {
        if(this.rank == c.rank || this.suit == c.suit) {
            return true;
        }
        else {
            return false;
        }
    }   
 }
