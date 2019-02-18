		package commandline;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;


/**
 * Top Trumps command line application
 */
public class TopTrumpsCLIApplication {

	/**
	 * This main method is called by TopTrumps.java when the user specifies that
	 * they want to run in command line mode. The contents of args[0] is whether we
	 * should write game logs to a file.
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws FileNotFoundException {

		TopTrumpsModel model = new TopTrumpsModel();
		

		boolean writeGameLogsToFile = false; // Should we write game logs to file?
		// if (args[0].equalsIgnoreCase("true")) writeGameLogsToFile=true; // Command
		// line selection

		// State
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application

		// Loop until the user wants to exit the game
		while (!userWantsToQuit) {

			// ----------------------------------------------------
			// Add your game logic here based on the requirements
			// ----------------------------------------------------

			userWantsToQuit = true; // use this when the user wants to exit the game
			
//			
			   SQL connect = new SQL(); // Creating a new SQL object just for the sake of connecting to the database in order to get a new gameID 
			   int gameid = connect.getGameIDfromDB();
			   model.setGameID(gameid);
			
				int x = model.gameIntro();
				
				model.addCardsToList();
				if(x==1) {
					
					SQL db = new SQL();
					db.getGameStats();
				
				

				} else if(x == 2) {
			
					//Nothing at the moment, we essentially do not need this..
					

	}

	}
	}
}