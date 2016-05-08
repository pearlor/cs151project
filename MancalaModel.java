import java.util.ArrayList;

/**
 * The MancalaModel serves as the Model component of MVC pattern.
 * This class is able to set up a mancala board, distribute the stones from a specific pit, and check if the game has ended.
 * @authors Ann Le, Ha Nguyen, Pearl Or
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
	private boolean clickedPit;
	
	
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
		clickedPit = false;
		prevUndoTurn = -1;
		undoCounter = 0;
		whoWon = 0;
		
		for(int i = 1; i < board[0].length; i++)
		{
			board[0][i] = setUpNum;
			board[1][i] = setUpNum;
			before[0][i] = setUpNum;
			before[1][i] = setUpNum;
		}
	}
	
	/**
	 * Get the 2-D array containing the number of stones in each pit and mancala.
	 * @return 2-D array containing the number of stones in each pit and mancala.
	 */
	public int[][] getBoard()
	{
		return board;
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
	 * Get which player has the current turn
	 * @return 0 if player A has the turn, 1 if player B has the turn
	 */
	public int getWhoseTurn() {
		return whoseTurn; 
	}
	
	
	/**
	 * Get how many times the player has clicked the undo button
	 * @return undoCounter;
	 */
	public int getUndoCounter() {
		return undoCounter;
	}
	
	public boolean hasClickedPit() {
		return clickedPit;
	}
	
	
	/**
	 * Get the number of stones in a specific player's mancala
	 * @param i: indicates which player (0 is A and 1 is B)
	 * @return the number of stone in the player's mancala
	 */
	public int getMancalaStones(int i)
	{
		return board[i][0];
	}
	
	/**
	 * Update the number of stones in each pit in this model and notifies all listeners of changes of the number of stones.
	 * Player A is 0, Player B is 1, and the pits are from 1-6. The index for mancala in the array is 0 for both player.
	 * @param player: the player's number
	 * @param pit the pit number
	 */
	public void update(int player, int pit)
	{
		if (getStonesOf(player, pit) != 0 )
		{
			if (prevUndoTurn == whoseTurn)
				undoCounter = 0;

			setStateOfBoard(before, board);
			
			if(undoAfterMancala && (undoCounter > 2) && prevMoveMancala)
				undoCounter = 0;
			
			undoAfterMancala = false;
			prevMoveMancala = false;
			
			distribute(player, pit);
			
			if(!playersMancala)
				whoseTurn = changeSide(whoseTurn);
			else
			{
				undoAfterMancala = true;
				prevMoveMancala = true;
			}
			
			//The pit is clicked, the stones are distributed
			clickedPit = true;
		}

		if (hasGameEnded())
		{
			int mancalaA = board[0][0];
			int mancalaB = board[1][0];
			
			if(mancalaA > mancalaB)
				whoWon = 0;
			else if(mancalaB > mancalaA)
				whoWon = 1;
			else
				whoWon = -1;
			
			for(MancalaBoard board : viewList)
			{
				board.updateGraphics(true);
			}
		}
		else {
			for(MancalaBoard board : viewList)
			{
				board.updateGraphics(false);
			}
		}
	}
	
	/**
	 * Get the number of stones within a specified pit or mancala.
	 * Player A is 0, Player B is 1
	 * Player A's pits are 1-6, Player B's pits are 6-1 (converted to 1-6), and mancala is 0 for both players.
	 * @param player: the player's number.
	 * @param pit: the pit/mancala number
	 * @return the number of stones in specified pit or mancala.
	 */
	private int getStonesOf(int player, int pit)
	{
		if(player == 1)
		{
			return board[player][getBPits(pit)];
		}
		return board[player][pit];
	}
	
	
	/**
	 * Distribute counter-clockwise stones within the board array.
	 *      <----
	 * B 6 5 4 3 2 1 
	 * M             M
	 *   1 2 3 4 5 6 A
	 *      ---->
	 * @param player the current player's side number
	 * @param startingPit the starting pit's number
	 */
	private void distribute(int player, int startingPit)
	{
		boolean isPlayerASide = true;
		int pitToEmpty = startingPit;
		int cSide = player; 
		int cPit = pitToEmpty + 1;
		boolean endedInMancala = false;
		
		if (player == 1) //Current turn is Player B: Player's Input needs to be converted
		{
			isPlayerASide = false;
			pitToEmpty = getBPits(startingPit);
			cPit = pitToEmpty - 1;
		}
		
		int stones = board[player][pitToEmpty];
		board[player][pitToEmpty] = 0;
		
		while (stones != 0)
		{
			if(cPit > 6 || cPit == 0) //The current position is a mancala
			{
				if(player == cSide) {
					endedInMancala = true;
					board[cSide][0]++;		//This mancala is the current player's mancala
				}
				else //If the current mancala is not the current player's mancala, SKIP it!
					stones++;	//Increase the number of stones by 1 since we will substract 1 from it at the end of the while loop (NO CHANGE).
				
				//Start again from pit 1
				cPit = 1;
				
				if (isPlayerASide)
					cPit = 6;
				
				//Switch side
				cSide = changeSide(cSide);
				isPlayerASide = !isPlayerASide;
			}
			else //The current position is a pit
			{
				endedInMancala = false;
				
				//The last stone is placed in an empty pit, take that stone and all stones in the opposite pit. Place them in the current player's mancala.
				if (cSide == player && stones == 1 && cPit != 0 && board[cSide][cPit] == 0 && board[changeSide(cSide)][cPit] > 0 )
					acrossCapture(cSide, cPit);
				else
					board[cSide][cPit]++;
				
				if (isPlayerASide) //If the current side is Player A's side, go to the right
					cPit++;
				else			   //If not, go to the left
					cPit--;
			}
			//1 stone was placed
			stones--;			
		}
		playersMancala = endedInMancala;
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
	 * The helper method of distribute to swap side.
	 * If the current side is Player A, switch to Player B's side, and vice versa.
	 * @param side: indicates the current side belongs to which player (0 is player A, 1 is player B)
	 * @return 0 or 1 depending on side number
	 */
	private int changeSide(int side)
	{
		if(side == 0)
			return 1;
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
		board[sideNum][0] += 1 + board[otherSide][pitNum];
		board[sideNum][pitNum] = 0;
		board[otherSide][pitNum] = 0;
	}
	
	
	/**
	 * Resets the board to previous state before last change.
	 */
	public void undo()
	{
		if(undoAfterMancala && (undoCounter < 3))
		{
			undoCounter++;
			prevMoveMancala = false;
			setStateOfBoard(board, before);
			for(MancalaBoard board : viewList)
			{
				board.updateGraphics(false);
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
				board.updateGraphics(false);
			}
		}
		//Already use the Undo button
		clickedPit = false;
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
	private boolean hasGameEnded()
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
		
		if (sideAEmpty)
		{
			//then Player A's side is empty, so Player's B pits empty to his/her mancala.
			endCapture(1);
			return true;
		}

		for(int i = 1; i < board[1].length; i++) //Player B 6-1
		{
			if(board[1][i] != 0) sideBEmpty = false;
		}			
		
		if (sideBEmpty)
		{
			//then Player B's side is empty, so Player's A pits empty to his/her mancala.
			endCapture(0);
			return true;
		}
		return false;
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
	 * Returns the player's number who won the game.
	 * @return the number of the player who won
	 */
	public int getWinner()
	{
		return whoWon;
	}
}