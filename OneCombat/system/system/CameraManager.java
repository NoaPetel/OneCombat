package system;
import system.physics.FlatVector;
public class CameraManager {
	
	public final static float PIXELSIZE = 0.01f ;
	
	private FlatVector position;
	private float width;
	private float height;
	private float scale = 0.05f;
	
	public CameraManager(float w, float h) {
		position = FlatVector.Zero();
		width = w;
		height = h;
	}
	
	public void update() {
		
	}
	
	public FlatVector getPosition() {
		return position;
	}
	
	public void setScale(float s) {
		scale = s;
	}
	
	public float getScale() {
		return scale;
	}
	
	public void setSize(float w, float h) {
		width = w;
		height = h;
	}
	public float getHeight() {
		return height;
	}

	public float getWidth() {
		return width;
	}
	
	public void setPos(float newX, float newY) {
		position = new FlatVector(newX, newY);
	}

	public void movePos(float newX, float newY) {
		position = FlatVector.plus(new FlatVector(newX, newY), position);
	}
	
	public boolean inView(float posX, float posY, float w, float h) {
		if (position.x + width / 2f * scale >= posX && position.x - width / 2f * scale <= posX + w
				&& position.y - height / 2f * scale <= posY + h && posY <= position.y + height / 2f * scale) {
			return true;
		}
		return false;
	}
	
	public float[] getPosInView(float posX, float posY) {
		float newX = (posX - position.x + width / 2f * scale) / scale;
		float newY = (posY - position.y + height / 2f * scale) / scale;
		float[] pos = { newX, newY };
		return pos;
	}
	
	/**
	 * Creating an "imaginary" camera smaller than the real one.
	 * @returns true if players are in the small cam, false otherwise
	 * @param pretty clear ahah
	 */
	public boolean inSmallView(float posX, float posY, float w, float h, float rate) {
		float smallCameraWidth = width * rate;
		float smallCameraHeight = height * rate;

		if (position.x + smallCameraWidth / 2f * scale >= posX && position.x - smallCameraWidth / 2f * scale <= posX + w
				&& position.y - smallCameraHeight / 2f * scale <= posY + h
				&& posY <= position.y + smallCameraHeight / 2f * scale) {
			return true;
		}
		return false;
	}
}