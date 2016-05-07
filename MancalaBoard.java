import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
/**
 * This class contains the View and Controller portions of MVC pattern.
 * 
 * The view is responsible for the appearance of the program.
 * 
 * There is also the Controller.
 * They are the ActionListeners in this class. They call the mutators of the CalendarModel.
 * 
 * @authors Ann Le, Ha Nguyen, Pearl Or
 *
 * Class creates a MancalaBoard.
 * MancalaBoard is responsible for the visual and event handling aspects of the mancala game.
 */
public class MancalaBoard extends JFrame 
{
	private MancalaModel model;
	private MancalaLayoutManager layout;
	private int width;
	private int height;
	/**
	 * 
	 * @param mancala
	 * @param newLayout
	 * @param w
	 * @param h
	 */
	public MancalaBoard(MancalaModel mancala, MancalaLayoutManager newLayout, int w, int h) 
	{
		model = mancala;
		layout = newLayout;
		width = w;
		height = h;
		this.updateGraphics();
	}
	/**
	 * Sets the layout of the MancalaBoard.
	 * @param newLayout the MancalaLayoutManager to set as
	 */
	public void setLayout(MancalaLayoutManager newLayout) 
	{
		layout = newLayout;
		this.updateGraphics();
	}
	/**
	 * Updates the display of the MancalaBoard.
	 */
	public void updateGraphics()
	{
		this.getContentPane().removeAll();
		
		int[][] board = model.getBoard();
		
		layout.decorateBackground(this);
		
		final int UNIT_WIDTH = width/11;
		final int UNIT_HEIGHT = height/10;
		
		this.setTitle("Mancala Game");
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(width, height));

		//North panel
		JPanel north = new JPanel();
		north.setMaximumSize(new Dimension(width, UNIT_HEIGHT));
		north.setLayout(new BorderLayout());
		north.setOpaque(false);
		
		//Top panel contains all the setting buttons
		JPanel top = new JPanel();
		top.setOpaque(false);
		top.setLayout(new FlowLayout());
		
		JButton layoutButton = new JButton("Setting");
		layoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame setting = new JFrame();
				setting.setLocationRelativeTo(MancalaBoard.this);
				
				JLabel question2 = new JLabel("Select a style board design:");
				question2.setAlignmentX(JLabel.CENTER);
				question2.setOpaque(false);
				setting.add(question2, BorderLayout.NORTH);
				    
				JPanel questionPanel2 = new JPanel();
				questionPanel2.setOpaque(false);
				questionPanel2.setLayout(new FlowLayout());
				   
				final JRadioButton styleA = new JRadioButton("Classic Layout");
				final JRadioButton styleB = new JRadioButton("Modern Layout");
				   
				questionPanel2.add(styleA);
				questionPanel2.add(styleB);
				setting.add(questionPanel2, BorderLayout.CENTER);
				
				final ButtonGroup group = new ButtonGroup();
				group.add(styleA);
				group.add(styleB);
				
