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

		dealCards(cpuNumber, cardList);
		// ArrayList<Player> playersArray = createPlayersArray(cpuNumber);

		// find where in the ArrayList the player is after shuffling so he can be
		// accessed
		int playerArrayPos = findPlayerPosition(playersList);
		System.out.println("Game Start");

		while (true) {

			System.out.println("Round " + roundNumber);
			System.out.println("\n Round " + roundNumber + ": Players have drawn their cards");

			Card currentCard = playersList.get(playerArrayPos).getDeck().pollFirst();
			// ERROR HERE:
			// System.out.println("You drew " + currentCard.toString());
			System.out.println("There are " + playerDeck.size() + " cards in your deck");
			System.out.println("It is your turn to select a category, the categories are:" + "\n 1: " + headerArray[0]
					+ "\n 2: " + headerArray[1] + "\n 3: " + headerArray[2] + "\n 4: " + headerArray[3] + "\n 5: "
					+ headerArray[4]);
			int statSelection = getInt("Enter the number for your attribute:", userInput);

			// ADD MORE STUFF HERE

			// COMPARE STAT BETWEEN THE PLAYERS
			// GIVE CARDS TO THE WINNING PLAYER OR
			// IF DRAW: PUT THE CARDS ON A SEPERATE STACK

			// HOW TO MOVE ON TO THE NEXT PLAYER?

			checkPlayerEliminated(playerArrayPos);
			roundNumber++;
		}

	}

	public void checkPlayerEliminated(int playerPos) {
		for (int i = 0; i < playersList.size(); i++) {
			if(playersList.get(i).getDeck().isEmpty()) {
				if(i==playerPos) {
					System.out.println("Game over - you lost");
				}
				else {
					System.out.println("Player number " + i + "was eliminated");
					//remove CPU that lost from the players list
					playersList.remove(i);
				}
			}
		}
	}
	
	public Card cpuPlayCard(ArrayDeque<Card> cpuDeck) {
		Card currentCpuCard = cpuDeck.pollFirst();
		currentCpuCard.returnHighestCriterion(currentCpuCard);
		return currentCpuCard;
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

		// FIX DEALING CARDS
		mainDeck = shuffleCards(cardList);
		// System.out.println(mainDeck.toString());
		while (!mainDeck.isEmpty()) {
			playerDeck.addFirst(mainDeck.pollFirst());
			cpu1Deck.addFirst(mainDeck.pollFirst());
			if (cpuNumber > 1) {
				cpu2Deck.addFirst(mainDeck.pollFirst());
				if (cpuNumber > 2) {
					cpu3Deck.addFirst(mainDeck.pollFirst());
					if (cpuNumber > 3) {
						cpu4Deck.addFirst(mainDeck.pollFirst());
					}
				}
			}
		}

		createPlayersArray(cpuNumber);

	}

	public Deque<Card> shuffleCards(ArrayList<Card> cardList) {

		Collections.shuffle(cardList);
		Deque<Card> mainDeck = new ArrayDeque<Card>(cardList);

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
