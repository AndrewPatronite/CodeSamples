package com.nwea;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class WarTest
{
	String[] validArgs;
	private War war;
	private War warSpy;
	
	@Before
	public void before()
	{
		validArgs = new String[]{"4","13", "2"};
		war = new War();
		warSpy = spy(war);
	}
	
	@Test
	public void battleNeitherPlayerHasWon()
	{
		Card player1Card = new Card();
		Card player2Card = new Card();
		CardDeck mockPlayer1 = mock(CardDeck.class);
		CardDeck mockPlayer2 = mock(CardDeck.class);
		CardDeck mockPot = mock(CardDeck.class);
		
		when(mockPlayer1.deal()).thenReturn(player1Card);
		when(mockPlayer2.deal()).thenReturn(player2Card);
		when(warSpy.hasPlayerWon(mockPlayer1, mockPlayer2)).thenReturn(false);
		doNothing().when(mockPot).receiveCard(player1Card);
		doNothing().when(mockPot).receiveCard(player2Card);
		doNothing().when(warSpy).playRound(mockPlayer1, mockPlayer2, mockPot);
		warSpy.battle(mockPlayer1, mockPlayer2, mockPot);
		verify(mockPot).receiveCard(player1Card);
		verify(mockPot).receiveCard(player2Card);
		verify(warSpy).playRound(mockPlayer1, mockPlayer2, mockPot);
	}
	
	@Test
	public void battlePlayerHasWon()
	{
		CardDeck player1 = new CardDeck();
		CardDeck player2 = new CardDeck();
		CardDeck pot = new CardDeck();
		
		when(warSpy.hasPlayerWon(player1, player2)).thenReturn(true);
		warSpy.battle(player1, player2, pot);
		verify(warSpy, never()).playRound(any(CardDeck.class), any(CardDeck.class),
				any(CardDeck.class));
	}
	
	@Test
	public void createDeck()
	{
		int numberOfSuits = 0;
		int numberOfRanks = 0;
		CardDeck deck = new CardDeck();
		CardDeck deckSpy = spy(deck);
		
		war.buildDeck(deckSpy, numberOfSuits, numberOfRanks);
		verify(deckSpy).create(numberOfSuits, numberOfRanks);
	}
	
	@Test
	public void deal()
	{
		Card card1 = new Card();
		Card card2 = new Card();
		Card card3 = new Card();
		Card card4 = new Card();
		CardDeck mockSourceDeck = mock(CardDeck.class);
		
		when(mockSourceDeck.cardCount()).thenReturn(4).thenReturn(2).thenReturn(0);
		when(mockSourceDeck.deal()).thenReturn(card1).thenReturn(card2).thenReturn(card3).
				thenReturn(card4);
		
		CardDeck spyPlayer1 = spy(new CardDeck());
		CardDeck spyPlayer2 = spy(new CardDeck());
		
		doNothing().when(spyPlayer1).receiveCard(card1);
		doNothing().when(spyPlayer2).receiveCard(card2);
		doNothing().when(spyPlayer1).receiveCard(card3);
		doNothing().when(spyPlayer2).receiveCard(card4);
		war.deal(mockSourceDeck, spyPlayer1, spyPlayer2);
		verify(spyPlayer1).receiveCard(card1);
		verify(spyPlayer2).receiveCard(card2);
		verify(spyPlayer1).receiveCard(card3);
		verify(spyPlayer2).receiveCard(card4);
	}
	
	@Test
	public void hasPlayerWonPlayer1Wins()
	{
		CardDeck mockPlayer1 = mock(CardDeck.class);
		CardDeck mockPlayer2 = mock(CardDeck.class);
		
		when(mockPlayer1.cardCount()).thenReturn(5);
		when(mockPlayer2.cardCount()).thenReturn(0);
		doNothing().when(warSpy).println(War.PLAYER_1_WINS);
		
		boolean result = warSpy.hasPlayerWon(mockPlayer1, mockPlayer2);
		
		verify(warSpy).println(War.PLAYER_1_WINS);
		assertTrue("Expected result to be true", result);
	}
	
	@Test
	public void hasPlayerWonPlayer2Wins()
	{
		CardDeck mockPlayer1 = mock(CardDeck.class);
		CardDeck mockPlayer2 = mock(CardDeck.class);
		
		when(mockPlayer1.cardCount()).thenReturn(0);
		when(mockPlayer2.cardCount()).thenReturn(5);
		doNothing().when(warSpy).println(War.PLAYER_2_WINS);
		
		boolean result = warSpy.hasPlayerWon(mockPlayer1, mockPlayer2);
		
		verify(warSpy).println(War.PLAYER_2_WINS);
		assertTrue("Expected result to be true.", result);
	}
	
	@Test
	public void hasPlayerWonNeitherPlayerWins()
	{
		CardDeck mockPlayer1 = mock(CardDeck.class);
		CardDeck mockPlayer2 = mock(CardDeck.class);
		
		when(mockPlayer1.cardCount()).thenReturn(4);
		when(mockPlayer2.cardCount()).thenReturn(5);
		
		boolean result = warSpy.hasPlayerWon(mockPlayer1, mockPlayer2);
		
		verify(warSpy, never()).println(any(String.class));
		assertFalse("Expected result to be false.", result);
	}
	
	@Test
	public void play()
	{
		int numberOfSuits = 0;
		int numberOfRanks = 0;
		int numberOfPlayers = 0;
		
		doNothing().when(warSpy).buildDeck(any(CardDeck.class), eq(numberOfSuits),
				eq(numberOfRanks));
		doNothing().when(warSpy).shuffleDeck(any(CardDeck.class));
		doNothing().when(warSpy).deal(any(CardDeck.class), any(CardDeck.class),
				any(CardDeck.class));
		doNothing().when(warSpy).play(any(CardDeck.class), any(CardDeck.class));
		warSpy.play(numberOfSuits, numberOfRanks, numberOfPlayers);
		verify(warSpy).buildDeck(any(CardDeck.class), eq(numberOfSuits),
				eq(numberOfRanks));
		verify(warSpy).shuffleDeck(any(CardDeck.class));
		verify(warSpy).deal(any(CardDeck.class), any(CardDeck.class), any(CardDeck.class));
		verify(warSpy).play(any(CardDeck.class), any(CardDeck.class));
	}
	
	@Test
	public void playerWinsPot()
	{
		CardDeck player = new CardDeck();
		Card card1 = new Card();
		Card card2 = new Card();
		CardDeck pot = new CardDeck();
		
		pot.receiveCard(card1);
		pot.receiveCard(card2);
		war.playerWinsPot(player, pot);
		assertSame("Expected card 1.", card1, player.deal());
		assertSame("Expected card 2.", card2, player.deal());
	}
	
	@Test
	public void playHelperWherePlayerHasNotWon()
	{
		CardDeck player1 = new CardDeck();
		CardDeck player2 = new CardDeck();
		
		when(warSpy.hasPlayerWon(player1, player2)).thenReturn(false).thenReturn(true);
		doNothing().when(warSpy).playRound(any(CardDeck.class), any(CardDeck.class),
				any(CardDeck.class));
		warSpy.play(player1, player2);
		verify(warSpy).playRound(same(player1), same(player2), any(CardDeck.class));
	}
	
	@Test
	public void playHelperWherePlayerHasWon()
	{
		CardDeck player1 = new CardDeck();
		CardDeck player2 = new CardDeck();
		
		when(warSpy.hasPlayerWon(player1, player2)).thenReturn(true);
		warSpy.play(player1, player2);
		verify(warSpy, never()).playRound(any(CardDeck.class), any(CardDeck.class),
				any(CardDeck.class));
	}
	
	@Test
	public void playRoundPlayer1WinsRound()
	{
		Card mockPlayer1Card = mock(Card.class);
		Card mockPlayer2Card = mock(Card.class);
		CardDeck mockPlayer1 = mock(CardDeck.class);
		CardDeck mockPlayer2 = mock(CardDeck.class);
		CardDeck spyPot = spy(new CardDeck());
		
		when(mockPlayer1Card.rank()).thenReturn(5);
		when(mockPlayer2Card.rank()).thenReturn(4);
		when(mockPlayer1.deal()).thenReturn(mockPlayer1Card);
		when(mockPlayer2.deal()).thenReturn(mockPlayer2Card);
		doNothing().when(spyPot).receiveCard(mockPlayer1Card);
		doNothing().when(spyPot).receiveCard(mockPlayer2Card);
		doNothing().when(warSpy).playerWinsPot(mockPlayer1, spyPot);
		warSpy.playRound(mockPlayer1, mockPlayer2, spyPot);
		verify(spyPot).receiveCard(mockPlayer1Card);
		verify(spyPot).receiveCard(mockPlayer2Card);
		verify(warSpy).playerWinsPot(mockPlayer1, spyPot);
		verify(warSpy, never()).playerWinsPot(mockPlayer2, spyPot);
		verify(warSpy, never()).battle(any(CardDeck.class), any(CardDeck.class),
				any(CardDeck.class));
	}
	
	@Test
	public void playRoundPlayer2WinsRound()
	{
		Card mockPlayer1Card = mock(Card.class);
		Card mockPlayer2Card = mock(Card.class);
		CardDeck mockPlayer1 = mock(CardDeck.class);
		CardDeck mockPlayer2 = mock(CardDeck.class);
		CardDeck spyPot = spy(new CardDeck());
		
		when(mockPlayer1Card.rank()).thenReturn(4);
		when(mockPlayer2Card.rank()).thenReturn(5);
		when(mockPlayer1.deal()).thenReturn(mockPlayer1Card);
		when(mockPlayer2.deal()).thenReturn(mockPlayer2Card);
		doNothing().when(spyPot).receiveCard(mockPlayer1Card);
		doNothing().when(spyPot).receiveCard(mockPlayer2Card);
		doNothing().when(warSpy).playerWinsPot(mockPlayer2, spyPot);
		warSpy.playRound(mockPlayer1, mockPlayer2, spyPot);
		verify(spyPot).receiveCard(mockPlayer1Card);
		verify(spyPot).receiveCard(mockPlayer2Card);
		verify(warSpy).playerWinsPot(mockPlayer2, spyPot);
		verify(warSpy, never()).playerWinsPot(mockPlayer1, spyPot);
		verify(warSpy, never()).battle(any(CardDeck.class), any(CardDeck.class),
				any(CardDeck.class));
	}
	
	@Test
	public void playRoundPlayerBattle()
	{
		Card mockPlayer1Card = mock(Card.class);
		Card mockPlayer2Card = mock(Card.class);
		CardDeck mockPlayer1 = mock(CardDeck.class);
		CardDeck mockPlayer2 = mock(CardDeck.class);
		CardDeck spyPot = spy(new CardDeck());
		
		when(mockPlayer1Card.rank()).thenReturn(5);
		when(mockPlayer2Card.rank()).thenReturn(5);
		when(mockPlayer1.deal()).thenReturn(mockPlayer1Card);
		when(mockPlayer2.deal()).thenReturn(mockPlayer2Card);
		doNothing().when(spyPot).receiveCard(mockPlayer1Card);
		doNothing().when(spyPot).receiveCard(mockPlayer2Card);
		doNothing().when(warSpy).playerWinsPot(mockPlayer2, spyPot);
		warSpy.playRound(mockPlayer1, mockPlayer2, spyPot);
		verify(spyPot).receiveCard(mockPlayer1Card);
		verify(spyPot).receiveCard(mockPlayer2Card);
		verify(warSpy, never()).playerWinsPot(mockPlayer1, spyPot);
		verify(warSpy, never()).playerWinsPot(mockPlayer2, spyPot);
		verify(warSpy).battle(any(CardDeck.class), any(CardDeck.class),
				same(spyPot));
	}
	
	@Test
	public void playWar()
	{	
		
		int expectedNumberOfSuits = Integer.parseInt(War.WAR_NUMBER_OF_SUITS);
		int expectedRank = Integer.parseInt(War.WAR_NUMBER_OF_RANKS);
		int expectedNumberOfPlayers = Integer.parseInt(War.WAR_NUMBER_OF_PLAYERS);
		
		doNothing().when(warSpy).validateArguments(validArgs);
		doNothing().when(warSpy).play(eq(expectedNumberOfSuits), eq(expectedRank),
				eq(expectedNumberOfPlayers));
		warSpy.playWar(validArgs);
		verify(warSpy).validateArguments(validArgs);
		verify(warSpy).play(eq(expectedNumberOfSuits), eq(expectedRank),
				eq(expectedNumberOfPlayers));
	}
	
	@Test
	public void shuffleDeck()
	{
		CardDeck mockDeck = mock(CardDeck.class);
		war.shuffleDeck(mockDeck);
		
		verify(mockDeck).shuffle();
	}
	
	@Test
	public void validateArguments()
	{
		war.validateArguments(validArgs);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void validateArgumentsThrowsIllegalArgumentExceptionBecauseOfLength()
	{
		String[] badArgsBecauseOfLength = {"4", "13"};
		
		try
		{
			war.validateArguments(badArgsBecauseOfLength);
		}
		catch(IllegalArgumentException exception)
		{
			assertEquals("Unexpected exception message.", War.USAGE, exception.getMessage() );
			throw exception;
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void validateArgumentsThrowsIllegalArgumentExceptionInvalidPlayers()
	{
		String[] badArgsBecauseOfLength = {"4", "13", "99"};
		
		try
		{
			war.validateArguments(badArgsBecauseOfLength);
		}
		catch(IllegalArgumentException exception)
		{
			assertEquals("Unexpected exception message.", War.INVALID_ARGUMENTS,
					exception.getMessage() );
			throw exception;
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void validateArgumentsThrowsIllegalArgumentExceptionInvalidRanks()
	{
		String[] badArgsBecauseOfLength = {"4", "99", "2"};
		
		try
		{
			war.validateArguments(badArgsBecauseOfLength);
		}
		catch(IllegalArgumentException exception)
		{
			assertEquals("Unexpected exception message.", War.INVALID_ARGUMENTS,
					exception.getMessage() );
			throw exception;
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void validateArgumentsThrowsIllegalArgumentExceptionInvalidSuits()
	{
		String[] badArgsBecauseOfLength = {"99", "13", "2"};
		
		try
		{
			war.validateArguments(badArgsBecauseOfLength);
		}
		catch(IllegalArgumentException exception)
		{
			assertEquals("Unexpected exception message.", War.INVALID_ARGUMENTS,
					exception.getMessage() );
			throw exception;
		}
	}
}
