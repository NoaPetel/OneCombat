package system.automate;

import Interfaces.IEntity;

public class AutCondIsEmpty extends AutCondition{
	public AutCondIsEmpty() {
		
	}
	
	public boolean eval(IEntity entity) {
		return entity.getCondition().isEmpty();
	}
}
