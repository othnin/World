package geography;

public class Hills implements Terrain{

	final int MOVEMODIFIER = 30;
	int capacity=2;
	
	@Override
	public int getMoveMod() {
		return MOVEMODIFIER;
	}


	@Override
	public int getCapacity() {
		return capacity;
	}
	
	@Override
	public String toString() {
		return "Hills";
	}


	@Override
	public boolean isWater() {
		return false;
	}

}
