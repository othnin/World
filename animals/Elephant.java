package animals;

public class Elephant extends Herbrivore{

	
	private final int mp=5;
	private int currentMp = mp;
	private boolean herdBehavior=true;
	
	
	/**constructor*/
	public Elephant(int x, int y) {
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
		return "Elephant";
	}

	@Override
	public void resetMP() {
		currentMp = mp;	
	}


}
