import java.awt.*;
import java.awt.geom.*;
import javax.swing.JComponent;

/**
 * The MancalaComponent class displays the mancala in the Mancala Game.
 * @author Ann Le, Ha Nguyen, Pearl Or (Team Infinity)
 */
public class MancalaComponent extends JComponent implements BoardComponent {
	private int count;
	private int xpit;
	private int ypit;
	private Color color;
	
	/**
	 * To construct a MancalaComponent with specific number of stones, the x and y components of the left top corner.
	 * @param num: number of stones
	 * @param xcomp: x-component of the left top corner
	 * @param ycomp: y-component of the right top corner
	 */
	public MancalaComponent(int num, int xcomp, int ycomp) {
		count = num;
		xpit = xcomp;
		ypit = ycomp;
	}
	
	
	@Override
	/**
	 * To set the color of the stones
	 * @param newColor: the color of the stones
	 */
	public void setColor(Color newColor) {
		color = newColor;
	}
	

	@Override
	/**
	 * The mancala is designed to always be in the ellipse shape. This method is a dummy method (no expected outcome).
	 */
	public void setRoundPit(boolean check) {
		//No need for implementation. Mancala is designed to always be round.
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		//Draw Mancala
		g2.setColor(Color.WHITE);
		
		//Always draw ellipse for Mancala
		g2.fill(new Ellipse2D.Double(xpit, ypit, this.getWidth() - 40, this.getHeight() - 100));
		
		//Draw the stones
		g2.setColor(color);
		int x = xpit + 25;
		int y = ypit + 70;
		int num = 1;
		
		for (int i = 1; i <= count; i++) {
			if (num > 6) {		//If there are enough stones in one row, move to the next row
				x = xpit + 25;
				y += 22;
				num = 1;
			}
			//Draw the stone
			g2.fill(new Ellipse2D.Double(x, y, 20, 20));
			x += 22;
			num++;
		}
	}
}
