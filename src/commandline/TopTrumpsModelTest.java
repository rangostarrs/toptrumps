package commandline;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author Nick's Account
 *
 */
class TopTrumpsModelTest {
	
	private TopTrumpsModel m = new TopTrumpsModel();
	private ArrayList<Card> c = new ArrayList<Card>();
	private ArrayList<Player> p;
	private Deque<Card> playerDeck = new ArrayDeque<Card>();
	private Deque<Card> cpu1Deck = new ArrayDeque<Card>();
	private Deque<Card> cpu2Deck = new ArrayDeque<Card>();
	private Deque<Card> cpu3Deck = new ArrayDeque<Card>();
	private Deque<Card> cpu4Deck = new ArrayDeque<Card>();

	
	@BeforeEach
	void setUp() throws Exception {
	}
	
	@Test
	void createPlayersTest() {
		 
		assertEquals(5,m.createPlayersArray(5).size());
		
		
	}
	
	@Test
	void shuffleCardsTest() {
		
		// i think a completely new object with different cards is being created by this 
		assertEquals(c,m.shuffleCards(c));
	
		
	}
	

	@Test
	void checkPlayerEliminatedTest() {
		
		ArrayList<Player> p= m.createPlayersArray(5);
		// i *think* this works because none of the player decks have been filled with any cards
		//therefore causing all the players to be eliminated when the method is run
		assertEquals(0,m.checkPlayerEliminated(p));
	
	}
	
	@Test
	void dealCardsTest() {
		
	//This Section Tests the Method addCardsToList 
	//The size of the previously Empty Arraylist of Cards (i.e the game deck) is now equal to 40
		c =(m.addCardsToList());
		assertEquals(40,c.size());
		
		
	//	This Section tests the Method dealCards by checking if the same ArrayList from above (which has now been 
	//  changed to a deque) still has any cards in it (which it shouldn't if the method has been successful)
	//
		assertTrue(m.dealCards(5, c).isEmpty());
		
		
	// This section checks the cards have been dealt properly by running the test
	//through the game loop method	
		
		
	}

	
}