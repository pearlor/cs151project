import java.awt.Color;
/**
 * 
 * @authors Ann Le, Ha Nguyen, Pearl Or
 *
 * BoardComponent is an interface containing abstract methods: setColor and setRoundPit. 
 * 
 */
public interface BoardComponent 
{
	/**
	 * Sets the color of a BoardComponent.
	 * @param newColor the color to set BoardComponent as
	 */
	public void setColor(Color newColor);
	/**
	 * Sets if a BoardComponent should be round or not. If true, then the BoardComponet will be round; otherwise, rectangular.
	 * @param haveRoundPits is true if should be round; otherwise false
	 */
	public void setRoundPit(boolean haveRoundPits);
}
