package system.physics;

import system.Constante;
import system.enumeration.ShapeType;

public interface IBody {
		
	public FlatVector[] getTransformVertices();

	public FlatAABB getAABB();

	public void move(FlatVector amount);

	public void moveTo(FlatVector amount);

	public void rotate(float amount);
	
	public void rotateTo(float amount);

	public void addForce(FlatVector amount);

}
