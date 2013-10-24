package com.nwea;

public class Card
{
	Suit suit;
	Rank rank;
	
	public Card() {}
	
	public Card(Suit suit, Rank rank)
	{
		this.suit = suit;
		this.rank = rank;
	}

	public int rank()
	{
		return this.rank.getRank();
	}

	@Override public String toString()
	{
		return String.format("%sof%s", rank.toString(), suit.toString());
	}
}
