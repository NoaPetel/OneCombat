package system.automate;

import Interfaces.IEntity;

public class AutCondTrue extends AutCondition{
	
	public boolean expression;
	
	public AutCondTrue() {
		this.expression = true;
	}
	
	@Override
	public boolean eval(IEntity e) {
		return this.expression;
	}
}
