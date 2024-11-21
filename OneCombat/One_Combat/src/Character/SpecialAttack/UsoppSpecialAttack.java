package Character.SpecialAttack;

import Character.Components.CharacterData;
import Interfaces.ISpecialAttack.AttackEffect;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import system.physics.FlatVector;

public class UsoppSpecialAttack extends BasicSpecialAttack {

	// Checks Parameters
	private FlatVector rangeAttack = new FlatVector(2.5f, 2.5f);
	private FlatVector positionAttack = new FlatVector(2.5f, -0.2f);
	private float specialAttackEffectTime = 2.0f;
	private float damage = 60;

	// Attacks Effect
	protected AttackEffect attackEffect;

	public UsoppSpecialAttack(GameObject g, Transform t, CharacterData c) {
		super(g, t, c);
		attackEffects = new AttackEffect[1];
		attackEffects[0] = new AttackEffect(rangeAttack, positionAttack, specialAttackEffectTime, damage);
	}
}
