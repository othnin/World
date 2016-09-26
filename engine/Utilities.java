package engine;

import java.io.*;
import java.lang.reflect.Constructor;
import java.util.Scanner;
import geography.Tile;



public class Utilities {
	
	public static final int NUM_ROWS = 20;
	public static final int NUM_COLS = 20;
	
	//TODO: Still hardwired for 20x20
	public static void printMap(Tile[][] map) {
		for (int x=0; x<NUM_ROWS;x++) {
			for (int y=0; y < NUM_COLS; y++)
				System.out.print( "("+ x +"," +y +")"  + map[x][y].getTerrain().toString() + "," + map[x][y].getAnimals().toString()+ "     ");
			System.out.println();
		}
	}
	
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
	
}
