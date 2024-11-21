package Character.SpecialAttack;

import Character.Components.CharacterData;
import Interfaces.ISpecialAttack.AttackEffect;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import system.physics.FlatVector;

public class ViviSpecialAttack extends BasicSpecialAttack {

	// Checks Parameters
	private FlatVector rangeAttack1 = new FlatVector(2.6f, 2f);
	private FlatVector positionAttack1 = new FlatVector(0f, -0.2f);
	private float specialAttackEffectTime1 = 0.6f;
	private float damage1 = 30;
	
	private FlatVector rangeAttack2 = new FlatVector(2.6f, 2f);
	private FlatVector positionAttack2 = new FlatVector(1f, -0.2f);
	private float specialAttackEffectTime2 = 0.7f;
	private float damage2 = 30;
	
	private FlatVector rangeAttack3 = new FlatVector(2.6f, 2f);
	private FlatVector positionAttack3 = new FlatVector(2f, -0.2f);
	private float specialAttackEffectTime3 = 0.8f;
	private float damage3 = 30;
	
	private FlatVector rangeAttack4 = new FlatVector(2.6f, 2f);
	private FlatVector positionAttack4 = new FlatVector(3f, -0.2f);
	private float specialAttackEffectTime4 = 0.9f;
	private float damage4 = 30;
	
	private FlatVector rangeAttack5 = new FlatVector(2.6f, 2f);
	private FlatVector positionAttack5 = new FlatVector(4f, -0.2f);
	private float specialAttackEffectTime5 = 1f;
	private float damage5 = 30;
	
	private FlatVector rangeAttack6 = new FlatVector(2.6f, 2f);
	private FlatVector positionAttack6 = new FlatVector(5f, -0.2f);
	private float specialAttackEffectTime6 = 1.1f;
	private float damage6 = 30;
	
	

	// Attacks Effect
	protected AttackEffect attackEffect;

	public ViviSpecialAttack(GameObject g, Transform t, CharacterData c) {
		super(g, t, c);
		attackEffects = new AttackEffect[6];
		attackEffects[0] = new AttackEffect(rangeAttack1, positionAttack1, specialAttackEffectTime1, damage1);
		attackEffects[1] = new AttackEffect(rangeAttack2, positionAttack2, specialAttackEffectTime2, damage2);
		attackEffects[2] = new AttackEffect(rangeAttack3, positionAttack3, specialAttackEffectTime3, damage3);
		attackEffects[3] = new AttackEffect(rangeAttack4, positionAttack4, specialAttackEffectTime4, damage4);
		attackEffects[4] = new AttackEffect(rangeAttack5, positionAttack5, specialAttackEffectTime5, damage5);
		attackEffects[5] = new AttackEffect(rangeAttack6, positionAttack6, specialAttackEffectTime6, damage6);
	}
}
