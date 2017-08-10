package decision_engine;
import utils.OddsBucket;
import utils.Action;
import utils.BetSizeBucket;
import utils.PotSizeBucket;
import utils.*;

import java.util.*;
import domain_model.*;


public class DecisionTree {
	public Node<Integer> head;
	public State currState;
	
	public DecisionTree(State state) {
		head = new Node<Integer>(null, 0, null);
		this.currState = state;
	}
	
	//helper function to select random action
	//disregards sm and bb actions
	public static Action act() {
		int n = Action.values().length - 2;
		int i = (int) Math.floor(Math.random() * n);
		return Action.values()[i];
	}
	
	/*
	 * Specifies all possible previous action for each stage of
	 * a player; 'isFinal' parameter is used to specify whether or not
	 * the player is final or not
	 */
	private Set<Node<Action>> specifyPrevAction(Node<Boolean> n, boolean isFinal) {
		Set<Node<Action>> prevActions = new TreeSet<Node<Action>>();	
		for (Action a1: Action.values()) {
			Node<Action> n1 = new Node<Action>(VarType.PREF, a1, null);
			n.add(n1);
			
			for (Action a2: Action.values()) {
				Node<Action> n2 = new Node<Action>(VarType.FLOP, a2, null);
				n1.add(n2);
				
				for (Action a3: Action.values()) {
					Node<Action> n3 = new Node<Action>(VarType.TURN, a3, null);
					n2.add(n3);
					
					for (Action a4: Action.values()) {
						Node<Action> n4;
						if (isFinal)
							n4 = new Node<Action>(VarType.RIVER, a4, act());
						else 
							n4 = new Node<Action>(VarType.RIVER, a4, null);
						
						n3.add(n4);
						prevActions.add(n4);
					}
				}
			}
		}
		return prevActions;
	}
	
	/* 
	 * recursively adds nodes for all possible scenarios of each player being
	 * either in or out; uses prevActions method to specify all possible scenarios
	 * of each action of each player that is in
	 */
	private void specifyPlayers(Node<?> n, int player, int max) {
		 if (player == max) {
			Node<Boolean> trueNode = new Node<Boolean>(VarType.PLAYERISIN, true, null);
			n.add(trueNode);
			specifyPrevAction(trueNode, true);
			Node<Boolean> falseNode = new Node<Boolean>(VarType.PLAYERISIN, false, act());
			n.add(falseNode);
		}
		else {
			Node<Boolean> trueNode = new Node<Boolean>(VarType.PLAYERISIN, true, null);
			n.add(trueNode);
			Set<Node<Action>> prevAction = specifyPrevAction(trueNode, false);
			Node<Boolean> falseNode = new Node<Boolean>(VarType.PLAYERISIN, false, null);
			n.add(falseNode);
			
			for (Node<Action> no : prevAction) {
				specifyPlayers(no, player + 1, max);
			}
			specifyPlayers(falseNode, player + 1, max);
		}
	}
	
	//creates random decision tree
	//creates a tree of all possible gameStates based on relevant variables and random action
	public void create() {
		//Stage  var
		for (Stage s : Stage.values()) {
			Node<Stage> n1 = new Node<Stage>(VarType.STAGE, s, null);
			head.add(n1);
			
			//Odds of winning var
			for (OddsBucket o : OddsBucket.values()) {
				Node<OddsBucket> n2 = new Node<OddsBucket>(VarType.ODDS, o, null);
				n1.add(n2);
				
				//max num players var
				for (int p = 1; p <= 9; p++) {
					Node<Integer> n3 = new Node<Integer>(VarType.TOTALNUMPLAYERS, p, null);
					n2.add(n3);
					
					//position variable
					for (int pos = 1; pos <= p; p++) {
						Node<Integer> n4 = new Node<Integer>(VarType.POSITION, pos, null);
						n3.add(n4);
											
						//Big blinds left var
						for (BbLeftBucket  b : BbLeftBucket.values()) {
							Node<BbLeftBucket> n5 = new Node<BbLeftBucket>(VarType.BLINDSLEFT, b, null);
							n4.add(n5);
							
							//size of pot var
							for (PotSizeBucket pb : PotSizeBucket.values()) {
								Node<PotSizeBucket> n6 = new Node<PotSizeBucket>(VarType.POTSIZE, pb, null);
								n5.add(n6);
								
								//personality types left in game
								for (PersonBucket perb : PersonBucket.values()) {
									Node<PersonBucket> n7 = new Node<PersonBucket>(VarType.PERSONALITIESLEFT, perb, null);
									n6.add(n7);
									
									//current bet to match
									for (BetSizeBucket bsb : BetSizeBucket.values()) {
										Node<BetSizeBucket> n8 = new Node<BetSizeBucket>(VarType.BETSIZE, bsb, null);
										n7.add(n8);
										
										//all players still in and their previous action
										specifyPlayers(n8, 1, p);								
									}
								}
							}						
						}
					}
				}
			}
		}
	}
	
	//matches currState to correct decision node
	private boolean matches(Node<?> node) {
		VarType v = node.varType();
		if (v == null) {
			return true;
		}
		if (v == VarType.STAGE) {
			return node.stateVar() == currState.currStage();
		}
		if (v == VarType.ODDS) {
			return node.stateVar() == currState.odds();
		}
		if (v == VarType.TOTALNUMPLAYERS) {
			return (Integer) node.stateVar() == currState.numTotalPlayers();
		}
		if (v == VarType.POSITION) {
			return (Integer) node.stateVar() == currState.pos();
		}
		if (v == VarType.BLINDSLEFT) {
			return node.stateVar() == currState.bbLeft();
		}
		if (v == VarType.POTSIZE) {
			return node.stateVar() == currState.potSize();
		}
		if (v == VarType.PERSONALITIESLEFT) {
			return node.stateVar() == currState.persLeft();
		}
		if (v == VarType.BETSIZE) {
			return node.stateVar() == currState.betSize();
		}
		return false;
	}
	
	private Node<?> downLevel(Node<?> node) throws Exception {	
		for (Node<?> n : node.children()) {
			if (n.varType() != VarType.PLAYERISIN) {
				if (matches(n))
					return n;
			}
			
			//downLevel for all player possibilities
			//TODO figure out logic
			for (int i = 0; i < currState.numTotalPlayers(); i++) {
				if (currState.players().get(i).isInStage()) {
					Iterator<Node<?>> it = n.children().iterator();
					while (it.hasNext()) {
						Node<?> boolNode = it.next();
						if ((boolean) boolNode.stateVar())
							n = boolNode;
						
						else
							n = it.next();
					}
				}
			}
		}
		//TODO change to return null or to an exception I've created
		throw new Exception();
	}
	
	//method that is called when it is time to find the current state and act
	public Action decide() throws Exception {
		Node<?> it = head;	
		while(!it.children().isEmpty()) {
			it = downLevel(it);	
		}
		return it.action();
	}
	//TODO : iterator function
	
	
}
