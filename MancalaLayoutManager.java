import javax.swing.*;

/**
 * The MancalaLayoutManager interface sets requirements for a layout class designed for the MancalaBoard class.
 * @author Ann Le, Ha Nguyen, Pearl Or (Team Infinity)
 */
public interface MancalaLayoutManager {
	
	/**
	 * To decorate the BoardComponent of the MancalaBoard.
	 * @param pit: the BoardComponent to be decorated
	 */
	public void decoratePit(BoardComponent pit);
	
	/**
	 * To decorate the player's name label
	 * @param label: the label to be decorated
	 */
	public void decoratePlayerLable(JLabel label);
	
	/**
	 * To set the background of the JFrame (MancalaBoard).
	 * @param frame: the JFrame to be decorated
	 */
	public void decorateBackground(JFrame frame);
	
	/**
	 * To decorate the pit's name label
	 * @param label: the label to be decorated
	 */
	public void decoratePitLabel(JLabel label);
}
