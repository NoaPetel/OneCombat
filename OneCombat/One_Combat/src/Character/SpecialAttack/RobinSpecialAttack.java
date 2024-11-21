package Character.SpecialAttack;

import Character.Components.CharacterData;
import Interfaces.ISpecialAttack.AttackEffect;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import system.physics.FlatVector;

public class RobinSpecialAttack extends BasicSpecialAttack {


	// Checks Parameters
	private FlatVector rangeAttack = new FlatVector(4.5f, 1.6f);
	private FlatVector positionAttack = new FlatVector(1.6f, -0.2f);
	private float specialAttackEffectTime = 1.5f;
	private float damage = 60;
	
	//Attacks Effect 
	protected  AttackEffect attackEffect;

	public RobinSpecialAttack(GameObject g, Transform t, CharacterData c) {
		super(g, t, c);
		attackEffects = new AttackEffect[1];
		attackEffects[0] = new AttackEffect(rangeAttack, positionAttack, specialAttackEffectTime, damage);
	}
}
