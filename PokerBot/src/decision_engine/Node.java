package decision_engine;
import utils.Action;
import java.util.*;

public class Node<T> {
	private final int lvl;
	private final T stateVar;
	private Action action;
	private Set<Node<?>> children;
	
	public Node(int i, T stateVar, Action a) {
		this.lvl = i;
		this.stateVar = stateVar;
		this.action = a;
		this.children = new TreeSet<Node<?>>();
	}
	
	public void add(Node<?> n) {
		this.children.add(n);
	}
	
	public int lvl() {
		return this.lvl;
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
