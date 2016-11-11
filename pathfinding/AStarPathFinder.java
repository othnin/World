package pathfinding;

import java.util.PriorityQueue;
import geography.Map;


public class AStarPathFinder extends Pathfinding{
	//TODO: move to a constants file
	public static final double  DIAGONAL_COST = 1.4;
    public static final double V_H_COST = 1;
	
    static class Cell{  
        int heuristicCost = 0; //Heuristic cost
        int finalCost = 0; //G+H
        int i, j;
        Cell parent; 
        
        Cell(int i, int j){
            this.i = i;
            this.j = j; 
        }
        
        @Override
        public String toString(){
            return "["+this.i+", "+this.j+"]";
        }
    }
    
    //Blocked cells are just null Cell values in grid
    static Cell [][] grid = new Cell[5][5];
    
    static PriorityQueue<Cell> open;
     
    static boolean closed[][];
    static int startI, startJ;
    static int endI, endJ;
            
    public static void setBlocked(int i, int j){
        grid[i][j] = null;
    }
    
    public static void setStartCell(int i, int j){
        startI = i;
        startJ = j;
    }
    
    public static void setEndCell(int i, int j){
        endI = i;
        endJ = j; 
    }
    
    static void checkAndUpdateCost(Cell current, Cell t, double d){
        if(t == null || closed[t.i][t.j])return;
        int t_final_cost = (int) (t.heuristicCost+d);
        
        boolean inOpen = open.contains(t);
        if(!inOpen || t_final_cost<t.finalCost){
            t.finalCost = t_final_cost;
            t.parent = current;
            if(!inOpen)open.add(t);
        }
    }
    
    public static int getMoveModifier(Map map, int x, int y) {
    	return map.getMoveMod(x, y);
    }
    
    public static void AStar(Map map){ 
        
        //add the start location to open list.
        open.add(grid[startI][startJ]);
        
        Cell current;
        
        while(true){ 
            current = open.poll();
            if(current==null)break;
            closed[current.i][current.j]=true; 

            if(current.equals(grid[endI][endJ])){
                return; 
            } 

            Cell t;
            int cost;
            if(current.i-1>=0){
                t = grid[current.i-1][current.j];
                cost = getMoveModifier(map, current.i-1, current.j);
                checkAndUpdateCost(current, t, current.finalCost+(cost*V_H_COST)); 

                if(current.j-1>=0){                      
                    t = grid[current.i-1][current.j-1];
                    cost = getMoveModifier(map, current.i-1, current.j-1);
                    checkAndUpdateCost(current, t, current.finalCost+(cost*DIAGONAL_COST)); 
                }

                if(current.j+1<grid[0].length){
                    t = grid[current.i-1][current.j+1];
                    cost = getMoveModifier(map, current.i-1, current.j+1);
                    checkAndUpdateCost(current, t, current.finalCost+(cost*DIAGONAL_COST)); 
                }
            } 

            if(current.j-1>=0){
                t = grid[current.i][current.j-1];
                cost = getMoveModifier(map, current.i, current.j-1);
                checkAndUpdateCost(current, t, current.finalCost+(cost*V_H_COST)); 
            }

            if(current.j+1<grid[0].length){
                t = grid[current.i][current.j+1];
                cost = getMoveModifier(map, current.i, current.j+1);
                checkAndUpdateCost(current, t, current.finalCost+(cost*V_H_COST)); 
            }

            if(current.i+1<grid.length){
                t = grid[current.i+1][current.j];
                cost = getMoveModifier(map, current.i+1, current.j);
                checkAndUpdateCost(current, t, current.finalCost+(cost*V_H_COST)); 

                if(current.j-1>=0){
                    t = grid[current.i+1][current.j-1];
                    cost = getMoveModifier(map, current.i+1, current.j-1);
                    checkAndUpdateCost(current, t, current.finalCost+(cost*DIAGONAL_COST)); 
                }
                
                if(current.j+1<grid[0].length){
                   t = grid[current.i+1][current.j+1];
                   cost = getMoveModifier(map, current.i+1, current.j+1);
                    checkAndUpdateCost(current, t, current.finalCost+(cost*DIAGONAL_COST)); 
                }  
            }
        } 
        System.out.println("END");
    }
    
    
    public void displayScores(Map map) {
    	int x = map.getMapSizeX();
    	//int y = map.getMapSizeY();
    	System.out.println("\nScores for cells: ");
    	for(int i=0;i<x;++i){
    		for(int j=0;j<x;++j){
    			if(grid[i][j]!=null)System.out.printf("%-3d ", grid[i][j].finalCost);
    			else System.out.print("BL  ");
    		}
    		System.out.println();
    	}
    	System.out.println();
    }
    
    
    public void displayInitalMap(Map map, int si, int sj, int ei, int ej) {
    	int x = map.getMapSizeX();
    	int y = map.getMapSizeY();
    	 System.out.println("Grid: ");
         for(int i=0;i<x;++i){
             for(int j=0;j<y;++j){
                if(i==si&&j==sj)System.out.print("SO  "); //Source
                else if(i==ei && j==ej)System.out.print("DE  ");  //Destination
                else if(grid[i][j]!=null) {
                	String TT = map.getTerrainType(i, j);
                	System.out.printf("%-3s ", TT);
                }
                else System.out.print("BL  "); 
             }
             System.out.println();
         } 
         System.out.println();
    }
    