				//Save button
				JButton button = new JButton("SAVE");
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (styleA.isSelected() || styleB.isSelected()) {
							MancalaLayoutManager newLayout = new ClassicLayout();
							if (styleB.isSelected())
							   newLayout = new ModernLayout();
							setLayout(newLayout);
						}
						setting.dispose();
					}
				});
				
				JPanel buttonPanel = new JPanel();
				buttonPanel.add(button);
				
				setting.add(buttonPanel, BorderLayout.SOUTH);
				setting.pack();
				setting.setVisible(true);
			}
		});
		top.add(layoutButton);
		
		JButton undoButton = new JButton("UNDO");
		undoButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				model.undo();
			}
		});
		top.add(undoButton);
		
		north.add(top, BorderLayout.NORTH);
		
		//PlayerB name
		JLabel playerBLabel = new JLabel("PLAYER B");
		playerBLabel.setPreferredSize(new Dimension(width, UNIT_HEIGHT));
		playerBLabel.setHorizontalAlignment(JLabel.CENTER);
		layout.decoratePlayerLabel(playerBLabel);
		
		north.add(playerBLabel, BorderLayout.CENTER);
		
		//Add North panel to the JFrame
		this.add(north, BorderLayout.NORTH);
		
		
		//PlayerA name
		JLabel playerALabel = new JLabel("PLAYER A");
		playerALabel.setPreferredSize(new Dimension(width, UNIT_HEIGHT));
		playerALabel.setHorizontalAlignment(JLabel.CENTER);
		layout.decoratePlayerLabel(playerALabel);
		this.add(playerALabel, BorderLayout.SOUTH);
		

		//Panel containing all the pits together
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setMaximumSize(new Dimension(UNIT_WIDTH*8, UNIT_HEIGHT*8));
		
		//namePanel contains all the name labels
		JPanel namePanel = new JPanel();
		namePanel.setOpaque(false);
		namePanel.setPreferredSize(new Dimension(UNIT_WIDTH*8, 30));
		namePanel.setBorder(BorderFactory.createEmptyBorder());
		namePanel.setLayout(new FlowLayout());
		
		//pitButtonPanel contains all the PitButtons
		JPanel pitButtonPanel = new JPanel();
		pitButtonPanel.setOpaque(false);
		pitButtonPanel.setPreferredSize(new Dimension(UNIT_WIDTH*8, UNIT_HEIGHT*4 - 40));
		pitButtonPanel.setBorder(BorderFactory.createEmptyBorder());
		pitButtonPanel.setLayout(new FlowLayout());
		
		for (int i = 1; i <= 6; i++) 
		{
			//Create the label
			JLabel pitLabel = new JLabel("B" + (7 - i));
			pitLabel.setPreferredSize(new Dimension(UNIT_WIDTH, 20));
			pitLabel.setFont(new Font("Arial", Font.BOLD, 16));
			pitLabel.setHorizontalAlignment(JLabel.CENTER);
			layout.decoratePitLabel(pitLabel);
			namePanel.add(pitLabel);
			
			final int temp = i;
			//Create the PitButton
			PitButton pit = new PitButton(board[1][i]);
			
			pit.setPreferredSize(new Dimension(UNIT_WIDTH, UNIT_HEIGHT*4 - 70));
			pit.addMouseListener(new MouseAdapter() 
			{
				public void mouseClicked(MouseEvent e) 
				{
					model.update(1, (7-temp)); //since A's is reversed the selection needs to be reverted
				}
			});
			layout.decorateStone(pit);
			layout.decoratePit(pit);
			pitButtonPanel.add(pit);
		}
		
		panel.add(namePanel);
		panel.add(pitButtonPanel);
		
		
		//namePanel contains all the name labels
		namePanel = new JPanel();
		namePanel.setOpaque(false);
		namePanel.setPreferredSize(new Dimension(UNIT_WIDTH*8, 30));
		namePanel.setBorder(BorderFactory.createEmptyBorder());
		namePanel.setLayout(new FlowLayout());
		
		//pitButtonPanel contains all the PitButtons
		pitButtonPanel = new JPanel();
		pitButtonPanel.setOpaque(false);
		pitButtonPanel.setPreferredSize(new Dimension(UNIT_WIDTH*8, UNIT_HEIGHT*4 - 40));
		pitButtonPanel.setBorder(BorderFactory.createEmptyBorder());
		pitButtonPanel.setLayout(new FlowLayout());
		
		for (int i = 1; i <= 6; i++) 
		{
			//Create the label
			JLabel pitLabel = new JLabel("A" + i);
			pitLabel.setPreferredSize(new Dimension(UNIT_WIDTH, 20));
			pitLabel.setFont(new Font("Arial", Font.BOLD, 16));
			pitLabel.setHorizontalAlignment(JLabel.CENTER);
			layout.decoratePitLabel(pitLabel);
			namePanel.add(pitLabel);
			
			final int temp = i;
			//Create the PitButton
			PitButton pit = new PitButton(board[0][i]);
			
			pit.setPreferredSize(new Dimension(UNIT_WIDTH, UNIT_HEIGHT*4 - 70));
			pit.addMouseListener(new MouseAdapter() 
			{
				public void mouseClicked(MouseEvent e) 
				{
					model.update(0, temp);
				}
			});
			layout.decorateStone(pit);
			layout.decoratePit(pit);
			pitButtonPanel.add(pit);
		}
		
		panel.add(pitButtonPanel);
		panel.add(namePanel);
		
		this.add(panel, BorderLayout.CENTER);
		
		
		//Left Mancala
		MancalaComponent mancalaLeft = new MancalaComponent(board[1][0], 20, 40);
		mancalaLeft.setPreferredSize(new Dimension(UNIT_WIDTH*2, UNIT_HEIGHT*8));
		layout.decorateStone(mancalaLeft);
		layout.decoratePit(mancalaLeft);
		this.add(mancalaLeft, BorderLayout.WEST);
		
		
		//Right Mancala
		MancalaComponent mancalaRight = new MancalaComponent(board[0][0], 0, 40);
		mancalaRight.setPreferredSize(new Dimension(UNIT_WIDTH*2, UNIT_HEIGHT*8));
		layout.decorateStone(mancalaRight);
		layout.decoratePit(mancalaRight);
		
		if(model.isGameOver())
		{	
		   	
			Object[] options = {"Reset Game", "Close Game"};
			String winnerMsg = "won!";
			int winner = model.getWinner();
			if(winner == 0)
			{
				winnerMsg = "Player A " + winnerMsg;
			}
			else if(winner == 1)
			{
				winnerMsg = "Player B " + winnerMsg;
			}
			else
			{
				winnerMsg = "It's a Draw!";
			}
			
			winnerMsg += "\nPlayer A's Mancala has: " + model.getMancalaAStones() + " stones!" + "\nPlayer B's Mancala has: " + model.getMancalaBStones() + " stones!";
			//setVisible(true);
			
			
			int n = JOptionPane.showOptionDialog(null, winnerMsg, "Play Again?", JOptionPane.OK_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
			if(n == 0) //Reset Game
			{
				model.resetBoard();
			}
			else
			{
				System.exit(0);
			}
		
		}
		
		this.add(mancalaRight, BorderLayout.EAST);
		
		this.setVisible(true);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.revalidate();
		this.repaint();
	}
}