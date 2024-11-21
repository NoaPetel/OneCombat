package Character.Components;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import Interfaces.IDamagable;
import Interfaces.ISpecialAttack;
import system.Physics;
import system.Time;
import system.enumeration.Layer;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.rigidbody.Rigidbody;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import system.physics.FlatVector;

public class CharacterAttack extends MonoBehavior {

	// Components
	CharacterLocomotion characterLocomotion;
	CharacterMovement characterMovement;
	CharacterEnergy characterEnergy;
	CharacterData characterData;
	CharacterHealth characterHealth;
	ISpecialAttack characterSpecialAttack;
	CharacterSound characterSound;
	Rigidbody rb;

	// State Parameters
	public boolean isAttacking;

	// Input Parameters
	public float lastPressedAttackTime;
	public float lastPressedLongAttackTime;
	public float lastPressedSpecialAttackTime;

	// Timers
	private float onAttackTime;
	private float onAttackEffectTime;
	private float onLongAttackTime;
	private float onLongAttackEffectTime;

	public CharacterAttack(GameObject g, Transform t, CharacterData c) {
		super(g, t);
		characterData = c;
	}

	public void start() {
		characterMovement = gameObject.getComponent(CharacterMovement.class);
		characterLocomotion = gameObject.getComponent(CharacterLocomotion.class);
		characterEnergy = gameObject.getComponent(CharacterEnergy.class);
		characterSpecialAttack = gameObject.getComponent(ISpecialAttack.class);
		characterSound = gameObject.getComponent(CharacterSound.class);
		characterHealth = gameObject.getComponent(CharacterHealth.class);
		rb = gameObject.getRigidbody();
	}

	public void update() {

		lastPressedAttackTime -= Time.deltaTime;
		lastPressedLongAttackTime -= Time.deltaTime;
		lastPressedSpecialAttackTime -= Time.deltaTime;
		onAttackTime -= Time.deltaTime;
		onLongAttackTime -= Time.deltaTime;

		// Atack Update
		if (canAttack()) {
			if (lastPressedAttackTime > 0 && characterEnergy.canUseEnergy(characterData.attackEnergy)) {
				attack();
			} else if (lastPressedLongAttackTime > 0 && characterEnergy.canUseEnergy(characterData.longAttackEnergy)) {
				longAttack();
			} else if (lastPressedSpecialAttackTime > 0
					&& characterEnergy.canUseEnergy(characterData.specialAttackEnergy)) {
				specialAttack();
			}
		}

		
		if (isAttacking) {
			
			//Update Timer effect
			if(onAttackEffectTime > 0) {
				onAttackEffectTime -= Time.deltaTime;
				if(onAttackEffectTime <= 0f) {
					attackEffect();
				}
			}
			
			if(onLongAttackEffectTime > 0) {
				onLongAttackEffectTime -= Time.deltaTime;
				if(onLongAttackEffectTime <= 0f) {
					longAttackEffect();
				}
			}
			
			
			// End Attack update
//			if(!characterSpecialAttack.isSepcialAttacking()) {
//				if(onAttackTime > 0) {
//					onAttackTime -= Time.deltaTime;
//					if(onAttackTime <= 0f) {
//						attackComplete();
//					}
//				}
//				if(onLongAttackTime > 0) {
//					onLongAttackTime -= Time.deltaTime;
//					if(onLongAttackTime <= 0f) {
//						attackComplete();
//					}
//				}
//			}
			if (onAttackTime <= 0 && onLongAttackTime <= 0 && !characterSpecialAttack.isSepcialAttacking()) {
				attackComplete();
			}
		}
	}

	public void attack() {
		isAttacking = true;
		lastPressedAttackTime = 0;
		onAttackTime = characterData.attackTime;
		onAttackEffectTime = characterData.attackEffectTime;
		characterMovement.isAttack();
		characterLocomotion.attack();
		characterSound.attack();
		characterEnergy.useEnergy(characterData.attackEnergy);
	}

	public void longAttack() {
		isAttacking = true;
		lastPressedLongAttackTime = 0;
		onLongAttackTime = characterData.longAttackTime;
		onLongAttackEffectTime = characterData.longAttackEffectTime;
		characterMovement.isAttack();
		characterLocomotion.longAttack();
		characterSound.longAttack();
		characterEnergy.useEnergy(characterData.longAttackEnergy);
	}

	public void specialAttack() {
		isAttacking = true;
		lastPressedSpecialAttackTime = 0;
		characterMovement.isAttack();
		characterSpecialAttack.specialAttack();
		characterEnergy.useEnergy(characterData.specialAttackEnergy);
	}
	
	public void attackEffect() {
		rectAttacking(characterData.attackDamage * characterData.attackMultiplicator, characterData.attackPosition, characterData.attackRange);
	}
	
	public void longAttackEffect() {
		rectAttacking(characterData.longAttackDamage * characterData.attackMultiplicator, characterData.longAttackPosition, characterData.longAttackRange);
	}

