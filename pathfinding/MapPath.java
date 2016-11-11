package pathfinding;

import java.util.Stack;

public class MapPath {
	static int highFC; //Final Cost(highest for the path), may not need now that I have a destinationNode
	private Stack<Node> nodePath = new Stack<Node>();
	public Node destinationNode;		

	public void addNode(int x, int y, int FC) {
		nodePath.push(new Node(x, y, FC));
	}
	
	public Node peek() {
		return nodePath.peek();
	}
	
	public Node pop() {
		return nodePath.pop();
	}
	
	public void push(Node node) {
		nodePath.push(node);
	}
	
	public void setFC(int FC) {
		MapPath.highFC = FC;
	}
	
	public int getFC() {
		return MapPath.highFC;
	}
	
	public int size() {
		return nodePath.size();
	}

	public void addDestNode(int x, int y, int FC) {
		destinationNode = new Node(x, y, FC);
		
	}
	
	public Node getDestNode() {
		return destinationNode;
	}
	
	public int getDestX() {
		return destinationNode.getX();
	}
	
	public int getDestY() {
		return destinationNode.getY();
	}
	
}