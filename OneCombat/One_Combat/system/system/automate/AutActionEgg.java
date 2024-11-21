package system.automate;

import Interfaces.IAction;
import Interfaces.IEntity;

public class AutActionEgg extends AutAction{
	private IAction.RELDIRECTION directionREL;
	private IAction.ABSDIRECTION directionABS;
	private boolean hasDirection;
	
	public AutActionEgg(int percent) {
		super(percent);
		this.directionABS = null;
		this.directionREL = null;
		this.hasDirection = false;
	}
	
	public AutActionEgg(IAction.RELDIRECTION direction, int percent) {
		super(percent);
		this.directionREL = (IAction.RELDIRECTION) direction;
		this.directionABS = null;
	}
	
	public AutActionEgg(IAction.ABSDIRECTION direction, int percent) {
		super(percent);
		this.directionABS = (IAction.ABSDIRECTION) direction;
		this.directionREL = null;
	}
	
	public void exec(IEntity entity) {
		if(this.hasDirection == true) {
			if(this.directionABS == null) {
				entity.getAction().Egg(this.directionREL);
			}
			else {
				entity.getAction().Egg(this.directionABS);
			}
		}
		else {
			entity.getAction().Egg();
		}
	}
}
