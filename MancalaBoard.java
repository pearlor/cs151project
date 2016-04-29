import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class creates the Mancala Board
 */

public class MancalaBoard{

	public static void createBoard(Container frame) {
		
		frame.setLayout(new BorderLayout());
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(new GridBagLayout());
		GridBagConstraints constraint = new GridBagConstraints();
		constraint.fill = GridBagConstraints.HORIZONTAL;
		constraint.insets = new Insets(0,5,0,5);
		constraint.ipady = 0;
		
		// labels each pit accordingly 
		for(int i = 1; i <= 6; i ++) {
			constraint.gridx = i;
			constraint.gridy = 0;
				panel_1.add(new JLabel("   B" + i), constraint);
			constraint.gridy = 3;
				panel_1.add(new JLabel("   A" + i), constraint);
			
		}
		//constraint.insets = new Insets(0,5,0,5);
		constraint.ipady = 40;
		constraint.gridheight = 1;
		
		// Pit B1 button
		JButton b1 = new JButton();
		constraint.gridx = 1;
		constraint.gridy = 1;
		b1.setBackground(Color.ORANGE);
		panel_1.add(b1, constraint);
		
		// Pit A1 button
		JButton a1 = new JButton();
		constraint.gridx = 1;
		constraint.gridy = 2;
		a1.setBackground(Color.ORANGE);
		panel_1.add(a1, constraint);
		
		// Pit B2 button
		JButton b2 = new JButton();
		constraint.gridx = 2;
		constraint.gridy = 1;
		b2.setBackground(Color.ORANGE);
		panel_1.add(b2, constraint);
		
		// Pit A2 button
		JButton a2 = new JButton();
		constraint.gridx = 2;
		constraint.gridy = 2;
		a2.setBackground(Color.ORANGE);
		panel_1.add(a2, constraint);
		
		// Pit B3 button
		JButton b3 = new JButton();
		constraint.gridx = 3;
		constraint.gridy = 1;
		b3.setBackground(Color.ORANGE);
		panel_1.add(b3, constraint);
		
		// Pit A3 button
		JButton a3 = new JButton();
		constraint.gridx = 3;
		constraint.gridy = 2;
		a3.setBackground(Color.ORANGE);
		panel_1.add(a3, constraint);
		
		// Pit B4 button
		JButton b4 = new JButton();
		constraint.gridx = 4;
		constraint.gridy = 1;
		b4.setBackground(Color.ORANGE);
		panel_1.add(b4, constraint);
		
		// Pit A4 button
		JButton a4 = new JButton();
		constraint.gridx = 4;
		constraint.gridy = 2;
		a4.setBackground(Color.ORANGE);
		panel_1.add(a4, constraint);
		
		// Pit B5 button
		JButton b5 = new JButton();
		constraint.gridx = 5;
		constraint.gridy = 1;
		b5.setBackground(Color.ORANGE);
		panel_1.add(b5, constraint);
		
		// Pit A5 button
		JButton a5 = new JButton();
		constraint.gridx = 5;
		constraint.gridy = 2;
		a5.setBackground(Color.ORANGE);
		panel_1.add(a5, constraint);
		
		// Pit B6 button
		JButton b6 = new JButton();
		constraint.gridx = 6;
		constraint.gridy = 1;
		b6.setBackground(Color.ORANGE);
		panel_1.add(b6, constraint);
		
		// Pit A6 button
		JButton a6 = new JButton();
		constraint.gridx = 6;
		constraint.gridy = 2;
		a6.setBackground(Color.ORANGE);
		panel_1.add(a6, constraint);
		
			
		// Mancala A pit
		constraint.ipady = 100;
		constraint.gridheight = 2;
		JButton mancala_A = new JButton();
		mancala_A.setBackground(Color.YELLOW);
		mancala_A.setEnabled(false);
		constraint.gridx = 7;
		constraint.gridy = 1;
		panel_1.add(mancala_A, constraint);
		
		// Mancala B pit 
		JButton mancala_B = new JButton();
		mancala_B.setBackground(Color.YELLOW);
		constraint.gridx = 0;
		constraint.gridy = 1;
		constraint.ipady = 100;
		constraint.gridheight = 2;
		mancala_B.setEnabled(false);
		panel_1.add(mancala_B, constraint);
		
	
		// Label Player B at the top
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(new FlowLayout());
		JLabel player_B = new JLabel("<~~~ Player B");
		panel_2.add(player_B);
		
		// Label Player A at the bottom
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(new FlowLayout());
		JLabel player_A = new JLabel("Player A ~~~>");
		panel_3.add(player_A);
		
		// Label Mancala B pit on left side
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(new FlowLayout());
		JLabel mancala_B_lab = new JLabel("Mancala B");
		panel_4.add(mancala_B_lab);
		
		// Label Mancala A pit at right side
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(new FlowLayout());
		JLabel mancala_A_lab = new JLabel("Mancala A");
		panel_5.add(mancala_A_lab);
		
	
		frame.add(panel_1, BorderLayout.CENTER); // The pits
		frame.add(panel_2, BorderLayout.NORTH); // Player B label
		frame.add(panel_3, BorderLayout.SOUTH); // Player A label
		frame.add(panel_4, BorderLayout.WEST); // Mancala B label
		frame.add(panel_5, BorderLayout.EAST); // Mancala A label
		
	}
	
	public void initialize() {
		JFrame frame = new JFrame("Mancala Game");
		createBoard(frame.getContentPane());
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}