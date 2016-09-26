package geography;

public class Water implements Terrain{
	
	final int MOVEMODIFIER = 1;
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
		return "Water";
	}

	@Override
	public boolean isWater() {
		return true;
	}

}
