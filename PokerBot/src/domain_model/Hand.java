package domain_model;

public class Hand {
	private String id;
	private String name;
	private String desc;
	private int NumStartPlayers;
	private int numCurrPlayers;
	private int machinePosition;
	private double blindsLeft;
	private double bigBlindAmount;
	private PersonalitiesLeft personalitiesLeft;
	
	
	public String getId() {return this.id;}
	public String getName() {return this.name;}
	public String getDesc() {return this.desc;}
	public int machinePosition() {return this.machinePosition;}
	public int numStartPlayers() {return this.NumStartPlayers;}
	public int numCurrPlayers() {return this.numCurrPlayers;}
	public double blindsLeft() {return this.blindsLeft;}
	public double bigBlindAmount() {return this.bigBlindAmount;}
	public PersonalitiesLeft personalitiesLeft() {return this.personalitiesLeft;}
	
	
	public void setId(String s) {this.id = s;}
	public void setName(String s) {this.name = s;}
	public void setDesc(String s) {this.desc = s;}
	public void setNumStartPlayers(int i) {this.NumStartPlayers = i;}
	public void setNumCurrPlayers(int i) {this.numCurrPlayers = i;}
	public void setBlindsLeft(double d) {this.blindsLeft = d;}
	public void setBlindAmount(double d) {this.bigBlindAmount = d;}
	public void setPersonalitiesLeft(PersonalitiesLeft p) {this.personalitiesLeft = p;}
	public void setMachinePosition(int i) {this.machinePosition = i;}
	
}
