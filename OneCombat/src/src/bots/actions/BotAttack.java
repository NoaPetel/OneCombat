package src.bots.actions;

import java.awt.Graphics;
import java.util.ArrayList;

import Interfaces.IDamagable;
import system.Physics;
import system.Time;
import system.enumeration.Layer;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.renderer.SpriteRenderer;
import system.object.component.rigidbody.Rigidbody;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import system.physics.FlatVector;

public class BotAttack extends MonoBehavior{
	
	public boolean isAttacking;
	
	private float attackSpaceTime = 5f;
	private float onAttackSpaceTime = 0;
	private float attackTime = 1f;
	private float onAttackTime = 0f;
	
	private float DEGAT = 20;
	
	//Compoenents
	BotLocomotion BotLocomotion;
	BotThrow BotThrow;
	BotHealth BotHealth;
	SpriteRenderer spriteRenderer;
	BotMovement movement;
	
	public BotAttack(GameObject gameObject, Transform transform) {
		super(gameObject, transform);
		this.isAttacking = false;
	}
	
	public void start() {
		BotLocomotion = gameObject.getComponent(BotLocomotion.class);
		BotThrow = gameObject.getComponent(BotThrow.class);
		BotHealth = gameObject.getComponent(BotHealth.class);
		spriteRenderer = (SpriteRenderer) gameObject.getRenderer();
		this.movement = gameObject.getComponent(BotMovement.class);
		this.onAttackSpaceTime = attackSpaceTime;
	}
	
	public void update() {
		if(isAttacking == false) {
			onAttackSpaceTime -= Time.deltaTime;

		}
		else {
			if(onAttackTime <= 0) {
				isAttacking = false;
			}
			else {
				onAttackTime -= Time.deltaTime;
			}
		}
	}
				
	
	
	public void attack() {
		
		if(!canAttack()) return;
		
		onAttackSpaceTime = attackSpaceTime;
		isAttacking = true;
		BotLocomotion.attack();
		onAttackTime = attackTime;
		
		FlatVector attack = new FlatVector(0.5f,-0.2f);
		if(this.movement.isFlip) {
			attack.x = -attack.x;
		}
		FlatVector position = FlatVector.plus(transform.position, attack);
		
		ArrayList<Rigidbody> rbs = Physics.OverlapPolygon(position, 0.6f, 1f, Layer.Character);
		rbs.addAll(Physics.OverlapPolygon(position, 0.7f, 1f, Layer.Map));
		if(rbs.size() > 0) {
			for(Rigidbody rb : rbs) {
				IDamagable damagable = rb.gameObject.getComponent(IDamagable.class);
				if(damagable != null) {
					damagable.setDamage(DEGAT);
				}
			}
		}

	}
	
	public boolean canAttack() { // à recuperer dans l'automate
		return !isAttacking && onAttackSpaceTime <= 0 && !BotThrow.isShooting && BotHealth.canBeMovable();
	}
	
	public void onDrawGizmos(Graphics g) {
		FlatVector attack = new FlatVector(0.5f,-0.2f);
		if(this.movement.isFlip) {
			attack.x = -attack.x;
		}
		FlatVector position = FlatVector.plus(transform.position, attack);
		drawRectGizmos(g,position, 0.6f, 1f);
	}

}
