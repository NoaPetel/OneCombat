package system.automate;

import Interfaces.IEntity;

public class AutActionStore extends AutAction{
	public AutActionStore(int percent) {
		super(percent);
	}
	
	public void exec(IEntity entity) {
		entity.getAction().Store();
	}
}
