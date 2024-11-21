package system.automate;

import Interfaces.IEntity;

public class AutCondHasMissile extends AutCondition{
	public AutCondHasMissile() {
		
	}
	
	public boolean eval(IEntity entity) {
		return entity.getCondition().hasMissile();
	}
}
