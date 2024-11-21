package Automate;

import java.util.ArrayList;
import java.util.List;

import Visitor.IVisitor;

public class AST {
	private List<Automate> listAutomate;
	
	public AST() {
		this.listAutomate = new ArrayList<Automate>();
	}
	
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}
}
