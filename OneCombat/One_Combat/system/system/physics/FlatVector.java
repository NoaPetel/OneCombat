package system.physics;

import java.util.ArrayList;

public class FlatVector {

	public float x;
	public float y;

	public static FlatVector Zero() { return new FlatVector(0f, 0f); }
	public static FlatVector right() { return new FlatVector(1f, 0f); }
	public static FlatVector left() { return new FlatVector(-1f, 0f); }
	public static FlatVector up() { return new FlatVector(0f, -1f); }
	public static FlatVector down() { return new FlatVector(0f, 1f); }

	public FlatVector(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public static FlatVector plus(FlatVector a, FlatVector b) {
		return new FlatVector(a.x + b.x, a.y + b.y);
	}

	public static FlatVector minus(FlatVector a, FlatVector b) {
		return new FlatVector(a.x - b.x, a.y - b.y);
	}

	public static FlatVector unaryMinus(FlatVector a) {
		return new FlatVector(-a.x, -a.y);
	}

	public static FlatVector times(FlatVector a, float s) {
		return new FlatVector(a.x * s, a.y * s);
	}
	
	public static FlatVector times(FlatVector a, FlatVector b) {
		return new FlatVector(a.x * b.x + a.x + b.y, a.y * b.x + a.y * b.y);
	}

	public static FlatVector div(FlatVector a, float s) {
		return new FlatVector(a.x / s, a.y / s);
	}

	public static FlatVector Transform(FlatVector v, FlatTransform transform) {
		return new FlatVector(transform.cos * v.x - transform.sin * v.y + transform.x,
				transform.sin * v.x + transform.cos * v.y + transform.y);
	}
	
	public static FlatVector lerp(FlatVector a, FlatVector b, float t) {
		float x = a.x * (1 - t) + b.x * t;
		float y = a.y * (1 - t) + b.y * t;
		return new FlatVector(x, y);
	}

	@Override
	public boolean equals(Object that) {
		return this == that || that != null && getClass() == that.getClass() && x == ((FlatVector) that).x
				&& y == ((FlatVector) that).y;
	}
}
	
