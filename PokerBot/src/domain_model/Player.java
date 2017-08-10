package domain_model;
import java.util.*;
import utils.*;
import utils.Personality;
public class Player {
	//i.e. "calling machine, nit, pro, bluffer"
	// TODO create type_classifier class
	private Personality personality;
	private boolean isIn;
	private double numBlindsLeft;
	private String personalityId;
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
		this.personality = Personality.UNKNOWN;
	}
	
	//getters
	//public String getType() {return this.type;}
	public boolean isInStage() {return this.isIn;}
	public double getBbLeft() {return this.numBlindsLeft;}
	public int getPos() {return this.pos;}
	public Map<Stage, Action> getPrevAction() {return this.prevAction;}

	//setters
	//public void setType(String s) {this.type = s;}
	public void setInHand(boolean b) {this.isIn = b;}
	public void setBbLeft(double d) {this.numBlindsLeft = d;}
	public void setPos(int i) {this.pos = i;}
	
	public void addAction(Stage x, Action a) {
		prevAction.put(x, a);
	}
	
	public void clearAction() {
		prevAction.clear();
	}
	
}
