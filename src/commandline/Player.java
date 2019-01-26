package commandline;

import java.util.ArrayDeque;
import java.util.Deque;

public class Player {

	private Deque<Card> deck = new ArrayDeque<Card>();
	
	public Player(Deque<Card> deck) {
		this.setDeck(deck);
	}

	public Deque<Card> getDeck() {
		return deck;
	}

	public void setDeck(Deque<Card> deck) {
		this.deck = deck;
	}
}
