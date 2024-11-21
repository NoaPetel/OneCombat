package system.automate;

import Interfaces.IAction;
import Interfaces.IEntity;

public class AutActionMove extends AutAction {
	
	private IAction.RELDIRECTION directionREL;
	private IAction.ABSDIRECTION directionABS;
	private boolean hasDirection;
	
	public AutActionMove(int percent) {
		super(percent);
		this.directionABS = null;
		this.directionREL = null;
		this.hasDirection = false;
	}
	
	public AutActionMove(IAction.RELDIRECTION direction, int percent) {
		super(percent);
		this.directionREL = (IAction.RELDIRECTION) direction;
		this.directionABS = null;
		this.hasDirection = true;
	}
	
	public AutActionMove(IAction.ABSDIRECTION direction, int percent) {
		super(percent);
		this.directionABS = (IAction.ABSDIRECTION) direction;
		this.directionREL = null;
		this.hasDirection = true;
	}
	
	public void exec(IEntity entity) {
		if(this.hasDirection == true) {
			if(this.directionABS == null) {
				entity.getAction().Move(this.directionREL);
			}
			else {
				entity.getAction().Move(this.directionABS);
			}
		}
		else {
			entity.getAction().Move();
		}
	}
}
