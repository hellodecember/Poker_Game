/**
 * Name: Autumn Arnold
 * Date: 5/1/2020
 * Project: Poker Game
 */

package pokergame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//=================================================================================

/** PlayingCardException: It is used for errors related to Card and Deck objects **/

//=================================================================================

@SuppressWarnings("serial")
class PlayingCardException extends Exception {
	// default constructor
	PlayingCardException() {
		super();
	}
	
	// alternate constructor
	PlayingCardException(String reason) {
		super(reason);
	}
}


//=================================================================================

/** Card: for creating playing card objects **/

//=================================================================================

class Card {
	static final String[] Suit = {"","Clubs", "Diamonds", "Hearts", "Spades" };
	static final String[] Rank = {"","A","2","3","4","5","6","7","8","9","10","J","Q","K"};
	private int cardSuit;
	private int cardRank;
	
	// default constructor
	public Card(int suit, int rank) throws PlayingCardException {
		if((suit < 1) || (suit > 4)) {
			throw new PlayingCardException("Invalid suit: " + suit);
		}
		else {
			cardSuit = suit;
		}
		
		if((rank < 1) || (rank > 13)) {
			throw new PlayingCardException("Invalid rank: " + rank);
		}
		else {
			cardRank = rank;
		}
	}
	
	// accessors
	public int getSuit() {
		return cardSuit;
	}
	
	public int getRank() {
		return cardRank;
	}
	
	@Override
	public String toString() {
		return Rank[cardRank] + " " + Suit[cardSuit];
	}
}


//=================================================================================

/** Deck: n decks of 52 playing cards **/

//=================================================================================

class Decks {
	private List<Card> originalDecks;	// to keep track of all decks
	private List<Card> currentDecks;	// holds the remaining cards during games
	private int numOfDecks;				// number of 52 card decks in this object
	
	// default constructor
	public Decks() {
		this(1);
	}
	
	public Decks(int n) {
		this.numOfDecks = n;
		originalDecks = new ArrayList<Card>();
		for(int i = 0; i < this.numOfDecks; i++) {
			for(int suit = 1; suit <= 4; suit++) {
				for(int rank = 1; rank <= 13; rank++) {
					try {
						originalDecks.add(new Card(suit, rank));
					} catch(PlayingCardException pce) {
						System.out.println(pce.getMessage());
					}
				}
			}
		}
		
		currentDecks = new ArrayList<Card>(originalDecks);
	}
	
	// shuffles the cards in currentDecks
	public void shuffle() {
		Collections.shuffle(currentDecks);
	}
	
	// deals cards from the deck
	public List<Card> deal(int numOfCards) throws PlayingCardException {
		List<Card> drawnCards = new ArrayList<Card>();
		if(numOfCards > numOfRemainingCards()) {
			throw new PlayingCardException("Not enough cards for a new deal.");
		}
		for(int i = 0; i < numOfCards; i++) {
			drawnCards.add(currentDecks.remove(0));
		}
		
		return drawnCards;
	}
	
	// resets the playedDeck
	public void reset() {
		currentDecks.clear();
		currentDecks.addAll(originalDecks);
	}
	
	// return number of remaining cards in deal deck
	public int numOfRemainingCards() {
		return currentDecks.size();
	}
	
	// return a string representing cards in deal deck
	@Override
	public String toString() {
		return "" + currentDecks;
	}
}
