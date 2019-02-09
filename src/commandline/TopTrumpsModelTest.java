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
	private ArrayList<Card> commonPile = new ArrayList<Card>();
	
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
		
		//  this test works by demonstrating that players with no cards are immediately eliminated/
		// players are created without the decks being filled with any cards
		//therefore causing all the players to be eliminated when the method is run
		//see console 
		
		assertEquals(0,m.checkPlayerEliminated(p));
	
	}
	
	@Test
	void dealCardsTest() {
		
	//THIS FIRST SECTION TESTS THE addCardsToList METHOD 
			
	//The size of the previously Empty Arraylist of Cards (i.e the game deck) is now equal to 40
		c =(m.addCardsToList());
		assertEquals(40,c.size());
		
		
	//	THE SECTION TESTS THE METHOD dealCards by checking if the same ArrayList from above (which has now been 
	//  changed to a deque) still has any cards in it (which it shouldn't if the method has been successful)
	//
		
		
		//showing that the array contains decks and therefore something has been returned

		 ArrayList <Deque<Card>> playerDeques = m.dealCards(2, c);
		assertFalse(playerDeques.isEmpty());
		
		
		//in the case of a three player game the card split should come out unevenly
		//this section checks that split has been done correctly
		assertEquals(14,playerDeques.get(0).size());
		assertEquals(13,playerDeques.get(1).size());
		assertEquals(13,playerDeques.get(2).size());
		
	
		
			
		
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
		
		//this is equal to four because the number of players is always the method argument's
		//input value plus one for the human player
		assertEquals(4,p.size());
		
		
	}
	
	@Test
	
	void giveHandsToWinnerTest() {
		
		// this test 
		//variables to represent card stats
		int a=0,b=0,c=0,d =0,e=0;
		Card x;
		Card y;
		//In this case we have created 4 cards that are identical to simulate a draw situation
		//in a hypothetical 4 player game
		for (int i = 0; i<4;i++){
			x = new Card ("Fun guy", a, b, c, d, e);
			currentHands.add(x);
			//DRAW!!
			}
		assertEquals(4, currentHands.size());
		
		System.out.println("CurrHand size "+ currentHands.size());
		
		//The cards are added to the commonPile
		for (int j= 0; j<8;j++) {
			
	if (!currentHands.isEmpty()) {
			y = currentHands.remove(0); 
			commonPile.add(y);
		}}
		
		
		// check to make sure that this has happened correctly
		
		
		assertEquals(0, currentHands.size());
		assertEquals(4, commonPile.size());
		
		
		// now adding a case in which the content current hands
		//and 
		for (int i = 0; i<4;i++){
			Card z = new Card ("Fun guy", a, b, c, d, e);
			currentHands.add(z);
			a++;
			b+=2;
			c+=2;
			d++;
			e+=2;
			// This case has been setup such that computer player 4 wins this round
			
		}
		
		for (int i=0;i<10;i++) {
			
			if (!currentHands.isEmpty()) {
				y = currentHands.remove(0); 
				commonPile.add(y);
			}
			
		}

		
		
		
	}
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