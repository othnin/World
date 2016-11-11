package models;

import java.util.List;
import animals.Animals;
import geography.Map;
import pathfinding.AStarPathFinder;
import pathfinding.MapPath;
import resources.Plants;

public class HungerBehavior implements Behavior{

	@Override
	public MapPath moveDirection(Animals animal, Map map) {
		//need to return a node path to water using A*
		//Not sure  if I need to checkSurrondings for food. If so find the nearest via A* 
		//check memory for nearest water.
		//If water found in memory. Find the nearest water via A* and return that.
		//If not begin a search pattern for water.
		MapPath bestMapPath = null;
		
		
		//Right now doing herbrivores
		if ((animal.getClass().getSuperclass().getName().equals("animals.Herbrivore"))) {
			List<Plants> plants = ((animals.Herbrivore)animal).getPlantsInMemory();
			
			if (plants.size() >0) {		//check if plants in memory
				//	Stack<Node> nodePath = new Stack<Node>();
				MapPath mapPath = new MapPath();	
				bestMapPath = new MapPath();	
				AStarPathFinder path = new AStarPathFinder();
				Plants tempPlant = plants.get(0);
				bestMapPath = path.returnMoves(map, animal.getX(), animal.getY(),tempPlant.getX(),tempPlant.getY(), null);
				int bestFC = bestMapPath.getFC();
				for (int i=1; i < plants.size(); i++) {
					//AStarPathFinder path = new AStarPathFinder();
					//	nodePath = path.returnMoves(map, animal.getX(), animal.getY(), x, y, null);
					Plants plant = plants.get(i);
					mapPath = path.returnMoves(map, animal.getX(), animal.getY(),plant.getX(),plant.getY(), null);
					int currentFC = mapPath.getFC();
					if (currentFC < bestFC) {
						bestMapPath = mapPath;
						bestFC = currentFC;
					}
				
				}
			}   
			else {//else begin search pattern. 
				//System.out.println("************************************plant.size error in HungerBehavior********");
				//TODO: Need to implement a better search pattern than random
				rndMoveBehavior rndMove = new rndMoveBehavior(); 
				return rndMove.moveDirection(animal, map);
				
			}
		} 		
		return bestMapPath;
	}

}
