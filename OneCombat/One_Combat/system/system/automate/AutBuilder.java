package system.automate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Interfaces.IAction;
import Interfaces.ICondition;
import info3.game.automata.ast.AST;
import info3.game.automata.ast.Action;
import info3.game.automata.ast.Automaton;
import info3.game.automata.ast.Behaviour;
import info3.game.automata.ast.BinaryOp;
import info3.game.automata.ast.Category;
import info3.game.automata.ast.Condition;
import info3.game.automata.ast.Direction;
import info3.game.automata.ast.FunCall;
import info3.game.automata.ast.IVisitor;
import info3.game.automata.ast.Key;
import info3.game.automata.ast.Mode;
import info3.game.automata.ast.State;
import info3.game.automata.ast.Transition;
import info3.game.automata.ast.UnaryOp;
import info3.game.automata.ast.Underscore;
import info3.game.automata.ast.Value;

public class AutBuilder implements IVisitor {

	@Override
	public Object visit(Category cat) {
		return cat.toString();
	}

	@Override
	public Object visit(Direction dir) {
		return dir.toString();
	}

	@Override
	public Object visit(Key key) {
		return key.toString();
	}

	@Override
	public Object visit(Value v) {
		return v.value;
	}

	@Override
	public Object visit(Underscore u) {
		return u.toString();
	}

	@Override
	public void enter(FunCall funcall) {
		// TODO Auto-generated method stub

	}

