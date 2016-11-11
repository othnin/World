package factory;

import animals.*;
import geography.Terrain;

public class AnimalFactory extends AbstractFactory{
	@Override
	public Animals getAnimal(String anType, int x, int y) {
		
		if (anType == null) {
			return null;
		}
		
		if (anType.equalsIgnoreCase("ZEBRA")) {
			return new Zebra(x, y);
		}
		
		if (anType.equalsIgnoreCase("HIPPO")) {
			return new Hippo(x, y);
		}
		
		if (anType.equalsIgnoreCase("ELEPHANT")) {
			return new Elephant(x, y);
		}
		
		return null;		
	}


	@Override
	public 	Terrain getTerrain(String terrType, int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}
}
