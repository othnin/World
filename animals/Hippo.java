package animals;

public class Hippo extends Herbrivore{

	private int mp=3;
	private int currentMp = mp;
	private boolean herdBehavior=true;
	
	/**constructor*/
	public Hippo(int x, int y) {
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
		return "Hippo";
	}

	@Override
	public void resetMP() {
		currentMp = mp;	
	}
}
