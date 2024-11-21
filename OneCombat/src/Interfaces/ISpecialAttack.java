package Interfaces;

import system.physics.FlatVector;

public interface ISpecialAttack {

	public class AttackEffect {

		// type = 0 for simple attack effect
		// type = 1 for attack effect with move entity Position;
		public int type = 0;
		public boolean isDestroyTerrain = false;

		public FlatVector rangeAttack;
		public FlatVector positionAttack;
		public float onSpecialAttackEffectTime;
		public float specialAttackEffectTime;
		public float damage;
		public FlatVector endEntityPosition;

		public AttackEffect(FlatVector rangeAttack, FlatVector positionAttack, float specialAttackEffectTime,
				float damage, int type, boolean isDestroyTerrain, FlatVector endEntityPosition) {
			this.rangeAttack = rangeAttack;
			this.positionAttack = positionAttack;
			this.specialAttackEffectTime = specialAttackEffectTime;
			this.onSpecialAttackEffectTime = specialAttackEffectTime;
			this.damage = damage;
			this.type = type;
			this.isDestroyTerrain = isDestroyTerrain;
			this.endEntityPosition = endEntityPosition;
		}
		
		public AttackEffect(FlatVector rangeAttack, FlatVector positionAttack, float specialAttackEffectTime,
				float damage) {
			this.rangeAttack = rangeAttack;
			this.positionAttack = positionAttack;
			this.specialAttackEffectTime = specialAttackEffectTime;
			this.onSpecialAttackEffectTime = specialAttackEffectTime;
			this.damage = damage;
		}

		public void resetTimer() {
			onSpecialAttackEffectTime = specialAttackEffectTime;
		}
	}

	void specialAttack();

	boolean isSepcialAttacking();
	// void isHiting();
}
