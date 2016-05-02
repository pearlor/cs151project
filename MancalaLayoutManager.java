import java.awt.geom.Ellipse2D;
import javax.swing.*;

public interface MancalaLayoutManager {
	
	public void decorateStone(BoardComponent pit);
	
	public void decoratePlayerLable(JLabel label);
	
	public void decorateBackground(JFrame frame);
	
	public void decoratePitLabel(JLabel label);
	
}
