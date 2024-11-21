package Character.Components;

import Interfaces.IAction.ABSDIRECTION;
import Interfaces.IAction.RELDIRECTION;
import Interfaces.ICondition;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;

public class CharacterAiCondition extends MonoBehavior implements ICondition {
	
	private CharacterHealth characterHealth;
	private CharacterAttack characterAttack;
	private CharacterAiMovement characteraimovement;
	private CharacterAiHandler characterAiHandler;
	



	public CharacterAiCondition(GameObject g, Transform t) {
		super(g, t);
	}
	public void start() {
		characteraimovement = gameObject.getComponent(CharacterAiMovement.class);
		characterAiHandler = gameObject.getComponent(CharacterAiHandler.class);
		characterAttack = gameObject.getComponent(CharacterAttack.class);
		characterHealth = gameObject.getComponent(CharacterHealth.class);
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isNotEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean gotPower() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasAdversaire() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean True() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasTeammate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasMissile() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Key(int c) {
		switch (c) {
		case 49: //Key(1) random 50 50
			
			double rand = Math.random();
			boolean a = (rand >0.5);
			//System.out.println("ici " + a);
			return (rand > 0.5);
			
		case 50: //Key(2) isDamaged
			//System.out.println(characterHealth.isHiting());
			return characterHealth.isHiting();
			
		case 51: //Key(3) isAttacking
			return characterAttack.isAttacking;
			
		case 52: //Key(4) isClose//TODO
			return characterAiHandler.isClose();
			
		case 53: //Key(5) isAlive
			return characterHealth.isAlive();
			
		case 54: //Key(6) hasEnergyLong //TODO
			return true;
			
		case 55: //Key(7) hasEnergySpecial //TODO
			return true;
			
		case 56: //Key(8) isBloqued
			//System.out.println(characterAiHandler.isBloquedCheck());
			return characterAiHandler.isBloquedCheck();
			
		case 57: //Key(9) isArrived
			//System.out.println("key9  "+characteraimovement.isArrived());

			return characteraimovement.isArrived();
		default: 
			return false;
		
			}
	}

	@Override
	public boolean MyDir(RELDIRECTION d) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean MyDir(ABSDIRECTION d) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Cell(ABSDIRECTION d, TYPE t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean gotStuff() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Closest(TYPE t, ABSDIRECTION d) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Closest(TYPE t, RELDIRECTION d) {
		// TODO Auto-generated method stub
		return false;
	}
}
