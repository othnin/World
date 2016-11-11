package geography;

import resources.Resources;

public class Water implements Terrain, Resources{
	
	final int MOVEMODIFIER = 10;
	int capacity=2;
	private int x,y;
	
	
	public Water(int x, int y) {
		this.x = x;
		this.y =y;
	}
	
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

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setX(int newX) {
		this.x = newX;
		
	}

	@Override
	public void setY(int newY) {
		this.y = newY;
	}

}
