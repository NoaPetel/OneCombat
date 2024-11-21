package system.automate;

import Interfaces.IAction;
import Interfaces.IEntity;

public class AutActionGet extends AutAction{
	private IAction.RELDIRECTION directionREL;
	private IAction.ABSDIRECTION directionABS;
	
	public AutActionGet(int percent) {
		super(percent);
		this.directionABS = null;
		this.directionREL = null;
	}
	
	public AutActionGet(IAction.RELDIRECTION direction, int percent) {
		super(percent);
		this.directionREL = (IAction.RELDIRECTION) direction;
		this.directionABS = null;
	}
	
	public AutActionGet(IAction.ABSDIRECTION direction, int percent) {
		super(percent);
		this.directionABS = (IAction.ABSDIRECTION) direction;
		this.directionREL = null;
	}
	
	public void exec(IEntity entity) {
		if(this.directionABS == null && this.directionREL != null) {
			entity.getAction().Get(this.directionREL);
		}
		else if(this.directionABS != null && this.directionREL == null){
			entity.getAction().Get(this.directionABS);
		}
		else {
			entity.getAction().Get();
		}
	}
}
