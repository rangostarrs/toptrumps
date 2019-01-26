package commandline;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.InputMismatchException;
import java.util.Scanner;

//--------------------------------
//When we deal cards, we add cards at the TOP of the deque (addFirst() method)
//When we take a card from the top of our stack, we we use pollFirst()
//When we put cards on the bottom of our stack, we use addLast()
//--------------------------------
public class TopTrumpsModel {

	private ArrayList<Card> cardList = new ArrayList<Card>();
	private String headerArray[] = new String[5];
	private Deque<Card> mainDeck = new ArrayDeque<Card>();
	private Deque<Card> playerDeck = new ArrayDeque<Card>();
	private Deque<Card> cpu1Deck = new ArrayDeque<Card>();
	private Deque<Card> cpu2Deck = new ArrayDeque<Card>();
	private Deque<Card> cpu3Deck = new ArrayDeque<Card>();
	private Deque<Card> cpu4Deck = new ArrayDeque<Card>();
	private Player player = new Player(playerDeck);
	private ArrayList<Player> playersList = new ArrayList<Player>();
	private ArrayList<Card> currentHands = new ArrayList<Card>();
	// common pile used in case of a draw
	private ArrayList<Card> commonPile = new ArrayList<Card>();

	TopTrumpsModel() {

	}

	public void gameIntro() {

		Scanner userInput = new Scanner(System.in);

		// intro message
		System.out.println("--------------------\n" + "--- Top Trumps   ---\n" + "--------------------\n"
				+ "Do you want to see past results or play a game?\n" + "   1: Print Game Statistics\n"
				+ "   2: Play game\n");

		int selection = getInt("Enter the number for your selection:", userInput);

		if (selection == 1) {
			// PRINT GAME STATISTICS
		} else if (selection == 2) {
			int cpuNumber = getInt("Choose number of oppononents", userInput);
			addCardsToList();
			gameLoop(cpuNumber, cardList);
		}

	}

	public void gameLoop(int cpuNumber, ArrayList<Card> cardList) {

		Scanner userInput = new Scanner(System.in);
		int roundNumber = 1;
		int statSelection = 0;

		dealCards(cpuNumber, cardList);
		// ArrayList<Player> playersArray = createPlayersArray(cpuNumber);

		// find where in the ArrayList the player is after shuffling so he can be
		// accessed
		int playerArrayPos = findPlayerPosition(playersList);
		System.out.println("Game Start");

		while (true) {

			playerArrayPos = findPlayerPosition(playersList);
			System.out.println("Round " + roundNumber);
			System.out.println("\n Round " + roundNumber + ": Players have drawn their cards");


			Card currentCard = playersList.get(playerArrayPos).getDeck().peekFirst();

			System.out.println("You drew " + currentCard.toString());
			System.out.println("There are " + playersList.get(playerArrayPos).getDeck().size() + " cards in your deck");

			// check who's the current player - user or cpu
			statSelection = currentPlayerMove(playerArrayPos, statSelection, userInput);

			// collect hands
			collectCurrentHands(statSelection);

			// compare stat between the players
			int highestStatPlayer = compareStat(statSelection);

			// <draw>
			if (highestStatPlayer == 6) {
				for (int i = 0; i < currentHands.size(); i++) {
					commonPile.add(currentHands.get(i));
				}
				
				System.out.println("DRAW \n");

			}
			// </end of draw>
			else {
				System.out.println("Round " + roundNumber + " won by: " + playersList.get(highestStatPlayer).getClass().getName());
				// ADD AN ARROW INDICATING WINNING STAT:
				System.out.println("The winning card was: \n" + currentHands.get(highestStatPlayer).toString());

				// GIVE CARDS TO THE WINNING PLAYER
				giveHandsToWinner(highestStatPlayer);

				// check if any decks are empty
				checkPlayerEliminated(playerArrayPos);

				// adjust playersList (add winner on position 0)
				reorderPlayersList(playersList, highestStatPlayer);
				
				//clear hands
				currentHands.clear();
			}

			
			// move on to the next round
			roundNumber++;
			
			//ADD WINNING CONDITION
		}

	}

	public void reorderPlayersList(ArrayList<Player> playersList, int highestStatPlayer) {
		playersList.add(0, playersList.remove(highestStatPlayer));
	}

	public void giveHandsToWinner(int highestStatPlayer) {
		for (int i = 0; i < currentHands.size(); i++) {
			playersList.get(highestStatPlayer).getDeck().addLast(currentHands.get(i));
		}
		if (!commonPile.isEmpty()) {
			for (int i = 0; i < commonPile.size(); i++) {
				playersList.get(highestStatPlayer).getDeck().addLast(commonPile.get(i));
			}
		}
	}

	public int compareStat(int statSelection) {
		int highestStatPlayer = 0;
		for (int i = 1; i < currentHands.size(); i++) {
			System.out.println("This should print values of the chosen stat for each hand: " + currentHands.get(i).returnStat(statSelection));
			if (currentHands.get(i).returnStat(statSelection) > currentHands.get(highestStatPlayer)
					.returnStat(statSelection)) {
				highestStatPlayer = i;

			} else if (currentHands.get(i).returnStat(statSelection) == currentHands.get(highestStatPlayer)
					.returnStat(statSelection)) {
				// if draw, set highestStatPlayer to 6 (max value of i is 5)
				highestStatPlayer = 6;
			}
		}

		return highestStatPlayer;
	}

