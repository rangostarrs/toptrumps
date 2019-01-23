package commandline;

public class Card {
  private String description;
  private int size;
  private int speed;
  private int range;
  private int firepower;

	//constructor
	public Card(String d, int si, int sp, int r, int f) {
	this.setDescription(d);
	this.setSize(si);
	this.setSpeed(sp);
    this.setRange(r);
    this.setFirepower(f);
	}

	//setters
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

	//getters
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

	 @Override
	    public String toString() {
	         return ("Description: " + description + "\n" + 
	                     "Size: " + size + "\n" + 
	                     "Speed: " + speed + "\n" + 
                       "Range: " + range + "\n" + 
                       "Firepower: " + firepower);
	    }
}
