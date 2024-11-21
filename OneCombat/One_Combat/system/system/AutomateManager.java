package system;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import info3.game.automata.ast.AST;
import info3.game.automata.ast.AstPrinter;
import info3.game.automata.ast.Automaton;
import info3.game.automata.parser.AutomataParser;
import system.automate.AutAutomate;
import system.automate.AutBuilder;

public class AutomateManager {
	HashMap<String, AutAutomate> automateDataBase = new HashMap<String, AutAutomate>();
	String path = "gal/exemples/exemples.gal";

	public AutomateManager() throws Exception {
		System.out.println(loadAutomataList(path));
	}

	List<AutAutomate> loadAutomataList(String filename) throws Exception {

		AST ast = (AST) AutomataParser.from_file(filename);

		// ast_printer = new AstPrinter();
		AutBuilder visitor = new AutBuilder();

		List<AutAutomate> resList = (List<AutAutomate>) ast.accept(visitor);

		/*
		Iterator<AutAutomate> debugIterator = resList.iterator();
		while (debugIterator.hasNext()) {
			debugIterator.next().printDebug();
		}*/

		this.automateDataBase = new HashMap<String, AutAutomate>();
		Iterator<AutAutomate> mapIterator = resList.iterator();
		while (mapIterator.hasNext()) {
			AutAutomate currentAutomaton = mapIterator.next();
			this.automateDataBase.put(currentAutomaton.getName(), currentAutomaton);
		}

		return resList;
	}
	public AutAutomate getAutomate(String name) {
		return automateDataBase.get(name);
		
	}

}
