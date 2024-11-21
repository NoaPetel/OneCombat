package Character.SpecialAttack;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import Character.Components.CharacterAttack;
import Character.Components.CharacterData;
import Character.Components.CharacterLocomotion;
import Character.Components.CharacterMovement;
import Character.Components.CharacterSound;
import Character.SpecialAttack.SpecialAttackObject.LuffyHand.LuffyHand;
import Interfaces.ISpecialAttack;
import system.GameSystem;
import system.Time;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import system.physics.FlatVector;
import system.scene.IScene;

public class LuffySpecialAttack extends MonoBehavior implements ISpecialAttack {

	// Components
	CharacterData characterData;
	CharacterLocomotion characterLocomotion;
	CharacterAttack characterAttack;
	CharacterMovement characterMovement;
	protected CharacterSound characterSound;
	IScene scene;
	Random r;

	// State Parameters
	public boolean isSpecialAttacking;

	// Timer Paramerters
	private float onSpecialAttackTime;
	private float onSpecialAttackEffectStartTime;
	private float onSpecialAttackEffectEndTime;
	private float onDeltaSpawnTime;

	// Checks Parameters
	private FlatVector rangeEntitySpawn = new FlatVector(0.4f, 1.5f);
	private FlatVector positionEntitySpawn = new FlatVector(-0.5f, -0.25f);
	private float specialAttackEffectStartTime = 1.2f;
	private float specialAttackEffectEndTime = 2.5f;
	private float deltaSpawnTime = 0.02f;

	// EndPosition
	private FlatVector endPosition = new FlatVector(0.2f, 0f);

	public LuffySpecialAttack(GameObject g, Transform t, CharacterData c) {
		super(g, t);
		characterData = c;
		r = new Random();
	}

	public void start() {
		characterLocomotion = gameObject.getComponent(CharacterLocomotion.class);
		characterAttack = gameObject.getComponent(CharacterAttack.class);
		characterMovement = gameObject.getComponent(CharacterMovement.class);
		characterSound = gameObject.getComponent(CharacterSound.class);
		scene = GameSystem.system.sceneManager.getCurrentScene();
	}

	public void update() {
		if (!isSpecialAttacking)
			return;

		onSpecialAttackTime -= Time.deltaTime;
		onSpecialAttackEffectStartTime -= Time.deltaTime;
		onSpecialAttackEffectEndTime -= Time.deltaTime;
		onDeltaSpawnTime -= Time.deltaTime;

		// Update Timer effect
		if(onSpecialAttackEffectStartTime < 0 && onSpecialAttackEffectEndTime > 0) {
			if(onDeltaSpawnTime <= 0) {
				onDeltaSpawnTime += deltaSpawnTime;
				
				float deltaX = characterMovement.isFacingRight ? positionEntitySpawn.x : -positionEntitySpawn.x;
				float x = r.nextFloat() * rangeEntitySpawn.x + deltaX + transform.position.x;
				float y = r.nextFloat() * rangeEntitySpawn.y + positionEntitySpawn.y + transform.position.y
						- gameObject.getRigidbody().height / 2f;
				scene.addGameObject(new LuffyHand(new FlatVector(x, y), characterMovement.isFacingRight, gameObject.getRigidbody()));
			}
			
		}

		// End Attack update
		if (onSpecialAttackTime <= 0) {
			attackComplete();
		}
	}

	public void specialAttack() {
		onSpecialAttackTime = characterData.specialAttackTime;
		onSpecialAttackEffectStartTime = specialAttackEffectStartTime;
		onSpecialAttackEffectEndTime = specialAttackEffectEndTime;
		characterLocomotion.specialAttack();
		characterSound.specialAttack();
		isSpecialAttacking = true;
	}

	public void attackComplete() {
		isSpecialAttacking = false;
		onSpecialAttackTime = 0;
		if (!characterMovement.isFacingRight)
			transform.position.x -= endPosition.x;
		else
			transform.position.x += endPosition.x;
		transform.position.y += endPosition.y;
	}

	@Override
	public boolean isSepcialAttacking() {
		return isSpecialAttacking;
	}

	public void onDrawGizmos(Graphics g) {

		//EntitySpawn
		drawRectRelativeGizmos(g, positionEntitySpawn, rangeEntitySpawn.x, rangeEntitySpawn.y,
				Color.magenta, transform.position, characterMovement.isFacingRight);
		
		// EndPosiotn
		drawRectRelativeGizmos(g, endPosition, gameObject.getRigidbody().width, gameObject.getRigidbody().height,
				Color.orange, transform.position, characterMovement.isFacingRight);
	}
}
