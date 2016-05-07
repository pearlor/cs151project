import java.util.ArrayList;

/**
 * This class is the Model portion of MVC pattern.
 * 
 * @authors Ann Le, Ha Nguyen, Pearl Or
 * 
 * This class creates a MancalaModel and its required methods.
 */
public class MancalaModel 
{
	private int[][] board;
	private int[][] before;
	private ArrayList<MancalaBoard> viewList;
	
	private int stonesPer;
	
	private int whoseTurn;
	private boolean playersMancala;
	private boolean prevMoveMancala;
	private int prevUndoTurn;
	private int undoCounter;
	private boolean undoAfterMancala;
	private int whoWon;
	private boolean gameEnded;
	
	/**
	 * Creates a MancalaModel. The board will be 2x7 with 2 mancalas and 12 pits. 
	 * B 6 5 4 3 2 1 
	 * M             M
	 *   1 2 3 4 5 6 A
	 * @param setUpNum the starting amount of stones each pit should have
	 */
	public MancalaModel(int setUpNum)
	{
		board = new int[2][7]; //  2 mancalas and 12 pits
		before = new int[2][7];
		viewList = new ArrayList<>();
		stonesPer = setUpNum;
		whoseTurn = 0;
		playersMancala = false;
		prevMoveMancala = false;
		prevUndoTurn = -1;
		undoCounter = 0;
		whoWon = 0;
		gameEnded = false;
		for(int i = 1; i < board[0].length; i++)
		{
			board[0][i] = setUpNum;
			board[1][i] = setUpNum;
			before[0][i] = setUpNum;
			before[1][i] = setUpNum;
		}
	}
	
	/**
	 * Returns the array containing the pits and mancalas.
	 * @return an array of the board containing pits and mancalas
	 */
	public int[][] getBoard()
	{
		return board;
	}
	/**
	 * Resets the array and most of the class's instance variables to reset the MancalaModel to its setup state.
	 */
	public void resetBoard()
	{
		board[0][0] = 0;
		board[1][0] = 0;
		
		for(int i = 1; i < board[0].length; i++)
		{
			board[0][i] = stonesPer;
			board[1][i] = stonesPer;
			before[0][i] = stonesPer;
			before[1][i] = stonesPer;
		}
		
		whoseTurn = 0;
		prevMoveMancala = false;
		prevUndoTurn = -1;
		undoCounter = 0;
		whoWon = 0;
		gameEnded = false;
		for(MancalaBoard board : viewList)
		{
			board.updateGraphics();
		}
	}
	/**
	 * Prints the MancalaBoard.
	 */
	public void printBoard()
	{
		for(int i = 0; i < board[1].length; i++)
		{
			System.out.print(board[1][i] + " ");
		}
		System.out.println("");
		for(int j = 1; j < board[0].length; j++)
		{
			System.out.print(board[0][j] + " ");
		}
		System.out.print(board[0][0]);
	}
	/**
	 * Returns the number of stones within a specified pit or mancala.
	 * Player A is 0, Player B is 1
	 * Player A's pits are 1-6, Player B's pits are 6-1 (converted to 1-6), and mancala is 0 for both players.
	 * @param player the player's number.
	 * @param pit the pit/mancala number
	 * @return the number of stones in specified pit or mancala.
	 */
	public int getStonesOf(int player, int pit)
	{
		if(player == 1)
		{
			return board[player][getBPits(pit)];
		}
		return board[player][pit];
	}
	/**
	 * Returns the number of stones in Player A's mancala.
	 * @return the number of stones in mancala A
	 */
	public int getMancalaAStones()
	{
		return board[0][0];
	}
	/**
	 * Returns the number of stones in Player B's mancala.
	 * @return the number of stones in mancala B
	 */
	public int getMancalaBStones()
	{
		return board[1][0];
	}
	/**
	 * Returns Player B's actual pit numbers since the pits are reversed in display.
	 * @param pitNum
	 * @return
	 */
	private int getBPits(int pitNum)
	{
		return 7 - pitNum;
	}
	
	/**
	 * Distributes counter-clockwise stones within the board array.
	 *      <----
	 * B 6 5 4 3 2 1 
	 * M             M
	 *   1 2 3 4 5 6 A
	 *      ---->
	 * @param player the current player's side number
	 * @param startingPit the starting pit's number
	 */
	public void distribute(int player, int startingPit)
	{
		boolean isPlayerASide = true;
		int pitToEmpty = startingPit;
		int cSide = player; 
		int cPit = pitToEmpty +1;
		boolean endedInPlayer = false;
		
		if(player == 1) //Player's Input needs to be converted :)
		{
			isPlayerASide = false;
			pitToEmpty = getBPits(startingPit);
			cPit = pitToEmpty - 1;
		}
		
		int stones = board[player][pitToEmpty];
		board[player][pitToEmpty] = 0;
		
		while(stones != 0)
		{
			
			if(cPit > 6 || cPit == 0) // If cPit > 6, then cPit is supposedly current side's mancala. Put a stone into mancala.
			{
				board[cSide][0]++; // The mancala
				if(player == cSide)
				{
					endedInPlayer = true;
				}
				cPit = 1;
				
				if(isPlayerASide)
				{
					cPit = 6;
				}
				cSide = changeSide(cSide);
				isPlayerASide = !isPlayerASide;
			}
			else
			{
				endedInPlayer = false;
				if(cSide == player && stones == 1 && cPit != 0 && board[cSide][cPit] == 0 && board[changeSide(cSide)][cPit] > 0 )
				{
					acrossCapture(cSide, cPit);
				}
				
				board[cSide][cPit]++;
				
				if(isPlayerASide)
				{
					cPit++;
				}
				else
				{
					cPit--;
				}
			}
			stones--;			
		}
		playersMancala = endedInPlayer;
	}
	
	
	/**
	 * The helper method of distribute to swap side.
	 * If side = 0, then return 1. Otherwise, return 0.
	 * @param side the side number of which player
	 * @return 0 or 1 depending on side's number
	 */
	private int changeSide(int side)
	{
		if(side == 0)
		{
			return 1;
		}
		return 0;
	}
	
