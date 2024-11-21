package system.automate;

import java.util.List;

public class AutTransition {
	private AutState etatDepart;
	private AutCondition condition;
	private List<AutAction> actions;
	private AutState etatArrive;
	
	public AutTransition(AutCondition condition, List<AutAction> actions, AutState etatArrive) {
		this.condition = condition;
		this.actions = actions;
		this.etatArrive = etatArrive;
		this.etatDepart = null;
	}
	
	public AutTransition(AutState etatDepart, AutCondition condition, List<AutAction> actions, AutState etatArrive) {
		this.etatDepart = etatDepart;
		this.condition = condition;
		this.actions = actions;
		this.etatArrive = etatArrive;
	}
	
	public void setEtatDepart(AutState etatDepart) {
		this.etatDepart = etatDepart;
	}
	
	public AutCondition getCondition() {
		return this.condition;
	}
	
	public AutState getEtatDepart() {
		return this.etatDepart;
	}
	
	public AutState getEtatArrive() {
		return this.etatArrive;
	}
	
	public List<AutAction> getAction() {
		return this.actions;
	}
	
	public AutAction getActionTemporary() {
		return this.actions.get(0);
	}
	
	public void printDebug() {
		System.out.println("**********");
		System.out.println("Etat départ : " + this.getEtatDepart().name);
		if(this.condition == null) {
			System.out.println("Condition : aucune");
		}
		else {
			System.out.println("Condition : " + this.condition.getClass());
		}
		if(this.actions == null) {
			System.out.println("Action : aucune");
		}
		else {
			for(int i = 0; i < this.actions.size(); i++) {
				System.out.println("Action n°" + i + " : " + this.actions.get(i).getClass() + " | percentage : " + this.actions.get(i).getPercent());
			}
		}
		System.out.println("Etat arrivée : " + this.getEtatArrive().name);
	}
}
