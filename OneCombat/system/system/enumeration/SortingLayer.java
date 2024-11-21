package system.enumeration;

public enum SortingLayer {
	Background(-1),
	Default(0),
	Map(1),
	Decor(2),
	Entity(3),
	Character(4),
	BackgroundUI(5),
	UI(6);
	
	
	private final int value;
    private SortingLayer(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
