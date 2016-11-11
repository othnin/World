package resources;

public class Grass extends Plants{

	
	//how many turns until grass regrows
	private int regrowRate=3;
	private int currentRegrowRate;
	
	//how may turns grass will last while being eaten
	private int currentHP;
	private final int HP=2;
	
	public Grass(int x, int y) {
		setX(x);
		setY(y);
		setHP(HP);
		ManageResources.addResource(this);
	}
	
	
	@Override
	public int getRegrowRate() {
		return regrowRate;
	}
		
	@Override
	public int getHP() {
		return currentHP;
	}

	@Override
	public void setHP(int HP) {
		this.currentHP = HP;
	}

	@Override
	public String toString() {
		return "Grass";
	}

	@Override
	public int getCurrentRegrowRate() {
		return currentRegrowRate;
	}

	@Override
	public void setCurrentRegrowRate(int regrowRate) {
		currentRegrowRate = regrowRate;
	}

}
