package resources;

public abstract class Plants implements Resources{

	private int x,y;
	
	
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
		x = newX;
	}

	@Override
	public void setY(int newY) {
		y = newY;
	}
	
	
	public abstract int getRegrowRate();
	public abstract int getCurrentRegrowRate();
	public abstract void setCurrentRegrowRate(int regrowRate);
	public abstract int getHP();
	public abstract void setHP(int HP);
	
}
