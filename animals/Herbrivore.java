package animals;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class Herbrivore implements Animals{
	
	private int x;
	private int y;
	private int id;
	private int hungry=6;
	private int thirst=3;
	private static AtomicInteger uniqueId = new AtomicInteger();
	
	Herbrivore() {
		id = uniqueId.getAndIncrement();	
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
	public int getHunger() {
		return hungry;
	}

	@Override
	public boolean isHungry() {
		return hungry==0;
	}

	@Override
	public void setHunger(int newHunger) {
		hungry = newHunger;
	}
	
	public int getID() {
		return id;
	}
	
	@Override
	public int getThirst() {
		return thirst;
	}

	@Override
	public void setThirst(int thirst) {
		this.thirst = thirst;		
	}
	
	
	

}
