package com.nwea;

public enum Suit
{
	HEARTS(0),
	DIAMONDS(1),
	CLUBS(2),
	SPADES(3);
	
	private int suitId;
	
	Suit(int suitId)
	{
		this.suitId = suitId;
	}
	
	public static Suit getSuitById(int suitId)
	{
		Suit returnSuit = null;
		
		for (Suit suit : Suit.values())
		{
			if (suit.getSuitId() == suitId)
			{
				returnSuit = suit;
				break;
			}
		}
		return returnSuit;
	}
	
	public int getSuitId()
	{
		return this.suitId;
	}
}
