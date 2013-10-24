package com.nwea;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class CardDeck implements Deck
{
	private ArrayList<Card> cards = new ArrayList<Card>();
	
	public void receiveCard(Card card)
	{
		cards.add(card); 
	}
	
	public int cardCount()
	{
		return cards.size();
	}

	@Override
	public void create(int numberOfSuits, int numberOfRanks)
	{
		for(int suitId = 0; suitId < numberOfSuits; suitId++)
		{
			Suit suit = Suit.getSuitById(suitId);
			
			for(int rankId = 0; rankId < numberOfRanks; rankId++)
			{
				Rank rank = Rank.getRankById(rankId);
				Card card = new Card(suit, rank);
				
				this.receiveCard(card);
			}
		}
	}
	
	@Override
	public Card deal()
	{
		return (this.cardCount() > 0) ? cards.remove(0) : null;
	}

	@Override
	public void shuffle()
	{
		long seed = System.nanoTime();
		
		Collections.shuffle(cards, new Random(seed));
	}
	
	@Override public String toString()
	{
		StringBuilder builder = new StringBuilder();
		int cardsDisplayedOnLine = 0;
		
		for (Card card : this.cards)
		{
			builder.append(card.toString());
			if(4 == cardsDisplayedOnLine++)
			{
				cardsDisplayedOnLine = 0;
				builder.append("\n");
			}
			else
			{
				builder.append(", ");
			}
		}
		builder.append("\n");
		return builder.toString();
	}
}
