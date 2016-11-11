package animals;

public class Elephant extends Herbrivore{

	
	private final int elephantMP=50;
	private boolean elephantHerdBehavior=true;
	private final int elephantVisionDistance = 5;
	
	
	/**constructor*/
	public Elephant(int x, int y) {
		setX(x);
		setY(y);
		setMaxMP(elephantMP);
		setVisionDistance(elephantVisionDistance);
		ManageAnimals.addAnimal(this);
	}
	
	
	@Override
	public String toString() {
		return "Elephant";
	}



}
