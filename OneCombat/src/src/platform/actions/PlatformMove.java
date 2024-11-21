package src.platform.actions;

import Interfaces.IPlatformMovement;
import system.Time;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.renderer.SpriteRenderer;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import system.physics.FlatVector;

public class PlatformMove extends MonoBehavior implements IPlatformMovement{
	private float coefBump = 0.9f;
	private boolean isBumping = false;
	private int bumpTimer = 0;
	private int direction;
	private float speed = 10;
	SpriteRenderer spriteRenderer;
	private float timer = 5f;
	private float refreshtime = 2f;
	private int o;

	public PlatformMove(GameObject gameObject, Transform transform, float speed) {
        super(gameObject, transform);
        this.direction = 0;
        this.speed = speed;
	}


	@Override
	public void start() {
	}
	
	@Override
	public void update() {
		if(this.isBumping) {
			if(this.bumpTimer == 15) {
				this.isBumping = false;
				this.bumpTimer = 0;
			}
			else {
				this.bumpTimer++;
			}
		}
		if (this.direction == 2) {
			if (this.transform.getPositionY() < -15) {
				this.o = 1;
			} else if (this.transform.getPositionY() > 15) {
				this.o = -1;
			}
		} else if (this.direction == 1) {
			if (this.transform.getPositionX() < -15) {
				this.o = 1;
			} else if (this.transform.getPositionY() > -15) {
				this.o = -1;
			}
		}
		if (timer <= 0f) {
			this.direction = (int) (Math.random() * 2 + 1); // 1 gd 2 hb
			this.o = (int) (Math.random() * 2);
			if (o == 0) {
				this.o -= 1;
			}
			timer = refreshtime;
		} else {
			timer -= Time.deltaTime;
		}
		this.run();
	}

	public void run() {
		if (this.direction == 2) {
			float movement = o * Time.deltaTime * this.speed;
			gameObject.getRigidbody().move(new FlatVector(0, movement));
		} else if (this.direction == 1) {
			float movement = o * Time.deltaTime * this.speed;
			gameObject.getRigidbody().move(new FlatVector(movement, 0));
		}
	}
	
	public void reverseMouvement() {
		if(!this.isBumping) {
			this.isBumping = true;
			this.bumpTimer = 0;
			this.speed = coefBump * this.speed;
			this.o = -this.o;
		}
	}
}
