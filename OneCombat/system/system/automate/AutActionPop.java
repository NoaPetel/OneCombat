package system.automate;

import Interfaces.IEntity;

public class AutActionPop extends AutAction{
	public AutActionPop(int percent) {
		super(percent);
	}
	
	public void exec(IEntity entity) {
		entity.getAction().Pop();
	}
}
