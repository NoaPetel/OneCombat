package system.object.component.transform;

import system.object.component.Component;
import system.object.gameobject.GameObject;
import system.physics.FlatVector;

public class Transform extends Component {
	
	public Transform(GameObject g, Transform t) {
		super(g, t);
	}
	
	public Transform(GameObject g) {
		super(g);
		position = FlatVector.Zero();
		rotation = 0;
		scale = 1;
	}

	public FlatVector position;
	public float rotation;
	public float scale;

	public FlatVector getPosition() {
		return position;
	}
	public float getPositionX() {
		return position.x;
	}
	
	public float getPositionY() {
		return position.y;
	}
	
	public float getRotation() {
		return rotation;
	}
	
	public float getScale() {
		return scale;
	}
	
	
	public void setPositionX(float newX) {
		position.x = newX;
	}
	
	public void setPositionY(float newY) {
		position.y = newY;
	}
	
	public void setPosition(FlatVector position) {
		this.position = position;
	}
	
	public void setRotation(float newRotation) {
		rotation = newRotation;
	}
	
	public void setScale(float newScale) {
		scale = newScale;
	}
}