	/**
	 * Captures the adjacent pits' stones.
	 * @param sideNum the side the last stone is put into.
	 * @param pitNum the pit number the last stone is put into.
	 */
	private void acrossCapture(int sideNum, int pitNum)
	{
		int otherSide = changeSide(sideNum);
		board[sideNum][pitNum] = board[changeSide(sideNum)][pitNum];
		board[otherSide][pitNum] = 0;
	}
	
	
	/**
	 * Attaches views to model.
	 * @param the view to attach
	 */
	public void attach(MancalaBoard board)
	{
		viewList.add(board);
	}
	
	
	/**
	 * Updates the number of stones in each pit in this model and notifies all listeners of changes of the number of stones.
	 * Player A is 0, Player B is 1, and the pits are from 1-6. 
	 * @param player the player's number
	 * @param pit the pit number
	 */
	public void update(int player, int pit)
	{
		if(whoseTurn == player && getStonesOf(player, pit) != 0 )
		{
			if(prevUndoTurn == whoseTurn)
			{
				undoCounter = 0;
			}
			setStateOfBoard(before, board);
			
			if(undoAfterMancala && undoCounter > 2 && prevMoveMancala)
			{
				System.out.println("**");
				undoCounter = 0;
			}
			
			undoAfterMancala = false;
			prevMoveMancala = false;
			
			distribute(player, pit); 
			if(!playersMancala)
			{
				whoseTurn = changeSide(whoseTurn);
			}
			else
			{
				undoAfterMancala = true;
				prevMoveMancala = true;
			}
		
			for(MancalaBoard board : viewList)
			{
				board.updateGraphics();
			}
		}
		
		hasGameEnded();
		if(gameEnded)
		{
			int mancalaA = board[0][0];
			int mancalaB = board[1][0];
			
			if(mancalaA > mancalaB)
			{
				whoWon = 0;
			}
			else if(mancalaB > mancalaA)
			{
				whoWon = 1;
			}
			else
			{
				whoWon = -1;
			}
			for(MancalaBoard board : viewList)
			{
				board.updateGraphics();
			}
		}
	}
	/**
	 * Resets the board to previous state before last change.
	 */
	public void undo()
	{
		if(undoAfterMancala && undoCounter < 3 )
		{
			undoCounter++;
			prevMoveMancala = false;
			setStateOfBoard(board, before);
			for(MancalaBoard board : viewList)
			{
				board.updateGraphics();
			}
		}
		else if(undoCounter < 3 )
		{
			undoCounter++;
			prevMoveMancala = false;
			prevUndoTurn = whoseTurn;
			whoseTurn = changeSide(whoseTurn);
			setStateOfBoard(board, before);
			for(MancalaBoard board : viewList)
			{
				board.updateGraphics();
			}
		}
	}
	/**
	 * A helper method to set a the state of one 2-D array or board to another specified board or 2-D array.
	 * @param boardSetAs the board to set
	 * @param boardToBe the board to be set as
	 */
	private void setStateOfBoard(int[][] boardSetAs, int[][] boardToBe)
	{
		for(int i = 0; i < boardSetAs.length; i++)
		{
			for(int j = 0; j < boardSetAs[0].length; j++)
			{
				boardSetAs[i][j] = boardToBe[i][j];
			}
		}
	}
	/**
	 * Checks and handles if a game is over.
	 */
	private void hasGameEnded()
	{
		boolean sideAEmpty = true;
		boolean sideBEmpty = true;
		for(int i = 1; i < board[0].length; i++) //Player A 1-6
		{
			if(board[0][i] != 0)
			{
				sideAEmpty = false;
			}
		}
		if(sideAEmpty)
		{
			//then Player A's side is empty, so Player's B pits empty to his/her mancala.
			endCapture(1);
			gameEnded = true;
		}
		else
		{
			for(int i = 1; i < board[1].length; i++) //Player B 6-1
			{
				if(board[1][i] != 0)
				{
					sideBEmpty = false;
				}
			}			
			if(sideBEmpty)
			{
				//then Player B's side is empty, so Player's A pits empty to his/her mancala.
				endCapture(0);
				gameEnded = true;
			}
		}
	}
	/**
	 * Captures all stones from a player's row to player's mancala if the other player's row is completely empty.
	 * @param player the player's rows to empty
	 */
	private void endCapture(int player)
	{
		for(int i = 1; i < board[player].length; i++)
		{
			board[player][0] += board[player][i]; //Player's mancala
			board[player][i] = 0;
		}
	}
	/**
	 * Returns true if the game is over; otherwise, false.
	 * @return true if game is over; otherwise, false
	 */
	public boolean isGameOver()
	{
		return gameEnded;
	}
	/** 
	 * Returns the player's number who won the game.
	 * @return the number of the player who won
	 */
	public int getWinner()
	{
		return whoWon;
	}
}