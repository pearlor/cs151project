import javax.swing.*;
/**
 * 
 * @authors Ann Le, Ha Nguyen, Pearl Or
 *
 * This interface contains 4 abstract methods that must be implemented by classes that have an IS-A relationship to MancalaLayoutManagers.
 *
 */
public interface MancalaLayoutManager 
{
	/**
	 * Decorate the stones of the specified BoardComponent.
	 * @param pit the BoardComponent to be decorated.
	 */
	public void decorateStone(BoardComponent pit);
	/**
	 * Decorate the pit style of the specified BoardComponent.
	 * @param pit
	 */
	public void decoratePit(BoardComponent pit);
	/**
	 * Decorate a player's label.
	 * @param label the JLabel to be decorated
	 */
	public void decoratePlayerLabel(JLabel label);
	/**
	 * Decorate a JFrame's background.
	 * @param frame the JFrame to be decorated
	 */
	public void decorateBackground(JFrame frame);
	/**
	 * Decorate a pit's label.
	 * @param label the JLabel to be decorated.
	 */
	public void decoratePitLabel(JLabel label);
}
