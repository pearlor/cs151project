import java.awt.*;
import javax.swing.*;

/**
 * The ModernLayout class is a part of the Strategic Pattern.
 * This class sets a specific design for the MancalaBoard.
 * It can be used to set background for the MancalaBoard, decorate the BoardComponenet (PitButton and MancalaComponent), labels for player's name and each pit in the board.
 * @author Ann Le, Ha Nguyen, Pearl Or (Team Infinity)
 */
public class ModernLayout implements MancalaLayoutManager {

	@Override
	/**
	 * To decorate the BoardComponent of the MancalaBoard. The color of the stones is blue, the shape of the pit (if PitButton) is rectangle.
	 * @param pit: the BoardComponent to be decorated 
	 */
	public void decoratePit(BoardComponent pit) {
		pit.setColor(Color.BLUE);
		pit.setRoundPit(false);
	}
	
	@Override
	/**
	 * To decorate the player's name label.
	 * The font is Arial, BOLD, with a size of 30. The color of the text inside the label is white.
	 * @param label: label to be decorated
	 */
	public void decoratePlayerLable(JLabel label) {
		label.setFont(new Font("Arial", Font.BOLD, 30));
		label.setForeground(Color.WHITE);
	}

	@Override
	/**
	 * To decorate the background of the JFrame.
	 * The JFrame will take the image "color.png" as the background.
	 * @param frame: JFrame to be decorated
	 */
	public void decorateBackground(JFrame frame) {
		JLabel background = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().createImage("color.png")));
		frame.setContentPane(background);
	}

	@Override
	/**
	 * To decorate the pit's label
	 * The font is Arial, BOLD, with a size of 16. The color of the text inside the label is set to be white.
	 * @param label: label to be decorated
	 */
	public void decoratePitLabel(JLabel label) {
		label.setFont(new Font("Arial", Font.BOLD, 16));
		label.setForeground(Color.WHITE);
	}
}
