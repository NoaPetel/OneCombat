package Character.SpecialAttack;

import Character.Components.CharacterData;
import Interfaces.ISpecialAttack.AttackEffect;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import system.physics.FlatVector;

public class AceSpecialAttack extends BasicSpecialAttack {

	// Checks Parameters
	private FlatVector rangeAttack1 = new FlatVector(2.4f, 2.4f);
	private FlatVector positionAttack1 = new FlatVector(-0.2f, -1.8f);
	private float specialAttackEffectTime1 = 1.7f;
	private float damage1 = 20;

	private FlatVector rangeAttack2 = new FlatVector(3f, 3f);
	private FlatVector positionAttack2 = new FlatVector(1.5f, -1.5f);
	private float specialAttackEffectTime2 = 1.9f;
	private float damage2 = 40;

	private FlatVector rangeAttack3 = new FlatVector(3f, 3f);
	private FlatVector positionAttack3 = new FlatVector(3f, -1f);
	private float specialAttackEffectTime3 = 2f;
	private float damage3 = 60;

	public AceSpecialAttack(GameObject g, Transform t, CharacterData c) {
		super(g, t, c);
		attackEffects = new AttackEffect[3];
		attackEffects[0] = new AttackEffect(rangeAttack1, positionAttack1, specialAttackEffectTime1, damage1);
		attackEffects[1] = new AttackEffect(rangeAttack2, positionAttack2, specialAttackEffectTime2, damage2);
		attackEffects[2] = new AttackEffect(rangeAttack3, positionAttack3, specialAttackEffectTime3, damage3);
	}
}
