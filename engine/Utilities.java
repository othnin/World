package engine;



import animals.Animals;
import geography.Map;
import pathfinding.MapPath;
import pathfinding.Node;




public class Utilities {
	/*
	public static final int NUM_ROWS = 20;
	public static final int NUM_COLS = 20;
	
	//TODO: Still hardwired for 20x20
	//Not used delete
	public static void printMap(Tile[][] map) {
		for (int x=0; x<NUM_ROWS;x++) {
			for (int y=0; y < NUM_COLS; y++)
				System.out.print( "("+ x +"," +y +")"  + map[x][y].getTerrain().toString() + "," + map[x][y].getAnimals().toString()+ "     ");
			System.out.println();
		}
	}
	*/
	//not used delete
	/*
	public static Tile[][] readFileMap(File inputFile) {
		
		Tile[][] map = new Tile[NUM_ROWS][NUM_COLS]; 
		
		try {
			Scanner read = new Scanner(inputFile);
			read.useDelimiter("/");
			int i=1;
			int x=0;
			int y=0;
			
			while(read.hasNext()) {
				String values = read.next();
				String[] value = values.split("\\;");
				value[0] = "geography." + value[0];
				if (value[1].equals("null"))
				//	map[x][y] = new Tile( Class.forName(value[0]).newInstance() , null);
					map[x][y] = new Tile( Class.forName(value[0]).newInstance());
				else {
					value[1] = "animals." + value[1];
				//	map[x][y] = new Tile( Class.forName(value[0]).newInstance() , Class.forName(value[1]).newInstance());
					Constructor c = Class.forName(value[1]).getConstructor(Integer.TYPE, Integer.TYPE);
					map[x][y] = new Tile( Class.forName(value[0]).newInstance() , c.newInstance(x,y));
				//	map[x][y] = new Tile( Class.forName(value[0]).newInstance() , Class.getDeclaredConstructor(value[1]).newInstance(x,y));
				}
				if (i% NUM_ROWS ==0 && (read.hasNext())) {
					read.next();
					x=0;
					y++;
				} else
					x++;
				i++;
			}			
			read.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return map;
	}
	*/
	
	
	
	/*
	 * 
	 * Check for new obj plants, water in the surronding area. What is in it's vision range.
	 * 
	 * @param: the map used
	 * @param: the animal that is looking around
	 */
	public static boolean checkSurrondings(Map map, Animals animal) {   
	
		int visionDistance = animal.getVisionDistance();
		int origX = animal.getX();
		int origY = animal.getY();
		int upperLeftX, upperLeftY;
		int lowerRightX, lowerRightY;
		boolean surrondingsChanged = false;
	
		
		//upper left coords
		if (origX - visionDistance < 0) upperLeftX = 0;
		else upperLeftX = origX - visionDistance;
		
		if (origY - visionDistance < 0) upperLeftY = 0;
		else upperLeftY = origY - visionDistance;
		
		//lower right coords
		if (origX + visionDistance > map.getMapSizeX()) lowerRightX = map.getMapSizeX()-1; 
		else lowerRightX = origX + visionDistance;
		
		if (origY + visionDistance > map.getMapSizeY()) lowerRightY = map.getMapSizeY()-1; 
		else lowerRightY = origY + visionDistance;
		
		//right now doing a herbrivore search for plants. Carnivore later.
		for (int y=upperLeftY; y < lowerRightY; y++) {
	    	for (int x=upperLeftX; x<lowerRightX; x++) {
	    			if ( (map.getTerrain(x, y).isWater()) & (!((animals.Herbrivore) animal).inWaterHistory(map.getTerrain(x, y)))) {
	    		//      System.out.println("H2O Not located lets add to memory");
	    		      ((animals.Herbrivore) animal).addWaterToMemory(map.getTerrain(x,y));
	    		      surrondingsChanged = true;
	    			}
	    		
	    		if ( (map.getPlant(x, y)!= null) & (animal.getClass().getSuperclass().getName().equals("animals.Herbrivore"))) {				
	    			//if the plant isn't in the herbrivores memory add it.
	    			if ( !((animals.Herbrivore) animal).inPlantHistory(map.getPlant(x,y))) {
	    				((animals.Herbrivore) animal).addPlantToMemory(map.getPlant(x,y));
	    				surrondingsChanged = true;
	    			}	    			
	    		}
	    	}
	    }
		return surrondingsChanged;
	}
	
	
}
