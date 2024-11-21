package system.automate;

import Interfaces.IEntity;

public class AutActionJump extends AutAction {
	
	public AutActionJump(int percent) {
		super(percent);
	}

	
	public void exec(IEntity entity) {
		entity.getAction().Jump();
	}
}
