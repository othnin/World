package animals;

public interface Animals {
	
	public int getX();
	public int getY();
	public void setX(int newX);
	public void setY(int newY);
	public int getHunger();
	public boolean isHungry();
	public void setHunger(int newHungry);
	public int getThirst();
	public void setThirst(int thirst);
	public int getMp();
	public void setMp(int newMp);
	public void resetMP();
	public boolean areHerds();
	public int getID();
	/*sleep(), roam(), */
}
