package system;

public class Time {

	public static float deltaTime;

	public void SetDeltaTime(long elapsed) {
		deltaTime = (float)elapsed / 1000f;
	}
	
}
