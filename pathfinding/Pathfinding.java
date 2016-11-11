package pathfinding;

import geography.Map;

public abstract class Pathfinding {
	
	
	public abstract MapPath returnMoves(Map map, int si, int sj, int ei, int ej, int[][] blocked);
}
