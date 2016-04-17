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
	 * Creates a MancalaModel.
	 * @param setUpNum the starting amount of stones each pit should have
	 */
	public MancalaModel(int setUpNum)
	{
		board = new int[2][7]; // 12 pits and 2 mancalas
		before = new int[2][7];
		listeners = new ArrayList<ChangeListener>();
		for(int i = 1; i < board.length; i++)
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
	/**
	 * Returns the number of stones within a specified pit or mancala.
	 * Player A is 0, Player B is 1, pits are 1-6, and mancala is 0.
	 * @param player the player's number.
	 * @param pit the pit/mancala number
	 * @return the number of stones in specified pit or mancala.
	 */
	public int getStonesOf(int player, int pit)
	{
		return board[player][pit];
	}
	/**
	 * Distributes stones within the board array.
	 * @param player the player's number
	 * @param startingPit the starting pit's number
	 */
	public void distribute(int player, int startingPit)
	{
		int stones = board[player][startingPit];
		int cPlayer = player;
		int cPit = startingPit;
		int nPit = startingPit + 1;
		int nPlayer = player;
		if(nPit > 6)
		{
			nPit = 0;
			nPlayer = changePlayer(nPlayer);
		}
		while(stones != 0)
		{
			board[cPlayer][cPit]--;
			board[nPlayer][nPit]++;
			
			cPit++;
			nPit++;
			if(cPit > 6)
			{
				cPit = 0;
				cPlayer = changePlayer(cPlayer);
			}
			if(nPit > 6)
			{
				nPit = 0;
				nPlayer = changePlayer(nPlayer);
			}
			stones--;
		}
	}
	/**
	 * The helper method of distribute to swap players.
	 * If player = 0, then return 1. Otherwise, return 0.
	 * @param player the player's number
	 * @return 0 or 1 depending on player's number
	 */
	public int changePlayer(int player)
	{
		if(player == 0)
		{
			return 1;
		}
		return 0;
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
		distribute(player, pit);
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
}
