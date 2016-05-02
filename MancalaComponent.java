import java.awt.*;
import java.awt.geom.Ellipse2D;
import javax.swing.JComponent;

public class MancalaComponent extends JComponent implements BoardComponent {
	private int count;
	private int xpit;
	private int ypit;
	private Color color;
	
	public MancalaComponent(int num, int xcomp, int ycomp) {
		count = num;
		xpit = xcomp;
		ypit = ycomp;
	}
	
	public void setColor(Color newColor) {
		color = newColor;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		//Draw Mancala
		g2.setColor(Color.WHITE);
		g2.fill(new Ellipse2D.Double(xpit, ypit, this.getWidth() - 40, this.getHeight() - 100));
		
		//Draw the stones
		g2.setColor(color);
		int x = xpit + 25;
		int y = ypit + 70;
		int num = 1;
		
		for (int i = 1; i <= count; i++) {
			if (num <= 6) {
				g2.fill(new Ellipse2D.Double(x, y, 20, 20));
				x +=22;
				num++;
			}
			else {
				x = xpit + 25;
				y += 22;
				num = 1;
			}
		}
	}
}
