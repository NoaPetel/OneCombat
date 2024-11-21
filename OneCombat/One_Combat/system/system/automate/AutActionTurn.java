package system.automate;

import Interfaces.IAction;
import Interfaces.IEntity;

public class AutActionTurn extends AutAction{
	private IAction.RELDIRECTION directionREL;
	private IAction.ABSDIRECTION directionABS;
	
	public AutActionTurn(IAction.RELDIRECTION direction, int percent) {
		super(percent);
		this.directionREL = (IAction.RELDIRECTION) direction;
		this.directionABS = null;
	}
	
	public AutActionTurn(IAction.ABSDIRECTION direction, int percent) {
		super(percent);
		this.directionABS = (IAction.ABSDIRECTION) direction;
		this.directionREL = null;
	}
	
	public void exec(IEntity entity) {
		if(this.directionABS == null) {
			entity.getAction().Turn(this.directionREL);
		}
		else {
			entity.getAction().Turn(this.directionABS);
		}
	}
}
