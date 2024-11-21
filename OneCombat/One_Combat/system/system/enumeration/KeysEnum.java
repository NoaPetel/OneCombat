package system.enumeration;

public enum KeysEnum {
	A(65), B(66), C(67), D(68), E(69), F(70), G(71), H(72), I(73), J(74), K(75), L(76), M(77), N(78), O(79), P(80),
	Q(81), R(82), S(83), T(84), U(85), V(86), W(87), X(88), Y(89), Z(90), FU(38), FD(40), FR(39), FL(37), SPACE(32),
	ENTER(13), ONE(49), TWO(50), THREE(51), FOUR(52), FIVE(53), SIX(54), SEVEN(55), HEIGHT(56), NINE(57); 

	private int value;

	private KeysEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}
}
