package system.automate;

import Interfaces.IAction;
import Interfaces.ICondition;
import Interfaces.IEntity;

public class AutCondClosest extends AutCondition{
	
	private IAction.RELDIRECTION directionREL;
	private IAction.ABSDIRECTION directionABS;
	
	private ICondition.TYPE categorie;
	
	public AutCondClosest(ICondition.TYPE categorie, IAction.RELDIRECTION direction) {
		this.directionREL = direction;
		this.directionABS = null;
		this.categorie = categorie;
	}
	
	public AutCondClosest(ICondition.TYPE categorie, IAction.ABSDIRECTION direction) {
		this.directionABS = direction;
		this.directionREL = null;
		this.categorie = categorie;
	}
	
	public boolean eval(IEntity entity) {
		if(this.directionABS == null) {
			return entity.getCondition().Closest(this.categorie, this.directionREL);
		}
		else {
			return entity.getCondition().Closest(this.categorie, this.directionABS);
		}
	}
}
