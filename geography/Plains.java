package geography;

public class Plains implements Terrain{

	final int MOVEMODIFIER = 1;
	int capacity=4;
	
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
		return "Plains";
	}

	@Override
	public boolean isWater() {
		return false;
	}

}
