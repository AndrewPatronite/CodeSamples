package com.nwea;

public enum Rank
{
	TWO(0),
	THREE(1),
	FOUR(2),
	FIVE(3),
	SIX(4),
	SEVEN(5),
	EIGHT(6),
	NINE(7),
	TEN(8),
	JACK(9),
	QUEEN(10),
	KING(11),
	ACE(12);
	
	private int rank;
	
	Rank(int rank)
	{
		this.rank = rank;
	}

	public int getRank()
	{
		return this.rank;
	}
	
	public static Rank getRankById(int rankId)
	{
		Rank returnRank = null;
		
		for (Rank rank : Rank.values())
		{
			if (rank.getRank() == rankId)
			{
				returnRank = rank;
				break;
			}
		}
		return returnRank;
	}
}
