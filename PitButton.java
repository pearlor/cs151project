import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

/**
 * The PitButton class displays the pit in the Mancala Game.
 * @author Ann Le, Ha Nguyen, Pearl Or (Team Infinity)
 */
public class PitButton extends JComponent implements BoardComponent {
	private int count;
	private Color color;
	private boolean isRound;
	
	/**
	 * To construct a PitButton with a specific number of stones.
	 * @param num: the number of stones
	 */
	public PitButton(int num) {
		count = num;
	}
	
	@Override
	/**
	 * To set the color of the stones.
	 * @param newColor: the color of the stones
	 */
	public void setColor(Color newColor) {
		color = newColor;
	}
	
	@Override
	/**
	 * To specify the preferred shape of the pit.
	 * @param check: true if the preferred shape is ellipse, otherwise false.
	 */
	public void setRoundPit(boolean check) {
		isRound = check;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.WHITE);
		
		if(!isRound)
			g2.fill(new Rectangle2D.Double(0, 0, this.getWidth() - 2, this.getHeight() - 2));
		else
			g2.fill(new Ellipse2D.Double(0, 0, this.getWidth() - 2, this.getHeight() - 2));
		
		//Draw the stones
		g2.setColor(color);
		int x = 10;
		int y = 50;
		int num = 1;
		
		for (int i = 1; i <= count; i++) {
			if (num > 4) {		//If there are enough stones in one row, move to the next row
				x = 10;
				y += 22;
				num = 1;
			}
			//Draw the stone
			g2.fill(new Ellipse2D.Double(x, y, 20, 20));
			x +=22;
			num++;
		}
	}
}