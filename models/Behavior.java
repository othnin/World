package models;


import animals.Animals;
import geography.Map;
import pathfinding.MapPath;

public interface Behavior {

	MapPath moveDirection(Animals animal, Map map);

}
