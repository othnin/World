package models;

import java.util.ArrayList;

import animals.Animals;
import geography.Map;

public interface Behavior {

	Direction moveDirection(ArrayList<Direction> moveDirectionArray, Animals animal, Map map);

}
