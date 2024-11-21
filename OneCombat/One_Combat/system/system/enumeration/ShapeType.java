package system.enumeration;

public enum ShapeType {
	Circle(0),
	Box(1);
	
	
	private final int value;
    private ShapeType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