	/*
	 * parameters est une List<String> c la liste des parametres renvoie une
	 * implementation de AutCondition ou de AutAction
	 */
	@Override
	public Object exit(FunCall funcall, List<Object> parameters) {
		String functionName = funcall.name;
		switch (functionName) {
		case "Move":
			if (parameters.isEmpty()) {
				return new AutActionMove(funcall.percent);
			} else {
				IAction.ABSDIRECTION directionABS = IAction.ABSDIRECTION.toEnum((String) parameters.get(0));
				if (directionABS == null) {
					IAction.RELDIRECTION directionREL = IAction.RELDIRECTION.toEnum((String) parameters.get(0));
					return new AutActionMove(directionREL, funcall.percent);
				}
				return new AutActionMove(directionABS, funcall.percent);
			}

		case "Jump":
			return new AutActionJump(funcall.percent);

		case "Hit":
			if (parameters.isEmpty()) {
				return new AutActionHit(funcall.percent);
			} else {
				IAction.ABSDIRECTION directionABS = IAction.ABSDIRECTION.toEnum((String) parameters.get(0));
				if (directionABS == null) {
					IAction.RELDIRECTION directionREL = IAction.RELDIRECTION.toEnum((String) parameters.get(0));
					return new AutActionHit(directionREL, funcall.percent);
				}
				return new AutActionHit(directionABS, funcall.percent);
			}

		case "Egg":
			if (parameters.isEmpty()) {
				return new AutActionEgg(funcall.percent);
			} else {
				IAction.ABSDIRECTION directionABS = IAction.ABSDIRECTION.toEnum((String) parameters.get(0));
				if (directionABS == null) {
					IAction.RELDIRECTION directionREL = IAction.RELDIRECTION.toEnum((String) parameters.get(0));
					return new AutActionEgg(directionREL, funcall.percent);
				}
				return new AutActionEgg(directionABS, funcall.percent);
			}

		case "Get":
			if (parameters.isEmpty()) {
				return new AutActionGet(funcall.percent);
			} else {
				IAction.ABSDIRECTION directionABS = IAction.ABSDIRECTION.toEnum((String) parameters.get(0));
				if (directionABS == null) {
					IAction.RELDIRECTION directionREL = IAction.RELDIRECTION.toEnum((String) parameters.get(0));
					return new AutActionGet(directionREL, funcall.percent);
				}
				return new AutActionGet(directionABS, funcall.percent);
			}

		case "Explode":
			return new AutActionExplode(funcall.percent);

		case "Pick":
			IAction.ABSDIRECTION directionABS = IAction.ABSDIRECTION.toEnum((String) parameters.get(0));
			if (directionABS == null) {
				IAction.RELDIRECTION directionREL = IAction.RELDIRECTION.toEnum((String) parameters.get(0));
				return new AutActionPick(directionREL, funcall.percent);
			}
			return new AutActionPick(directionABS, funcall.percent);

		case "Pop":
			return new AutActionPop(funcall.percent);

		case "Power":
			return new AutActionPower(funcall.percent);

		case "Protect":
			return new AutActionProtect(funcall.percent);

		case "Store":
			return new AutActionStore(funcall.percent);

		case "Turn":
			IAction.ABSDIRECTION directionABS2 = IAction.ABSDIRECTION.toEnum((String) parameters.get(0));
			if (directionABS2 == null) {
				IAction.RELDIRECTION directionREL = IAction.RELDIRECTION.toEnum((String) parameters.get(0));
				return new AutActionTurn(directionREL, funcall.percent);
			}
			return new AutActionTurn(directionABS2, funcall.percent);

		case "Throw":
			if (parameters.isEmpty()) {
				return new AutActionThrow(funcall.percent);
			} else {
				IAction.ABSDIRECTION directionABS3 = IAction.ABSDIRECTION.toEnum((String) parameters.get(0));
				if (directionABS3 == null) {
					IAction.RELDIRECTION directionREL = IAction.RELDIRECTION.toEnum((String) parameters.get(0));
					return new AutActionThrow(directionREL, funcall.percent);
				}
				return new AutActionThrow(directionABS3, funcall.percent);
			}

		case "Wait":
			return new AutActionWait(funcall.percent);

		case "Wizz":
			return new AutActionWizz(funcall.percent);

		// LES CONDITIONS
			
		case "isEmpty":
			return new AutCondIsEmpty();

		case "isNotEmpty":
			return new AutCondIsNotEmpty();

		case "gotPower":
			return new AutCondGotPower();

		case "hasAdversaire":
			return new AutCondHasAdversaire();

		case "True":
			return new AutCondTrue();

		case "hasTeammate":
			return new AutCondHasTeammate();

		case "hasMissile":
			return new AutCondHasMissile();

		case "Key":
			String characterInputCode = (String) parameters.get(0);
			return new AutCondKey(characterInputCode);

		case "MyDir":
			IAction.ABSDIRECTION directionABS4 = IAction.ABSDIRECTION.toEnum((String) parameters.get(0));
			if (directionABS4 == null) {
				IAction.RELDIRECTION directionREL = IAction.RELDIRECTION.toEnum((String) parameters.get(0));
				return new AutCondMyDir(directionREL);
			}
			return new AutCondMyDir(directionABS4);

		case "Cell":
			IAction.ABSDIRECTION direction6 = IAction.ABSDIRECTION.toEnum((String) parameters.get(0));
			ICondition.TYPE categorie = ICondition.TYPE.toEnum((String) parameters.get(1));
			return new AutCondCell(direction6, categorie);

		case "gotStuff":
			return new AutCondGotStuff();

		case "Closest":
			ICondition.TYPE categorie2 = ICondition.TYPE.toEnum((String) parameters.get(0));
			IAction.ABSDIRECTION  directionABS5 = IAction.ABSDIRECTION.toEnum((String) parameters.get(1));
			if (directionABS5 == null) {
				IAction.RELDIRECTION  directionREL4 = IAction.RELDIRECTION.toEnum((String) parameters.get(1));
				return new AutCondClosest(categorie2, directionREL4);
			}
			return new AutCondClosest(categorie2, directionABS5);

		default:
			return null;
		}
	}

	/*
	 * Visit dans la classe BinaryOP Renvoie un AutCondtion (qui est un
	 * AutBinaryCondition)
	 */
	@Override
	public Object visit(BinaryOp operator, Object left, Object right) {
		return new AutBinaryCondition((AutCondition) left, (AutCondition) right, operator.operator);
	}

