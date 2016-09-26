package animals;

public class Zebra extends Herbrivore{

	private int mp=8;
	private int currentMp = mp;
	private boolean herdBehavior=true;
	
	/**constructor*/
	public Zebra(int x, int y) {
		setX(x);
		setY(y);
		ManageAnimals.addAnimal(this);
	}
	
	@Override
	public int getMp() {
		return currentMp;
	}
	
	@Override
	public void setMp(int newMp) {
		currentMp = newMp;
	}
	
	@Override
	public boolean areHerds() {
		return herdBehavior;
	}
	
	@Override
	public String toString() {
		return "Zebra";
	}

	@Override
	public void resetMP() {
		currentMp = mp;
	}
}

