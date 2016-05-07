import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * 
 * @authors Ann Le, Ha Nguyen, Pearl Or
 *
 * This class displays the menu screen that lists
 * the options for the user to select before starting a mancala game.
 * After user input, this class creates the MancalaModel and MancalaBoard for the actual game.
 *
 */
public class MancalaOptionMenu extends JFrame 
{  
	/**
	 * Creates a MancalaOptionMenu.
	 */
   public MancalaOptionMenu()
   {
	   /*
	   JLabel background = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().createImage("menu.png")));
	   this.setContentPane(background);
	   */
	   
	   this.setTitle("Mancala Game");
	   this.setSize(new Dimension(500, 200));
	   
	   //Panel for the first question
	   JPanel part1 = new JPanel();
	   part1.setOpaque(false);
	   part1.setLayout(new BorderLayout());

	   JLabel question1 = new JLabel("Select number of stones per pit:");
	   question1.setAlignmentX(JLabel.CENTER);
	   question1.setOpaque(false);
	   part1.add(question1, BorderLayout.NORTH);
	   
	   JPanel questionPanel1 = new JPanel();
	   questionPanel1.setOpaque(false);
	   questionPanel1.setLayout(new FlowLayout());
	   
	   final JRadioButton stones3 = new JRadioButton("3 stones");
	   final JRadioButton stones4 = new JRadioButton("4 stones");
	   
	   questionPanel1.add(stones3);
	   questionPanel1.add(stones4);
	   part1.add(questionPanel1, BorderLayout.CENTER);
	   
	   final ButtonGroup group1 = new ButtonGroup();
	   group1.add(stones3);
	   group1.add(stones4);
	   
	   //Add part1 to JFrame
	   this.add(part1, BorderLayout.NORTH);

	   
	   //Panel for the second question
	   JPanel part2 = new JPanel();
	   part2.setOpaque(false);
	   part2.setLayout(new BorderLayout());
	   
	   JLabel question2 = new JLabel("Select a design for the board:");
	   question2.setAlignmentX(JLabel.CENTER);
	   question2.setOpaque(false);
	   part2.add(question2, BorderLayout.NORTH);
	    
	   JPanel questionPanel2 = new JPanel();
	   questionPanel2.setOpaque(false);
	   questionPanel2.setLayout(new FlowLayout());
	   
	   final JRadioButton styleA = new JRadioButton("Classic Layout");
	   final JRadioButton styleB = new JRadioButton("Modern Layout");
	   
	   questionPanel2.add(styleA);
	   questionPanel2.add(styleB);
	   part2.add(questionPanel2, BorderLayout.CENTER);
	   
	   final ButtonGroup group2 = new ButtonGroup();
	   group2.add(styleA);
	   group2.add(styleB);
	   
	   //Add part2 to JFrame
	   this.add(part2, BorderLayout.CENTER);
	   
	   //The START button
	   JPanel bottomPanel = new JPanel();
	   bottomPanel.setOpaque(false);
	   
	   JButton startButton = new JButton("Let's START!!!");
	   startButton.addActionListener(new ActionListener() {
		   @Override
		   public void actionPerformed(ActionEvent e) {
			   if ((stones3.isSelected() || stones4.isSelected()) && (styleA.isSelected() || styleB.isSelected())) {
				   int num = 0;
				   if (stones3.isSelected())
					   num = 3;
				   else num = 4;
			   
				   MancalaLayoutManager layout = new ModernLayout();
				   if (styleA.isSelected())
					   layout = new ClassicLayout();
			   
				   //Initiate the model
				   MancalaModel model = new MancalaModel(num);
				   
				   //Create the view-controller component
				   MancalaBoard board = new MancalaBoard(model, layout, 1200, 700);
				   
				   //Attach the model to the view and controller component
				   model.attach(board);
					
				   
				   //Close the menu window
				   dispose();
			   }
			   else 
			   {
				   JFrame errorMessage = new JFrame();
				   errorMessage.setTitle("Error");
				   errorMessage.setSize(450, 100);
				   JLabel message = new JLabel("Please select 1 option for stone number and 1 option for board design!");
				   errorMessage.add(message);
				   errorMessage.setLocationRelativeTo(MancalaOptionMenu.this);
				   errorMessage.setVisible(true);
				   errorMessage.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			   }
		   }
	   });
	   bottomPanel.add(startButton);
	   
	   //Add the bottom panel to JFrame
	   this.add(bottomPanel, BorderLayout.SOUTH);
	   
	   this.setVisible(true);
	   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
}