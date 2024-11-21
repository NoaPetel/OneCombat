package system.automate;

import Interfaces.IAction;
import Interfaces.IEntity;

public class AutActionThrow extends AutAction{
	private IAction.RELDIRECTION directionREL;
	private IAction.ABSDIRECTION directionABS;
	
	public AutActionThrow(int percent) {
		super(percent);
		this.directionABS = null;
		this.directionREL = null;
	}
	
	public AutActionThrow(IAction.RELDIRECTION direction, int percent) {
		super(percent);
		this.directionREL = (IAction.RELDIRECTION) direction;
		this.directionABS = null;
	}
	
	public AutActionThrow(IAction.ABSDIRECTION direction, int percent) {
		super(percent);
		this.directionABS = (IAction.ABSDIRECTION) direction;
		this.directionREL = null;
	}
	
	public void exec(IEntity entity) {
		if(this.directionABS == null && this.directionREL != null) {
			entity.getAction().Throw(this.directionREL);
		}
		else if(this.directionABS != null && this.directionREL == null) {
			entity.getAction().Throw(this.directionABS);
		}
		else {
			entity.getAction().Throw();
		}
	}
}
