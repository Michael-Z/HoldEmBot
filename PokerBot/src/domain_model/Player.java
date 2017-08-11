package domain_model;
import java.util.*;
import utils.*;
import utils.Personality;
public class Player {
	// TODO create type_classifier class
	private Personality personality;
	private boolean isIn;
	private double numBlindsLeft;
	private String personalityId;
	
	// 0, 1 are sb and bb, last is button
	private int pos;
	private LinkedList<Action> prevAction;
	//TODO numPreflop folds, num calls for type classifier
	
	public Player() {
		this.prevAction = new LinkedList<Action>();
		//instantiate a null action for each stage
		prevAction.add(null);
		prevAction.add(null);
		prevAction.add(null);
		prevAction.add(null);
		this.personality = Personality.UNKNOWN;
	}
	
	//getters
	//public String getType() {return this.type;}
	public boolean isIn() {return this.isIn;}
	public double getBbLeft() {return this.numBlindsLeft;}
	public int getPos() {return this.pos;}
	public List<Action> getPrevAction() {return this.prevAction;}

	//setters
	//public void setType(String s) {this.type = s;}
	public void setIn(boolean b) {this.isIn = b;}
	public void setBbLeft(double d) {this.numBlindsLeft = d;}
	public void setPos(int i) {this.pos = i;}
	
	public void addAction(int i, Action a) {
		prevAction.set(i, a);
	}
	
	public void clearAction() {
		prevAction.clear();
	}
	
}
