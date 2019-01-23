package commandline;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TopTrumpsModel {

	private static ArrayList<Card> cardList = new ArrayList<Card>();
	static private String headerArray[] = new String[6];

	TopTrumpsModel() {

	}

	public static void addToArrayList() throws FileNotFoundException {

		FileReader reader = null;

		try {

			reader = new FileReader("StarCitizenDeck.txt");
			Scanner scanner = new Scanner(reader);

			String line = scanner.nextLine();
			String[] tokens = line.split(" ");

			for(int i=0;i<6;i++) {
				headerArray[i]=tokens[i+1];

			for (int i = 0; i < 5; i++) {
				headerArray[i] = tokens[i + 1];

			}


					String description = tokens[0];
					int size = Integer.parseInt(tokens[1]);
					int speed = Integer.parseInt(tokens[2]);
					int range = Integer.parseInt(tokens[3]);
					int firepower = Integer.parseInt(tokens[4]);
					int cargo = Integer.parseInt(tokens[4]);

					Card cardObject = new Card(description, size, speed, range, firepower, cargo);
					cardList.add(cardObject);
				}
				scanner.close();
				System.out.println(cardList.toString());
		}
		catch(FileNotFoundException exception) {

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
			System.out.println(cardList.toString());
			System.out.println(cardList.toString());
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
}
