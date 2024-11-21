package system.automate;

import Interfaces.IEntity;

public class AutActionWizz extends AutAction{
	public AutActionWizz(int percent) {
		super(percent);
	}
	
	public void exec(IEntity entity) {
		entity.getAction().Wizz();
	}
}
