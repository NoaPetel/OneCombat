package system.enumeration;

public enum Item {
	Health(0),
	Energie(1),
	Power(2),
	Jump(3),
	Speed(4);
	
	
	
	private final int value;
    private Item(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
