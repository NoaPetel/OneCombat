package Character.SpecialAttack;

import java.awt.Color;
import java.awt.Graphics;

import Character.Components.CharacterAttack;
import Character.Components.CharacterData;
import Character.Components.CharacterLocomotion;
import Character.Components.CharacterMovement;
import Interfaces.ISpecialAttack;
import Interfaces.ISpecialAttack.AttackEffect;
import system.Time;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import system.physics.FlatVector;

public class SanjiSpecialAttack extends BasicSpecialAttack {


	// Checks Parameters
	private FlatVector rangeAttack1 = new FlatVector(0.7f, 0.7f);
	private FlatVector positionAttack1 = new FlatVector(1.42f, -0.5f);
	private float specialAttackEffectTime1 = 0.6f;
	private float damage1 = 20;
	
	private FlatVector rangeAttack2 = new FlatVector(0.6f, 0.8f);
	private FlatVector positionAttack2 = new FlatVector(1.52f, -0.4f);
	private float specialAttackEffectTime2 = 0.9f;
	private float damage2 = 20;
	
	private FlatVector rangeAttack3 = new FlatVector(0.8f, 0.4f);
	private FlatVector positionAttack3 = new FlatVector(1.32f, 0.1f);
	private float specialAttackEffectTime3 = 1.4f;
	private float damage3 = 20;
	
	private FlatVector rangeAttack4 = new FlatVector(0.8f, 0.6f);
	private FlatVector positionAttack4 = new FlatVector(1.40f, -0.7f);
	private float specialAttackEffectTime4 = 1.6f;
	private float damage4 = 20;
	
	private FlatVector rangeAttack5 = new FlatVector(0.45f, 1.1f);
	private FlatVector positionAttack5 = new FlatVector(1.84f, -0.45f);
	private float specialAttackEffectTime5 = 1.7f;
	private float damage5 = 20;
	
	private FlatVector rangeAttack6 = new FlatVector(0.5f, 0.5f);
	private FlatVector positionAttack6 = new FlatVector(1.90f, -1f);
	private float specialAttackEffectTime6 = 2.1f;
	private float damage6 = 20;
	
	private FlatVector rangeAttack7 = new FlatVector(1f, 0.4f);
	private FlatVector positionAttack7 = new FlatVector(2.2f, 0f);
	private float specialAttackEffectTime7 = 2.5f;
	private float damage7 = 20;
	
	private FlatVector rangeAttack8 = new FlatVector(0.9f, 0.6f);
	private FlatVector positionAttack8 = new FlatVector(2.7f, -0.5f);
	private float specialAttackEffectTime8 = 2.9f;
	private float damage8 = 20;
	
	private FlatVector rangeAttack9 = new FlatVector(0.4f, 0.6f);
	private FlatVector positionAttack9 = new FlatVector(2.85f, -0.05f);
	private float specialAttackEffectTime9 = 3.3f;
	private float damage9 = 20;
	
	private FlatVector rangeAttack10 = new FlatVector(1.5f, 1f);
	private FlatVector positionAttack10 = new FlatVector(3f, -0.2f);
	private float specialAttackEffectTime10 = 3.9f;
	private float damage10 = 20;

	public SanjiSpecialAttack(GameObject g, Transform t, CharacterData c) {
		super(g, t, c);
		attackEffects = new AttackEffect[10];
		attackEffects[0] = new AttackEffect(rangeAttack1, positionAttack1, specialAttackEffectTime1, damage1, 1, true, FlatVector.plus(positionAttack1,  new FlatVector(0.5f, 0)));
		attackEffects[1] = new AttackEffect(rangeAttack2, positionAttack2, specialAttackEffectTime2, damage2, 1, true, FlatVector.plus(positionAttack2,  new FlatVector(0.5f, 0)));
		attackEffects[2] = new AttackEffect(rangeAttack3, positionAttack3, specialAttackEffectTime3, damage3, 1, true, FlatVector.plus(positionAttack3,  new FlatVector(0.5f, 0)));
		attackEffects[3] = new AttackEffect(rangeAttack4, positionAttack4, specialAttackEffectTime4, damage4, 1, true, FlatVector.plus(positionAttack4,  new FlatVector(0.5f, 0)));
		attackEffects[4] = new AttackEffect(rangeAttack5, positionAttack5, specialAttackEffectTime5, damage5, 1, true, FlatVector.plus(positionAttack5,  new FlatVector(0.5f, 0)));
		attackEffects[5] = new AttackEffect(rangeAttack6, positionAttack6, specialAttackEffectTime6, damage6, 1, true, FlatVector.plus(positionAttack6,  new FlatVector(0.5f, 0)));
		attackEffects[6] = new AttackEffect(rangeAttack7, positionAttack7, specialAttackEffectTime7, damage7, 1, true, FlatVector.plus(positionAttack7,  new FlatVector(0.5f, 0)));
		attackEffects[7] = new AttackEffect(rangeAttack8, positionAttack8, specialAttackEffectTime8, damage8, 1, true, FlatVector.plus(positionAttack8,  new FlatVector(0.5f, 0)));
		attackEffects[8] = new AttackEffect(rangeAttack9, positionAttack9, specialAttackEffectTime9, damage9, 1, true, FlatVector.plus(positionAttack9,  new FlatVector(0.5f, 0)));
		attackEffects[9] = new AttackEffect(rangeAttack10, positionAttack10, specialAttackEffectTime10, damage10, 1, true, FlatVector.plus(positionAttack10,  new FlatVector(0.5f, 0)));
		endPosition = new FlatVector(3f, 0f);
	}


}
