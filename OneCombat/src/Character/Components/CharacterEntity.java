package Character.Components;

import Interfaces.IAction;
import Interfaces.ICondition;
import Interfaces.IEntity;
import system.GameSystem;
import system.automate.AutAutomate;
import system.object.component.monobehavior.MonoBehavior;
import system.object.component.transform.Transform;
import system.object.gameobject.GameObject;

public class CharacterEntity extends MonoBehavior implements IEntity {

	AutAutomate automate;
	ICondition conditions;
	IAction actions;
	
	public CharacterEntity(GameObject g, Transform t, String automateName) {
		super(g, t);
		automate = GameSystem.system.automateManager.getAutomate(automateName);
	}
	
	public void start() {
		conditions = gameObject.getComponent(ICondition.class);
		actions = gameObject.getComponent(IAction.class);
	}
	
	public void update() {
		automate.step(this);
	}

	@Override
	public IAction getAction() {
		return actions;
	}

	@Override
	public ICondition getCondition() {
		return conditions;
	}
	
}
