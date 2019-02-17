package online.dwResources;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.Scanner;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import online.configuration.TopTrumpsJSONConfiguration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import commandline.Card;
import commandline.Player;

@Path("/toptrumps") // Resources specified here should be hosted at http://localhost:7777/toptrumps
@Produces(MediaType.APPLICATION_JSON) // This resource returns JSON content
@Consumes(MediaType.APPLICATION_JSON) // This resource can take JSON content as input
/**
 * This is a Dropwizard Resource that specifies what to provide when a user
 * requests a particular URL. In this case, the URLs are associated to the
 * different REST API methods that you will need to expose the game commands
 * to the Web page.
 * 
 * Below are provided some sample methods that illustrate how to create
 * REST API methods in Dropwizard. You will need to replace these with
 * methods that allow a TopTrumps game to be controled from a Web page.
 */
public class TopTrumpsRESTAPI {
	
	ObjectWriter oWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
	private String deckFile;
	private int numPlayers;
	private int roundNumber;
	private Deque<Card> mainDeck;
	private Deque<Card> playerDeck = new ArrayDeque<Card>();
	private Deque<Card> cpu1Deck = new ArrayDeque<Card>();
	private Deque<Card> cpu2Deck = new ArrayDeque<Card>(); 
	private Deque<Card> cpu3Deck = new ArrayDeque<Card>();
	private Deque<Card> cpu4Deck = new ArrayDeque<Card>();
	private ArrayList<Card> cardList;
	private Player playerAI1, playerAI2, playerAI3, playerAI4;
	private ArrayList<Player> playersList;
	private Player playerHuman;
	private Card currentHandCard;
	private static String headerArray[] = new String[6];
	private int highestStatPlayer;
	private int playerRoundWin, cpu1RoundWin, cpu2RoundWin, cpu3RoundWin, cpu4RoundWin;
	private ArrayList<Card> cardListCurrentHands;
	private ArrayList<Card> commonPile;

	/** A Jackson Object writer. It allows us to turn Java objects
	 * into JSON strings easily. */	
	
	/**
	 * Contructor method for the REST API. This is called first. It provides
	 * a TopTrumpsJSONConfiguration from which you can get the location of
	 * the deck file and the number of AI players.
	 * @param conf
	 */
	public TopTrumpsRESTAPI(TopTrumpsJSONConfiguration conf) {
		deckFile = conf.getDeckFile(); // card deck is loaded when the app is run
		numPlayers = conf.getNumAIPlayers() + 1; // number of ai players plus human player
	}
	
	@GET
	@Path("/setNumberOfOpponents")
	@Consumes(MediaType.APPLICATION_JSON)
	public void setNumberOfOpponents(@QueryParam("Number") int Number) throws IOException {
		numPlayers = Number + 1;
		
		playersList = new ArrayList<Player>();
		playerHuman = new Player("Human", playerDeck);
		cardList = new ArrayList<Card>();
		
		addCardsToList(deckFile);
		Collections.shuffle(cardList);
		mainDeck = new ArrayDeque<Card>(cardList);
		dealCards(numPlayers, cardList);
		
		roundNumber = 1;
	}

	@GET
	@Path("/humanCompareStats")
	public String humanCompareStats(@QueryParam("Number") int Number) throws IOException {
		
		int catIndex = Number;
		// return index of the player with the highest score
		highestStatPlayer = 0;
		for (int i = 1; i < cardListCurrentHands.size(); i++) {
			if (cardListCurrentHands.get(i).returnStat(catIndex) > cardListCurrentHands.get(highestStatPlayer).returnStat(catIndex)) {
				highestStatPlayer = i;
			}
		}

		
		giveHandsToWinner(highestStatPlayer);
		String winner = playersList.get(highestStatPlayer).getName();
		roundNumber++;
//		reorderPlayersList(playersList, highestStatPlayer);
		return winner;
	}
	
	public void reorderPlayersList(ArrayList<Player> playersList, int highestStatPlayer) {
		try {
			playersList.add(0, playersList.remove(highestStatPlayer));
		} catch (IndexOutOfBoundsException e) {}
	}
	
	public void dealCards(int cpuNumber, ArrayList<Card> cardList) {
		
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
		createPlayersArray(cpuNumber);
	}
	
	public ArrayList<Player> createPlayersArray(int cpuNumber) {
		
		playersList.add(playerHuman);
		playerAI1 = new Player("Opponent 1", cpu1Deck);
		playersList.add(playerAI1);
		if (cpuNumber > 1) {
			playerAI2 = new Player("Opponent 2", cpu2Deck);
			playersList.add(playerAI2);
			if (cpuNumber > 2) {
				playerAI3 = new Player("Opponent 3", cpu3Deck);
				playersList.add(playerAI3);
				if (cpuNumber > 3) {
					playerAI4 = new Player("Opponent 4", cpu4Deck);
					playersList.add(playerAI4);
				}
			}
		}
		
//		Collections.shuffle(playersList);
		return playersList;
	}
	
	public void addCardsToList(String fileName) {
		FileReader reader = null;

		try {

			reader = new FileReader(fileName);
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
	
	@GET
	@Path("/displayCards")
	public String displayCards() throws IOException	{
		
		cardListCurrentHands = new ArrayList<Card>();
		
		for (int i = 0; i < numPlayers; i++) {
				switch (playersList.get(i).getName()) {
				case ("Human"):
					cardListCurrentHands.add(playersList.get(i).getDeck().pollFirst());
					continue;
				case ("Opponent 1"):
					cardListCurrentHands.add(playersList.get(i).getDeck().pollFirst());
					continue;
				case ("Opponent 2"):
					cardListCurrentHands.add(playersList.get(i).getDeck().pollFirst());
					continue;
				case ("Opponent 3"):
					cardListCurrentHands.add(playersList.get(i).getDeck().pollFirst());
					continue;
				case ("Opponent 4"):
					cardListCurrentHands.add(playersList.get(i).getDeck().pollFirst());
					continue;
				default:
					System.err.println("There is no player");
				}
		}
		
				
		String currentHandsJSON = oWriter.writeValueAsString(cardListCurrentHands);
		return currentHandsJSON;
	}
	
	public void giveHandsToWinner(int highestStatPlayer) {

		for (int i = 0; i < cardListCurrentHands.size(); i++) {
			playersList.get(highestStatPlayer).getDeck().addLast(cardListCurrentHands.get(i));

		}

		cardListCurrentHands.clear();
	}
	
	@GET
	@Path("/roundNumber")
	public String roundNumber() throws JsonProcessingException {
		String roundNumberJSON = oWriter.writeValueAsString(roundNumber);
		return roundNumberJSON;
	}
	
}
