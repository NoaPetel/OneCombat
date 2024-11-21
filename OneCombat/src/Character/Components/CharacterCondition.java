package Character.Components;

import Interfaces.IAction.ABSDIRECTION;
import Interfaces.IAction.RELDIRECTION;
import Interfaces.ICondition;
import system.InputManager;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;

public class CharacterCondition extends MonoBehavior implements ICondition {

	public CharacterCondition(GameObject g, Transform t) {
		super(g, t);
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
		return true;
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
		return InputManager.IsPressed(c);
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
