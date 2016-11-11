package factory;

import animals.Animals;
import geography.Terrain;

public abstract class AbstractFactory {
	public abstract Animals getAnimal(String type, int x, int y);
//	abstract Terrain getTerrain(String terrType); 
	public abstract Terrain getTerrain(String terrType, int x, int y); 
}
