package commandline;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This test class is non exhaustive but aims to put some of the more important methods 
 * and serves alongside the log file to demonstrate the functionality of the game.
 *
 */
class TopTrumpsModelTest {

	TopTrumpsModel model;
	ArrayList <Player> player;
	ArrayList<Card> cardy;
	Deque<Card> shuffled;
	Deque<Card> playerDeck;
	Deque<Card> cpu1Deck;
	Deque<Card> cpu2Deck;
	Deque<Card> cpu3Deck;
	Deque<Card> cpu4Deck;
	ArrayList<Card> currentHands;
	ArrayList<Card> commonPile;

	@BeforeEach
	void setUp() throws Exception {
		model = new TopTrumpsModel();
		player= new ArrayList<Player>();
		cardy= new ArrayList<Card>();
		playerDeck = new ArrayDeque<Card>();
		cpu1Deck = new ArrayDeque<Card>();
		cpu2Deck = new ArrayDeque<Card>();
		cpu3Deck = new ArrayDeque<Card>();
		cpu4Deck = new ArrayDeque<Card>();
		currentHands = new ArrayList<Card>();
	    commonPile = new ArrayList<Card>();
	}
	
	/*This shows that every time the player array is created we have set up a four player
	 * game. However, the game will immediately eliminate players depending upon how many
	 * opponents the human player has selected */

	@Test
	void createPlayersTest() {

		assertEquals(5,model.createPlayersArray(5).size());
		cardy = new ArrayList<Card>();

	}

	
	/* this test is intended to fail. It shows that the order of the list of cards in 
	 * the shuffled array is different to that of the list of the unshuffled array of cards generated
	 * when the game is initiated.*/
	
	@Test
	void shuffleCardsTest() {
		
		cardy = model.addCardsToList();
		
		String [] one = new String [40];
		for (int i =0; i<one.length;i++) {
			
			one[i] = cardy.get(i).toString();
		}
		
		shuffled = model.shuffleCards(cardy);
		
		String [] two = new String[40];
		
		for (int i = 0; i<two.length;i++) {
			
			two [i] = shuffled.poll().toString();
		}
		
		
		for (int i = 0; i<one.length;i++) {
			
			assertTrue(one[i]==two[i]);
			
		}

	}


	@Test
	void checkPlayerEliminatedTest() {

		ArrayList<Player> p= model.createPlayersArray(5);

		/* this test works by demonstrating that players with no cards are immediately eliminated/
		 players are created without the decks being filled with any cards
		 therefore causing all the players to be eliminated immediately when the method is run
		 ***see console output*** */

		assertEquals(0,model.checkPlayerEliminated(p));

	}
	
	
	/* This test covers two separate methods in the model class. 
	 * Firstly, it makes sure that the deck of cards is generated properly
	 * then secondly it ensures that the cards are dealt properly by
	 * testing the problematic case of three player games where one player must
	 * receive one card more than the other players */

	@Test
	void dealCardsTest() {

		//THIS SECTION TESTS THE addCardsToList METHOD 

		//The size of the previously Empty Arraylist of Cards (i.e the game deck) is now equal to 40
		cardy =(model.addCardsToList());
		assertEquals(40,cardy.size());

		
		
		/*	THE SECTION TESTS THE METHOD dealCards by checking if the same ArrayList from above (which has now been 
		  changed to a deque) still has any cards in it (which it shouldn't if the method has been successful)*/
		


		//showing that the array contains decks and therefore something has been returned

		ArrayList <Deque<Card>> playerDeques = model.dealCards(2, cardy);
		assertFalse(playerDeques.isEmpty());


		//in the case of a three player game the card split should come out unevenly
		//this section checks that split has been done correctly
		assertEquals(14,playerDeques.get(0).size());
		assertEquals(13,playerDeques.get(1).size());
		assertEquals(13,playerDeques.get(2).size());





	}
	/*checking that method for determining a draw works
	 * and by extension the stat comparison by creating 4 cards with
	 *  the same stats and  comparing them*/

	@Test
	void compareStatTest() {
		
		for (int i = 0; i<4;i++){
			Card x = new Card ("Fun guy", 9, 8, 7, 6, 3);
			currentHands.add(x);
		}
		/*the integer 3 in this case represents the stat Range
		the result is six because the stat is the same on each card
		and 6 is the integer return value that tells the program there 
		there has been a draw*/
		assertEquals(6,model.compareStat(3, currentHands));


	}

	@Test
	void createPlayersArrayTest () {

		/*Showing that creating the players are is being created 
		and the player array  filled */
		player=model.createPlayersArray(3);

		/*this is equal to four because the number of players is always the method argument's
		input value plus one for the human player*/
		assertEquals(4,player.size());


	}

}