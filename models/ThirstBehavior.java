package models;

import java.util.List;
import java.util.Stack;

import animals.Animals;
import geography.Map;
import geography.Water;
import pathfinding.AStarPathFinder;
import pathfinding.MapPath;
import resources.Plants;


public class ThirstBehavior implements Behavior{


	
	@Override
	public MapPath moveDirection(Animals animal, Map map) {
		//need to return a node path to water using A*
		//need to checkSurrondings for water. If so find the nearest via A* 
		//if not check memory for water.
		//If water found in memory. Find the nearest water via A* and return that.
		//If not begin a search pattern for water.
		MapPath bestMapPath = null;
		
		
		//Right now doing herbrivores
		if ((animal.getClass().getSuperclass().getName().equals("animals.Herbrivore"))) {
			List<Water> waters = ((animals.Herbrivore)animal).getWaterInMemory();
			
			if (waters.size() >0) {		//check if plants in memory
				//	Stack<Node> nodePath = new Stack<Node>();
				MapPath mapPath = new MapPath();	
				bestMapPath = new MapPath();	
				AStarPathFinder path = new AStarPathFinder();
				Water tempWater = waters.get(0);
				bestMapPath = path.returnMoves(map, animal.getX(), animal.getY(),tempWater.getX(),tempWater.getY(), null);
				int bestFC = bestMapPath.getFC();
				for (int i=1; i < waters.size(); i++) {
					//AStarPathFinder path = new AStarPathFinder();
					//	nodePath = path.returnMoves(map, animal.getX(), animal.getY(), x, y, null);
					Water water = waters.get(i);
					mapPath = path.returnMoves(map, animal.getX(), animal.getY(),water.getX(),water.getY(), null);
					int currentFC = mapPath.getFC();
					if (currentFC < bestFC) {
						bestMapPath = mapPath;
						bestFC = currentFC;
					}
				
				}
				return bestMapPath;	
			}   
			else {//else begin search pattern. 
				//System.out.println("************************************plant.size error in ThirstBehavior********");
				//Decide which way to go. Start one direction then give high percent chance of keep moving that direction.
				/*
				int moveDistance = 2 * animal.getVisionDistance();  //we are going to move twice as far as we can see.
				int origX = animal.getX();
				int origY = animal.getY();
				int upperLeftX, upperLeftY;
				int lowerRightX, lowerRightY;

				//upper left coords
				if (origX - moveDistance  < 0) upperLeftX = 0;
				else upperLeftX = origX - moveDistance ;
				
				if (origY - moveDistance  < 0) upperLeftY = 0;
				else upperLeftY = origY - moveDistance ;
				
				//lower right coords
				if (origX + moveDistance  > map.getMapSizeX()) lowerRightX = map.getMapSizeX()-1; 
				else lowerRightX = origX + moveDistance ;
				
				if (origY + moveDistance  > map.getMapSizeY()) lowerRightY = map.getMapSizeY()-1; 
				else lowerRightY = origY + moveDistance ;
				*/
				rndMoveBehavior rndMove = new rndMoveBehavior(); 
				return rndMove.moveDirection(animal, map);
				
				
			}
		} 		
	return null;
	}

}
