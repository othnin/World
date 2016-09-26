package geography;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import animals.Animals;
import plants.Grass;
import plants.Plants;

public class Tile {
	
	double plantAppear=.15;
	Terrain typeTerrain;
	Plants typePlants;
	List<Animals> typeAnimals = new ArrayList<Animals>();   //tile can have more than one animal in it?
	
	
	public boolean plantsGrowing(Terrain terrainType) {
		if (terrainType.toString() == "Plains") {
			if (Math.random() < plantAppear)
				return true;
		}
		return false;
	}
	
	
	public Tile(Object object) {
		typeTerrain = (Terrain) object;
		if (plantsGrowing(typeTerrain)) {
			typePlants = new Grass();
		}
	}
	
	public Tile(Object object, Object object2) {
		typeTerrain = (Terrain) object;
		typeAnimals.add((Animals) object2);
		if (plantsGrowing(typeTerrain)) {
			typePlants = new Grass();
		}
	}
	
	public Terrain getTerrain() {
		return typeTerrain;
	}
	
	public String getTerrainType() {
		return typeTerrain.toString();
	}
	
	public List<Animals> getAnimals() {
		return typeAnimals;
	}
	
	public Plants getPlant() {
		return typePlants;
	}
	
	public String getPlantName() {
		return typePlants.toString();
	}
	
	public void setPlantHP(int HP) {
		typePlants.setHP(HP);
	}
	
	public int getPlantHP() {
		return typePlants.getHP();
	}
	
	public int getRegrowRate() {
		return typePlants.getRegrowRate();
	}
	
	public void setRegrowRate(int rate) {
		typePlants.setCurrentRegrowRate(rate);
	}
	
	/*
	public void removeAnimal(Animals animal) {
		int ID = animal.getID();
		for (Iterator<Animals> iterator = typeAnimals.iterator(); iterator.hasNext();) {
			Animals animalIterator = iterator.next();
			if (animalIterator.getID() == ID) {
				iterator.remove();
				System.out.println("Inside remove animal");
			}
		}
	}
	*/
	
	public void removeAnimal(Animals animal) {
		typeAnimals.remove(animal);
	}
	
	//not sure  I need
	public Animals getAnimal(Animals animal) {
		int ID = animal.getID();
		//Animals animal;
		for (Iterator<Animals> iterator = typeAnimals.iterator(); iterator.hasNext();) {
			Animals animalIterator = iterator.next();
			if (animalIterator.getID() == ID)
				return animal;
		}
		return null;
	}
	
	
	public void addAnimal(Animals addAnimal) {
		typeAnimals.add(addAnimal);
	}

	public void print() {
		System.out.println("Something");
	}
/*
	public void setAnimalLocation(int x, int y, Animals animal) {
		int ID = animal.getID();
		for (Iterator<Animals> iterator = typeAnimals.iterator(); iterator.hasNext();) {
			Animals animalIterator = iterator.next();
			if (animalIterator.getID() == ID) {
				animal.setX(x);
				animal.setY(y);
				System.out.println("Inside setAnimalLocation");
			}
		}
		
	}
*/
	public void setAnimalLocation(int x, int y, Animals animal) {
		animal.setX(x);
		animal.setY(y);
	}
	
	/*
	public void setMP(int newMP, Animals animal) {
		// TODO Auto-generated method stub
		int ID = animal.getID();
		for (Iterator<Animals> iterator = typeAnimals.iterator(); iterator.hasNext();) {
			Animals animalIterator = iterator.next();
			if (animalIterator.getID() == ID) {
				animal.setMp(newMP);
				System.out.println("setMP Success");
			}
		}
	}
*/
	public void setMP(int newMP, Animals animal) {
		animal.setMp(newMP);
	}
	
	public int getMP(Animals animal) {
		return animal.getMp();
	}
	/*
	public int getMP(Animals animal) {
		// TODO Auto-generated method stub
		int ID = animal.getID();
		int MP=0;
		for (Iterator<Animals> iterator = typeAnimals.iterator(); iterator.hasNext();) {
			Animals animalIterator = iterator.next();
			if (animalIterator.getID() == ID) {
				MP = animal.getMp();
				System.out.println("getMP Success");
			}
		}
		return MP;
	}
*/
	public int getMoveMod() {
		return typeTerrain.getMoveMod();
	}
}
