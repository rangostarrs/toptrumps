package commandline;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import SQL.SQL;

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
	private Player player = new Player("Human", playerDeck);
	private ArrayList<Player> playersList = new ArrayList<Player>();
	private ArrayList<Card> currentHands = new ArrayList<Card>();
	// common pile used in case of a draw
	private ArrayList<Card> commonPile = new ArrayList<Card>();
	private int drawNumber = 0;
//	private int playerRoundWin = 0;
//	private int cpu1RoundWin = 0;
//	private int cpu2RoundWin = 0;
//	private int cpu3RoundWin = 0;
//	private int cpu4RoundWin = 0;
	//player with the highest stat, initially 0
	private int highestStatPlayer = 0;
	//THIS IS CURRENTLY HARDCODED FOR 4 AI PLAYERS:
	private int[] gameResults = {0, 0, 0, 0, 0};

	TopTrumpsModel() {

	}

	public int gameIntro() {

		Scanner userInput = new Scanner(System.in);

		// intro message
		System.out.println("--------------------\n" + "--- Top Trumps   ---\n" + "--------------------\n"
				+ "Do you want to see past results or play a game?\n" + "   1: Print Game Statistics\n"
				+ "   2: Play game\n");

		int selection = getInt("Enter the number for your selection:", userInput);

		if (selection == 1) {

		} else if (selection == 2) {
			int cpuNumber = getInt("Choose number of oppononents", userInput);
			addCardsToList();
			gameLoop(cpuNumber, cardList);
		}
		return selection;
	}

	public GameStats gameLoop(int cpuNumber, ArrayList<Card> cardList) {

		Scanner userInput = new Scanner(System.in);
		int roundNumber = 1;
		//stat that will be played in the given round
		int statSelection = 0;

		dealCards(cpuNumber, cardList);
		

		//REDUNDANT CODE? <ignore>:
		// find where in the ArrayList the player is after shuffling so he can be
		// accessed
		// int playerArrayPos = findPlayerPosition(playersList);
		
		System.out.println("Game Start");

		while (true) {

			//if this value = 0, it means that the player starts the round
			int playerArrayPos = 1;
			
			//reset variable
			highestStatPlayer = 0;
			
			System.out.println("\n \n Round " + roundNumber + "\n______________ ");
			System.out.println("\n Round " + roundNumber + ": Players have drawn their cards");

			//if player was not eliminated from the game, do this:
			if(!playerDeck.isEmpty()) {
				//check position of the player in the array (if 0, he starts the round)
				playerArrayPos = playersList.indexOf(player);
				//peek first card
				Card currentCard = playersList.get(playerArrayPos).getDeck().peekFirst();

				//display drawn card
				System.out.println("You drew " + currentCard.toString());
				System.out.println("There are " + playersList.get(playerArrayPos).getDeck().size() + " cards in your deck");
			}
			

			// check who's the current player - user or cpu
			try {
				//THERE WAS A LOGIC ERROR IN CARD CLASS RETURNING HIGHEST STAT -
				//IT WAS RETURNING VALUE OF THE STAT, NOT STAT INDEX -
				//try-catch NOT NEEDED ANYMORE?
				statSelection = currentPlayerMove(playerArrayPos, statSelection, userInput);
			} catch (NoSuchElementException e) {

				System.out.println("Doesn't Exist");

			}


			
			// collect hands (first card from each player)
			collectCurrentHands();
//			try {
				// compare stat between the players
				highestStatPlayer = compareStat(statSelection);
//			} catch (IndexOutOfBoundsException e) {
//
//				System.out.println("Too Big");
//			}
			// <draw>
			if (highestStatPlayer == 6) {
				for (int i = 0; i < currentHands.size(); i++) {
					commonPile.add(currentHands.get(i));
				}

				// clear hands
				currentHands.clear();

				System.out.println("DRAW \n");
				drawNumber++;
				
				checkPlayerEliminated(playerArrayPos);

			}
			// </end of draw>
			else {
				System.out
						.println("Round " + roundNumber + " won by: " + playersList.get(highestStatPlayer).toString());
				// ADD AN ARROW INDICATING WINNING STAT:
				System.out.println("The winning card was: \n" + currentHands.get(highestStatPlayer).toString());

				// GIVE CARDS TO THE WINNING PLAYER
				giveHandsToWinner(highestStatPlayer);

				// check if any decks are empty
				checkPlayerEliminated(playerArrayPos);

				// adjust playersList (add winner on position 0)
				reorderPlayersList(playersList, highestStatPlayer);

				// increase number of wins for the current player
				//PLACEHOLDER METHOD
				if (playersList.get(highestStatPlayer).toString() == "Human") {
					gameResults[0] = gameResults[0] + 1;
				}
				else if (playersList.get(highestStatPlayer).toString() == "Opponent 1") {
					gameResults[1] = gameResults[1] + 1;
				}
				
				else if(playersList.get(highestStatPlayer).toString() == "Opponent 2") {
					gameResults[2] = gameResults[2] + 1;
				}
				
				else if(playersList.get(highestStatPlayer).toString() == "Opponent 3") {
					gameResults[3] = gameResults[3] + 1;
				}
				
				else if(playersList.get(highestStatPlayer).toString() == "Opponent 4") {
					gameResults[4] = gameResults[4] + 1;
				}
				
			}

			// in case of win:
			if (playersList.size() == 1) {
				System.out.println("Game End");
				break;
			}

			//alternative winning condition - check if any player has over 40 cards
//			for (int i=0;i>playersList.size();i++) {
//				if(playersList.get(i).getDeck().size() >= 40) {
//					break;
//				}
//			}
			
			
			// stop the game at the end of the round
			pressAnyKeyToContinue();

			// move on to the next round
			roundNumber++;

			//summary of the common pile where cards are put in case of a draw
			System.out.println("Common pile: " + commonPile.size());
		}
		
		// print out how many games the player won
			System.out.println("The overall winner was " + playersList.get(0).toString() +
			"\n Scores: "
			+ "\n Human: " + gameResults[0] + 
			 "\n Opponent 1: " + gameResults[1] + 
			 "\n Opponent 2: " + gameResults[2] + 
			 "\n Opponent 3: " + gameResults[3] + 
			 "\n Opponent 4: " + gameResults[4]);


		// game end (placeholder):
		GameStats gameStats = new GameStats(0, playersList.get(0).toString(), roundNumber, gameResults[0], gameResults[1], gameResults[2],
				gameResults[3], gameResults[4], drawNumber);
		return gameStats;

		

	}

	public void increaseRoundWinStat(int winner) {
		// CHANGE - PLAYER IS NOT ALWAYS ON POSITION 1
//
//		switch (winner) {
//		case 0:
//			playerRoundWin++;
//			break;
//		case 1:
//			cpu1RoundWin++;
//			break;
//		case 2:
//			cpu2RoundWin++;
//			break;
//		case 3:
//			cpu3RoundWin++;
//			break;
//		case 4:
//			cpu4RoundWin++;
//			break;
//		}
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
		System.out.println("the winner now has "+playersList.get(highestStatPlayer).getDeck().size()+" cards");
		currentHands.clear();
		commonPile.clear();
	}

	public int compareStat(int statSelection) {
		int highestStatPlayer = 0;
//		System.out.println("Debugging: value of the chosen stat for the player on position 0: "
//				+ currentHands.get(0).returnStat(statSelection));
		for (int i = 1; i < currentHands.size(); i++) {
//			System.out.println("Debugging: value of the chosen stat for the player on position " + i + ": "
//					+ currentHands.get(i).returnStat(statSelection));
			if (currentHands.get(i).returnStat(statSelection) > currentHands.get(highestStatPlayer)
					.returnStat(statSelection)) {
				highestStatPlayer = i;

			} 
		}
			for (int i = 1; i < currentHands.size(); i++)
			{
				if(i == highestStatPlayer) {
					//skip this index
				}
				else if (currentHands.get(i).returnStat(statSelection) == currentHands.get(highestStatPlayer)
					.returnStat(statSelection)) {
				// if draw, set highestStatPlayer to 6 (max value of i is 5)
				highestStatPlayer = 6;
				break;
			}
		}
		System.out.println("The highest stat player is: " + highestStatPlayer );
		return highestStatPlayer;
	}

	public void collectCurrentHands() {

		//int statComparisonArray[] = new int[playersList.size()];
		
		for (int i = 0; i < playersList.size(); i++) {

			// retrieve hand of each player

			// playersList.get(i).getDeck().peekFirst().returnStat(statSelection);
			Card currentHandCard = playersList.get(i).getDeck().pollFirst();
			currentHands.add(currentHandCard);
			 
		}

		System.out.println("Current hand size =" +currentHands.size());
		//return statComparisonArray;

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
			System.out.println("Cpu Stat Selection = "+ statSelection); 
		}
		return statSelection;
	}

	public void checkPlayerEliminated(int playerPos) {
		//NOT SURE IF playersList size is verified on each iteration?
		for (int i = 0; i < playersList.size(); i++) {
			if (playersList.get(i).getDeck().isEmpty()) {
//				if (i == playerPos  && playersList.size() > 1) {
//					System.out.println("You have been eliminated from the game");
//				} else {
					System.out.println(playersList.get(i).toString() + " was eliminated");
//				}
				//remove players without cards from the playersList
				playersList.remove(i);
				System.out.println("player list size = "+ playersList.size());
				i--;
			}
		}
	}

	public int cpuPlayCard(ArrayList<Player> playersList, int pos) {
		Card currentCpuCard = playersList.get(pos).getDeck().peekFirst();
		int cpuStatChoice = currentCpuCard.returnHighestStat(currentCpuCard);
		System.out.println("the computer stat = " + cpuStatChoice);
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

	public void dealCards(int cpuNumber, ArrayList<Card> cardList) {

		mainDeck = shuffleCards(cardList);
		//System.out.println("Main deck" + mainDeck.toString());
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

	private void pressAnyKeyToContinue() {
		System.out.println("Press \"ENTER\" to continue...");
		   Scanner sc = new Scanner(System.in);
		   sc.nextLine();
		   
	}

}
