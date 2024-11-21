package system.physics;

public class FlatAABB {
	
	public FlatVector min;
	public FlatVector max;
	
	public FlatAABB(FlatVector min, FlatVector max) {
		this.min = min;
		this.max = max;
	}
	
	public FlatAABB(float minX, float minY, float maxX, float maxY) {
		min = new FlatVector(minX, minY);
		max = new FlatVector(maxX, maxY);
	}
}
