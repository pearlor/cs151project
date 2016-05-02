import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class PitButton extends JComponent implements BoardComponent {
	private int count;
	private Color color;
	
	public PitButton(int num) {
		count = num;
	}
	
	public void setColor(Color newColor) {
		color = newColor;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.WHITE);
		Ellipse2D pit = new Ellipse2D.Double(0, 0, this.getWidth() - 2, this.getHeight() - 2);
		g2.fill(pit);
		
		
		//Draw the stones
		g2.setColor(color);
		int x = 10;
		int y = 50;
		int num = 1;
		
		for (int i = 1; i <= count; i++) {
			if (num <= 4) {
				g2.fill(new Ellipse2D.Double(x, y, 20, 20));
				x +=22;
				num++;
			}
			else {
				x = 10;
				y += 22;
				num = 1;
			}
		}
	}
	
}
