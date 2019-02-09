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
	private ArrayList<Card> currentHands = new ArrayList<Card>();
	
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
	
	@Test
	void compareStatTest() {
		//checking that method for determining a draw works
		for (int i = 0; i<4;i++){
			Card x = new Card ("Fun guy", 9, 8, 7, 6, 3);
			currentHands.add(x);
		}
		//the int in this case is three but it doesn't matter what value is used
		//between 1 and 5 as all the cards are the same
		assertEquals(6,m.compareStat(3, currentHands));
		
		
		}

	@Test
	void createPlayersArrayTest () {
		
		//Showing that creating the players are is being created 
		//and the player array  filled 
		p=m.createPlayersArray(3);
		
		assertFalse(p.isEmpty());
		
	}
	
//	@Test
//	
//	void giveHandsToWinnerTest() {
//		
//		
//	}
//    @Test
//	
//	void collectCurrrentHandsTest() {
//	
//	
//	}
    @Test
    void cpuPlayCardTest() {
    	
    	p=m.createPlayersArray(2);
    	m.dealCards(2, m.addCardsToList());
    	int a =m.cpuPlayCard(p,2);
    	Card curr;
    	assertTrue(a>0);
    }
}