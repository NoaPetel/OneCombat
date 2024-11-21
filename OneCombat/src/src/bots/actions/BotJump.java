package src.bots.actions;

import system.object.component.monobehavior.MonoBehavior;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import java.util.Random;

public class BotJump extends MonoBehavior{

	private float speed;
	private int frameCounter;	// a jump take 20frames, 10 frames to go max altitude and 10 to fall back
	private boolean isFlip;
	private boolean isJumping;
	
	public BotJump(GameObject gameObject, Transform transform) {
		super(gameObject, transform);
		this.speed = 2f;
		this.isFlip = false;
		this.isJumping = false;
	}
	
	@Override
	public void start() {
		this.isJumping = true;
	}
	
	@Override
	public void update() {
		float formerYPosition = this.transform.getPositionY();
		if(this.isJumping == true) {
			if(this.frameCounter == 120) {
				this.frameCounter = 0;
				this.isJumping = false;
			}
			this.frameCounter++;
			this.transform.setPositionY(formerYPosition - this.speed);
		}	
	}
}
