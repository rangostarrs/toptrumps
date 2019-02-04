package commandline;

public class GameStats {

	static // Upon completion of the game, the user should automatically write the
	// following information about the game play to a database:

    //A generated gameId number for the database
	int gameID;
	// string for the name of the game winner
	String winner;


	// int for the total number of rounds played in the game
	int totalRounds;
	// int for the number of rounds that each player won *
	int playerRoundWin;
	int cpu1RoundWin;
	int cpu2RoundWin;
	int cpu3RoundWin;
	int cpu4RoundWin;
	
	// int for the number of draws in the game
	int draws;
	// * should we have a playerRoundWin and 3 cpuRoundWin's?

	// As long as a game isn't currently in progress, the user should be able to
	// connect to the database and get information about previous games. This should
	// include
	

	//A generated ResultID number for the database. Contains 
	//int ResultID;
	// int for the total number of games played
	//int totalGames;
	// int for the number of times the cpu has won **
	//int cpuWin;
	// int for the number of times the player has won
	//int playerWin;
	// int for the average number of draws per game
	//int avgDraws;
	// int for the most rounds played in a game
	//int mostRounds;

	// ** again, should we have a cpu1Win, cpu2 etc

	//constructor
	public GameStats(int gid, String w, int tr, int prw, int cpu1, int cpu2, int cpu3, int cpu4, int d) {


		this.setGameID(gid);
		this.setWinner(w);
		this.setTotalRounds(tr);
		this.setPlayerRoundWin(prw);
		this.setCpu1RoundWin(cpu1);
		this.setCpu2RoundWin(cpu2);
		this.setCpu3RoundWin(cpu3);
		this.setCpu4RoundWin(cpu4);
		this.setDraws(d);
		
	}

	private static void gameplayData(int draws, String winner, int totalRounds, int roundWin) {

	}

	private static void previousGameData(int totalGames, int cpuWin, int playerWin, int avgDraws, int mostRounds) {

	}

	public static int getGameID() {
		return gameID;
	}
	public void setGameID(int gameID) {
		this.gameID = gameID;
	}
	public int getCpu4RoundWin() {
		return cpu4RoundWin;
	}
	public void setCpu4RoundWin(int cpu4RoundWin) {
		this.cpu4RoundWin = cpu4RoundWin;
	}
	
	public int getDraws() {
		return draws;
	}

	public void setDraws(int draws) {
		this.draws = draws;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public int getTotalRounds() {
		return totalRounds;
	}

	public void setTotalRounds(int totalRounds) {
		this.totalRounds = totalRounds;
	}

	public int getPlayerRoundWin() {
		return playerRoundWin;
	}

	public void setPlayerRoundWin(int playerRoundWin) {
		this.playerRoundWin = playerRoundWin;
	}

	public int getCpu1RoundWin() {
		return cpu1RoundWin;
	}

	public void setCpu1RoundWin(int cpu1RoundWin) {
		this.cpu1RoundWin = cpu1RoundWin;
	}

	public int getCpu2RoundWin() {
		return cpu2RoundWin;
	}

	public void setCpu2RoundWin(int cpu2RoundWin) {
		this.cpu2RoundWin = cpu2RoundWin;
	}

	public int getCpu3RoundWin() {
		return cpu3RoundWin;
	}

	public void setCpu3RoundWin(int cpu3RoundWin) {
		this.cpu3RoundWin = cpu3RoundWin;
	}

//	public int getTotalGames() {
//		return totalGames;
//	}
//
//	public void setTotalGames(int totalGames) {
//		this.totalGames = totalGames;
//	}
//
//	public int getCpuWin() {
//		return cpuWin;
//	}
//
//	public void setCpuWin(int cpuWin) {
//		this.cpuWin = cpuWin;
//	}
//
//	public int getPlayerWin() {
//		return playerWin;
//	}
//
//	public void setPlayerWin(int playerWin) {
//		this.playerWin = playerWin;
//	}
//
//	public int getAvgDraws() {
//		return avgDraws;
//	}
//
//	public void setAvgDraws(int avgDraws) {
//		this.avgDraws = avgDraws;
//	}
//
//	public int getMostRounds() {
//		return mostRounds;
//	}
//
//	public void setMostRounds(int mostRounds) {
//		this.mostRounds = mostRounds;
//	}

}
