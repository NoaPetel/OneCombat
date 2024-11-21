package Character.Components;

import Interfaces.IDamagable;
import camera.components.CameraShake;
import src.items.Item;
import src.manager.GameManager;
import src.manager.ItemManager;
import system.GameSystem;
import system.Time;
import system.enumeration.ItemType;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import system.physics.FlatMath;
import system.physics.FlatVector;

public class CharacterHealth extends MonoBehavior implements IDamagable {

	// Parameters
	private float currentHealth;
	private float onDamageTime;

	// Components
	private CharacterLocomotion characterLocomotion;
	private CharacterAttack characterAttack;
	private CharacterMovement characterMovement;
	private CharacterData characterData;
	private CharacterSound characterSound;
	private CharacterUI characterUI;

	public CharacterHealth(GameObject g, Transform t, CharacterData c) {
		super(g, t);
		characterData = c;
	}

	public void start() {
		currentHealth = characterData.startHealth;
		characterMovement = gameObject.getComponent(CharacterMovement.class);
		characterLocomotion = gameObject.getComponent(CharacterLocomotion.class);
		characterAttack = gameObject.getComponent(CharacterAttack.class);
		characterSound = gameObject.getComponent(CharacterSound.class);
		characterUI = gameObject.getComponent(CharacterUI.class);

		characterUI.setupHealth(currentHealth, characterData.maxHealth);
	}

	public void update() {
		if (onDamageTime >= 0)
			onDamageTime -= Time.deltaTime;
	}

	public void death() {
		characterAttack.isDeath();
		characterLocomotion.death();
		characterSound.death();
		GameManager.PlayerDeath(gameObject);

		float random = (float) Math.random();
		ItemType typeChoice = ItemType.Default;
		if (0f < random && random < 0.3f) {
			typeChoice = ItemType.Speed;
		} else if(0.3 < random && random < 0.6){
			typeChoice = ItemType.Jump;
		} else {
			typeChoice = ItemType.Power;
		}
		GameSystem.system.sceneManager.getCurrentScene()
				.addGameObject(Item.CreateItem(gameObject.transform.getPosition(), 1f, typeChoice, false));
	}

	public void hit() {
		characterLocomotion.hit();
		characterSound.hit();
		onDamageTime = characterData.damageBufferTime;
		characterAttack.isHiting();
		characterMovement.isHiting();
		gameObject.getRigidbody().linearVelocity = FlatVector.Zero();
	}

	@Override
	public void setDamage(float amount) {
		if (canTakeDamage()) {
			CameraShake.instance.shake(0.2f, 0.2f);
			currentHealth -= amount;
			if (currentHealth <= 0f)
				death();
			else {
				hit();
			}
			currentHealth = FlatMath.clamp(currentHealth, 0f, characterData.maxHealth);
			characterUI.setHealth(currentHealth);
		}
	}

	public boolean canTakeDamage() {
		return onDamageTime <= 0 && currentHealth > 0f && !characterMovement.isDashing
				&& !characterAttack.isSpecialAttacking();
	}

	public boolean isHiting() {
		return onDamageTime > 0;
	}

	public boolean isAlive() {
		return currentHealth > 0;
	}

	public boolean canBeMovable() {
		return !isHiting() && isAlive();
	}

	@Override
	public void addHealth(float amount) {
		if (currentHealth > 0f) {
			currentHealth += amount;
			currentHealth = FlatMath.clamp(currentHealth, 0f, characterData.maxHealth);
			characterUI.setHealth(currentHealth);
		}
	}

}
