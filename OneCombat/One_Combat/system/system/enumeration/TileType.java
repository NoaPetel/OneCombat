package system.enumeration;

public enum TileType {	
	Grass(1),
	Dirt(2),
	Rock(3),
	Wall(4);

	private static float GRASS_HEALTH = 100;
	private static float DIRT_HEALTH = 200;
	private static float ROCK_HEALTH = 500;
	private static float WALL_HEALTH = 1000; // ne peut pas prendre de damage
	
	private static float[] HEALTH = {0,GRASS_HEALTH,DIRT_HEALTH,ROCK_HEALTH,WALL_HEALTH};
	
	private final int value;
	private float health;
    private TileType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    public static float getHealth(int value) {
    	return HEALTH[value];
    }
}