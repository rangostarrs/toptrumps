package commandline;

public class Card {

	private String description;
	private int size;
	private int speed;
	private int range;
	private int firepower;
	private int cargo;

	// constructor
	public Card(String d, int si, int sp, int r, int f, int c) {

		this.setDescription(d);
		this.setSize(si);
		this.setSpeed(sp);
		this.setRange(r);
		this.setFirepower(f);
		this.setCargo(c);

	}

	// setters
	public void setDescription(String d) {
		description = d;
	}

	public void setSize(int si) {
		size = si;
	}

	public void setSpeed(int sp) {
		speed = sp;
	}

	public void setRange(int r) {
		range = r;
	}

	public void setFirepower(int f) {
		firepower = f;
	}

	public void setCargo(int c) {
		cargo = c;
	}

	// getters
	public String getDescription() {
		return description;
	}

	public int getSize() {
		return size;
	}

	public int getSpeed() {
		return speed;
	}

	public int getRange() {
		return range;
	}

	public int getFirepower() {
		return firepower;
	}

	public int getCargo() {
		return cargo;
	}

	public void returnHighestCriterion(Card c) {
		
		// CREATE METHOD FOR RETURNING HIGHEST STAT
			//something like this maybe?
//		
		int firsttwo = Math.max( this.size,  this.speed);
		int nexttwo = Math.max(this.range, this.firepower);
		int bestoffour = Math.max(firsttwo, nexttwo);
		int AndTheWinnerIs = Math.max(bestoffour, cargo); 
		return AndTheWinnerIs;
		
		
//		//OR this?
//		
		int best=0;
		int [] choice = new int[5];
		choice[0]= c.size;
		choice[1]= c.speed;
		choice[2]= c.range;
		choice[3]= c.firepower;
		choice[4]= c.cargo;
		for (int i = 0;i<choice.length;i++) {
			if (choice[i]>best) {
				best = choice[i];

			}
		}
		return best;
		//int Math.max(int this.size, int this.speed);
		//AS HERE? https://stackoverflow.com/questions/12792692/need-to-find-a-max-of-three-numbers-in-java
	}

	@Override
	public String toString() {
		return ("\n" + "Description: " + description + "\n" + " > " + "Size: " + size + "\n" + " > " + "Speed: " + speed
				+ "\n" + " > " + "Range: " + range + "\n" + " > " + "Firepower: " + firepower + "\n" + " > " + "Cargo: "
				+ cargo + "\n");
	}

}
