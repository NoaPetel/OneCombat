package system.automate;

import Interfaces.IEntity;

public abstract class AutAction {
	
	protected int percent;
	
	public AutAction(int percent) {
		this.percent = percent;
	}
	
	abstract public void exec(IEntity e);
	
	public int getPercent() {
		return this.percent;
	}
}
