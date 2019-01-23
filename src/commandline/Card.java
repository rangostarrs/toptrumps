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

	@Override
	public String toString() {
		return ("\n" + "Description: " + description + "\n" + 
				" > " + "Size: " + size + "\n" + 
				" > " + "Speed: " + speed + "\n" + 
				" > " + "Range: " + range + "\n" + 
				" > " + "Firepower: " + firepower + "\n" + 
				" > " + "Cargo: " + cargo + "\n");

	}
}
