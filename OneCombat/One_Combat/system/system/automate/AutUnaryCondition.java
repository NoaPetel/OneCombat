package system.automate;

import Interfaces.IEntity;

public class AutUnaryCondition extends AutCondition {
	private AutCondition expression;
	private String operator;
	
	public AutUnaryCondition(AutCondition expression, String operator) {
		this.expression = expression;
		this.operator = operator;
	}
	
	public boolean eval(IEntity entity) {
		if(this.operator == "!") {
			return !(this.expression.eval(entity));
		}
		else {
			return this.expression.eval(entity);
		}
	}
}
