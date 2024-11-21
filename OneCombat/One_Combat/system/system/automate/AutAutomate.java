package system.automate;

import java.util.ArrayList;

import java.util.List;

import Interfaces.IEntity;

public class AutAutomate {
	private String name;
	private AutState currentState;				//Lorskon le construit, le currentState = l'état de départ
	private List<AutTransition> transitions;	//All transitions of every state of the Automata
	
	public AutAutomate(String name, AutState initialState) {
		this.name = name;
		this.currentState = initialState;
		this.transitions = new ArrayList<AutTransition>();
	}
	
	public AutAutomate(String name, AutState initialState, List<AutTransition> transitionsList) {
		this.name = name;
		this.currentState = initialState;
		this.transitions = transitionsList;
	}
	
	public void step(IEntity entity) {
		for (AutTransition transition : transitions) {
			String a = this.currentState.name;
			String b = transition.getEtatDepart().name;
			if (transition.getCondition().eval(entity)&& (a.equals(b))) {
				transition.getActionTemporary().exec(entity);
				this.currentState = transition.getEtatArrive();
				break;
			}
		}
	}
	
	public String getName() {
		return this.name;
	}
	
	public void printDebug() {
		System.out.println("---------------------------------------------------------------------");
		System.out.println("Automate Name : " + this.name);
		System.out.println("Initial State : " + this.currentState.name);
		for(int i = 0; i < this.transitions.size(); i++) {
			this.transitions.get(i).printDebug();
		}
	}
	
}
