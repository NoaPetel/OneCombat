package system.automate;

import Interfaces.IAction;
import Interfaces.IEntity;

public class AutActionPick extends AutAction{
	private IAction.RELDIRECTION directionREL;
	private IAction.ABSDIRECTION directionABS;
	
	public AutActionPick(IAction.RELDIRECTION direction, int percent) {
		super(percent);
		this.directionREL = (IAction.RELDIRECTION) direction;
		this.directionABS = null;
	}
	
	public AutActionPick(IAction.ABSDIRECTION direction, int percent) {
		super(percent);
		this.directionABS = (IAction.ABSDIRECTION) direction;
		this.directionREL = null;
	}
	
	public void exec(IEntity entity) {
		if(this.directionABS == null) {
			entity.getAction().Pick(this.directionREL);
		}
		else {
			entity.getAction().Pick(this.directionABS);
		}
	}
}
