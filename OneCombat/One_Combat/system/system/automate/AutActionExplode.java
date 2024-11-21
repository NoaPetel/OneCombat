package system.automate;

import Interfaces.IEntity;

public class AutActionExplode extends AutAction{
	public AutActionExplode(int percent) {
		super(percent);
	}
	
	public void exec(IEntity entity) {
		entity.getAction().Explode();
	}
}
