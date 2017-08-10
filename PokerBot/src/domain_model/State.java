package domain_model;
import utils.*;
import java.util.*;

/*This class specifies a data structure containing all
 * variables relating to a specific instance of a poker game
 */
public class State {
	
	//Decision Variables
	private Stage st;
	private OddsBucket odds;
	private int numTotalPlayers;
	private int position;
	private BbLeftBucket blindsLeft;
	private PotSizeBucket potSize;
	private PersonBucket personalitiesLeft;
	private BetSizeBucket betToMatch;
	
	//Game variables
	private double bigBlindValue;
	private Hand hand;
	private List<Player> players;
	private Set<Card> communityCards;
	private int maxPlayers;
	private boolean isTurnToAct;
	
	//Double representation for bucket
	private double betToMatchD;
	private double oddsD;
	
	
	//TODO write getters and setters for^                                                                      
	//TODO data_source object is parameter
	//TODO write state update function
	
	public State() {
		this.players = new LinkedList<Player>();
		this.communityCards = new TreeSet<Card>();
	}
	
	//getters
	public BbLeftBucket bbLeft() {return this.blindsLeft;}
	public Hand hand() {return this.hand;}
	public int numPlayers() {return this.players.size();}
	public Stage currStage() {return this.st;}
	public List<Player> players() {return this.players;}
	public int pos() {return this.position;}
	public Set<Card> comCards() {return this.communityCards;}
	public PotSizeBucket potSize() {return this.potSize;}
	public double prevBet() {return this.betToMatchD;}
	public OddsBucket odds() {return this.odds;}
	public double bbVal() {return this.bigBlindValue;}
	public int numTotalPlayers() {return this.numTotalPlayers;}
	public BetSizeBucket betSize() {return this.betToMatch;}
	public PersonBucket persLeft() {return this.personalitiesLeft;}
	
	
	//setters
	//public void setBbLeft(double d) {this.bbLeft = d;}
	public void setHand(Hand h) {this.hand = h;}
	public void setStage(Stage s) {this.st = s;}
	//public void setPotSize(double d) {this.potSize = d;}
	public void setPrevBet(double d) {this.betToMatchD = d;}
	public void setOdds(double d) {this.oddsD = d;}
	public void setBbVal(double d) {this.bigBlindValue = d;}

	public int numPlayersLeft() {
		int i = 0;
		for (Player p : players) {
			if (p.isInStage())
				i++;
		}
		return i;
	}
	
	public void addPlayer(int pos, Player p) {
		players.add(pos, p);
	}
	
	public void removePlayer(Player p) {
		players.remove(p);
	}
	
}
