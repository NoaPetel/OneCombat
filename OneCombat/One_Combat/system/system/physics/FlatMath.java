package system.physics;

public class FlatMath {
	
	public final static float VerySmallAmount = 0.0005f;
	public final static float CloseAmount = 0.5f;

	public static float clamp(float value, float min, float max) {
		if(min == max) return min;
		if(min > max) System.out.println("the min can be greater than the max");
		
		if(value < min) return min;
		if(value > max) return max;
		return value;
	}
	
	public static int clamp(int value, int min, int max) {
		if(min == max) return min;
		if(min > max) System.out.println("the min can be greater than the max");
		
		if(value < min) return min;
		if(value > max) return max;
		return value;
	}
	
	public static float lengthSquared(FlatVector v) {
		return (v.x * v.x + v.y * v.y);
	}
	
	public static float length(FlatVector v) {
		return (float)Math.sqrt(v.x * v.x + v.y * v.y);
	}

	public static float distanceSquared(FlatVector a, FlatVector b) {
		float dx = a.x - b.x;
		float dy = a.y - b.y;
		return dx * dx + dy * dy;
	}

	public static float distance(FlatVector a, FlatVector b) {
		float dx = a.x - b.x;
		float dy = a.y - b.y;
		return (float)Math.sqrt(dx * dx + dy * dy);
	}

	public static FlatVector normalize(FlatVector v) {
		float len = FlatMath.length(v);
		if(len == 0) return FlatVector.Zero();
		return new FlatVector(v.x / len, v.y / len);
	}

	public static float dot(FlatVector a, FlatVector b) {
		return a.x * b.x  + a.y * b.y;
	}

	public static float cross(FlatVector a, FlatVector b) {
		return a.x * b.y - a.y * b.x;
	}
	
	public static boolean nearlyEqual(float a, float b) {
		return Math.abs(a - b) < VerySmallAmount;
	}
	
	public static boolean nearlyEqual(FlatVector a, FlatVector b) {
		return  distanceSquared(a, b) < VerySmallAmount * VerySmallAmount;
	}
	
	public static boolean nearlyClose(float a, float b) {
		return Math.abs(a - b) < CloseAmount;
	}
	
	public static boolean nearlyClose(FlatVector a, FlatVector b) {
		return Math.abs(a.x - b.x) < CloseAmount && Math.abs(a.y - b.y) < CloseAmount;
	}
}
