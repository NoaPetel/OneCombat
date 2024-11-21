package Character.SpecialAttack;

import Character.Components.CharacterData;
import Interfaces.ISpecialAttack.AttackEffect;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;
import system.physics.FlatVector;

public class NamiSpecialAttack extends BasicSpecialAttack {


	// Checks Parameters
	private FlatVector rangeAttack = new FlatVector(4.5f, 1.6f);
	private FlatVector positionAttack = new FlatVector(0.8f, -0.4f);
	private float specialAttackEffectTime = 2.05f;
	private float damage = 60;
	
	//Attacks Effect 
	protected  AttackEffect attackEffect;

	public NamiSpecialAttack(GameObject g, Transform t, CharacterData c) {
		super(g, t, c);
		attackEffects = new AttackEffect[1];
		attackEffects[0] = new AttackEffect(rangeAttack, positionAttack, specialAttackEffectTime, damage);
	}
}
