package system.automate;

import Interfaces.IEntity;

public class AutCondIsNotEmpty extends AutCondition{
	public AutCondIsNotEmpty() {
		
	}
	
	public boolean eval(IEntity entity) {
		return entity.getCondition().isNotEmpty();
	}
}
