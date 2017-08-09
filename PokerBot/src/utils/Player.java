package utils;
import java.util.*;
public class Player {
	//i.e. "calling machine, nit, pro, bluffer"
	// TODO create type_classifier class
	private Personality type;
	private boolean inHand;
	private double bbLeft;
	// 0, 1 are sb and bb, last is button
	private int pos;
	//maps Stage to action
	//can include:
	//check, call, raise, fold, re-raise
	private Map<Stage, Action> prevAction;
	//TODO numPreflop folds, num calls for type classifier
	
	public Player(int pos) {
		this.pos = pos;
		this.prevAction = new TreeMap<Stage, Action>();
		this.type = Personality.UNKNOWN;
	}
	
	//getters
	//public String getType() {return this.type;}
	public boolean isInStage() {return this.inHand;}
	public double getBbLeft() {return this.bbLeft;}
	public int getPos() {return this.pos;}
	public Map<Stage, Action> getPrevAction() {return this.prevAction;}

	//setters
	//public void setType(String s) {this.type = s;}
	public void setInHand(boolean b) {this.inHand = b;}
	public void setBbLeft(double d) {this.bbLeft = d;}
	public void setPos(int i) {this.pos = i;}
	
	public void addAction(Stage x, Action a) {
		prevAction.put(x, a);
	}
	
	public void clearAction() {
		prevAction.clear();
	}
	
}
