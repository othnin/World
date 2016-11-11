package animals;

import java.util.ArrayList;
import java.util.Iterator;

public class ManageAnimals {

	//right now I am using this list but still have to get an x,y for the animal.
	//If I got rid of this I would have go through every tile in the array instead to 
	//find every animal. 
	static ArrayList<Animals> allAnimals = new ArrayList<Animals>();

	public static ArrayList<Animals> getAllAnimals() {
		return allAnimals;
	}
	
	//used by the constructors for the animals to add to the allanimals list.
	//only reason we are still using this static class is for the static call
	//from the constructors
	
	//TODO: get rid of static class
	public static void addAnimal(Animals animal) {
		allAnimals.add(animal);
	}
	
	public static void removeAnimal(Animals animal) {
		allAnimals.remove(animal);
		
		/*
		Iterator<Animals> iter = allAnimals.iterator();
		while (iter.hasNext()) {
			Animals iterAnimal = iter.next();
			if (iterAnimal == animal)
				iter.remove();
		}
		*/
	}
	
	

}