	public void rectAttacking(float damage, FlatVector pos, FlatVector range) {
		FlatVector position = getRelativePosition(transform.position, pos, characterMovement.isFacingRight);
		ArrayList<Rigidbody> rbs = Physics.OverlapPolygon(position, range.x, range.y, Layer.getDamagableLayers());
		if(rbs.size() > 0) {
			for(Rigidbody rb : rbs) {
				if(rb == this.rb) continue;
				
				IDamagable damagable = rb.gameObject.getComponent(IDamagable.class);
				if(damagable != null) {
					damagable.setDamage(damage);
				}		
			}
		}
	}
	
	public void rectEntityAttacking(float damage, FlatVector pos, FlatVector range, FlatVector endPos) {
		FlatVector position = getRelativePosition(transform.position, pos, characterMovement.isFacingRight);
		ArrayList<Rigidbody> rbs = Physics.OverlapPolygon(position, range.x, range.y, Layer.getEntityLayers());
		if(rbs.size() > 0) {
			for(Rigidbody rb : rbs) {
				if(rb == this.rb) continue;
				
				IDamagable damagable = rb.gameObject.getComponent(IDamagable.class);
				if(damagable != null) {
					if(damagable.canTakeDamage()) {
						damagable.setDamage(damage);
						rb.moveTo(endPos);
					}
				}		
			}
		}
	}
	
	public void rectDestroyingTerrain(FlatVector pos, FlatVector range) {
		FlatVector position = getRelativePosition(transform.position, pos, characterMovement.isFacingRight);
		ArrayList<Rigidbody> rbs = Physics.OverlapPolygon(position, range.x, range.y, Layer.getTerrainLayers());
		if(rbs.size() > 0) {
			for(Rigidbody rb : rbs) {
				if(rb == this.rb) continue;
				
				IDamagable damagable = rb.gameObject.getComponent(IDamagable.class);
				if(damagable != null) {
					damagable.setDamage(Float.MAX_VALUE);
				}		
			}
		}
	}
	
	public void circleAttacking(float damage, FlatVector pos, float radius) {
		FlatVector position = getRelativePosition(transform.position, pos, characterMovement.isFacingRight);
		ArrayList<Rigidbody> rbs = Physics.OverlapCircle(position, radius, Layer.getDamagableLayers());
		if(rbs.size() > 0) {
			for(Rigidbody rb : rbs) {
				if(rb == this.rb) continue;
				
				IDamagable damagable = rb.gameObject.getComponent(IDamagable.class);
				if(damagable != null) {
					damagable.setDamage(damage);
				}		
			}
		}
	}
	
	public boolean canAttack() {
		return characterMovement.canAttack() && !isAttacking && characterHealth.canBeMovable();
	}

	public boolean canUseAttackEnergy() {
		return characterEnergy.canUseEnergy(characterData.attackEnergy);
	}
	
	public boolean canUseLongAttackEnergy() {
		return characterEnergy.canUseEnergy(characterData.longAttackEnergy);
	}
	
	public boolean canUseSpecialAttackEnergy() {
		return characterEnergy.canUseEnergy(characterData.specialAttackEnergy);
	}
	
	// If we want Invincibility
	public boolean canTakeDamage() {
		return !isAttacking;
	}

	public void attackComplete() {
		isAttacking = false;
		lastPressedAttackTime = 0;
		lastPressedLongAttackTime = 0;
		lastPressedSpecialAttackTime = 0;
	}

	public void onAttackInput() {
		lastPressedAttackTime = characterData.attackInputBufferTime;
	}

	public void onLongAttackInput() {
		lastPressedLongAttackTime = characterData.longAttackInputBufferTime;
	}

	public void onSpecialAttackInput() {
		lastPressedSpecialAttackTime = characterData.specialAttackInputBufferTime;
	}
	

	public void isHiting() {
		isAttacking = false;
		lastPressedAttackTime = 0;
		lastPressedLongAttackTime = 0;
		lastPressedSpecialAttackTime = 0;
		onAttackTime = 0;
		onAttackEffectTime = 0;
		onLongAttackTime = 0;
		onLongAttackEffectTime = 0;
		// characterSpecialAttack.isHiting();
	}
	
	public void isDeath() {
		rb.linearVelocity = FlatVector.Zero();
	}

	public boolean isSpecialAttacking() {
		return characterSpecialAttack.isSepcialAttacking();
	}

	public void onDrawGizmos(Graphics g) {
		// Attack
		drawRectRelativeGizmos(g, characterData.attackPosition, characterData.attackRange.x, characterData.attackRange.y,
				Color.red, transform.position, characterMovement.isFacingRight);
		// Long Attack
		drawRectRelativeGizmos(g, characterData.longAttackPosition, characterData.longAttackRange.x, characterData.longAttackRange.y,
				Color.yellow, transform.position, characterMovement.isFacingRight);
	}
	
	public void powerPowerUp(float val) {
		characterData.attackMultiplicator += val;
	}
}