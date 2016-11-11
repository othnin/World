package animals;

public class Zebra extends Herbrivore{

	private int zebraMP=80;
	//
	private boolean zebraHerdBehavior=true;
	private final int zebraVisionDistance=6;
	
	/**constructor*/
	public Zebra(int x, int y) {
		setX(x);
		setY(y);
		setVisionDistance(zebraVisionDistance);
		setMaxMP(zebraMP);
		ManageAnimals.addAnimal(this);
	}
	
	
	@Override
	public String toString() {
		return "Zebra";
	}

	
}

