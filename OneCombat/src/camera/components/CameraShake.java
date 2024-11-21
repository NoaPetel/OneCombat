package camera.components;

import system.CameraManager;
import system.GameSystem;
import system.Time;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;

public class CameraShake extends MonoBehavior {

	public static CameraShake instance;
	
	private CameraManager cameraManager;
	private float amplitude;
	private float duration;

	public CameraShake(GameObject g, Transform t) {
		super(g, t);
		instance = this;
		cameraManager = GameSystem.system.cameraManager;
	}

	public void start() {
		amplitude = 0;
		duration = 0;
	}
	public void update() {
		if (duration > 0) {
			cameraManager.movePos(2 * amplitude * (float) Math.random() - amplitude,
					2 * amplitude * (float) Math.random() - amplitude);
			duration -= Time.deltaTime;
		} else {
			duration = 0;
		}
	}
	
	public void shake(float amplitude, float duration) {
		this.amplitude = amplitude;
		this.duration = duration;
	}
}