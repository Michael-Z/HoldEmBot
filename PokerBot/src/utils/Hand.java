package utils;

public class Hand {
	private final Card c1;
	private final Card c2;
	
	public Hand(Card c1, Card c2) {
		this.c1 = c1;
		this.c2 = c2;
	}
	
	public Card c1() {
		return this.c1;
	}
	
	public Card c2() {
		return this.c2;
	}
}
