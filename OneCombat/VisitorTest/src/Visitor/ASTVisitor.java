package Visitor;

import java.util.ArrayList;
import java.util.List;

import Automate.AST;
import Automate.Automate;

public class ASTVisitor implements IVisitor {
	private List<Automate> listAutomateCreated;
	
	public ASTVisitor() {
		this.listAutomateCreated = new ArrayList<Automate>();
	}
	
	public void visit(AST ast) {
		System.out.println("Bonjour");
	}
	
	public void visit(Automate automate) {
		
	}
}
