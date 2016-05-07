import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
/**
 * 
 * @authors Ann Le, Ha Nguyen, Pearl Or
 *
 * A MancalaComponent to display a mancala and its stones for a mancala game display- specifically the MancalaBoard.
 *
 */
public class MancalaComponent extends JComponent implements BoardComponent {
	private int count;
	private int xpit;
	private int ypit;
	private Color color;
	private boolean isRound;
	/**
	 * Creates a MancalaComponent.
	 * @param num the number of stones in MancalaComponent
	 * @param xcomp the x-coordinates of where stones should be drawn.
	 * @param ycomp the x-coordinates of where stones should be drawn.
	 */
	public MancalaComponent(int num, int xcomp, int ycomp) 
	{
		count = num;
		xpit = xcomp;
		ypit = ycomp;
	}
	/**
	 * Sets the color of a MancalaComponent.
	 * @param newColor the color to set MancalaComponent as
	 */
	public void setColor(Color newColor) 
	{
		color = newColor;
	}
	/**
	 * Sets if a MancalaComponent should be round or not. If true, then the MancalaComponet will be round; otherwise, rectangular.
	 * @param haveRoundPits is true if should be round; otherwise false
	 */
	public void setRoundPit(boolean haveRoundPits)
	{
		isRound = haveRoundPits;
	}
	/**
	 * Paints the MancalaComponent.
	 * @param g the Graphics
	 */	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		//Draw Mancala
		g2.setColor(Color.WHITE);
		Shape mancala = null;
		if(!isRound)
		{
			mancala = new Rectangle2D.Double(xpit, ypit, this.getWidth() - 40, this.getHeight() - 100);
		}
		else
		{
			mancala = new Ellipse2D.Double(xpit, ypit, this.getWidth() - 40, this.getHeight() - 100);
		}
		g2.fill(mancala);
		
		//Draw the stones
		g2.setColor(color);
		int x = xpit + 25;
		int y = ypit + 70;
		int num = 1;
		
		for (int i = 1; i <= count; i++) 
		{
			if (num <= 6) 
			{
				g2.fill(new Ellipse2D.Double(x, y, 20, 20));
				x +=22;
				num++;
			}
			if(num > 6)
			{
				x = xpit + 25;
				y += 22;
				num = 1;
			}
		}
	}
}
