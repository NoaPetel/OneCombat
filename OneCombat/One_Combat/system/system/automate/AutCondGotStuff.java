package system.automate;

import Interfaces.IEntity;

public class AutCondGotStuff extends AutCondition{
	public AutCondGotStuff() {
		
	}
	
	public boolean eval(IEntity entity) {
		return entity.getCondition().gotStuff();
	}
}
