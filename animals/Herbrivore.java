package animals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import geography.Terrain;
import geography.Water;
import resources.Plants;
import resources.Resources;

public abstract class Herbrivore implements Animals{
	
	private int x;
	private int y;
	private int id;
	private int currentMp;
	private int mp;
	private final int totalMemory=15;
	private int hungry=10;
	private int currentHungry=hungry;
	private int thirst=5;
	private int currentThirst=thirst;
	private int visionDistance;
	private boolean herdBehavior;
	private static AtomicInteger uniqueId = new AtomicInteger();
	private List<Plants>  plantsInMemory = new ArrayList<Plants>(); 
	private List<Water>  waterInMemory = new ArrayList<Water>();
	
	
	Herbrivore() {
		id = uniqueId.getAndIncrement();	
	}
	
	public boolean inWaterHistory(Terrain terrain) {
		if (waterInMemory.contains(terrain)) return true;
		return false;
	}
	
	public void addWaterToMemory(Terrain water) {
		if (waterInMemory.size() < totalMemory)
			waterInMemory.add((Water) water);
		else {
			for (int i=0; i < waterInMemory.size()-1; i++) {
				waterInMemory.set(i, waterInMemory.get(i+1));
			}
			waterInMemory.set(waterInMemory.size()-1, (Water) water);			
		}
		System.out.println("H2O Buffer sz: " + waterInMemory.size());
	}
	
	public void removeWaterFromMemory(Terrain water) {
		Iterator<Water> iter = waterInMemory.iterator();
		while(iter.hasNext()) {
			Resources resource = (Resources) iter.next();
			if (resource == water) {
				iter.remove();
			//	System.out.println("***Remove plant from menory");
			}
		}
	}
	
	public List<Water> getWaterInMemory() {
		return waterInMemory;
	}
	
	public List<Plants> getPlantsInMemory() {
		return plantsInMemory;
	}
	
	public int getPlantHistorySize() {
		return plantsInMemory.size();
	}
	
	public boolean inPlantHistory(Plants plant) {
		if (plantsInMemory.contains(plant)) return true; 
		return false;
	}
	
	//Add plant to memory. if memory full shift and add the newest to the first position
	public void addPlantToMemory(Plants plant) {
		if (plantsInMemory.size() < totalMemory)
			plantsInMemory.add(plant);
		else {
			for (int i=0; i < plantsInMemory.size()-1; i++) {
				plantsInMemory.set(i, plantsInMemory.get(i+1));
			}
			plantsInMemory.set(plantsInMemory.size()-1, plant);			
		}
	}
	
	
	public void removePlantFromMemory(Plants plant) {
		//boolean res = plantsInMemory.remove(plant);System.out.println("Remove plants: " +res );
		Iterator<Plants> iter = plantsInMemory.iterator();
		while(iter.hasNext()) {
			Resources resource = iter.next();
			if (resource == plant) {
				iter.remove();
			//	System.out.println("***Remove plant from menory");
			}
		}
		
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
		x = newX;
	}

	@Override
	public void setY(int newY) {
		y = newY;
	}

	@Override
	public int getCurrentHunger() {
		return currentHungry;
	}

	@Override
	public boolean isHungry() {
		return currentHungry==0;
	}

	@Override
	public void setHunger(int newHunger) {
		currentHungry = newHunger;
	}
	
	@Override
	public int getMaxHunger() {
		return hungry;
	}
	
	public int getID() {
		return id;
	}
	
	@Override
	public int getMaxThirst() {
		return thirst;
	}
	
	@Override 
	public int getCurrentThirst() {
		return currentThirst;
	}

	@Override
	public void setThirst(int thirst) {
		this.thirst = thirst;		
	}
	
	public int getVisionDistance(){
		return this.visionDistance;
	}
	
	@Override
	public void setVisionDistance(int distance) {
		this.visionDistance = distance;
	}
	
	@Override
	public void resetMP() {
		currentMp = mp;	
	}

	@Override
	public int getMp() {
		return currentMp;
	}
	
	public void setMaxMP(int mp) {
		this.mp = mp;
		this.currentMp = mp;
	}
	
	
	@Override
	public void setMp(int newMp) {
		currentMp = newMp;
	}
	
	@Override
	public boolean areHerds() {
		return herdBehavior;
	}
	

}
