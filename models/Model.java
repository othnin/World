package models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Observable;
import java.util.Random;
import java.util.stream.Collectors;

import javax.swing.SwingWorker;

import geography.Map;
import pathfinding.MapPath;
import pathfinding.Node;
import resources.Plants;
import animals.Animals;
import animals.ManageAnimals;
import engine.Utilities;

public class Model extends Observable{
	Map map;
	Behavior behavior;
	SwingWorker<Void, Object> runSimTask;
//	SwingWorker<Void, Void> runAStar;
	
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
	
	public int getMapSizeX() {
		return map.getMapSizeX();
	}
	
	public int getMapSizeY() {
		return map.getMapSizeY();
	}
	

	public class MsgObj {
		//private List <String> msg;
		private String msg;
		//List <Integer> numAnimals;
		
		private MsgObj(String msg2) {
			msg = msg2;
		}
		
		public String getMsg() {
			return msg;
		}
		
		public void addMsg(String msg) {
			this.msg = msg;
		}	
	}
	//TODO: Make this a static and have one object for all the statistics
	public class StatsObj {
		private int numAnimals;
		
		public StatsObj() {
			numAnimals=0;
		}
		/*
		private StatsObj(int chunks) {
			numAnimals = chunks;
		}
		*/
		public int getNumAnimals() {
			return numAnimals;
		}
		
		public void setNumAnimals(int num) {
			numAnimals = num;
		}
		public StatsObj getStatObj() {
			return this;
		}
		
	}
	


	
	public void resetMPforAllAnimals(ArrayList<Animals> allAnimals) {
		for (Animals animal : allAnimals) {
			animal.resetMP();
		}
	}


/*
	private class RunAStar extends SwingWorker<Void,Void> {
		@Override
		protected Void doInBackground() throws Exception {
			ArrayList<Animals> allAnimals;
			while(!isCancelled()) {
				allAnimals = getAllAnimals();
				for (Animals animal : allAnimals) {
					System.out.println("Name: " + animal.toString()+ " "+  animal.getID());
					AStarPathFinder path = new AStarPathFinder();
					path.returnMoves(map, getMapSizeX(), getMapSizeY(), animal.getX(), animal.getY(), 10, 10, null);
				}	
				stopSimulation();
			}
			
			return null;
			
		}
	}
	*/
	
	
	/*
	 * Takes an animal, the map, and mapPath and proceeds to move the animal through the map based on what the mapPath has
	 * 
	 * @param aminal the animal being moved
	 * @param mapPath the animals path through the map that has been prev determined
	 * @param map the current map that has been loaded
	 * @param canMove the boolean used to see if the animal has reached the dest or can't move anymore for the turn.
	 * 
	 */
	private boolean moveAnimal(Animals animal, MapPath mapPath, Map map, boolean canMove) {
				
		if (mapPath.size() ==0) {  //have reached the goal?
			return canMove = false;
		}
		int x = animal.getX();
		int y = animal.getY();
		Node moveNode = mapPath.peek();
		
		int toX = moveNode.getX();
		int toY = moveNode.getY();
		/*
		if (mapPath.size() ==1) {
			System.out.println("SZ 1");
			System.out.println("MP: " + animal.getMp() + " (" + animal.getX() + ", " +animal.getY() + ") "+ " DN: " + mapPath.getDestNode().getX() + ", " + mapPath.getDestNode().getY() + " mapPath sz: "+ mapPath.size());
			System.out.println("MoveMode: "+ map.getMoveMod(moveNode.getX(), moveNode.getY()));
		}
	*/
		if (animal.getMp() >= map.getMoveMod(toX, toY) ) {
			moveNode = mapPath.pop();
			if (y - toY == 1) {  //moving northerly
				if (x - toX == 1) {
					System.out.println("NW ");
					map.removeAnimal(x, y, animal);
					map.setAnimalLocation(x-1, y-1, animal);
					map.setMP(x-1, y-1, (int) (map.getMP(x-1, y-1, animal)- (map.getMoveMod(x-1, y-1) * map.getDiagMoveModifier(x-1, y-1))), animal);
					map.addAnimal(x-1,y-1,animal);
					
				}
				else if (x - toX ==-1){
					System.out.println(" NE");
					map.removeAnimal(x, y, animal);
					map.setAnimalLocation(x+1, y-1, animal);
					map.setMP(x+1, y-1,(int) (map.getMP(x+1, y-1, animal)- (map.getMoveMod(x+1, y-1) * map.getDiagMoveModifier(x+1, y-1))), animal);
					map.addAnimal(x+1,y-1,animal);
					
				}
				else {
					System.out.println(" N");
					map.removeAnimal(x, y, animal);
					map.setAnimalLocation(x, y-1, animal);
					map.setMP(x, y-1, map.getMP(x, y-1, animal)- map.getMoveMod(x, y-1), animal);
					map.addAnimal(x,y-1,animal);
					
				}
			} else if (y - toY == -1) {  	//moving southerly
				if (x - toX == 1) {
					System.out.println(" SW");
					map.removeAnimal(x, y, animal);
					map.setAnimalLocation(x-1, y+1, animal);
					map.setMP(x-1, y+1, (int) (map.getMP(x-1, y+1, animal)- (map.getMoveMod(x-1, y+1) * map.getDiagMoveModifier(x-1, y+1))), animal);
					map.addAnimal(x-1,y+1,animal);
					
				}
				else if (x - toX == -1) {
					System.out.println(" SE");
					map.removeAnimal(x, y, animal);
					map.setAnimalLocation(x+1, y+1, animal);
					map.setMP(x+1, y+1, (int) (map.getMP(x+1, y+1, animal)- (map.getMoveMod(x+1, y+1) * map.getDiagMoveModifier(x+1, y+1))), animal);
					map.addAnimal(x+1,y+1,animal);
					
				}
				else {
					System.out.println(" S");
					//remove animal from tile
					map.removeAnimal(x, y, animal);
					//add animal to tile
					map.addAnimal(x,y+1,animal);			
					//set animals (x,y)
					map.setAnimalLocation(x, y+1, animal);			
					map.setMP(x, y+1, map.getMP(x, y+1, animal)- map.getMoveMod(x, y+1), animal);
					
				}
			}
			else if (x - toX == -1) {
				System.out.println(" E");
				map.removeAnimal(x, y, animal);
				map.setAnimalLocation(x+1, y, animal);
				map.setMP(x+1, y, map.getMP(x+1, y, animal)- map.getMoveMod(x+1, y), animal);
				map.addAnimal(x+1, y, animal);
				
			}
			else if (x - toX == 1) {
				System.out.print(" W");
				map.removeAnimal(x, y, animal);
				map.setAnimalLocation(x-1, y, animal);
				map.setMP(x-1, y, map.getMP(x-1, y, animal)- map.getMoveMod(x-1, y), animal);
				map.addAnimal(x-1, y, animal);
				
			} else {
				//no movement
				System.out.print(" No move");
				if ( (moveNode.getX()== mapPath.getDestNode().getX()) & (moveNode.getY()==mapPath.getDestNode().getY())/* &mapPath.size() ==1*/  ) {
				//	System.out.println(" moveNode == destNode bail out !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					return canMove = false;	
				}
			}
		}
		else {
			canMove = false;
		}
		return canMove;		
	}

	
	
