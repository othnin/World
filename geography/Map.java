package geography;

import java.util.List;
import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import animals.Animals;
import geography.Tile;
import plants.Plants;

public class Map {
	private int rows;
	ArrayList<ArrayList<Tile>> map = new ArrayList<ArrayList<Tile>>();
	
	public Map() {
		
	}
	

	/**
	 * 
	 * @param mapFile
	 * Uses 2 arraylists of Tiles to create a map. When you add another line to the arraylist it adds it as a row instead of a column.
	 */
	public void readMapFile(String mapFile) {
	//	ArrayList<ArrayList<String>> map = new ArrayList<ArrayList<String>>();
		//int rows=0;
		int x = 0,y=0;
	    try {
			Scanner file = new Scanner(new File(mapFile));
   
			while (file.hasNextLine()) {
				x=0;
			    ArrayList<Tile> line = new ArrayList<Tile>();    
			    final String nextLine = file.nextLine();
			    final String[] items = nextLine.split(",");

			    for (int i = 0; i < items.length; i++) {
			    
			    	//Split items into terrain and animals
			    	for (int sep=0; sep < items[i].length(); sep++) {
			    	 if (items[i].charAt(sep) == ';') {
			    	//	System.out.print(items[i].substring(1, sep));
			    	//	System.out.println(" " + items[i].substring(sep+1, items[i].length()-1));
			    		String terrain = "geography." + items[i].substring(1, sep);
			    		String animal = items[i].substring(sep+1, items[i].length()-1 );
			    		//System.out.println("x,y: " + x + "," + y);
			    		if (animal.equals("null")) {
			    			line.add(new Tile(Class.forName(terrain).newInstance()));
			    		} else {
			    			animal = "animals." + animal;
			    			Constructor c = Class.forName(animal).getConstructor(Integer.TYPE, Integer.TYPE);
			    			line.add(new Tile(Class.forName(terrain).newInstance(), c.newInstance(x,y)));
			    		}
			    	  break;
			    	 }
			    	
			    	
			    	}
			    x++;	
			    }

			    map.add(line);
			    rows++; y++;
			    Arrays.fill(items, null); // to clear out the 'items' array
			}
			file.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void printMap() {
		for (int y=0; y < rows; y++) {
	    	for (int x=0; x<map.get(y).size(); x++) {
	    		System.out.print("X: " + x + " Y: " + y + " " + getTerrainType(x,y));
	    		System.out.println(" " + getAnimals(x,y));
	    	}
	    	System.out.println("");
	    }
	}
	
	public ArrayList<ArrayList<Tile>> getMap() {
		return map;
	}
	

	/**
	 * Swap the x,y coordinates since the map arraylist is inverted
	 */
	public String getTerrainType(int x,int y) {
		return map.get(y).get(x).getTerrainType();
	}
	
	
	public Terrain getTerrain(int x, int y) {
		return map.get(y).get(x).getTerrain();
	}

	//-----Plants
	public Plants getPlant(int x, int y) {
		return map.get(y).get(x).getPlant();
	}
	
	public void setPlantHP(int x, int y, int HP) {
		map.get(y).get(x).setPlantHP(HP);
	}
	
	public int getPlantHP(int x, int y) {
		return map.get(y).get(x).getPlantHP();
	}
	
	public int getRegrowRate(int x, int y) {
		return map.get(y).get(x).getRegrowRate();
	}
	
	public void getRegrowRate(int x, int y, int rate) {
		map.get(y).get(x).setRegrowRate(rate);
	}
	
	//---Animals------
	public List<Animals> getAnimals(int x, int y) {
		return map.get(y).get(x).getAnimals();
	}
	
	public void removeAnimal(int x, int y, Animals animal) {
		map.get(y).get(x).removeAnimal(animal);
	}
	
	public void addAnimal(int x, int y, Animals animal) {
		map.get(y).get(x).addAnimal(animal);
	}
	
	public void setAnimalLocation(int x, int y, Animals animal) {
		map.get(y).get(x).setAnimalLocation(x, y, animal);
	}
	
	public void setMP(int x, int y, int newMP, Animals animal) {
		map.get(y).get(x).setMP(newMP, animal);
	}
	
	public int getMP(int x, int y, Animals animal) {
		return map.get(y).get(x).getMP(animal);
	}
	
	public int getMoveMod(int x, int y) {
		return map.get(y).get(x).getMoveMod();
	}
	
}