    /**
    *Params :
    *map = map to pass 
    *si, sj = start location's x and y coordinates
    *ei, ej = end location's x and y coordinates
    *int[][] blocked = array containing inaccessible cell coordinates
    */
    public MapPath returnMoves(Map map, int si, int sj, int ei, int ej, int[][] blocked) {
    	int x = map.getMapSizeX();
    	int y = map.getMapSizeY();
    	grid = new Cell[x][y];
        closed = new boolean[x][y];
        open = new PriorityQueue<>((Object o1, Object o2) -> {
             Cell c1 = (Cell)o1;
             Cell c2 = (Cell)o2;

             return c1.finalCost<c2.finalCost?-1:
                     c1.finalCost>c2.finalCost?1:0;
         });
        //Set start position
        setStartCell(si, sj);  //Setting to 0,0 by default. Will be useful for the UI part

        //Set End Location
        setEndCell(ei, ej); 
        
        //Heuristic Cost
        for(int i=0;i<x;++i){
           for(int j=0;j<y;++j){
               grid[i][j] = new Cell(i, j);
               grid[i][j].heuristicCost = Math.abs(i-endI)+Math.abs(j-endJ);
         //      System.out.print(grid[i][j].heuristicCost+" ");
           }
         //  System.out.println();
        }
        grid[si][sj].finalCost = 0;
    
        /*
          Set blocked cells. Simply set the cell values to null
          for blocked cells.
        */
        /*
        for(int i=0;i<blocked.length;++i){
            setBlocked(blocked[i][0], blocked[i][1]);
        }*/
               
        AStar(map); 
       
        MapPath mapPath = new MapPath();
        if(closed[endI][endJ]){    	
            //Trace back the path 
             Cell current = grid[endI][endJ];
     //        System.out.print(current + " FC: " + current.finalCost);
             mapPath.addNode(current.i, current.j, current.finalCost);
             mapPath.addDestNode(current.i, current.j, current.finalCost);
             mapPath.setFC(current.finalCost);  //may not need now that I have a destinationNode
             while(current.parent!=null){
          //  	 System.out.print(" -> "+current.parent + " FC: " + current.finalCost);
            	 mapPath.addNode(current.i, current.j, current.finalCost);
                 current = current.parent;
             } 
      //       System.out.println();
         }
       return mapPath;
       }
          
     /*
    public static void main(String[] args) throws Exception{   
    	/*
        test(1, 5, 5, 0, 0, 3, 2, new int[][]{{0,4},{2,2},{3,1},{3,3}}); 
        test(2, 5, 5, 0, 0, 4, 4, new int[][]{{0,4},{2,2},{3,1},{3,3}});   
        test(3, 7, 7, 2, 1, 5, 4, new int[][]{{4,1},{4,3},{5,3},{2,3}});
        
        test(1, 5, 5, 0, 0, 4, 4, new int[][]{{3,4},{3,3},{4,3}});
        
        */
   /* }*/
    
}