	private class RunSimTask extends SwingWorker<Void, Object> {
		//private Behavior behavior;
		
		/*
		 * Pushes data to the view output text window.
		 * @param msg the String that is pushed to the output text window in the view
		 * 
		 */
		public void publishData(String msg) {
			
			//publish(msg);
			publish(new MsgObj(msg));
		}
		
		
		public void publishStats(int num) {
			publish(new StatsObj());
		}


		/*
		 * The main processing loop for the sim. Runs when the user hits start and run continuously until the
		 * user hits stop.
		 */
		@Override
		protected Void doInBackground() throws Exception {
			ArrayList<Animals> allAnimals;
			boolean canMove;
			int turn =1;
			MapPath mapPath = new MapPath();
			
			while(!isCancelled()) {
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~START TURN: " + turn +"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				publishData("Start turn " + turn);
				allAnimals = getAllAnimals();
				for (Animals animal : allAnimals) {
					canMove = true;
					System.out.println("Name: " + animal.toString()+ " "+  animal.getID());
					Utilities.checkSurrondings(map, animal);
					determineBehavior(map, animal);
					mapPath =  performBehavior(map, animal);					
					while (canMove) {
						if (Utilities.checkSurrondings(map, animal)) {
							//look at animals vision and add it to grass or water array
							determineBehavior(map, animal);
							mapPath = performBehavior(map, animal);						
						}
						canMove = moveAnimal(animal, mapPath, map, canMove);
						
						//TODO: Reloading the entire map whenever we move an animal. Just need to reload the old terrain
						setChanged();
						notifyObservers();
						Thread.sleep(250);
					}
					System.out.println("");
				}	
				//		all animals moved.
				adjustDrivesforAllAnimals(allAnimals);
				resetMPforAllAnimals(allAnimals);
				map.regrowPlants();
				setChanged();
				notifyObservers();
				Thread.sleep(250);
				publishStats(1);
				publishData("End turn " + turn);
				
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~END TURN: " + turn + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				turn++;
			}
			return null;
		}
		

		@Override
		protected void done() {
			try {
				get();
				System.out.println("Operation completed");
			} catch (Exception ex) {
				System.out.println("Failed");
				ex.printStackTrace();
			}
		}
		
		@Override
		protected void process(List <Object> chunks) {
			setChanged();
			for (Object obj : chunks) {
				notifyObservers(obj);
			}			
		}
		
		
    } //End RunSimTask		
	
	
	
	public void startSimulation() {
		runSimTask = new RunSimTask();
		runSimTask.execute();
	}
		

	public void stopSimulation() {
		System.out.println("STOPSIMULATION");
		runSimTask.cancel(true);
	}
	
	public MapPath performBehavior(Map map, Animals animal) {  
		return behavior.moveDirection(animal, map);
	}
	
