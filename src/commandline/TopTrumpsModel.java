package commandline;

import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.sun.javafx.collections.MappingChange.Map;

//--------------------------------
//When we deal cards, we add cards at the TOP of the deque (addFirst() method)
//When we take a card from the top of our stack, we use pollFirst()
//When we put cards on the bottom of our stack, we use addLast()
//--------------------------------
public class TopTrumpsModel {

	private ArrayList<Card> cardList = new ArrayList<Card>();
	private static String headerArray[] = new String[6];
	private Deque<Card> mainDeck = new ArrayDeque<Card>();
	private Deque<Card> playerDeck = new ArrayDeque<Card>();
	private Deque<Card> cpu1Deck = new ArrayDeque<Card>();
	private Deque<Card> cpu2Deck = new ArrayDeque<Card>();
	private Deque<Card> cpu3Deck = new ArrayDeque<Card>();
	private Deque<Card> cpu4Deck = new ArrayDeque<Card>();
	private Player player = new Player("Human", playerDeck);
	private ArrayList<Player> playersList = new ArrayList<Player>();
	private ArrayList<Card> currentHands = new ArrayList<Card>();
	// common pile used in case of a draw
	private ArrayList<Card> commonPile = new ArrayList<Card>();
	// Stats used to create and update the database
	private int drawNumber = 0;
	private int playerRoundWin = 0;
	private int cpu1RoundWin = 0;
	private int cpu2RoundWin = 0;
	private int cpu3RoundWin = 0;
	private int cpu4RoundWin = 0;
	// player with the highest stat, initially 0
	private int highestStatPlayer = 0;
	private int gameID;
	private int roundNumber;
	private String winner;
	private SQL db;
	private PrintStream o = new PrintStream(new File("TopTrumps.log"));
	private PrintStream c = System.out;

	TopTrumpsModel() throws FileNotFoundException {

	}

	public int gameIntro() {

		Scanner userInput = new Scanner(System.in);

		// intro message
		System.out.println("--------------------\n" + "--- Top Trumps   ---\n" + "--------------------\n"
				+ "Do you want to see past results or play a game?\n" + "   1: Print Game Statistics\n"
				+ "   2: Play game\n");

		int selection = 0;
		// number of opponents
		int cpuNumber = 1;
		while (true) {
			selection = getInt("Enter the number for your selection:", userInput);
			if (selection == 1) {
				break;
			} else if (selection == 2) {
				while (true) {
					cpuNumber = getInt("Choose number of oppononents (1-4)", userInput);
					if (cpuNumber > 0 && cpuNumber < 5) {
						break;
					}
				}
				
				addCardsToList();
				gameLoop(cpuNumber, cardList);

				break;
			} else {
				System.out.println("Please enter '1' to print game statistics or '2' to play game");
			}
		}
		return selection;
	}

