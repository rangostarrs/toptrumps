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
		private GameStats gamestats;
		private TopTrumpsModel model;
		
		public SQL() {
			connectToDB();
			
		}
		
		
		
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
				c = DriverManager.getConnection("jdbc:postgresql://localhost:5434/","postgres", "Postimees55");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.print("Connection Failed!");
				return;
			}
			
			
			
		}
		
		
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
		
		public void getGameStats() {
			
			try {

			
			
			Statement stmt = c.createStatement();         
	        ResultSet rs = stmt.executeQuery("Select Max (gameid) \r \n" +
			 "From game;");
			
	        System.out.println("The history of game statistics is the following:");
	        
	         while ( rs.next() ) {             
	        	 int gameid = rs.getInt(1);
	        	 System.out.println("There has been " + gameid + " games in total.");}
	         rs.close();
	         stmt.close();
	         Statement stm2 = c.createStatement();
	         ResultSet rs2 = stm2.executeQuery("Select Count (winner) \r \n" + "From Game \r \n" + "Where winner LIKE 'cpu%';");
	         while(rs2.next()) {
	        	 int c_wins = rs2.getInt(1);
	        	 System.out.println("CPU has won " + c_wins + " times.");
	         }
	         rs2.close();
	         stm2.close();
	         Statement stm3 = c.createStatement();
	         ResultSet rs3 = stm3.executeQuery("Select Count (winner) \r \n" + "From Game \r \n" + "Where winner LIKE 'p%';");
	         
	         while(rs3.next()) {
	        	 int h_wins = rs3.getInt(1);
	        	 System.out.println("Humans have won " + h_wins + " times.");
	         }
	         rs3.close();
	         stm3.close();
	         Statement stm4 = c.createStatement();
	         ResultSet rs4 = stm4.executeQuery("Select Avg(draws) \r \n" + "From Game;");
	         
	         while(rs4.next()) {
	        	 double avg_draws = rs4.getDouble(1);
	        	 NumberFormat formatter = new DecimalFormat("#0");
	        	 System.out.println("There has been approximately " + formatter.format(avg_draws) + " draws per game.");
	        	 
	         }
	         rs4.close();
	         stm4.close();
	         Statement stm5 = c.createStatement();
	         ResultSet rs5 = stm5.executeQuery("Select Max(totalrounds) \r \n" + "From Game;");
	         
	         while(rs5.next()) {
	        	 int highest_totalrounds = rs5.getInt(1);
	        	 System.out.println("The highest number of rounds played in any of the games has been " + highest_totalrounds + ".");
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
		
//		public void insertGameStats() {
//			TopTrumpsModel.gameID;
//			try {
//				
//				Statement stmt = c.createStatement();         
//		        
//				//Inserting game statistics after played game into the database
//				stmt.executeUpdate("INSERT INTO Game " + "VALUES (" + TopTrumpsModel. + ", " + "'" + model.getWinner() + "', " + model.getRoundNumber() + ", " + model.getDrawNumber() + ", " + model.getPlayerRoundWin() + ", " + model.getCpu1RoundWin() + ", " + model.getCpu2RoundWin() + ", " + model.getCpu3RoundWin() + ", " + model.getCpu4RoundWin() + ")");
//		         
//		        stmt.close();
//		         
//		         c.close();
//		         c = null;
//			}catch(SQLException e) {
//				System.out.println("Connection Failed!");
//				e.printStackTrace();
//				}
//			
//		}
		
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



		
			
		}

