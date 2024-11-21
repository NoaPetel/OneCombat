package src.bots.actions;

import system.Time;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.renderer.SpriteRenderer;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import system.physics.FlatVector;

public class BotMovement extends MonoBehavior {

	public float speed = 1f;
	private float moveInput; // -1 s'il va a gauche, 1 s'il va a droite
	public boolean isFlip = true; // true = regarde a droite | false = regarde a gauche
	private float turnTime = 2f;
	private float onTurntime = 0f;

	// Compoenents
	BotLocomotion BotLocomotion;
	BotAttack BotAttack;
	BotThrow BotThrow;
	BotHealth BotHealth;
	SpriteRenderer spriteRenderer;

	public BotMovement(GameObject gameObject, Transform transform) {
		super(gameObject, transform);
		this.moveInput = 1;
	}

	public BotMovement(GameObject gameObject, Transform transform, int speed) {
		super(gameObject, transform);
		this.moveInput = 1;
		this.speed = speed;
		this.isFlip = true;
	}

	@Override
	public void start() {
		BotLocomotion = gameObject.getComponent(BotLocomotion.class);
		BotAttack = gameObject.getComponent(BotAttack.class);
		BotHealth = gameObject.getComponent(BotHealth.class);
		BotThrow = gameObject.getComponent(BotThrow.class);
		spriteRenderer = (SpriteRenderer) gameObject.getRenderer();
		BotLocomotion.idle();
	}

	@Override
	public void update() {
		// change input
		if (moveInput != 0) {
			checkDirectionToFace(moveInput < 0);
		}

		if (canTurn() == false) {
			this.onTurntime -= Time.deltaTime;
		}

	}

	public void run() {
		float movement = Time.deltaTime * this.speed * this.moveInput;

		if (canMove()) {
			if (movement != 0) {
				BotLocomotion.run();
				gameObject.getRigidbody().move(new FlatVector(movement, 0));
			} else {
				BotLocomotion.idle();
			}
		}
	}

	/***
	 * Turn methodes
	 ***/
	public void turn() {
		if (this.spriteRenderer != null) {
			this.isFlip = !isFlip;
			this.spriteRenderer.setIsFlip(!isFlip);
		}
	}

	public void checkDirectionToFace(boolean isMovingRight) {
		if (isMovingRight != isFlip && canFlip())
			turn();
	}

	public boolean canFlip() {
		return canMove();
	}

	public boolean canTurn() {
		return canMove() && onTurntime <= 0;
	}

	public boolean canMove() {
		return !BotAttack.isAttacking && BotHealth.canBeMovable() && !BotThrow.isShooting;
	}

	public void moveTurn() {
		this.onTurntime = turnTime;
		if (this.moveInput == -1) {
			this.moveInput = 1;
		} else {
			this.moveInput = -1;
		}
	}

	public void setMoveInput(float move) {
		this.moveInput = move;
	}
}