	public void gameLoop(int cpuNumber, ArrayList<Card> cardList) {

		Scanner userInput = new Scanner(System.in);
		roundNumber = 1;
		// stat that will be played in the given round
		int statSelection = 0;

		dealCards(cpuNumber, cardList);

		System.out.println("Game Start");

		while (true) {

			// if this value = 0, it means that the player starts the round
			int playerArrayPos = 1;

			// reset variable
			highestStatPlayer = 0;

			System.out.println("\n \n Round " + roundNumber + "\n______________ ");
			System.out.println("\n Round " + roundNumber + ": Players have drawn their cards");

			// if player was not eliminated from the game, do this:
			if (!playerDeck.isEmpty()) {
				// check position of the player in the array (if 0, he starts the round)
				playerArrayPos = playersList.indexOf(player);
				// peek first card
				Card currentCard = playersList.get(playerArrayPos).getDeck().peekFirst();
				// display drawn card
				System.out.println("\n You drew " + currentCard.toString());
				// display how many cards are left in the player's deck (without the currently
				// drawn card)
				System.out.println(
						"There are " + (playersList.get(playerArrayPos).getDeck().size() - 1) + " cards in your deck");
			}
			// play card
			statSelection = currentPlayerMove(playerArrayPos, statSelection, userInput);

			// collect hands (first card from each player)
			collectCurrentHands();

			// compare stat between the players
			highestStatPlayer = compareStat(statSelection, currentHands);
			System.setOut(o);
			System.out.println("The selected stat was stat number "+ statSelection );
			for (Card c:currentHands) {
				System.out.println(c.returnStat(statSelection));
			}
			System.setOut(c);
			// <draw>
			if (highestStatPlayer == 6) {
				// add hands to the common pile in case of a draw

				for (int i = 0; i < currentHands.size(); i++) {
					commonPile.add(currentHands.get(i));
				}
				System.setOut(o);
				System.out.println("-------------------------------\n-----------------------------");
				System.out.println("Common Pile Contents");
				for (Card c : commonPile) {
					System.out.println(c.toString());
				}
				System.out.println("--------------------\n--------------------");
				System.setOut(c);
				
				// clear hands
				currentHands.clear();

				System.out.println("DRAW \nStat chosen was: " + headerArray[statSelection]);

				setDrawNumber(getDrawNumber() + 1);

				checkPlayerEliminated(playersList);
			}
			// </end of draw>
			else {
				System.out.println(
						"\nRound " + roundNumber + " won by: " + playersList.get(highestStatPlayer).toString());
				// ADD AN ARROW INDICATING WINNING STAT:
				System.out.println("\nThe winning card was: " + currentHands.get(highestStatPlayer).toString());
				System.out.println("The winning stat was: " + headerArray[statSelection+1] + ": "
						+ currentHands.get(highestStatPlayer).returnStat(statSelection));

				// give hands to the winner
				giveHandsToWinner(highestStatPlayer);

				// check if any decks are empty
				checkPlayerEliminated(playersList);

				// adjust playersList (add winner on position 0)
				reorderPlayersList(playersList, highestStatPlayer);

				// increase number of wins for the current player
				try {
					increaseRoundWin(highestStatPlayer);

				} catch (IndexOutOfBoundsException e) {
					
				}
			}

			// finish the game if only 1 player left
			if (playersList.size() == 1) {
				System.out.println("Game End");
				break;
			}

			// stop the game at the end of the round
			pressAnyKeyToContinue();

			// move on to the next round
			roundNumber++;

			// summary of the common pile where cards are put in case of a draw
			System.out.println("Common pile: " + commonPile.size());
		}

		// print out how many games the player won

		System.out.println("The overall winner was " + playersList.get(0).toString() + "\n Scores: " + "\n Human: "
				+ playerRoundWin + "\n Opponent 1: " + cpu1RoundWin + "\n Opponent 2: " + cpu2RoundWin
				+ "\n Opponent 3: " + cpu3RoundWin + "\n Opponent 4: " + cpu4RoundWin);

		winner = playersList.get(0).toString();
		
		System.setOut(o);
		System.out.println("\n----------------\n The winner of the game is" + winner);
		System.setOut(c);
		System.out.println("\n" + "GameID is " + gameID);

		// game end:
		// GameStats gameStats = new GameStats(gameID, playersList.get(0).toString(),
		// roundNumber, playerRoundWin,
		// cpu1RoundWin, cpu2RoundWin, cpu3RoundWin, cpu4RoundWin, drawNumber);

		saveStats();

	}

	public void increaseRoundWin(int highestStatPlayer) {

		if (highestStatPlayer == 6) {
			// do nothing in case of a draw
		} else if (playersList.get(highestStatPlayer).toString() == "Human") {
			
			playerRoundWin++;

		} else if (playersList.get(highestStatPlayer).toString() == "Opponent 1") {
			
			cpu1RoundWin++;
		}

		else if (playersList.get(highestStatPlayer).toString() == "Opponent 2") {
			
			cpu2RoundWin++;
		}

		else if (playersList.get(highestStatPlayer).toString() == "Opponent 3") {
			
			cpu3RoundWin++;
		}

		else if (playersList.get(highestStatPlayer).toString() == "Opponent 4") {
			
			cpu4RoundWin++;
		}
	}

	public void reorderPlayersList(ArrayList<Player> playersList, int highestStatPlayer) {
		try {
			playersList.add(0, playersList.remove(highestStatPlayer));
		} catch (IndexOutOfBoundsException e) {
		}
	}

	public int giveHandsToWinner(int highestStatPlayer) {

		for (int i = 0; i < currentHands.size(); i++) {
			playersList.get(highestStatPlayer).getDeck().addLast(currentHands.get(i));

		}
		if (!commonPile.isEmpty()) {
			for (int i = 0; i < commonPile.size(); i++) {
				playersList.get(highestStatPlayer).getDeck().addLast(commonPile.get(i));
			}
		}
		int g = currentHands.size() + commonPile.size();
		System.out.println("The winner takes " + g + " cards");
		System.out.println("\nThe winner now has " + playersList.get(highestStatPlayer).getDeck().size() + " cards \n");

		// Added this ArrayList of ArrayLists to test in JUnit

		currentHands.clear();
		commonPile.clear();
		return g;

	}

