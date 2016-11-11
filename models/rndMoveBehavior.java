package models;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import animals.Animals;
import geography.Map;
import pathfinding.MapPath;
import pathfinding.Node;

public class rndMoveBehavior implements Behavior{
	Stack<Node> nodePath = new Stack<Node>();
	ArrayList<Node> nodePathChoice = new ArrayList<Node>();
	MapPath mapPath = new MapPath();
	ArrayList<Node> moveNodePath = new ArrayList<Node>();
	int x,y;
	
	//TODO: Completely arbitrary number for the number of steps taken. Can cause an error if too many no moves are recorded.
	int numSteps = 20;
	
	
	/*
	 * TODO: Code isn't as tight as it can be. Needs to be refactored.
	 */
	
	
	
	ArrayList<Node> getDirectionsAllowed(Map map, Animals animal) {
		
		x = animal.getX();
		y = animal.getY();
		int mp = animal.getMp();
		int columnCount = map.getMapSizeX()-1;
		int rowCount = map.getMapSizeY()-1;
		nodePathChoice.clear();
		
		//can I move direction?
		//mapPath.addNode(x, y, 0);
		nodePathChoice.add(new Node(x, y, 0));		//can always not move
		if ( (y != 0) && (mp >= map.getTerrain(x, y-1).getMoveMod())) { //North
			nodePathChoice.add(new Node(x, y-1, 0));
	//		System.out.println("N");
		}
		if ( (y != rowCount ) && (mp >= map.getTerrain(x, y+1).getMoveMod())) {	//South
			nodePathChoice.add(new Node(x, y+1, 0));
	//		System.out.println("S");
		}
		if ( (x !=  columnCount) &&(mp >= map.getTerrain(x+1, y).getMoveMod())) {	//East
			nodePathChoice.add(new Node(x+1, y, 0));
	//		System.out.println("E");
		}
		if ((x != 0) && (mp >= map.getTerrain(x-1, y).getMoveMod()))	{	//west
			nodePathChoice.add(new Node(x-1, y, 0));
	//		System.out.println("W");
		}
		if ( (x!=columnCount & y!=0 ) && (mp >= (map.getTerrain(x+1, y-1).getMoveMod()) * map.getDiagMoveModifier(x+1, y-1)) )  {  //northeast
			nodePathChoice.add(new Node(x+1, y-1, 0));
	//		System.out.println("NE");
		}
		if ( (x!=0 & y!=0) && (mp >= (map.getTerrain(x-1, y-1).getMoveMod())* map.getDiagMoveModifier(x-1, y-1)) )  {  //northwest
			nodePathChoice.add(new Node(x-1, y-1, 0));
	//		System.out.println("NW");
		}
		if ( (x!=columnCount & y!=rowCount) &&(mp >= (map.getTerrain(x+1, y+1).getMoveMod()) * map.getDiagMoveModifier(x+1, y+1)))  {  //southeast
			nodePathChoice.add(new Node(x+1, y+1, 0));
	//		System.out.println("SE");
		}
		if ( (x!=0 & y!=rowCount) &&(mp >= (map.getTerrain(x-1, y+1).getMoveMod()) * map.getDiagMoveModifier(x-1, y+1)))  {  //southwest
			nodePathChoice.add(new Node(x-1, y+1, 0));
	//		System.out.println("SW");
		}
		return nodePathChoice;
	}
	
	

	@Override
	public MapPath moveDirection(Animals animal, Map map) {
		//System.out.println("Start rndmovebehavior::movedirection");
		for (int i=0; i < numSteps; i++) {
			nodePathChoice = getDirectionsAllowed(map, animal);
			int rnd = new Random().nextInt(nodePathChoice.size());
			moveNodePath.add(nodePathChoice.get(rnd));	  //select a random path and add it to the path.	
		}
		for (int i=moveNodePath.size(); i > 0; i-- ) {
			if (i==moveNodePath.size()) {
				Node destNode = moveNodePath.get(i-1);
				mapPath.addDestNode(destNode.getX(), destNode.getY(), destNode.getFC());
			}
				//nodePath.push(moveNodePath.remove(i-1));
			mapPath.push(moveNodePath.remove(i-1));
		}
	//	System.out.println("End movedirection");
		return mapPath;
	}
	
}


