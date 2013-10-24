package com.nwea;

public class War
{
	protected static final String INVALID_ARGUMENTS =
			"War is played with 4 suits, 13 ranks, and 2 players";
	protected static final String PLAYER_1_WINS = "Player 1 wins!";
	protected static final String PLAYER_2_WINS = "Player 2 wins!";
	protected static final String USAGE =
			"Usage: War numberOfSuits, int numberOfRanks, int numberOfPlayers";
	protected static final String WAR_NUMBER_OF_PLAYERS = "2";
	protected static final String WAR_NUMBER_OF_RANKS = "13";
	protected static final String WAR_NUMBER_OF_SUITS = "4";	
	
	public static void main(String[] args)
	{
		War war = new War();
		
		war.playWar(args);
	}

	public void play(int numberOfSuits, int numberOfRanks, int numberOfPlayers)
	{
		CardDeck deck = new CardDeck();
		
		this.buildDeck(deck, numberOfSuits, numberOfRanks);
		this.shuffleDeck(deck);
		
		CardDeck player1 = new CardDeck();
		CardDeck player2 = new CardDeck();
		
		this.deal(deck, player1, player2);
		this.play(player1, player2);
	}
	
	protected void battle(CardDeck player1, CardDeck player2, CardDeck pot)
	{
		Card player1FaceDown = player1.deal();
		Card player2FaceDown = player2.deal();
		
		if(!hasPlayerWon(player1, player2))
		{
     		pot.receiveCard(player1FaceDown);
     		pot.receiveCard(player2FaceDown);
     		this.playRound(player1, player2, pot);
		}
	}
	
	protected void buildDeck(CardDeck deck, int numberOfSuits, int numberOfRanks)
	{
		deck.create(numberOfSuits, numberOfRanks);
	}
	
	protected void deal(CardDeck sourceDeck, CardDeck player1, CardDeck player2)
	{
		while(sourceDeck.cardCount() >= 2)
		{
			player1.receiveCard(sourceDeck.deal());
			player2.receiveCard(sourceDeck.deal());
		}
	}
	
	protected boolean hasPlayerWon(CardDeck player1, CardDeck player2)
	{
		boolean hasPlayerWon = false;
		
		if(player1.cardCount() <= 0)
		{
			this.println(PLAYER_2_WINS);
			println(String.format("Player1:\n%s", player1.toString()));
			println(String.format("Player2:\n%s", player2.toString()));
			hasPlayerWon = true;
		}
		else if(player2.cardCount() <= 0)
		{
			this.println(PLAYER_1_WINS);
			println(String.format("Player1:\n%s", player1.toString()));
			println(String.format("Player2:\n%s", player2.toString()));
			hasPlayerWon = true;
		}
		return hasPlayerWon;
	}

	protected void play(CardDeck player1, CardDeck player2)
	{
		while(!hasPlayerWon(player1, player2))
		{
			CardDeck emptyPot = new CardDeck();
			
			this.playRound(player1, player2, emptyPot);
		}
	}
	
	protected void playerWinsPot(CardDeck player, CardDeck pot)
	{
		Card card = pot.deal();
		
		while (null != card)
		{
			player.receiveCard(card);
			card = pot.deal();
		}
	}

	protected void playRound(CardDeck player1, CardDeck player2, CardDeck pot)
	{
		Card player1Card = player1.deal();
		Card player2Card = player2.deal();
		
		pot.receiveCard(player1Card);
		pot.receiveCard(player2Card);
		if(player1Card.rank() > player2Card.rank())
		{
			this.playerWinsPot(player1, pot);
		}
		else if(player2Card.rank() > player1Card.rank())
		{
			this.playerWinsPot(player2, pot);
		}
		else
		{
			this.battle(player1, player2, pot);
		}
		println(String.format("Player1:\n%s", player1.toString()));
		println(String.format("Player2:\n%s", player2.toString()));
	}
	
	protected void playWar(String[] args)
	{
		this.validateArguments(args);
		
		int numberOfSuits = Integer.parseInt(args[0]);
		int numberOfRanks = Integer.parseInt(args[1]);
		int numberOfPlayers = Integer.parseInt(args[2]);
		
		this.play(numberOfSuits, numberOfRanks, numberOfPlayers);
	}

	protected void println(String line)
	{
		System.out.println(line);
	}

	protected void shuffleDeck(CardDeck deck)
	{
		deck.shuffle();
	}

	protected void validateArguments(String[] args)
	{
		if(3 != args.length)
		{
			throw new IllegalArgumentException(USAGE);
		}
		else if((!WAR_NUMBER_OF_SUITS.equals(args[0])) || (!WAR_NUMBER_OF_RANKS.equals(args[1]))
					|| (!WAR_NUMBER_OF_PLAYERS.equals(args[2])))
		{
			throw new IllegalArgumentException(INVALID_ARGUMENTS);
		}
	}
}
