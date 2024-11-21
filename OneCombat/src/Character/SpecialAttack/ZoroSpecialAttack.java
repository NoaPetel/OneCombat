package Character.SpecialAttack;

import java.awt.Color;
import java.awt.Graphics;

import Character.Components.CharacterAttack;
import Character.Components.CharacterData;
import Character.Components.CharacterLocomotion;
import Character.Components.CharacterMovement;
import system.Time;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import system.physics.FlatVector;

public class ZoroSpecialAttack extends BasicSpecialAttack {


	// Checks Parameters
//	private FlatVector rangeAttack = new FlatVector(5f, 1.8f);
//	private FlatVector positionAttack = new FlatVector(3f, -0.5f);
//	private float specialAttackEffectTime = 1f;
//	private float damage = 60;
	
	//Attacks Effect 
	protected  AttackEffect attackEffect;

	// EndPosition
	//protected  FlatVector endPosition = new FlatVector(4.5f, 0f);
	//protected  FlatVector endEntityPosition = new FlatVector(2.2f, -1.5f);

	public ZoroSpecialAttack(GameObject g, Transform t, CharacterData c) {
		super(g, t, c);
		attackEffects = new AttackEffect[1];
		attackEffects[0] = new AttackEffect(new FlatVector(5f, 1.8f), new FlatVector(3f, -0.5f), 1f, 60, 1, true, new FlatVector(2.2f, -1.5f));
		endPosition = new FlatVector(4.5f, 0f);
	}
}
