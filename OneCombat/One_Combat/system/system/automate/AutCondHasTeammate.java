package system.automate;

import Interfaces.IEntity;

public class AutCondHasTeammate extends AutCondition{
	public AutCondHasTeammate() {
		
	}
	
	public boolean eval(IEntity entity) {
		return entity.getCondition().hasTeammate();
	}
}
