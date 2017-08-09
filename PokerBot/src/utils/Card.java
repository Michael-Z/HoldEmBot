package utils;

public class Card {
	//hearts, diamonds, clubs, spades
	private final String suit;
	
	//J = 11, Q = 12, K = 13, A = 14
	private final int val;
	
	public Card(String s, int v) {
		this.suit = s;
		this.val = v;
	}
	
	public String getSuit() {
		return suit;
	}
	
	public int getVal() {
		return val;
	}
}
