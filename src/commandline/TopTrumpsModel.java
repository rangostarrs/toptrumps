import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

package commandline;

public class TopTrumpsModel {
	
	private ArrayList<Card> cardList = new ArrayList<Card>();
	private String headerArray[] = new String[3];

	TopTrumpsModel() {
		
	}

	
public void addToArrayList (String selectedFilePath) throws FileNotFoundException
	{

	FileReader reader = new FileReader("C:\\Users\\Monique\\Documents\\GitHub\\toptrumps\\StarCitizenDeck.txt");
	Scanner scanner = new Scanner(reader);
	
		try {


			String line = scanner.nextLine();
			String[] tokens = line.split(",");
			for(int i=0;i<5;i++) {
				headerArray[i]=tokens[i];
			}
			
				while(scanner.hasNextLine()) {
					line = scanner.nextLine();
					tokens = line.split(",");

					String description = tokens[0];
					int size = Integer.parseInt(tokens[1]);
					int speed = Integer.parseInt(tokens[2]);
					int range = Integer.parseInt(tokens[3]);
					int firepower = Integer.parseInt(tokens[4]);

					Card cardObject = new Card(description, size, speed, range, firepower);
					cardList.add(cardObject);
				}
				scanner.close();
		}
		catch(FileNotFoundException exception) {
			exception.printStackTrace();
		}
		finally {
			if(reader!=null) {
				try {
					reader.close();
				}
				catch (IOException exception) {
					exception.printStackTrace();
				}
			}
		}
	}
}
