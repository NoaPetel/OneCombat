package system.physics;

public class FlatTransform {

	public float x;
	public float y;
	public float sin;
	public float cos;

	public static FlatTransform Zero = new FlatTransform(0, 0, 0);

	public FlatTransform(FlatVector position, float angle) {
		this.x = position.x;
		this.y = position.y;
		this.sin = (float) Math.sin(angle);
		this.cos = (float) Math.cos(angle);
	}
	
	public FlatTransform(float x, float y, float angle) {
		this.x = x;
		this.y = y;
		this.sin = (float) Math.sin(angle);
		this.cos = (float) Math.cos(angle);
	}
}
