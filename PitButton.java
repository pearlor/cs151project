import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
/**
 * 
 * @authors Ann Le, Ha Nguyen, Pearl Or
 *
 * A PitButton to display a pit and its stones for a mancala game display- specifically the MancalaBoard.
 *
 */
public class PitButton extends JComponent implements BoardComponent 
{
	private int count;
	private Color color;
	private boolean isRound;
	/**
	 * Creates a PitButton using a specified initial stone number.
	 * @param num the number of stones in the pit.
	 */
	public PitButton(int num) 
	{
		count = num;
	}
	/**
	 * Sets the color of a PitButton.
	 * @param newColor the color to set PitButton as
	 */
	public void setColor(Color newColor) 
	{
		color = newColor;
	}
	/**
	 * Sets if a PitButton should be round or not. If true, then the PitButton will be round; otherwise, rectangular.
	 * @param haveRoundPits is true if should be round; otherwise false
	 */
	public void setRoundPit(boolean roundPits)
	{
		isRound = roundPits;
	}
	/**
	 * Paints the PitButton.
	 * @param g the Graphics
	 */
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.WHITE);
		Shape pit = null;
		
		if(!isRound)
		{
			pit = new Rectangle2D.Double(0, 0, this.getWidth() - 2, this.getHeight() - 2);
		}
		else
		{
			pit = new Ellipse2D.Double(0, 0, this.getWidth() - 2, this.getHeight() - 2);
		}
		g2.fill(pit);
		
		
		//Draw the stones
		g2.setColor(color);
		int x = 10;
		int y = 50;
		int num = 1;
		
		for (int i = 1; i <= count; i++) 
		{
			if (num <= 4) 
			{
				g2.fill(new Ellipse2D.Double(x, y, 20, 20));
				x +=22;
				num++;
			}
			if(num > 4)
			{
				x = 10;
				y += 22;
				num = 1;
			}
		}
	}
	
}