	public int[] collectCurrentHands(int statSelection) {
		int statComparisonArray[] = new int[playersList.size()];
		for (int i = 0; i < playersList.size(); i++) {

			// retrieve hand of each player

			// playersList.get(i).getDeck().peekFirst().returnStat(statSelection);
			Card currentHandCard = playersList.get(i).getDeck().pollFirst();
			currentHands.add(currentHandCard);
		}

		return statComparisonArray;
	}

	public int currentPlayerMove(int playerArrayPos, int statSelection, Scanner userInput) {
		// if user, go to [USER MOVE]
		if (playerArrayPos == 0) {
			// [USER MOVE]
			System.out.println("It is your turn to select a category, the categories are:" + "\n 1: " + headerArray[0]
					+ "\n 2: " + headerArray[1] + "\n 3: " + headerArray[2] + "\n 4: " + headerArray[3] + "\n 5: "
					+ headerArray[4]);
			statSelection = getInt("Enter the number for your attribute:", userInput);
		}
		// [CPU MOVE]
		else {
			statSelection = cpuPlayCard(playersList, 0);
		}
		return statSelection;
	}

	public void checkPlayerEliminated(int playerPos) {
		for (int i = 0; i < playersList.size(); i++) {
			if (playersList.get(i).getDeck().isEmpty()) {
				if (i == playerPos) {
					System.out.println("Game over - you lost");
					break;
				} else {
					System.out.println("Player number " + i + "was eliminated");
					// remove CPU that lost from the players list
					playersList.remove(i);
				}
			}
		}
	}

	public int cpuPlayCard(ArrayList<Player> playersList, int pos) {
		Card currentCpuCard = playersList.get(pos).getDeck().peekFirst();
		int cpuStatChoice = currentCpuCard.returnHighestStat(currentCpuCard);
		return cpuStatChoice;
	}

	public int findPlayerPosition(ArrayList<Player> playersList) {
		int playerPos = 0;
		for (int i = 0; i < playersList.size(); i++) {
			if (playersList.get(i) == player) {
				playerPos = i;
			}
		}
		return playerPos;
	}

	public ArrayList<Player> createPlayersArray(int cpuNumber) {

		// ArrayList<Player> playersList = new ArrayList<Player>();

		playersList.add(player);
		Player cpu1 = new Player(cpu1Deck);
		playersList.add(cpu1);
		if (cpuNumber > 1) {
			Player cpu2 = new Player(cpu2Deck);
			playersList.add(cpu2);
			if (cpuNumber > 2) {
				Player cpu3 = new Player(cpu3Deck);
				playersList.add(cpu3);
				if (cpuNumber > 3) {
					Player cpu4 = new Player(cpu4Deck);
					playersList.add(cpu4);
				}
			}
		}

		// randomise order in which the players start
		Collections.shuffle(playersList);
		return playersList;
	}

	public void dealCards(int cpuNumber, ArrayList<Card> cardList) {

		mainDeck = shuffleCards(cardList);
		System.out.println("Main deck" + mainDeck.toString());
		while (!mainDeck.isEmpty()) {
			if (mainDeck.isEmpty()) {
				break;
			}
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
		System.out.println("Player deck" + playerDeck.toString());
		System.out.println("deck 1:" + cpu1Deck.toString());
		System.out.println("deck 2:" + cpu2Deck.toString());
		System.out.println("deck 3:" + cpu3Deck.toString());
		System.out.println("deck 4:" + cpu4Deck.toString());

		createPlayersArray(cpuNumber);

	}

	public Deque<Card> shuffleCards(ArrayList<Card> cardList) {

		Collections.shuffle(cardList);
		mainDeck = new ArrayDeque<Card>(cardList);

		return mainDeck;
	}

	public void addCardsToList() {

		FileReader reader = null;

		try {

			reader = new FileReader("StarCitizenDeck.txt");
			Scanner scanner = new Scanner(reader);

			String line = scanner.nextLine();
			String[] tokens = line.split(" ");

			for (int i = 0; i < 5; i++) {
				headerArray[i] = tokens[i + 1];
			}

			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				tokens = line.split(" ");

				String description = tokens[0];
				int size = Integer.parseInt(tokens[1]);
				int speed = Integer.parseInt(tokens[2]);
				int range = Integer.parseInt(tokens[3]);
				int firepower = Integer.parseInt(tokens[4]);
				int cargo = Integer.parseInt(tokens[5]);

				Card cardObject = new Card(description, size, speed, range, firepower, cargo);
				cardList.add(cardObject);
			}

			scanner.close();
			String listOfCards = cardList.toString();
			// System.out.println(listOfCards.replace("[", "").replace("]",
			// "").replaceAll(",", ""));
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

}
