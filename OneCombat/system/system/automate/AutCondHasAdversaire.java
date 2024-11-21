package system.automate;

import Interfaces.IEntity;

public class AutCondHasAdversaire extends AutCondition{
	public AutCondHasAdversaire() {
		
	}
	
	public boolean eval(IEntity entity) {
		return entity.getCondition().hasAdversaire();
	}
}
