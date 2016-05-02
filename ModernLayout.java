import java.awt.*;
import java.awt.geom.Ellipse2D;
import javax.swing.*;

public class ModernLayout implements MancalaLayoutManager {

	@Override
	public void decorateStone(BoardComponent pit) {
		pit.setColor(Color.YELLOW);
	}
	
	@Override
	public void decoratePlayerLable(JLabel label) {
		label.setFont(new Font("Arial", Font.BOLD, 30));
		label.setForeground(Color.WHITE);
	}

	@Override
	public void decorateBackground(JFrame frame) {
		JLabel background = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().createImage("color.png")));
		frame.setContentPane(background);
	}

	@Override
	public void decoratePitLabel(JLabel label) {
		label.setForeground(Color.WHITE);
	}
}
