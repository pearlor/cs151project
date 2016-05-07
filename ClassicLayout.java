import java.awt.*;
import javax.swing.*;
/**
 * This class uses the strategy pattern.
 * 
 * @authors Ann Le, Ha Nguyen, Pearl Or
 * 
 * ClassicLayout implements MancalaLayoutManager. Class contains methods to decorate components.
 */
public class ClassicLayout implements MancalaLayoutManager 
{
	/**
	 * Decorates the stones of a BoardComponent- specifically setting the color to black.
	 * @param pit the BoardComponent to be decorated.
	 */
	@Override
	public void decorateStone(BoardComponent pit) 
	{
		pit.setColor(Color.BLACK);
	/**
	 * Decorates the pit style of a BoardComponent- specifically setting the pit style to be round.
	 * @param pit the BoardComponent to be decorated.
	 */		
	}
	@Override
	public void decoratePit(BoardComponent pit) 
	{
		pit.setRoundPit(true);
	}
	/**
	 * Decorates the JLabel for a player- specifically, setting the font (Arial, bold, 30px) and color to black.
	 * @param label the JLabel to be decorated.
	 */
	@Override
	public void decoratePlayerLabel(JLabel label) {
		label.setFont(new Font("Arial", Font.BOLD, 30));
		label.setForeground(Color.BLACK);
	}
	/**
	 * Decorates a JFrame- specifically setting the background image to "bamboo.jpg".
	 * @param frame the JFrame to be decorated.
	 */
	@Override
	public void decorateBackground(JFrame frame) {
		JLabel background = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().createImage("bamboo.jpg")));
		frame.setContentPane(background);
	}
	/**
	 * Decorated a pit's label- specifically setting the foreground color to black.
	 * @param label the JLabel to be decorated
	 */
	@Override
	public void decoratePitLabel(JLabel label) 
	{
		label.setForeground(Color.BLACK);
	}
}