	public int compareStat(int statSelection, ArrayList<Card> currentHands) {
		System.setOut(o);
		// return index of the player with the highest score
		int highestStatPlayer = 0;
		for (int i = 1; i < currentHands.size(); i++) {
			if (currentHands.get(i).returnStat(statSelection) > currentHands.get(highestStatPlayer)
					.returnStat(statSelection)) {
				System.out.println("");
				
				highestStatPlayer = i;
			}
			System.out.println("the stat selection value of the active player  = "
					+ currentHands.get(i).returnStat(statSelection));
		}
		
		for (int i = 1; i < currentHands.size(); i++) {
			if (i == highestStatPlayer) {
				// skip this index
			} else if (currentHands.get(i).returnStat(statSelection) == currentHands.get(highestStatPlayer)
					.returnStat(statSelection)) {
				// if draw, set highestStatPlayer to 6 (max value of i is 5)
				highestStatPlayer = 6;
				break;
			}
		}
		System.out.println("-------------------------");
		System.setOut(c);
		return highestStatPlayer;
	}

	public ArrayList<Card> collectCurrentHands() {

		for (int i = 0; i < playersList.size(); i++) {
			// retrieve hand of each player
			Card currentHandCard = playersList.get(i).getDeck().pollFirst();
			currentHands.add(currentHandCard);

		}
		System.setOut(o);
		System.out.println("\n----------------------");
		System.out.println("Current Hands");
		for (Card c : currentHands) {
			System.out.println(c.toString());
		}
		System.out.println("\n----------------------");
		System.setOut(c);
		return currentHands;
	}

	public int currentPlayerMove(int playerArrayPos, int statSelection, Scanner userInput) {
		// if user, go to [USER MOVE]
		if (playerArrayPos == 0) {
			// [USER MOVE]
			System.out.println("It is your turn to select a category, the categories are:" + "\n 1: " + headerArray[1]
					+ "\n 2: " + headerArray[2] + "\n 3: " + headerArray[3] + "\n 4: " + headerArray[4] + "\n 5: "
					+ headerArray[5]);

			while (true) {
				try {
					statSelection = getInt("Enter the number for your attribute (1-5):", userInput);
					statSelection--;
					if (statSelection > -1 && statSelection < 5) {
						break;
					}
				} catch (NoSuchElementException e) {
					System.out.println("Try Again");
				}
			}

		}
		// [CPU MOVE]
		else {
			statSelection = cpuPlayCard(playersList, 0);
		}
		return statSelection;
	}

	public int checkPlayerEliminated(ArrayList<Player> playersList) {
		for (int i = 0; i < playersList.size(); i++) {
			if (playersList.get(i).getDeck().isEmpty()) {
				System.out.println(playersList.get(i).toString() + " was eliminated");
				// remove player without cards from the playersList
				playersList.remove(i);
				System.out.println("player list size = " + playersList.size());
				i--;
			}
		}
		return playersList.size();
	}

	public int cpuPlayCard(ArrayList<Player> playersList, int pos) {
		Card currentCpuCard = playersList.get(pos).getDeck().peekFirst();
		int cpuStatChoice = currentCpuCard.returnHighestStat(currentCpuCard);
		return cpuStatChoice;
	}

	public int findPlayerPosition(ArrayList<Player> playersList) {
		// find position of the player in the playerArrayList
		int playerPos = 0;
		for (int i = 0; i < playersList.size(); i++) {
			if (playersList.get(i) == player) {
				playerPos = i;
			}
		}
		return playerPos;
	}

	public ArrayList<Player> createPlayersArray(int cpuNumber) {

		playersList.add(player);
		Player cpu1 = new Player("Opponent 1", cpu1Deck);
		playersList.add(cpu1);
		if (cpuNumber > 1) {
			Player cpu2 = new Player("Opponent 2", cpu2Deck);
			playersList.add(cpu2);
			if (cpuNumber > 2) {
				Player cpu3 = new Player("Opponent 3", cpu3Deck);
				playersList.add(cpu3);
				if (cpuNumber > 3) {
					Player cpu4 = new Player("Opponent 4", cpu4Deck);
					playersList.add(cpu4);
				}
			}
		}

		// randomise order in which the players start
		Collections.shuffle(playersList);
		return playersList;
	}

