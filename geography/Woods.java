package geography;

public class Woods implements Terrain{

	final int MOVEMODIFIER = 20;
	int capacity=3;
	
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
		return "Woods";
	}

	@Override
	public boolean isWater() {
		return false;
	}

}
