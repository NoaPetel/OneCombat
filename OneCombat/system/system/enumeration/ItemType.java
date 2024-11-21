package system.enumeration;

public enum ItemType {
    Default(0),
	Health(1),
    Energy(2),
    Power(3),
    Jump(4),
    Speed(5);



    private final int value;
    private ItemType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}