	public ArrayList<Deque<Card>> dealCards(int cpuNumber, ArrayList<Card> cardList) {
		ArrayList<Deque<Card>> playerDeques = new ArrayList();
		mainDeck = shuffleCards(cardList);
		while (!mainDeck.isEmpty()) {
			playerDeck.addFirst(mainDeck.pollFirst());

			if (mainDeck.isEmpty()) {
				break;
			}
			cpu1Deck.addFirst(mainDeck.pollFirst());

			if (mainDeck.isEmpty()) {

				break;
			}
			if (cpuNumber > 1) {
				cpu2Deck.addFirst(mainDeck.pollFirst());

				if (mainDeck.isEmpty()) {
					break;
				}
				if (cpuNumber > 2) {
					cpu3Deck.addFirst(mainDeck.pollFirst());

					if (mainDeck.isEmpty()) {
						break;
					}
					if (cpuNumber > 3) {
						cpu4Deck.addFirst(mainDeck.pollFirst());

						if (mainDeck.isEmpty()) {
							break;
						}
					}
				}
			}
		}
		playerDeques.add(playerDeck);
		playerDeques.add(cpu1Deck);
		playerDeques.add(cpu2Deck);
		playerDeques.add(cpu3Deck);
		playerDeques.add(cpu4Deck);
		System.setOut(o);
		System.out.println(" Player Deck------------------------------------------");
		for (Card c : playerDeck) {

			System.out.println(c.toString());
		}
		System.out.println("\n cpu1Deck------------------------------------------");
		for (Card c : cpu1Deck) {

			System.out.println(c.toString());
		}
		System.out.println("\n cpu2Deck------------------------------------------");
		for (Card c : cpu2Deck) {

			System.out.println(c.toString());
		}
		System.out.println("\n cpu3Deck------------------------------------------");
		for (Card c : cpu3Deck) {

			System.out.println(c.toString());
		}
		System.out.println("\n cpu4Deck------------------------------------------");
		for (Card c : cpu4Deck) {

			System.out.println(c.toString());
		}
		System.out.println("");
		System.setOut(c);
		createPlayersArray(cpuNumber);
		return playerDeques;
	}

	public Deque<Card> shuffleCards(ArrayList<Card> cardList) {

		Collections.shuffle(cardList);
		mainDeck = new ArrayDeque<Card>(cardList);
		System.setOut(o);

		System.out.println("-----------------------------------------\n");
		System.out.println("Shuffled Cards list");
		for (Card c : mainDeck) {

			System.out.println(c.toString());
		}
		System.setOut(c);
		return mainDeck;
	}

	public ArrayList<Card> addCardsToList() {

		FileReader reader = null;

		try {

			reader = new FileReader("StarCitizenDeck.txt");
			Scanner scanner = new Scanner(reader);

			String line = scanner.nextLine();
			String[] tokens = line.split(" ");

			for (int i = 0; i < 6; i++) {
				headerArray[i] = tokens[i];
			}

			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				tokens = line.split(" ");

				String description = tokens[0];
				int stat1 = Integer.parseInt(tokens[1]);
				int stat2 = Integer.parseInt(tokens[2]);
				int stat3 = Integer.parseInt(tokens[3]);
				int stat4 = Integer.parseInt(tokens[4]);
				int stat5 = Integer.parseInt(tokens[5]);

				Card cardObject = new Card(description, stat1, stat2, stat3, stat4, stat5);
				cardList.add(cardObject);
			}

			scanner.close();
			String listOfCards = cardList.toString();

		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException exception) {
					exception.printStackTrace();
				}
			}
		}
		System.setOut(o);
		System.out.println("Unshuffled Cards list: \n");

		for (Card c : cardList) {

			System.out.println(c.toString());
		}

		System.out.println("\n --------------------");

		System.setOut(c);

		return cardList;

	}

	public int getInt(String message, Scanner input) {
		while (true) {
			System.out.println(message);
			String line = input.nextLine();
			try {
				return new Scanner(line).nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Please enter a digit");
			}
		}
	}

	private void pressAnyKeyToContinue() {
		System.out.println("Press \"ENTER\" to continue...");
		Scanner sc = new Scanner(System.in);
		sc.nextLine();

	}

	public void saveStats() {
		db = new SQL();
		db.setgameInfotoSQL(gameID, winner, roundNumber, drawNumber, playerRoundWin, cpu1RoundWin, cpu2RoundWin,
				cpu3RoundWin, cpu4RoundWin);
	}

	public static String[] getHeaderArray() {
		return headerArray;
	}

	public int getGameID() {
		return gameID;
	}

	public void setGameID(int gameID) {
		this.gameID = gameID;
	}


	public int getDrawNumber() {
		return drawNumber;
	}

	public void setDrawNumber(int drawNumber) {
		this.drawNumber = drawNumber;
	}

}