package Character.SpecialAttack;

import java.awt.Color;
import java.awt.Graphics;

import Character.Components.CharacterAttack;
import Character.Components.CharacterData;
import Character.Components.CharacterLocomotion;
import Character.Components.CharacterMovement;
import Character.Components.CharacterSound;
import Interfaces.ISpecialAttack;
import system.Time;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import system.physics.FlatVector;

public abstract class BasicSpecialAttack extends MonoBehavior implements ISpecialAttack {

	// Components
	protected CharacterData characterData;
	protected CharacterLocomotion characterLocomotion;
	protected CharacterAttack characterAttack;
	protected CharacterMovement characterMovement;
	protected CharacterSound characterSound;

	// State Parameters
	public boolean isSpecialAttacking;

	// Timer Paramerters
	protected float onSpecialAttackTime;
	
	//Attack Effects
	protected  AttackEffect[] attackEffects;
	
	//End position after the attacks
	protected  FlatVector endPosition = FlatVector.Zero();

	public BasicSpecialAttack(GameObject g, Transform t, CharacterData c) {
		super(g, t);
		characterData = c;
	}

	public void start() {
		characterLocomotion = gameObject.getComponent(CharacterLocomotion.class);
		characterAttack = gameObject.getComponent(CharacterAttack.class);
		characterMovement = gameObject.getComponent(CharacterMovement.class);
		characterSound = gameObject.getComponent(CharacterSound.class);
	}
	
	public void update() {
		if (!isSpecialAttacking)
			return;

		onSpecialAttackTime -= Time.deltaTime;

		
		
		for(int i = 0; i < attackEffects.length; i++) {
			// Update Timer effect
			if (attackEffects[i].onSpecialAttackEffectTime > 0) {
				attackEffects[i].onSpecialAttackEffectTime -= Time.deltaTime;
				if (attackEffects[i].onSpecialAttackEffectTime <= 0f) {
					if(attackEffects[i].isDestroyTerrain) {
						characterAttack.rectDestroyingTerrain(attackEffects[i].positionAttack, attackEffects[i].rangeAttack);
					}
					if(attackEffects[i].type == 0) {
						characterAttack.rectAttacking(attackEffects[i].damage * characterData.attackMultiplicator, attackEffects[i].positionAttack, attackEffects[i].rangeAttack);
					}
					else {
						FlatVector position = getRelativePosition(transform.position, attackEffects[i].endEntityPosition,
								characterMovement.isFacingRight);
						characterAttack.rectEntityAttacking(attackEffects[i].damage * characterData.attackMultiplicator, attackEffects[i].positionAttack,
								attackEffects[i].rangeAttack, position);
					}

				}
			}
		}

		// End Attack update
		if (onSpecialAttackTime <= 0) {
			attackComplete();
		}
	}
	
	
	@Override
	public void specialAttack() {
		onSpecialAttackTime = characterData.specialAttackTime;
		for(int i = 0; i < attackEffects.length; i++) {
			attackEffects[i].resetTimer();
		}
		characterLocomotion.specialAttack();
		characterSound.specialAttack();
		isSpecialAttacking = true;
	}
	
	public void attackComplete() {
		isSpecialAttacking = false;
		onSpecialAttackTime = 0;
		
		//Move To the final Position
		if (!characterMovement.isFacingRight)
			transform.position.x -= endPosition.x;
		else
			transform.position.x += endPosition.x;
		transform.position.y += endPosition.y;
		
		characterLocomotion.idle();
	}
	
	@Override
	public boolean isSepcialAttacking() {
		return isSpecialAttacking;
	}
	
	public void onDrawGizmos(Graphics g) {
		// Attack
		for(int i = 0; i < attackEffects.length; i++) {
			drawRectRelativeGizmos(g, attackEffects[i].positionAttack, attackEffects[i].rangeAttack.x, attackEffects[i].rangeAttack.y, Color.blue, transform.position,
				characterMovement.isFacingRight);
			if(attackEffects[i].type == 1) {
				FlatVector position = getRelativePosition(transform.position, attackEffects[i].endEntityPosition,
						characterMovement.isFacingRight);
				drawCircleGizmos(g, position, 0.1f, Color.gray);
			}
		}
		

		// EndPosition
		if(endPosition != FlatVector.Zero())
			drawRectRelativeGizmos(g, endPosition, gameObject.getRigidbody().width, gameObject.getRigidbody().height,
				Color.orange, transform.position, characterMovement.isFacingRight);

	}
}
