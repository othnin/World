package animals;

import geography.Terrain;
import geography.Water;

public interface Animals {
	
	public int getX();
	public int getY();
	public void setX(int newX);
	public void setY(int newY);
	public int getCurrentHunger();
	public int getMaxHunger();
	public boolean isHungry();
	public void setHunger(int newHungry);
	public int getMaxThirst();
	public int getCurrentThirst();
	public void setThirst(int thirst);
	public int getMp();
	public void setMp(int newMp);
	public void setMaxMP(int mp);
	public void resetMP();
	public boolean areHerds();
	public int getID();
	public int getVisionDistance();
	public void setVisionDistance(int visionDistance);

	
	/*
	public boolean inWaterHistory(Terrain terrain);
	public void addWaterToMemory(Water water);
	public void removeWaterFromMemory(Water water);
	*/
	/*sleep(), roam(), */
}
