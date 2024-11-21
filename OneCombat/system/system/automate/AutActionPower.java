package system.automate;

import Interfaces.IEntity;

public class AutActionPower extends AutAction{
	public AutActionPower(int percent) {
		super(percent);
	}
	
	public void exec(IEntity entity) {
		entity.getAction().Power();
	}
}
