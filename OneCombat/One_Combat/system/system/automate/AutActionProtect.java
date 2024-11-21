package system.automate;

import Interfaces.IEntity;

public class AutActionProtect extends AutAction{
	public AutActionProtect(int percent) {
		super(percent);
	}
	
	public void exec(IEntity entity) {
		entity.getAction().Protect();
	}
}
