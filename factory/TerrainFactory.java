package factory;

import animals.Animals;
import geography.Terrain;
import geography.*;

public class TerrainFactory extends AbstractFactory {
	
	@Override
	public Terrain getTerrain(String terrType, int x, int y) {
		
		if (terrType ==null) {
			return null;
		}
		if (terrType.equalsIgnoreCase("WOODS")) {
			return new Woods();
		}
		
		if (terrType.equalsIgnoreCase("HILLS")) {
			return new Hills();
		}
		
		if (terrType.equalsIgnoreCase("PLAINS")) {
			return new Plains();
		}
		
		if (terrType.equalsIgnoreCase("WATER")) {
			return new Water(x, y);
		}
		
		return null;
	}


	@Override
	public Animals getAnimal(String type, int x, int y) {
	// TODO Auto-generated method stub
	return null;
	}
	
}