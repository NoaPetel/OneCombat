package src.bots.actions;

import java.io.IOException;

import src.bots.Missile.Missile;
import system.GameSystem;
import system.Time;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import system.scene.IScene;

public class BotThrow extends MonoBehavior {

	// Components
	BotMovement movement;

	float waitTime = 5; // en seconde
	float onWaitTime = 0;
	float shootTime = 1; // en seconde
	float onShootTime = 0;

	boolean isWaiting = true;
	public boolean isShooting = false;

	BotLocomotion BotLocomotion;
	BotAttack BotAttack;
	BotHealth BotHealth;

	public BotThrow(GameObject gameObject, Transform transform) throws IOException {
		super(gameObject, transform);
		this.movement = gameObject.getComponent(BotMovement.class);
	}

	@Override
	public void start() {
		BotLocomotion = gameObject.getComponent(BotLocomotion.class);
		BotAttack = gameObject.getComponent(BotAttack.class);
		BotHealth = gameObject.getComponent(BotHealth.class);
	}

	@Override
	public void update() {
		if (isWaiting) {
			if (onWaitTime > 0) {
				onWaitTime -= Time.deltaTime;
			} else if (onWaitTime <= 0) {
				// onShoot();
			}
		}

		if (isShooting) {
			if (onShootTime > 0) {
				onShootTime -= Time.deltaTime;
			} else if (onShootTime <= 0) {
				shoot();
			}
		}
	}

	public void shoot() {
		
		Timer();
		
		if (!BotHealth.canBeMovable())
			return;

		try {
			IScene sc = GameSystem.system.sceneManager.getCurrentScene();
			sc.addGameObject(new Missile(this.transform, movement.isFlip));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onShoot() {

		if (!canShoot())
			return;

		isWaiting = false;
		isShooting = true;
		BotLocomotion.shoot();
		onShootTime = this.shootTime;
	}

	public void Timer() {
		isWaiting = true;
		isShooting = false;
		onWaitTime = this.waitTime;
	}

	public boolean canShoot() {
		return !isShooting && onWaitTime <= 0 && !BotAttack.isAttacking && BotHealth.canBeMovable();
	}
}
