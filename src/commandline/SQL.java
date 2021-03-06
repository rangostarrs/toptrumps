package commandline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;



public class SQL {

		private Connection c = null;
		
		private TopTrumpsModel model;
		private int gameid;
		private int h_wins;
		private int c_wins;
		private double avg_draws;
		private int highest_totalRounds;
		public SQL() {
			connectToDB();
			
		}
		
		
		//Method for connecting to the database
		
		public void connectToDB() {
			
			try {
				   Class.forName("org.postgresql.Driver");
				}
				catch(ClassNotFoundException ex) {
				   System.out.println("Error: unable to load driver class!");
				   System.exit(1);
				}
		System.out.println("PostgreSQL JDBC Driver Found!");
			
			
			
			try {
				c = DriverManager.getConnection("jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/","m_18_2417046l", "2417046l");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.print("Connection Failed!");
				return;
			}
			
			
			
		}
		
		//Method for closing the connection to the database
		public void closeConnectionToDB() {
			
			try {
				c.close();
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		//Method, which is used in the Model class to set values to the variables after the game, which are then used to insert data to the database.
		public void setgameInfotoSQL(int gameid, String winner, int totalRounds, int numberOfDraws, int playerRoundWins, int CPU1RoundWins,
				int CPU2RoundWins, int CPU3RoundWins, int CPU4RoundWins) {
			
			try {
				
				Statement stmt = c.createStatement();         
		        
				//Inserting game statistics after played game into the database
				stmt.executeUpdate("INSERT INTO Game " + "VALUES (" + gameid + ", " + "'" + winner + "', " + totalRounds + ", " + numberOfDraws + ", " + playerRoundWins + ", " + CPU1RoundWins + ", " + CPU2RoundWins + ", " + CPU3RoundWins + ", " + CPU4RoundWins + ")");
		         
		        stmt.close();
		         
		         c.close();
		         c = null;
			}catch(SQLException e) {
				System.out.println("Connection Failed!");
				e.printStackTrace();
				}
			
		}
		
		//Method to get game statistics from the database. 
	
		public void getGameStats() {
			
			try {

			
			
			Statement stmt = c.createStatement();         
	        ResultSet rs = stmt.executeQuery("Select Max (gameid) \r \n" +
			 "From game;");
			
	        System.out.println("The history of game statistics is the following:");
	        
	         while ( rs.next() ) {             
	        	 gameid = rs.getInt(1);
	        	 System.out.println("There has been " + gameid + " games in total.");}
	         rs.close();
	         stmt.close();
	         Statement stm2 = c.createStatement();
	         ResultSet rs2 = stm2.executeQuery("Select Count (winner) \r \n" + "From Game \r \n" + "Where winner LIKE 'O%';");
	         while(rs2.next()) {
	        	 c_wins = rs2.getInt(1);
	        	 System.out.println("CPU has won " + c_wins + " times.");
	         }
	         rs2.close();
	         stm2.close();
	         Statement stm3 = c.createStatement();
	         ResultSet rs3 = stm3.executeQuery("Select Count (winner) \r \n" + "From Game \r \n" + "Where winner LIKE 'H%';");
	         
	         while(rs3.next()) {
	        	 h_wins = rs3.getInt(1);
	        	 System.out.println("Humans have won " + h_wins + " times.");
	         }
	         rs3.close();
	         stm3.close();
	         Statement stm4 = c.createStatement();
	         ResultSet rs4 = stm4.executeQuery("Select Avg(draws) \r \n" + "From Game;");
	         
	         while(rs4.next()) {
	        	 avg_draws = rs4.getDouble(1);
	        	 NumberFormat formatter = new DecimalFormat("#0");
	        	 System.out.println("There has been approximately " + formatter.format(avg_draws) + " draws per game.");
	        	 
	         }
	         rs4.close();
	         stm4.close();
	         Statement stm5 = c.createStatement();
	         ResultSet rs5 = stm5.executeQuery("Select Max(totalrounds) \r \n" + "From Game;");
	         
	         while(rs5.next()) {
	        	 highest_totalRounds = rs5.getInt(1);
	        	 System.out.println("The highest number of rounds played in any of the games has been " + highest_totalRounds + ".");
	         }
	         rs5.close();
	         stm5.close();
	         
	         
	         c.close();
	         c = null;
			
		}catch(SQLException e) {
			System.out.println("Connection Failed22!");
			e.printStackTrace();
			return;
		}//Connection is completed
			
			
			
			
			
			
			
		}
		

		//MEthod for getting gameId from the database and adding 1 to it. This method is used before the game will start in order to have the correct gameID number.
		public int getGameIDfromDB() {
			int gameid = 0;
			try {
				
							
					Statement stmt = c.createStatement();         
					ResultSet rs = stmt.executeQuery("Select Max (gameid) \r \n" +
							"From game;");
							
					        
					        
					         while ( rs.next() ) {             
					        	 gameid = rs.getInt(1) + 1;
					        	 System.out.println("There has been " + gameid + " games in total.");
					        	 
					         }
					         	
					         
					         rs.close();
					         stmt.close();
					         c.close();
					         c = null;
						}catch(SQLException e) {
							System.out.println("Connection Failed!");
							e.printStackTrace();
							}
			
			return gameid;
		}

		
		//Method, which creates an integer array with the statistics and returns it. This is used to display the statistics in the online version of the game. 
		public int[]insertGameStatsOnline(){
			getGameStats();
			
			int[] statistics = {gameid,h_wins,c_wins,highest_totalRounds,(int) avg_draws};
			
			return statistics;
		
		}
		
			
		}

