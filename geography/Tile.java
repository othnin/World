package geography;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import animals.Animals;
import resources.Grass;
import resources.ManageResources;
import resources.Plants;
import resources.Resources;

public class Tile {
	
	//percent chance that a plant is added to that tile
	double plantAppearStart=.25;
	double plantAppearRergrow=.01;
	
	//move modifier for diaganol movements
	final double diagMoveModifier = 1.4;
	
	Terrain typeTerrain;
//	Plants typePlants;
	List<Animals> typeAnimals = new ArrayList<Animals>();   //tile can have more than one animal in it?
	List<Resources> typeResources = new ArrayList<Resources>();
	
	
	
	public void regrowPlants(int x, int y) {  //TODO: Need to check if there is more than one grass in the array
		//if (plantsGrowing(getTerrain())) {
	//	System.out.println("Tile::regrowPlants");
		if ((getTerrain().toString() == "Plains") & (Math.random() < getPlantRegrowChance()) & (getPlant() == null)) {	
			typeResources.add(new Grass(x,y));
			System.out.println("Added grass to: " + x + "," + y);
		}
		
	}
	
	public boolean plantsGrowing(Terrain terrainType) {
		if ((terrainType.toString() == "Plains") & (Math.random() < getPlantAppearChance())) {
			return true;
		}
		return false;
	}
	
	public double getPlantAppearChance() {
		return plantAppearStart;
	}
	
	public double getPlantRegrowChance() {
		return plantAppearRergrow;
	}
	
	public Tile(Object object, int x, int y) {
		typeTerrain = (Terrain) object;
		if (plantsGrowing(typeTerrain)) {
			typeResources.add(new Grass(x,y));
		}
	}
	
	public Tile(Object object, Object object2, int x, int y) {
		typeTerrain = (Terrain) object;
		typeAnimals.add((Animals) object2);
		if (plantsGrowing(typeTerrain)) {
			typeResources.add(new Grass(x,y));
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
		for (Resources resource : typeResources ) 
			if (resource.toString() == "Grass") return (Plants) resource;
		return null;
	}
	
	public void removePlant(Plants plant) {	
		Iterator<Resources> iter = typeResources.iterator();
		while(iter.hasNext()) {
			Resources resource = iter.next();
			if (resource == plant) {
				iter.remove();
				System.out.println("Removed plant");
				return;
			}
		}
		
		/*
		for (Resources resource : typeResources )
			if (resource.toString() == "Grass")
				typeResources.remove(resource);
			*/	
	}
	/*
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
	*/
	public double getDiagMoveModifier() {
		return diagMoveModifier;
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
		/*
		Iterator<Animals> iter = typeAnimals.iterator();
		while(iter.hasNext()) {
			Animals iterAnimal = iter.next();
			if (iterAnimal == animal)
				iter.remove();
		}*/
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
	
	public int getID(Animals animal) {
		return animal.getID();
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

	public Water getWater() {
		for (Resources resource : typeResources ) 
			if (resource.toString() == "Water") return (Water) resource;
		return null;
	}
}
	
