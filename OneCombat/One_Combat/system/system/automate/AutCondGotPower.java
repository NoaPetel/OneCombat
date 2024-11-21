package system.automate;

import Interfaces.IEntity;

public class AutCondGotPower extends AutCondition{
	public AutCondGotPower() {
		
	}
	
	public boolean eval(IEntity entity) {
		return entity.getCondition().gotPower();
	}
}
