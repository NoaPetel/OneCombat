package system.automate;

import Interfaces.IAction;
import Interfaces.IEntity;

public class AutCondMyDir extends AutCondition {
	
	private IAction.RELDIRECTION directionREL;
	private IAction.ABSDIRECTION directionABS;
	
	public AutCondMyDir(IAction.RELDIRECTION direction) {
		this.directionREL = direction;
		this.directionABS = null;
	}
	
	public AutCondMyDir(IAction.ABSDIRECTION direction) {
		this.directionABS = direction;
		this.directionREL = null;
	}
	
	public boolean eval(IEntity entity) {
		if(this.directionABS == null) {
			return entity.getCondition().MyDir(directionREL);
		}
		else {
			return entity.getCondition().MyDir(directionABS);
		}
	}
}
