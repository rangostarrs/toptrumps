package commandline;

public class gameStats {

	// Upon completion of the game, the user should automatically write the
	// following information about the game play to a database:

	// int for the number of draws in the game
	int draws;
	// string for the name of the game winner
	String winner;
	// int for the total number of rounds played in the game
	int totalRounds;
	// int for the number of rounds that each player won *
	int roundWin;
	// * should we have a playerRoundWin and 3 cpuRoundWin's?

	// As long as a game isn't currently in progress, the user should be able to 
	// connect to the database and get information about previous games. This should include

	// int for the total number of games played
	int totalGames;
	// int for the number of times the cpu has won **
	int cpuWin;
	// int for the number of times the player has won
	int playerWin;
	// int for the average number of draws per game
	int avgDraws;
	// int for the most rounds played in a game
	int mostRounds;

	// ** again, should we have a cpu1Win, cpu2 etc

	private static void gameplayData(int draws, String winner, int totalRounds, int roundWin) {

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

	public int getRoundWin() {
		return roundWin;
	}

	public void setRoundWin(int roundWin) {
		this.roundWin = roundWin;
	}

	public int getTotalGames() {
		return totalGames;
	}

	public void setTotalGames(int totalGames) {
		this.totalGames = totalGames;
	}

	public int getCpuWin() {
		return cpuWin;
	}

	public void setCpuWin(int cpuWin) {
		this.cpuWin = cpuWin;
	}

	public int getPlayerWin() {
		return playerWin;
	}

	public void setPlayerWin(int playerWin) {
		this.playerWin = playerWin;
	}

	public int getAvgDraws() {
		return avgDraws;
	}

	public void setAvgDraws(int avgDraws) {
		this.avgDraws = avgDraws;
	}

	public int getMostRounds() {
		return mostRounds;
	}

	public void setMostRounds(int mostRounds) {
		this.mostRounds = mostRounds;
	}

}