	public void setBehavior(Behavior behavior) {
		this.behavior = behavior;
	}
	
	
	/*
	 * We loop through all the animals and check where they ended up. If they are on water or food then check that. If there hunger is below
	 *  0 then they are dead.
	 *  
	 *  @param allAnimals our list of animals to loop through.
	 * 
	 */
	public void adjustDrivesforAllAnimals(ArrayList<Animals> allAnimals) {
		
		Iterator<Animals> iter = allAnimals.iterator();
		while (iter.hasNext()) {
			Animals animal = iter.next();
			//TODO: Right now we are doing all the animals at the end of a complete turn. Would probably be better to check at the end of the animals turn.
			//TODO: If animals moved alot last round would need to adjust more than one?
			animal.setThirst(animal.getCurrentThirst()-1);
			animal.setHunger(animal.getCurrentHunger()-1);
			
			//If herbrivore and on a plant space
			if ( (map.getPlant(animal.getX(), animal.getY())!= null) & (animal.getClass().getSuperclass().getName().equals("animals.Herbrivore"))) {
			
				
				if (  ((animals.Herbrivore) animal).getCurrentHunger() < ((animals.Herbrivore) animal).getMaxHunger()) {
			
					int grassHP = (map.getPlant(animal.getX(), animal.getY())).getHP();
					// Adjust herbrivores hunger level
					if (  ((animals.Herbrivore) animal).getCurrentHunger()   +2  < ((animals.Herbrivore) animal).getMaxHunger())  {
						((animals.Herbrivore) animal).setHunger(((animals.Herbrivore) animal).getCurrentHunger()+2);
						//Decrement Grass HP by 2
						(map.getPlant(animal.getX(), animal.getY())).setHP(grassHP-2);						
					}
					else  {
						((animals.Herbrivore) animal).setHunger(((animals.Herbrivore) animal).getMaxHunger());
						(map.getPlant(animal.getX(), animal.getY())).setHP(grassHP-1);
					}
					//Check to see if plant has been eaten
					if ((map.getPlant(animal.getX(), animal.getY())).getHP() <= 0) {
						System.out.println("Plant eaten (" + animal.getX() +", "+ animal.getY() + ")");
						((RunSimTask) runSimTask).publishData("Plant eaten (" + animal.getX() +", "+ animal.getY() + ")");
						((animals.Herbrivore) animal).removePlantFromMemory(map.getPlant(animal.getX(), animal.getY()));
						map.removePlant(animal.getX(), animal.getY(), map.getPlant(animal.getX(), animal.getY()));
					}
					
				}
			}
			
			if ( (map.getPlant(animal.getX(), animal.getY())!= null) ) {
				if (animal.getCurrentThirst() < animal.getCurrentThirst()) {
					if (  (animal.getCurrentThirst() +2)  < animal.getMaxThirst() ) {
						animal.setThirst(animal.getCurrentThirst()+2);				
					}
					else  {
						animal.setThirst(animal.getMaxThirst());	
					}
				}
			}
			
			if (animal.getCurrentHunger() < 0) {
				System.out.println("Animal "+ animal.getID() + " has died from hunger");
				((RunSimTask) runSimTask).publishData("Animal "+ animal.getID() + " has died from hunger");
				map.removeAnimal(animal.getX(),animal.getY(), animal);
				iter.remove();
			}
			if (animal.getCurrentThirst() < 0) {
				System.out.println("Animal "+ animal.getID() + " has died from thirst");
				((RunSimTask) runSimTask).publishData("Animal "+ animal.getID() + " has died from thirst");
				map.removeAnimal(animal.getX(),animal.getY(), animal);
				iter.remove();
			}
		}
	}
		

	/*
	 * Looks at the animals hunger and thirst percent and uses an equation to determine 
	 * what there behavior will be.
	 * 
	 * @param map the current map being used
	 * @param animal the current animal 
	 * 
	 */
	private void determineBehavior(Map map, Animals animal) {
		//TODO: All the below Strategy behaviors.
		//check animal hunger and thirst status
		//check if predator nearby.
		//use those to rnd determine behavior.
		//run setBehavior() with 
		
		
		//How to model hunger and thirst? Linearly f(x) = 2x or f(x) = x^2? 
		//Lets start with Hunger y= -10x+100 and Thirst y= -20x+100
		
		int hungerPercent = -10 *animal.getCurrentHunger() + 100;
		int thirstPercent = -20 * animal.getCurrentThirst() + 100;
		Random rn = new Random();
		int behavior = rn.nextInt(200);
		if (behavior <thirstPercent) {
			//thirst
			System.out.println("Thirst behavior");
			setBehavior(new ThirstBehavior());
		}
		else if ((behavior > thirstPercent) & (behavior < (hungerPercent+thirstPercent))) {
		//	System.out.println("Hunger behavior");
			setBehavior(new HungerBehavior());
		}
		else /*if ( (behavior > (hungerPercent+thirstPercent))) */{
		//	System.out.println("Random behvior");
			setBehavior(new rndMoveBehavior());
		}
	}


}
