import java.util.ArrayList;
import javax.swing.event.*;

/**
 * This class is the model portion of MVC pattern.
 * @author Pearl Or
 * Class creates a MancalaModel and its required methods.
 */
public class MancalaModel 
{
	private int[][] board;
	private int[][] before;
	private ArrayList<ChangeListener> listeners;
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
		listeners = new ArrayList<ChangeListener>();
		for(int i = 1; i < board[0].length; i++)
		{
			board[0][i] = setUpNum;
			board[1][i] = setUpNum;
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
	public int getBPits(int pitNum)
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
				System.out.println("hel" + cPit + " stones " + stones);
				board[cSide][0]++; // The mancala
	
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
				if(cSide == player && stones == 1 && cPit != 0 && board[cSide][cPit] == 0 && board[changeSide(cSide)][cPit] > 0 )
				{
					System.out.println("no " + cPit);
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
	}
	/**
	 * The helper method of distribute to swap side.
	 * If side = 0, then return 1. Otherwise, return 0.
	 * @param side the side number of which player
	 * @return 0 or 1 depending on side's number
	 */
	public int changeSide(int side)
	{
		if(side == 0)
		{
			return 1;
		}
		return 0;
	}
	public void acrossCapture(int sideNum, int pitNum)
	{
		
		int otherSide = changeSide(sideNum);
		board[sideNum][pitNum] = board[changeSide(sideNum)][pitNum];
		board[otherSide][pitNum] = 0;
		
	}
	/**
	 * Attaches listeners to model.
	 * @param cl the ChangeListener
	 */
	public void attach(ChangeListener cl)
	{
		listeners.add(cl);
	}
	/**
	 * Updates the number of stones in each pit in this model and notifies all listeners of changes of the number of stones.
	 * Player A is 0, Player B is 1, and the pits are from 1-6. 
	 * @param player the player's number
	 * @param pit the pit number
	 */
	public void update(int player, int pit)
	{
		before = board;
		//distribute(player, pit); 
		for(ChangeListener cl : listeners)
		{
			cl.stateChanged(new ChangeEvent(this));
		}
	}
	/**
	 * Resets the board to previous state before last change.
	 */
	public void undo()
	{
		board = before;
		for(ChangeListener cl : listeners)
		{
			cl.stateChanged(new ChangeEvent(this));
		}
	}
	public boolean gameEnded()
	{
		boolean isEnded = false;
		boolean sideAEmpty = true;
		boolean sideBEmpty = true;
		for(int i = 1; i < board[0].length; i++) //Player A 1-6
		{
			if(board[0][1] != 0)
			{
				sideAEmpty = false;
			}
		}
		if(sideAEmpty)
		{
			//then Player A's side is empty, so Player's B pits empty to his/her mancala.
			endCapture(1);
			isEnded = true;
		}
		else
		{
			for(int i = 1; i < board[1].length; i++) //Player B 6-1
			{
				if(board[1][1] != 0)
				{
					sideBEmpty = false;
				}
			}			
			if(sideBEmpty)
			{
				//then Player B's side is empty, so Player's A pits empty to his/her mancala.
				endCapture(0);
				isEnded = true;
			}
		}
		return isEnded;
	}
	public void endCapture(int player)
	{
		for(int i = 1; i < board[player].length; i++)
		{
			board[player][0] = board[player][i]; //Player's mancala
			board[player][i] = 0;
		}
	}
}
