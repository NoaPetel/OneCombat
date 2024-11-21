package system.automate;

import Interfaces.IEntity;

public class AutBinaryCondition extends AutCondition{
	private String operator;
	private AutCondition leftExpression;
	private AutCondition rightExpression;
	
	public AutBinaryCondition(AutCondition left, AutCondition right, String operator) {
		this.leftExpression = left;
		this.leftExpression = right;
		this.operator = operator;
	}
	
	public boolean eval(IEntity entity) {
		switch(this.operator) {
			case "&":
				return (this.leftExpression.eval(entity) && this.rightExpression.eval(entity));
			case "|":
				return (this.leftExpression.eval(entity) || this.rightExpression.eval(entity));
			default:
				return false;
		}
	}
}
