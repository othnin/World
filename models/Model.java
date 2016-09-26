package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javax.swing.SwingWorker;

import geography.Map;
import plants.Plants;
import animals.Animals;
import animals.ManageAnimals;

public class Model extends Observable{
	Map map;
	Behavior behavior;
	SwingWorker<Void, Void> runSimTask;
	
	
	public Model() {
		map = new Map();
	}
	
	public void readMapFile(String mapFile) {
		map.readMapFile(mapFile);
	}
	
	public void printMap() {
		System.out.println("*****Inside printmap");
		map.printMap();
	}
	//----called from view, start
	public String getTerrainType(int x, int y) {
		return map.getTerrainType(x, y);
	}
	
	public List<Animals> getAnimals(int x, int y) {
		return map.getAnimals(x, y);
	}
	
	public Plants getPlant(int x, int y) {
		return map.getPlant(x, y);
	}	
	
	public ArrayList<Animals> getAllAnimals() {
		return ManageAnimals.getAllAnimals();
	}

	ArrayList<Direction> getDirectionsAllowed(Map map, Animals animal) {
		ArrayList<Direction> moveArray = new ArrayList<Direction>();
		int x = animal.getX();
		int y = animal.getY();
		int mp = animal.getMp();
		
		//can I move direction?
		//TODO: change the hard coded 0 and 19 to a variable based on the
		//actual settings.
		if ( (y != 0) && (mp >= map.getTerrain(x, y-1).getMoveMod()))  //North
			moveArray.add(Direction.North);
		if ( (y != 19 ) && (mp >= map.getTerrain(x, y+1).getMoveMod()))	//South
			moveArray.add(Direction.South);
		if ( (x !=  19) &&(mp >= map.getTerrain(x+1, y).getMoveMod()))	//East
			moveArray.add(Direction.East);
		if ((x != 0) && (mp >= map.getTerrain(x-1, y).getMoveMod()))		//west
			moveArray.add(Direction.West);
		return moveArray;
	}
	
	private void moveAnimal(Animals animal, Direction direction, Map map) {
		
		int x = animal.getX();
		int y = animal.getY();
		
		switch (direction) {
		case North:
			map.removeAnimal(x, y, animal);
			map.setAnimalLocation(x, y-1, animal);
			map.setMP(x, y-1, map.getMP(x, y-1, animal)- map.getMoveMod(x, y-1), animal);
			map.addAnimal(x,y-1,animal);
			break;
			
		case South:
			//remove animal from tile
			map.removeAnimal(x, y, animal);
			//add animal to tile
			map.addAnimal(x,y+1,animal);			
			//set animals (x,y)
			map.setAnimalLocation(x, y+1, animal);			
			map.setMP(x, y+1, map.getMP(x, y+1, animal)- map.getMoveMod(x, y+1), animal);
			break;
			
		case East:
			map.removeAnimal(x, y, animal);
			map.setAnimalLocation(x+1, y, animal);
			map.setMP(x+1, y, map.getMP(x+1, y, animal)- map.getMoveMod(x+1, y), animal);
			map.addAnimal(x+1,y,animal);
			break;
			
		case West:
			map.removeAnimal(x, y, animal);
			map.setAnimalLocation(x-1, y, animal);
			map.setMP(x-1, y, map.getMP(x-1, y, animal)- map.getMoveMod(x-1, y), animal);
			map.addAnimal(x-1,y,animal);
			break;
			
		}
	}
	
	public void resetMPforAllAnimals(ArrayList<Animals> allAnimals) {
		for (Animals animal : allAnimals) {
			animal.resetMP();
		}
	}

	private class RunSimTask extends SwingWorker<Void, Void> {
		@Override
		protected Void doInBackground() throws Exception {
			ArrayList<Direction> moveDirectionArray;
			ArrayList<Animals> allAnimals;
			while(!isCancelled()) {
				allAnimals = getAllAnimals();
				for (Animals animal : allAnimals) {
					moveDirectionArray = getDirectionsAllowed(map, animal);	
					while (moveDirectionArray.size() > 0) {
						Direction d = determineBehavior(moveDirectionArray, map, animal);
						moveAnimal(animal, d, map);
						//TODO: Reloading the entire map whenever we move an animal. Just need to reload the old terrain
						setChanged();
						notifyObservers();
						Thread.sleep(500);
						moveDirectionArray = getDirectionsAllowed(map, animal);		
					}
				}	
				//		all animals moved.
				resetMPforAllAnimals(allAnimals);	
			}
			return null;
		}
	/*	
		@Override
		protected void process() {
			
		}
		*/
	}	
	
	public void startSimulation() {
		runSimTask = new RunSimTask();
		runSimTask.execute();
	}
		
	public void stopSimulation() {
		runSimTask.cancel(true);
	}
	
	public Direction  perfromBehavior(ArrayList<Direction> moveDirectionArray, Animals animal, Map map) {  
		return behavior.moveDirection(moveDirectionArray, animal, map);
	}
	
	public void setBehavior(Behavior behavior) {
		this.behavior = behavior;
	}
	
	
	private Direction determineBehavior(ArrayList<Direction> moveDirectionArray, Map map, Animals animal) {
		//TODO: All the below Strategy behaviors.
		//check animal hunger and thirst status
		//check if predator nearby.
		//use those to rnd determine behavior.
		//run setBehavior() with 
		setBehavior(new rndMoveBehavior());
		return perfromBehavior(moveDirectionArray, animal, map);
	}


}
