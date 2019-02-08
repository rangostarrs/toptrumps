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
	public static void main(String[] args) {

		TopTrumpsModel model = new TopTrumpsModel();
		TopTrumpsView view = new TopTrumpsView(model);
		TopTrumpsController controller = new TopTrumpsController(model, view);

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
			
//			try {
//				   Class.forName("org.postgresql.Driver");
//				}
//				catch(ClassNotFoundException ex) {
//				   System.out.println("Error: unable to load driver class!");
//				   System.exit(1);
//				}
//		System.out.println("PostgreSQL JDBC Driver Found!");
//		
//		Connection c = null;
//			
//		try {
//
//			c = DriverManager.getConnection("jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/", "m_18_2417046l", "2417046l");
//			System.out.println("Connection succeeded");
//			Statement stmt = c.createStatement();         
//	        ResultSet rs = stmt.executeQuery("Select Max (gameid) \r \n" +
//			 "From game;");
//			
//	        
//	        
//	         while ( rs.next() ) {             
//	        	 int gameid = rs.getInt(1) + 1;
//	        	 System.out.println("There has been " + gameid + " games in total.");
//	        	 model.setGameID(gameid);
//	         }
//	         	
//	         
//	         rs.close();
//	         stmt.close();
//	         c.close();
//	         c = null;
//		}catch(SQLException e) {
//			System.out.println("Connection Failed22!");
//			e.printStackTrace();
//			return;}
			
			
			
			
				int x = model.gameIntro();
				
				model.addCardsToList();
//				if(x==1) {
//					/// load the JDBC Driver 
//					try {
//						   Class.forName("org.postgresql.Driver");
//						}
//						catch(ClassNotFoundException ex) {
//						   System.out.println("Error: unable to load driver class!");
//						   System.exit(1);
//						}
//				System.out.println("PostgreSQL JDBC Driver Found!");
//				
//				
//					try {
//
//					c = DriverManager.getConnection("jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/", "m_18_2417046l", "2417046l");
//					System.out.println("Connection succeeded");
//					Statement stmt = c.createStatement();         
//			        ResultSet rs = stmt.executeQuery("Select Max (gameid) \r \n" +
//					 "From game;");
//					
//			        System.out.println("The history of game statistics is the following:");
//			        
//			         while ( rs.next() ) {             
//			        	 int gameid = rs.getInt(1);
//			        	 System.out.println("There has been " + gameid + " games in total.");}
//			         rs.close();
//			         stmt.close();
//			         Statement stm2 = c.createStatement();
//			         ResultSet rs2 = stm2.executeQuery("Select Count (winner) \r \n" + "From Game \r \n" + "Where winner LIKE 'cpu%';");
//			         while(rs2.next()) {
//			        	 int c_wins = rs2.getInt(1);
//			        	 System.out.println("CPU has won " + c_wins + " times.");
//			         }
//			         rs2.close();
//			         stm2.close();
//			         Statement stm3 = c.createStatement();
//			         ResultSet rs3 = stm3.executeQuery("Select Count (winner) \r \n" + "From Game \r \n" + "Where winner LIKE 'p%';");
//			         
//			         while(rs3.next()) {
//			        	 int h_wins = rs3.getInt(1);
//			        	 System.out.println("Humans have won " + h_wins + " times.");
//			         }
//			         rs3.close();
//			         stm3.close();
//			         Statement stm4 = c.createStatement();
//			         ResultSet rs4 = stm4.executeQuery("Select Avg(draws) \r \n" + "From Game;");
//			         
//			         while(rs4.next()) {
//			        	 double avg_draws = rs4.getDouble(1);
//			        	 NumberFormat formatter = new DecimalFormat("#0");
//			        	 System.out.println("There has been approximately " + formatter.format(avg_draws) + " draws per game.");
//			        	 
//			         }
//			         rs4.close();
//			         stm4.close();
//			         Statement stm5 = c.createStatement();
//			         ResultSet rs5 = stm5.executeQuery("Select Max(totalrounds) \r \n" + "From Game;");
//			         
//			         while(rs5.next()) {
//			        	 int highest_totalrounds = rs5.getInt(1);
//			        	 System.out.println("The highest number of rounds played in any of the games has been " + highest_totalrounds + ".");
//			         }
//			         rs5.close();
//			         stm5.close();
//			         
//			         Statement stm6 = c.createStatement();
//			         
//			         stm6.executeUpdate("INSERT INTO Game " + "VALUES (9, 'player', 45)");
//			         
//			         stm6.close();
//			         
//			         c.close();
//			         c = null;
//					
//				}catch(SQLException e) {
//					System.out.println("Connection Failed22!");
//					e.printStackTrace();
//					return;
//				}//Connection is completed
//				
//				
//				

// 			try {
// 				TopTrumpsController.startGame();
// //				TopTrumpsModel.addToArrayList();
// 			} catch (FileNotFoundException e) {
// 				// TODO Auto-generated catch block
// 				e.printStackTrace();
// 			}


//		} else if(x == 2) {
//			
//			try {
//
//				c = DriverManager.getConnection("jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/", "m_18_2417046l", "2417046l");
//				System.out.println("Connection succeeded");
//				Statement stmt = c.createStatement();         
//		        
//				//stmt.executeUpdate("INSERT INTO Game " + "VALUES (9, 'player', 45)");
//				stmt.executeUpdate("INSERT INTO Game " + "VALUES (" + model.getGameID() + ", " + "'" + model.getWinner() + "', " + model.getRoundNumber() + ", " + model.getDrawNumber() + ", " + model.getPlayerRoundWin() + ", " + model.getCpu1RoundWin() + ", " + model.getCpu2RoundWin() + ", " + model.getCpu3RoundWin() + ", " + model.getCpu4RoundWin() + ")");
//		         
//		        stmt.close();
//		         
//		         c.close();
//		         c = null;
//			}catch(SQLException e) {
//				System.out.println("Connection Failed22!");
//				e.printStackTrace();
//				return;}
			
		}

	}

}

