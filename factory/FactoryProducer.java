package factory;

public class FactoryProducer {
	public static AbstractFactory getFactory(String choice) {
		
		if (choice.equalsIgnoreCase("ANIMALS")) {
			return new AnimalFactory();
		} else if (choice.equalsIgnoreCase("TERRAIN")) {
			return new TerrainFactory();
		}
		return null;
	}
}
