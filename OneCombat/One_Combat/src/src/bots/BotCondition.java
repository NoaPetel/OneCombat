package src.bots;

import Interfaces.IAction.ABSDIRECTION;
import Interfaces.IAction.RELDIRECTION;
import Interfaces.ICondition;
import src.bots.actions.BotAttack;
import src.bots.actions.BotHeal;
import src.bots.actions.BotHealth;
import src.bots.actions.BotHit;
import src.bots.actions.BotMovement;
import src.bots.actions.BotThrow;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;

public class BotCondition extends MonoBehavior implements ICondition {
	
	private BotHealth botHealth;
	private BotAttack botAttack;
	private BotMovement botMovement;
	private BotThrow botThrow;
	private BotHit botHit;
	private BotHeal botHeal;
	



	public BotCondition(GameObject g, Transform t) {
		super(g, t);
	}
	public void start() {
		botMovement = gameObject.getComponent(BotMovement.class);
		botAttack = gameObject.getComponent(BotAttack.class);
		botHealth = gameObject.getComponent(BotHealth.class);
		botThrow = gameObject.getComponent(BotThrow.class);
		botHit = gameObject.getComponent(BotHit.class);
		botHeal = gameObject.getComponent(BotHeal.class);
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
		case 49: //Key(1)  canAttack (Wizz weakbot)
			return botAttack.canAttack();
			
		case 50: //Key(2) canTurn (Turn weakbot)
			return botMovement.canTurn();
			
		case 51: //Key(3) random 1% pour le heal
			double rand = Math.random();
			boolean a = (rand >0.5);
			//System.out.println("ici " + a);
			return (rand > 0.99);
			
			
		case 52: //Key(4) canThrow
			return botThrow.canShoot();
			
		case 53: //Key(5) is Throw
			return botThrow.isShooting;
			
		case 54: //Key(6) can hit
			return botHit.canHit();
			
		case 55: //Key(7) can heal
			return botHeal.canHeal();
			
		case 56: //Key(8) is Alive
			return botHealth.isAlive();
			
		case 57: //Key(9) can be movable
			return botHealth.canBeMovable();

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
