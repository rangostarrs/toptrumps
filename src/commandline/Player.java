package commandline;

import java.util.ArrayDeque;
import java.util.Deque;

public class Player {

	private String name;
	private Deque<Card> deck = new ArrayDeque<Card>();
	
	public Player(String name, Deque<Card> deck) {
		this.setName(name);
		this.setDeck(deck);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Deque<Card> getDeck() {
		return deck;
	}

	public void setDeck(Deque<Card> deck) {
		this.deck = deck;
	}
	
	@Override
	public String toString() {
		return (this.getName());
	}
}
