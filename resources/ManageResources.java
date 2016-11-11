package resources;


import java.util.ArrayList;


public class ManageResources {

	//right now I am using this list but still have to get an x,y for the resource.
	//If I got rid of this I would have go through every tile in the array instead to 
	//find every animal. 
	static ArrayList<Resources> allResources = new ArrayList<Resources>();

	public static ArrayList<Resources> getAllResouces() {
		return allResources;
	}
	
	//used by the constructors for the animals to add to the allResources list.
	//only reason we are still using this static class is for the static call
	//from the constructors
	//TODO: get rid of static class
	static void addResource(Resources resource) {
		allResources.add(resource);
	}
	
	static void removeResource(Resources resource) {
		allResources.remove(resource);
	}
	
	

}
