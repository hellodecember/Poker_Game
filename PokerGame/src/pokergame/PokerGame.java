/**
 * Name: Autumn Arnold
 * Date: 5/1/2020
 * Project: Poker Game
 */

package pokergame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class PokerGame {
	// constants
	private static final int STARTING_BALANCE = 100;
	private static final int NUMBER_OF_CARDS = 5;
	
	// default constant payout values and player hand types
	private static final int[] multipliers = { 1, 2, 3, 5, 6, 9, 25, 50, 250 };
	private static final String[] goodHandTypes = { "Royal Pair", "Two Pair",
			"Three of a Kind", "Straight", "Flush\t", "Full House",
			"Four of a Kind", "Straight Flush", "Royal Flush" };
	private static final Decks oneDeck = new Decks(1);	// using only one deck
	
	// player hand, balance, bet
	private List<Card> playerHand;
	private int playerBalance;
	private int playerBet;
	
	// default constructor
	public PokerGame() {
		this(STARTING_BALANCE);
	}
	
	// alternate constructor
	public PokerGame(int balance) {
		this.playerBalance = balance;
	}
	
	// display the payout table
	private void showPayoutTable() {
		System.out.println("\n\n");
		System.out.println("Payout Table          Multiplier   ");
		System.out.println("=======================================");
		int size = multipliers.length;
		for(int i = size - 1; i >= 0; i--) {
			System.out.println(goodHandTypes[i]+"\t|\t"+multipliers[i]);
		}
		System.out.println("\n\n");
	}
	
	private void checkHands() {
		if(isStraightFlush(playerHand)) {
			System.out.println(goodHandTypes[7] + "!");
	    }
	        
	    else if(isStraight(playerHand)) {
	        System.out.println(goodHandTypes[3] + "!");
	    }
	        
	    else if(isRoyalFlush(playerHand)) {
	        System.out.println(goodHandTypes[8] + "!");
	    }
	        
	    else if(isFlush(playerHand)) {
	        System.out.println(goodHandTypes[4] + "!");
	    }
	        
	    else if(isFourOfAKind(playerHand)) {
	        System.out.println(goodHandTypes[6] + "!");
	    }
	        
	    else if(isFullHouse(playerHand)) {
	        System.out.println(goodHandTypes[5] + "!");
	    }
	        
	    else if(isThreeOfAKind(playerHand)) {
	        System.out.println(goodHandTypes[2] + "!");
	    }
	        
	    else if(isTwoPair(playerHand)) {
	        System.out.println(goodHandTypes[1] + "!");
	    }
	        
	    else if(isRoyalPair(playerHand)) {
	        System.out.println(goodHandTypes[0] + "!");
	    }
	        
	    else {
	        System.out.println("Sorry, you lost!");
	    }
	}
	
	private void sortByRank(List<Card> playerHand) {
		Card[] cardsArray = new Card[playerHand.size()];
		int j, min;
		
		for(int i = 0; i < cardsArray.length; i++) {
			cardsArray[i] = playerHand.get(i);
		}
		
		for(int n = 0; n < cardsArray.length; n++) {
			min = n;
			for(j = n + 1; j < cardsArray.length; j++) {
				if(cardsArray[j].getRank() < cardsArray[min].getRank()) {
					min = j;
				}
			}
			
			Card temp = cardsArray[n];
			cardsArray[n] = cardsArray[min];
			cardsArray[min] = temp;
		}
		
		playerHand.clear();
		for(int k = 0; k < cardsArray.length; k++) {
			playerHand.add(cardsArray[k]);
		}
	}
	
	private boolean isRoyalFlush(List<Card> playerHand) {
		sortByRank(playerHand);
		Card[] cardsArray = new Card[playerHand.size()];
		
		for(int i = 0; i < cardsArray.length; i++) {
			cardsArray[i] = playerHand.get(i);
		}
		
		if(cardsArray[0].getRank() == 1 && cardsArray[1].getRank() == 10 &&
	       cardsArray[2].getRank() == 11 && cardsArray[3].getRank() == 12 &&
	       cardsArray[4].getRank() == 13) {
			return true;
	    }
	    else {
	    	return false;
	    } 
	}
	
	private boolean isStraight(List<Card> playerHand) {
		sortByRank(playerHand);
		Card[] cardsArray = new Card[playerHand.size()];
		
		for(int i = 0; i < cardsArray.length; i++) {
			cardsArray[i] = playerHand.get(i);
		}
		
		int test = cardsArray[0].getRank() + 1;
		
		for(int i=1; i < cardsArray.length; i++) {
			if(cardsArray[i].getRank() != test) {
				return false;
			}
			test++;
		}
		
		return true;
	}
	
	private boolean isFlush(List<Card> playerHand) {
		sortByRank(playerHand);
		Card[] cardsArray = new Card[playerHand.size()];
		
		for(int i = 0; i < cardsArray.length; i++) {
			cardsArray[i] = playerHand.get(i);
		}
		
		for(int i=1; i < cardsArray.length; i++) {
			if(cardsArray[i-1].getSuit() != cardsArray[i].getSuit()) {
				return false;
			}
		}
		
		return true;
	}
	
	private boolean isStraightFlush(List<Card> playerHand) {
		sortByRank(playerHand);
		Card[] cardsArray = new Card[playerHand.size()];
		
		for(int i = 0; i < cardsArray.length; i++) {
			cardsArray[i] = playerHand.get(i);
		}
		
		if(isFlush(playerHand) == true && isStraight(playerHand) == true) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean isFourOfAKind(List<Card> playerHand) {
		sortByRank(playerHand);
		Card[] cardsArray = new Card[playerHand.size()];
		
		for(int i = 0; i < cardsArray.length; i++) {
			cardsArray[i] = playerHand.get(i);
		}
		
		if(cardsArray[0].getRank() != cardsArray[3].getRank() &&
		   cardsArray[1].getRank() != cardsArray[4].getRank()) {
			return false;
		}  
		else {
			return true;
		}
	}
	
	private boolean isThreeOfAKind(List<Card> playerHand) {
		Card[] cardsArray = new Card[playerHand.size()];
        boolean a, b, c, d, e, f;
       
        for(int i=0; i < cardsArray.length; i++) {
           cardsArray[i] = playerHand.get(i);
        }
       
        if(cardsArray.length != 5) {
          return false;
        }
        
        a = cardsArray[0].getRank() == cardsArray[1].getRank() && 
            cardsArray[1].getRank() == cardsArray[2].getRank();
           
        b = cardsArray[2].getRank() == cardsArray[3].getRank() &&
            cardsArray[3].getRank() == cardsArray[4].getRank();
       
        c = cardsArray[0].getRank() == cardsArray[1].getRank() &&
            cardsArray[1].getRank() == cardsArray[3].getRank();
           
        d = cardsArray[0].getRank() == cardsArray[1].getRank() &&
            cardsArray[1].getRank() == cardsArray[4].getRank();
           
        e = cardsArray[1].getRank() == cardsArray[2].getRank() &&
            cardsArray[2].getRank() == cardsArray[3].getRank();
           
        f = cardsArray[1].getRank() == cardsArray[2].getRank() &&
            cardsArray[2].getRank() == cardsArray[4].getRank();
        
        return (a || b || c || d || e || f);
	}
	
	private boolean isRoyalPair(List<Card> playerHand) {
		sortByRank(playerHand);
		Card[] cardsArray = new Card[playerHand.size()];
		int pairs = 0;
		int count = 0;
		
		for(int i = 0; i < cardsArray.length; i++) {
			cardsArray[i] = playerHand.get(i);
		}
		
		for(int j = 11; j < 14; j++) {
			for(int i = 0; i < 5; i++) {
				if(cardsArray[i].getRank() == j) {
					count++;
				}
			}
			
			if(count == 2) {
				pairs++;
				count = 0;
			}
		}
		
		if(pairs == 1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean isOnePair(List<Card> playerHand) {
		sortByRank(playerHand);
		Card[] cardsArray = new Card[playerHand.size()];
		int pairs = 0;
		int count = 0;
		
		for(int i = 0; i < cardsArray.length; i++) {
			cardsArray[i] = playerHand.get(i);
		}
		
		for(int j = 1; j < 13; j++) {
			for(int i = 0; i < 5; i++) {
				if(cardsArray[i].getRank() == j) {
					count++;
				}
			}
			
			if(count == 2) {
				pairs++;
				count = 0;
			}
		}
		
		if(pairs == 1) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean isFullHouse(List<Card> playerHand) {
		if(isOnePair(playerHand) && isThreeOfAKind(playerHand)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean isTwoPair(List<Card> playerHand) {
		sortByRank(playerHand);
		Card[] cardsArray = new Card[playerHand.size()];
		int count = 0;
		
		for(int i = 0; i < cardsArray.length; i++) {
			cardsArray[i] = playerHand.get(i);
		}
		
		if(cardsArray.length != 5) {
			return false;
		}
		
		if(isFourOfAKind(playerHand) == true) {
			return false;
		}
		
		for(int i = 1; i < 5; i++) {
			if(cardsArray[i-1].getRank() == cardsArray[i].getRank()) {
				count++;
			}
		}
		
		if(count == 2) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private void placeBets() {
		Scanner input = new Scanner(System.in);
		boolean a = true;
		
		while(a) {
			System.out.print("Enter bet: ");
			int bet = input.nextInt();
			
			if(bet <= playerBalance) {
				playerBet = bet;
				playerBalance -= playerBet;
				a = false;
			}
			else {
				System.out.print("Insufficient funds.");
			}
		}
	}
	
	public Card getCard(int i) {
		Card card = playerHand.get(i);
		return card;
	}
	
	private List<Card> discardPile(List<Card> playerHand) {
		List<Card> temp = new ArrayList<Card>();
		List<Integer> discard = new ArrayList<Integer>();
		Scanner input = new Scanner(System.in);
		String string, string2[];
		string = null;
		int count = 1;
		
		while(count > 0) {
			count = 0;
			System.out.println("Enter positions of cards to keep (e.g. 1 4 5): ");
			string = input.nextLine();
			
			if(!string.isEmpty()) {
				string2 = string.split(" ");
	             
                if(string2.length > 5) {
                  System.out.println("Invalid number of cards selected.");
                  count++;
                }
             
                for(int i=0; i < string2.length; i++) {
                    int k = Integer.parseInt(string2[i]);
                    discard.add(i, k);
                }
             
                for(int i=0; i < discard.size(); i++) {
                    if(discard.get(i) < 1 || discard.get(i) >= 6) {
                      System.out.println("Selected position(s) out of range. Choose 1 - 5 only.");
                      count++;
                    }
                }
                
                if(count == 0) {
                	for(int i=0; i < discard.size(); i++) {
                        int x = discard.get(i);
                        temp.add(getCard(x - 1)); 
                    }

                    System.out.println("Kept cards: " + temp);

                    try {
                        temp.addAll(oneDeck.deal(5 - discard.size()));
                    } catch(PlayingCardException pce) {
                        pce.getMessage();
                    }
                }
                discard.clear();
			}
		}
		
		if(string.isEmpty()) {
			temp.clear();
            System.out.println("No cards selected.");
            try {
                temp.addAll(oneDeck.deal(5));
            } catch(PlayingCardException pce) {
                pce.getMessage();
            }
		}
		return temp;
	}
	
	private void payout(List<Card> playerHand) {
		if(isRoyalFlush(playerHand)) {
            playerBalance = playerBalance + (playerBet * multipliers[8]);
        }
         
        else if(isStraightFlush(playerHand)) {
            playerBalance = playerBalance + (playerBet * multipliers[7]);
        }
         
        else if(isFourOfAKind(playerHand)) {
            playerBalance = playerBalance + (playerBet * multipliers[6]);
        }
         
        else if(isFullHouse(playerHand)) {
            playerBalance = playerBalance + (playerBet * multipliers[5]);
        }
         
        else if(isFlush(playerHand)) {
            playerBalance = playerBalance + (playerBet * multipliers[4]);
        }
         
        else if(isStraight(playerHand)) {
            playerBalance = playerBalance + (playerBet * multipliers[3]);
        }
         
        else if(isThreeOfAKind(playerHand)) {
            playerBalance = playerBalance + (playerBet * multipliers[2]);
        }
         
        else if(isTwoPair(playerHand)) {
            playerBalance = playerBalance + (playerBet * multipliers[1]);
        }
         
        else if(isRoyalPair(playerHand)) {
            playerBalance = playerBalance + (playerBet * multipliers[0]);
        }
	}
	
	private boolean anotherDeal() {
		Scanner input = new Scanner(System.in);
		boolean a = true;
		boolean repeat = true;
		
		while(a) {
			System.out.println("Play again? (y / n)");  
            String decision = input.next();
            
            if(decision.equalsIgnoreCase("y")) {
                repeat = true;
                a = false;
            }          
            
            else if(decision.equalsIgnoreCase("n")) {
                repeat = false;
                a = false;
            }
            
            else {
                System.out.println("Choose (y / n)");
            }
		}
		
		return repeat;
	}
	
	private void askPayoutTable() {
		Scanner input = new Scanner(System.in);
		boolean a = true;
		
		while(a) {
			System.out.println("Would you like to see the payout table? (y / n)");
            String table = input.next();

            if(table.equalsIgnoreCase("y")) {
                showPayoutTable();
                a = false;
            }

            else if(table.equalsIgnoreCase("n")) {
                a = false;
            }

            else {
                System.out.println("Please enter y or n");
            }
		}
	}
	
	// the main function for the single player poker game
	public void play() {
		showPayoutTable();
		
		playerHand = new ArrayList<Card>();
		boolean play = true;
		
		while(play) {
			System.out.println("--------------------------");
			System.out.println("Balance: $" + playerBalance);
			placeBets();
			System.out.println("New Balance: $" + playerBalance);
			oneDeck.reset();
			oneDeck.shuffle();
			
			try {
				playerHand = new ArrayList<Card>(oneDeck.deal(NUMBER_OF_CARDS));
			} catch(PlayingCardException pce) {
				pce.getMessage();
			}
			
			System.out.println("Hand: " + playerHand);
			playerHand = discardPile(playerHand);
			System.out.println("Hand: " + playerHand);
	          
			checkHands();
			payout(playerHand);
			
			playerHand.clear();
			System.out.println("Your current balance: $" + playerBalance);
	          
			if((playerBalance > 0) && anotherDeal()) {
				askPayoutTable();
			}  
			else {
				System.out.println("Your final balance is $" + playerBalance + ", thanks for playing!");
				play = false;
			}
		}
	}
	
	// test method for checkHands()
	@SuppressWarnings("unused")
	private void testCheckHands() {
        try {
            playerHand = new ArrayList<Card>();

            // set Royal Flush
            playerHand.add(new Card(4,1));
            playerHand.add(new Card(4,10));
            playerHand.add(new Card(4,12));
            playerHand.add(new Card(4,11));
            playerHand.add(new Card(4,13));
            System.out.println(playerHand);
            checkHands();
            System.out.println("-----------------------------------");

            // set Straight Flush
            playerHand.set(0,new Card(4,9));
            System.out.println(playerHand);
            checkHands();
            System.out.println("-----------------------------------");

            // set Straight
            playerHand.set(4, new Card(2,8));
            System.out.println(playerHand);
            checkHands();
            System.out.println("-----------------------------------");

            // set Flush
            playerHand.set(4, new Card(4,5));
            System.out.println(playerHand);
            checkHands();
            System.out.println("-----------------------------------");

            // "Royal Pair" , "Two Pairs" , "Three of a Kind", "Straight", "Flush	",
            // "Full House", "Four of a Kind", "Straight Flush", "Royal Flush" };

            // set Four of a Kind
            playerHand.clear();
            playerHand.add(new Card(4,8));
            playerHand.add(new Card(1,8));
            playerHand.add(new Card(4,12));
            playerHand.add(new Card(2,8));
            playerHand.add(new Card(3,8));
            System.out.println(playerHand);
            checkHands();
            System.out.println("-----------------------------------");

            // set Three of a Kind
            playerHand.set(4, new Card(4,11));
            System.out.println(playerHand);
            checkHands();
            System.out.println("-----------------------------------");

            // set Full House
            playerHand.set(2, new Card(2,11));
            System.out.println(playerHand);
            checkHands();
            System.out.println("-----------------------------------");

            // set Two Pairs
            playerHand.set(1, new Card(2,9));
            System.out.println(playerHand);
            checkHands();
            System.out.println("-----------------------------------");

            // set Royal Pair
            playerHand.set(0, new Card(2,3));
            System.out.println(playerHand);
            checkHands();
            System.out.println("-----------------------------------");

            // non Royal Pair
            playerHand.set(2, new Card(4,3));
            System.out.println(playerHand);
            checkHands();
            System.out.println("-----------------------------------");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
	
	/*public static void main(String args[]) {
		PokerGame pokergame = new PokerGame();
		pokergame.testCheckHands();
	}*/
}
