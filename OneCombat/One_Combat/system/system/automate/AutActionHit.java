package system.automate;

import Interfaces.IAction;
import Interfaces.IEntity;

public class AutActionHit extends AutAction{
	private IAction.RELDIRECTION directionREL;
	private IAction.ABSDIRECTION directionABS;
	private boolean hasDirection;
	
	public AutActionHit(int percent) {
		super(percent);
		this.directionABS = null;
		this.directionREL = null;
		this.hasDirection = false;
	}
	
	public AutActionHit(IAction.RELDIRECTION direction, int percent) {
		super(percent);
		this.directionREL = (IAction.RELDIRECTION) direction;
		this.directionABS = null;
	}
	
	public AutActionHit(IAction.ABSDIRECTION direction, int percent) {
		super(percent);
		this.directionABS = (IAction.ABSDIRECTION) direction;
		this.directionREL = null;
	}
	
	public void exec(IEntity entity) {
		if(this.hasDirection == true) {
			if(this.directionABS == null) {
				entity.getAction().Hit(this.directionREL);
			}
			else {
				entity.getAction().Hit(this.directionABS);
			}
		}
		else {
			entity.getAction().Hit();
		}
	}
}
