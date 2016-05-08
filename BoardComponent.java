import java.awt.Color;

/**
 * The BoardComponent interface sets the requirements for a GUI component representing parts of the Mancala Game, such as PitButton and MancalaComponent.
 * @authors Ann Le, Ha Nguyen, Pearl Or (Team Infinity)
 */
public interface BoardComponent 
{
	/**
	 * To set the color of the stone.
	 * @param newColor: the color of the stone.
	 */
	public void setColor(Color newColor);
	
	/**
	 * To set the shape preference of the component.
	 * @param check: true if the component should be round; otherwise false.
	 */
	public void setRoundPit(boolean check);
}
