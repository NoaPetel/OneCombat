package system.automate;

import Interfaces.IEntity;

public class AutActionWait extends AutAction{
	public AutActionWait(int percent) {
		super(percent);
	}
	
	public void exec(IEntity entity) {
		entity.getAction().Wait();
	}
}
