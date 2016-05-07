import java.awt.*;
import java.awt.geom.Ellipse2D;
import javax.swing.*;
/**
 * This class uses the strategy pattern.
 * 
 * @authors Ann Le, Ha Nguyen, Pearl Or
 * 
 * ModernLayout implements MancalaLayoutManager. Class contains methods to decorate components.
 */
public class ModernLayout implements MancalaLayoutManager 
{
	/**
	 * Decorates the stones of a BoardComponent- specifically setting the color to yellow.
	 * @param pit the BoardComponent to be decorated.
	 */
	@Override
	public void decorateStone(BoardComponent pit) 
	{
		pit.setColor(Color.YELLOW);		
	}
	/**
	 * Decorates the pit style of a BoardComponent- specifically setting the pit style to be rectangular.
	 * @param pit the BoardComponent to be decorated.
	 */	
	@Override
	public void decoratePit(BoardComponent pit) 
	{
		pit.setRoundPit(false);
	}
	/**
	 * Decorates the JLabel for a player- specifically, setting the font (Arial, bold, 30px) and color to white.
	 * @param label the JLabel to be decorated.
	 */
	@Override
	public void decoratePlayerLabel(JLabel label) 
	{
		label.setFont(new Font("Arial", Font.BOLD, 30));
		label.setForeground(Color.WHITE);
	}
	/**
	 * Decorates a JFrame- specifically setting the background image to "color.png".
	 * @param frame the JFrame to be decorated.
	 */
	@Override
	public void decorateBackground(JFrame frame) 
	{
		JLabel background = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().createImage("color.png")));
		frame.setContentPane(background);
	}
	/**
	 * Decorated a pit's label- specifically setting the foreground color to white.
	 * @param label the JLabel to be decorated
	 */
	@Override
	public void decoratePitLabel(JLabel label) 
	{
		label.setForeground(Color.WHITE);
	}
}
