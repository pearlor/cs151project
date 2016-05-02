import java.awt.*;
import java.awt.geom.Ellipse2D;
import javax.swing.*;

public class ClassicLayout implements MancalaLayoutManager {
	
	@Override
	public void decorateStone(BoardComponent pit) {
		pit.setColor(Color.BLACK);
	}

	@Override
	public void decoratePlayerLable(JLabel label) {
		label.setFont(new Font("Arial", Font.BOLD, 30));
		label.setForeground(Color.BLACK);
	}

	@Override
	public void decorateBackground(JFrame frame) {
		JLabel background = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().createImage("bamboo.jpg")));
		frame.setContentPane(background);
	}

	@Override
	public void decoratePitLabel(JLabel label) {
		label.setForeground(Color.BLACK);
	}
}
