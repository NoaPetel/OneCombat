package system.enumeration;

public enum Layer {
	Default(0),
	Map(1),
	Decor(2),
	Character(3),
	WeakBot(4),
	Item(5),
	Projectil(6),
	UI(7),
	Platform(8);
	
	
	private final int value;
    private Layer(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    public static Layer[] getDamagableLayers() {
    	Layer[] layers = {Default, Map, Decor, Character, WeakBot, Platform};
    	return layers;
    }
    
    public static Layer[] getTerrainLayers() {
    	Layer[] layers = {Map, Decor};
    	return layers;
    }
    
    public static Layer[] getGroundLayers() {
    	Layer[] layers = {Default, Map, Platform};
    	return layers;
    }
    
    public static Layer[] getEntityLayers() {
    	Layer[] layers = {Character, WeakBot};
    	return layers;
    }
}
