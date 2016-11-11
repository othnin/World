package animals;

public class Hippo extends Herbrivore{

	private int hippoMP=30;
	private boolean hippoHerdBehavior=true;
	private final int hippoVisionDistance=4;
	
	/**constructor*/
	public Hippo(int x, int y) {
		setX(x);
		setY(y);
		setMaxMP(hippoMP);
		setVisionDistance(hippoVisionDistance);
		ManageAnimals.addAnimal(this);
	}

	
	@Override
	public String toString() {
		return "Hippo";
	}


}
