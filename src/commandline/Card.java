package commandline;

public class Card {

	private String description;
	private int stat1;
	private int stat2;
	private int stat3;
	private int stat4;
	private int stat5;

	// constructor
	public Card(String d, int si, int sp, int r, int f, int c) {

		this.setDescription(d);
		this.setstat1(si);
		this.setstat2(sp);
		this.setstat3(r);
		this.setstat4(f);
		this.setstat5(c);

	}

	// setters
	public void setDescription(String d) {
		description = d;
	}

	public void setstat1(int si) {
		stat1 = si;
	}

	public void setstat2(int sp) {
		stat2 = sp;
	}

	public void setstat3(int r) {
		stat3 = r;
	}

	public void setstat4(int f) {
		stat4 = f;
	}

	public void setstat5(int c) {
		stat5 = c;
	}

	// getters
	public String getDescription() {
		return description;
	}

	public int getstat1() {
		return stat1;
	}

	public int getstat2() {
		return stat2;
	}

	public int getstat3() {
		return stat3;
	}

	public int getstat4() {
		return stat4;
	}

	public int getstat5() {
		return stat5;
	}
	
	public int returnStat(int statChoice) {
		
		int stat = 0;
		
		switch (statChoice) {
        case 1:  stat = getstat1();
        break;
        case 2:  stat = getstat2();
        break;
        case 3:  stat = getstat3();
        break;
        case 4:  stat = getstat4();
        break;
        case 5:  stat = getstat5();
        break;

		}
		return stat;
	}

	public int returnHighestStat(Card c) {
			
		int best=0;
		int bestIndex=0;
		int [] choice = new int[5];
		choice[0]= c.stat1;
		choice[1]= c.stat2;
		choice[2]= c.stat3;
		choice[3]= c.stat4;
		choice[4]= c.stat5;
		for (int i = 0;i<choice.length;i++) {
			if (choice[i]>best) {
				best = choice[i];
				bestIndex=i;
			}
		}
		return bestIndex;
	}

	@Override
	public String toString() {
		return ("\n" + (TopTrumpsModel.getHeaderArray())[0] + ": " + description + "\n" + " > " + TopTrumpsModel.getHeaderArray()[1] +
				": " + stat1 + "\n" + " > " + (TopTrumpsModel.getHeaderArray())[2] + ": " + stat2 + "\n" + " > " +
				(TopTrumpsModel.getHeaderArray())[3] + ": " + stat3 + "\n" + " > " + (TopTrumpsModel.getHeaderArray())[4] +
				": " + stat4 + "\n" + " > " + (TopTrumpsModel.getHeaderArray())[5] + ": " + stat5 + "\n");
	}

}