	/*
	 * Visit dans la classe UnaryOP
	 * 
	 */
	@Override
	public Object visit(UnaryOp operator, Object expression) {
		return new AutUnaryCondition((AutCondition) expression, operator.operator);
	}

	/*
	 * Visit dans la classe State Renvoie un AutCondtion (qui est un
	 * AutUnaryCondition)
	 */
	@Override
	public Object visit(State state) {
		return new AutState(state.name);
	}

	/*
	 * Useless
	 * 
	 */
	@Override
	public void enter(Mode mode) {
		// TODO Auto-generated method stub

	}

	/*
	 * Le exit dans la classe Mode
	 * 
	 */
	@Override
	public Object exit(Mode mode, Object source_state, Object behaviour) {
		// Initial State
		AutState etatDepart = (AutState) source_state;

		List<AutTransition> transitionsResult = (List<AutTransition>) behaviour;
		Iterator<AutTransition> transitionIterator = transitionsResult.iterator();
		while (transitionIterator.hasNext()) {
			AutTransition currentTransition = transitionIterator.next();
			currentTransition.setEtatDepart(etatDepart);
		}
		return transitionsResult;
	}

	/*
	 * Le exit dans la classe Behaviour Doit retourner un objet de type
	 * List<AutTransition>* Retourne des AutTransitions sans état initiales.
	 */
	@Override
	public Object visit(Behaviour behaviour, List<Object> transitions) {
		List<AutTransition> resultTransitionList = new ArrayList<AutTransition>();
		Iterator<Object> transitionIterator = transitions.iterator();
		while (transitionIterator.hasNext()) {
			resultTransitionList.add((AutTransition) transitionIterator.next());
		}
		return resultTransitionList;
	}

	/*
	 * Enter dans la classe condition Sert a rien
	 */
	@Override
	public void enter(Condition condition) {
		// TODO Auto-generated method stub

	}

	/*
	 * Exit dans la classe condition
	 * 
	 */
	@Override
	public Object exit(Condition condition, Object expression) {
		return expression;
	}

	/*
	 * Enter dans la classe Action
	 * 
	 */
	@Override
	public void enter(Action acton) {
		// TODO Auto-generated method stub

	}

	/*
	 * Exit dans la classe Action
	 * 
	 */
	@Override
	public Object exit(Action action, List<Object> funcalls) {
		if (funcalls.isEmpty()) {
			return null;
		}
		List<AutAction> resAutActions = new ArrayList<AutAction>();
		for(int index = 0; index < funcalls.size(); index++) {
			resAutActions.add((AutAction) funcalls.get(index));
		}
		return resAutActions;
	}

	/*
	 * Visit dans la classe Transition
	 * 
	 */
	@Override
	public Object visit(Transition transition, Object condition, Object action, Object target_state) {
		return new AutTransition((AutCondition) condition, (List<AutAction>) action, (AutState) target_state);
	}

	/*
	 * Sert a rien
	 * 
	 */
	@Override
	public void enter(Automaton automaton) {
		// TODO Auto-generated method stub

	}

	/*
	 * 
	 */
	@Override
	public Object exit(Automaton automaton, Object initial_state, List<Object> modes) {
		String name = automaton.name;
		AutState etatDepart = (AutState) initial_state;
		List<AutTransition> resultTransitionsList = new ArrayList<AutTransition>();
		Iterator<Object> modesIterator = modes.iterator();
		while (modesIterator.hasNext()) {
			List<AutTransition> currentTransitionList = (List<AutTransition>) modesIterator.next();
			resultTransitionsList.addAll(currentTransitionList);
		}
		return new AutAutomate(name, etatDepart, resultTransitionsList);
	}

	@Override
	public Object visit(AST bot, List<Object> automata) {
		List<AutAutomate> resultAutomateList = new ArrayList<AutAutomate>();
		Iterator<Object> automateListe = automata.iterator();
		while (automateListe.hasNext()) {
			resultAutomateList.add((AutAutomate) automateListe.next());
		}
		return resultAutomateList;
	}

}
