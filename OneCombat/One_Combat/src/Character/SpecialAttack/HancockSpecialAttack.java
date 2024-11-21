package Character.SpecialAttack;

import Character.Components.CharacterData;
import Interfaces.ISpecialAttack.AttackEffect;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import system.physics.FlatVector;

public class HancockSpecialAttack extends BasicSpecialAttack {

	// Checks Parameters
	private FlatVector rangeAttack1 = new FlatVector(2f, 1f);
	private FlatVector positionAttack1 = new FlatVector(1.5f, -0.2f);
	private float specialAttackEffectTime1 = 0.9f;
	private float damage1 = 30;

	private FlatVector rangeAttack2 = new FlatVector(2f, 1f);
	private FlatVector positionAttack2 = new FlatVector(2.3f, -0.2f);
	private float specialAttackEffectTime2 = 1f;
	private float damage2 = 30;

	private FlatVector rangeAttack3 = new FlatVector(2f, 1f);
	private FlatVector positionAttack3 = new FlatVector(3.1f, -0.2f);
	private float specialAttackEffectTime3 = 1.1f;
	private float damage3 = 30;

	private FlatVector rangeAttack4 = new FlatVector(2f, 1.1f);
	private FlatVector positionAttack4 = new FlatVector(3.9f, -0.2f);
	private float specialAttackEffectTime4 = 1.2f;
	private float damage4 = 30;

	private FlatVector rangeAttack5 = new FlatVector(2f, 1.2f);
	private FlatVector positionAttack5 = new FlatVector(4.7f, -0.2f);
	private float specialAttackEffectTime5 = 1.3f;
	private float damage5 = 30;

	private FlatVector rangeAttack6 = new FlatVector(2f, 1.3f);
	private FlatVector positionAttack6 = new FlatVector(5.5f, -0.2f);
	private float specialAttackEffectTime6 = 1.4f;
	private float damage6 = 30;

	private FlatVector rangeAttack7 = new FlatVector(2f, 1.4f);
	private FlatVector positionAttack7 = new FlatVector(6.3f, -0.2f);
	private float specialAttackEffectTime7 = 1.5f;
	private float damage7 = 30;

	private FlatVector rangeAttack8 = new FlatVector(2f, 1.5f);
	private FlatVector positionAttack8 = new FlatVector(6.8f, -0.2f);
	private float specialAttackEffectTime8 = 1.6f;
	private float damage8 = 30;

	// Attacks Effect
	protected AttackEffect attackEffect;

	public HancockSpecialAttack(GameObject g, Transform t, CharacterData c) {
		super(g, t, c);
		attackEffects = new AttackEffect[8];
		attackEffects[0] = new AttackEffect(rangeAttack1, positionAttack1, specialAttackEffectTime1, damage1);
		attackEffects[1] = new AttackEffect(rangeAttack2, positionAttack2, specialAttackEffectTime2, damage2);
		attackEffects[2] = new AttackEffect(rangeAttack3, positionAttack3, specialAttackEffectTime3, damage3);
		attackEffects[3] = new AttackEffect(rangeAttack4, positionAttack4, specialAttackEffectTime4, damage4);
		attackEffects[4] = new AttackEffect(rangeAttack5, positionAttack5, specialAttackEffectTime5, damage5);
		attackEffects[5] = new AttackEffect(rangeAttack6, positionAttack6, specialAttackEffectTime6, damage6);
		attackEffects[6] = new AttackEffect(rangeAttack7, positionAttack7, specialAttackEffectTime7, damage7);
		attackEffects[7] = new AttackEffect(rangeAttack8, positionAttack8, specialAttackEffectTime8, damage8);
	}

}
