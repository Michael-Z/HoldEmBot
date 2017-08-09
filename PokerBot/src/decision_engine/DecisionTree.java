package decision_engine;
import utils.*;
import utils.OddsBucket;

import java.util.*;
import domain_model.*;


public class DecisionTree {
	public Node<Integer> head;
	public State currState;
	
	public DecisionTree(State state) {
		head = new Node<Integer>(0, 0, null);
		this.currState = state;
	}
	
	//helper function to select random action
	//TODO elimante sm and bb
	public Action act() {
		int n = Action.values().length - 1;
		int i = (int) Math.round(Math.random() * n);
		return Action.values()[i-2];
	}
	
	/*
	 * Specifies all possible previous action for each stage of
	 * a player; 'isFinal' parameter is used to specify whether or not
	 * the player is final or not
	 */
	private Set<Node<Action>> specifyPrevAction(Node<Boolean> n, boolean isFinal) {
		Set<Node<Action>> prevActions = new TreeSet<Node<Action>>();	
		for (Action a1: Action.values()) {
			Node<Action> n1 = new Node<Action>(n.lvl() + 1, a1, null);
			n.add(n1);
			
			for (Action a2: Action.values()) {
				Node<Action> n2 = new Node<Action>(n.lvl() + 2, a2, null);
				n1.add(n2);
				
				for (Action a3: Action.values()) {
					Node<Action> n3 = new Node<Action>(n.lvl() + 3, a3, null);
					n2.add(n3);
					
					for (Action a4: Action.values()) {
						Node<Action> n4;
						if (isFinal)
							n4 = new Node<Action>(n.lvl() + 4, a4, act());
						else 
							n4 = new Node<Action>(n.lvl() + 5, a4, null);
						
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
			Node<Boolean> trueNode = new Node<Boolean>(n.lvl() + player, true, null);
			n.add(trueNode);
			specifyPrevAction(trueNode, true);
			Node<Boolean> falseNode = new Node<Boolean>(n.lvl() + player, false, act());
			n.add(falseNode);
		}
		else {
			Node<Boolean> trueNode = new Node<Boolean>(n.lvl() + player, true, null);
			n.add(trueNode);
			Set<Node<Action>> prevAction = specifyPrevAction(trueNode, false);
			Node<Boolean> falseNode = new Node<Boolean>(n.lvl() + player, false, null);
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
			Node<Stage> n1 = new Node<Stage>(1, s, null);
			head.add(n1);
			
			//Odds of winning var
			for (OddsBucket o : OddsBucket.values()) {
				Node<OddsBucket> n2 = new Node<OddsBucket>(2, o, null);
				n1.add(n2);
				
				//max num players var
				for (int p = 1; p <= 9; p++) {
					Node<Integer> n3 = new Node<Integer>(3, p, null);
					n2.add(n3);
					
					//position variable
					for (int pos = 1; pos <= p; p++) {
						Node<Integer> n4 = new Node<Integer>(4, pos, null);
						n3.add(n4);
											
						//Big blinds left var
						for (BbLeftBucket  b : BbLeftBucket.values()) {
							Node<BbLeftBucket> n5 = new Node<BbLeftBucket>(5, b, null);
							n4.add(n5);
							
							//size of pot var
							for (PotSizeBucket pb : PotSizeBucket.values()) {
								Node<PotSizeBucket> n6 = new Node<PotSizeBucket>(6, pb, null);
								n5.add(n6);
								
								//personality types left in game
								for (PersonBucket perb : PersonBucket.values()) {
									Node<PersonBucket> n7 = new Node<PersonBucket>(7, perb, null);
									n6.add(n7);
									
									//current bet to match
									for (BetSizeBucket bsb : BetSizeBucket.values()) {
										Node<BetSizeBucket> n8 = new Node<BetSizeBucket>(8, bsb, null);
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
	
	//TODO use lvl to classify the player and action specification and to match those
	private boolean matches(Node<?> node) {
		int lvl = node.lvl();
		if (lvl == 0) {
			return true;
		}
		if (lvl == 1) {
			return node.stateVar() == currState.currStage();
		}
		if (lvl == 2) {
			return node.stateVar() == currState.currStage();
		}
		if (lvl == 3) {
			return (Integer) node.stateVar() == currState.maxPlayers();
		}
		if (lvl == 4) {
			return (Integer) node.stateVar() == currState.pos();
		}
		if (lvl == 5) {
			return node.stateVar() == currState.bbLeft();
		}
		if (lvl == 6) {
			return node.stateVar() == currState.potSize();
		}
		if (lvl == 7) {
			return node.stateVar() == currState.persLeft();
		}
		if (lvl == 8) {
			return node.stateVar() == currState.betSize();
		}
		
		return false;
	}
	
	//create container for currState variables
	private Node<?> downLevel(Node<?> node) throws Exception {	
		for (Node<?> n : node.children()) {
			if (matches(n))
				return n;
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
