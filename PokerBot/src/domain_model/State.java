package domain_model;
import utils.*;
import java.util.*;

/*This class specifies a data structure containing all
 * variables relating to a specific instance of a poker game
 */
public class State {
	
	//i.e. "sb, bb, utg, button, etc"
	private BbLeftBucket bbLeft;
	//TODO getter and setter^
	private Hand hand;
	private PersonBucket personalitiesLeft;
	//private int numPlayersLeft;	
	//i.e. "preflop, flop, turn, river"
	private Stage currStage;
	private List<Player> players;
	private int pos;
	private Set<Card> communityCards;
	private PotSizeBucket potSize;
	//TODO getter and setter^
	private double prevBet; /*in the current stage */
	private Set<Player> aggressors;
	//iterate through first aggressor, whether contain tight player, loose player, unkown
	//TODO getter and setter^
	private double oddsOfWinning;
	private OddsBucket oddsBucket;
	//TODO getter setter^
	//TODO write function for ^
	private double bbVal;
	private int numPlayersLeft;
	private int numPlayersCalled;
	private final int maxPlayers;
	//TODO write getters and setters for^                                                                      
	//TODO data_source object is parameter
	//TODO write state update function
	private BetSizeBucket betToMatch;
	public State() {
		this.players = new LinkedList<Player>();
		
		//dummy
		maxPlayers = 9;
	}
	
	//getters
	public BbLeftBucket bbLeft() {return this.bbLeft;}
	public Hand hand() {return this.hand;}
	public int numPlayers() {return this.players.size();}
	public Stage currStage() {return this.currStage;}
	public List<Player> players() {return this.players;}
	public int pos() {return this.pos;}
	public Set<Card> comCards() {return this.communityCards;}
	public PotSizeBucket potSize() {return this.potSize;}
	public double prevBet() {return this.prevBet;}
	public OddsBucket odds() {return this.oddsBucket;}
	public double bbVal() {return this.bbVal;}
	public int maxPlayers() {return this.maxPlayers;}
	public BetSizeBucket betSize() {return this.betToMatch;}
	public PersonBucket persLeft() {return this.personalitiesLeft;}
	
	public int numPlayersLeft() {
		int i = 0;
		for (Player p : players) {
			if (p.isInStage())
				i++;
		}
		return i;
	}
	
	//setters
	//public void setBbLeft(double d) {this.bbLeft = d;}
	public void setHand(Hand h) {this.hand = h;}
	public void setStage(Stage s) {this.currStage = s;}
	//public void setPotSize(double d) {this.potSize = d;}
	public void setPrevBet(double d) {this.prevBet = d;}
	public void setOdds(double d) {this.oddsOfWinning = d;}
	public void setBbVal(double d) {this.bbVal = d;}
	
	public void addPlayer(int pos, Player p) {
		players.add(pos, p);
	}
	
	public void removePlayer(Player p) {
		players.remove(p);
	}
	
}
