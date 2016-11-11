package pathfinding;

public class Node {
	private int x;
	private int y;
	private int FC;  //Final Cost	

	public Node(int x, int y, int FC) {
		this.x = x;
		this.y = y;
		this.FC = FC;
	}
	
	public int getFC() {
		return this.FC;
	}


	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
