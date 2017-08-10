package decision_engine;
import utils.*;
import java.util.*;

public class Node<T> {
	private final VarType varType;
	private final T stateVar;
	private Action action;
	private Set<Node<?>> children;
	
	public Node(VarType v, T stateVar, Action a) {
		this.varType = v;
		this.stateVar = stateVar;
		this.action = a;
		this.children = new TreeSet<Node<?>>();
	}
	
	public void add(Node<?> n) {
		this.children.add(n);
	}
	
	public VarType varType() {
		return this.varType;
	}
	
	public Action action() {
		return this.action;
	}
	
	public T stateVar() {
		return this.stateVar;
	}
	
	public Set<Node<?>> children() {
		return this.children;
	}
}
