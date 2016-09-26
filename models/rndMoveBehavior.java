package models;

import java.util.ArrayList;
import java.util.Random;

import animals.Animals;
import animals.ManageAnimals;
import geography.Map;

public class rndMoveBehavior implements Behavior{
	


	@Override
	public models.Direction moveDirection(ArrayList<models.Direction> moveDirectionArray, Animals animal, Map map) {
		int rnd = new Random().nextInt(moveDirectionArray.size());
		Direction moveDirection = moveDirectionArray.get(rnd);
		return moveDirection;
	}
	
}


