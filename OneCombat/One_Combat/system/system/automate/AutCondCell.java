package system.automate;

import Interfaces.IAction;
import Interfaces.ICondition;
import Interfaces.IEntity;

public class AutCondCell extends AutCondition{
	IAction.ABSDIRECTION directionABS;
	
	private ICondition.TYPE categorie;
	
	public AutCondCell(IAction.ABSDIRECTION  direction, ICondition.TYPE categorie) {
		this.directionABS = direction;
		this.categorie = categorie;
	}
	
	public boolean eval(IEntity entity) {
		return entity.getCondition().Cell(directionABS, this.categorie);
	}
}
