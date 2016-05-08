import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * The MancalaBoard class contains the View and Controller components of MVC pattern.
 * This class is responsible for the visual presentation and action of the Mancala game.
 * @authors Ann Le, Ha Nguyen, Pearl Or (Team Infinity)
 */
public class MancalaBoard extends JFrame {
	
	private MancalaModel model;
	private MancalaLayoutManager layout;
	private int width;
	private int height;
	
	/**
	 * To construct a MancalaBoard object with a specific model, layout, width and height.
	 * @param mancala: model of the Mancala game
	 * @param newLayout: design layout 
	 * @param w: width of the frame
	 * @param h: height of the frame
	 */
	public MancalaBoard(MancalaModel mancala, MancalaLayoutManager newLayout, int w, int h) {
		model = mancala;
		layout = newLayout;
		width = w;
		height = h;
		this.updateGraphics(false);
	}
	
	/**
	 * To set a new layout for the MancalaBoard.
	 * @param newLayout: new layout
	 */
	public void setLayout(MancalaLayoutManager newLayout) {
		layout = newLayout;
		this.updateGraphics(false);
	}

	/**
	 * To update the presentation of the MancalaBoard.
	 * @param check: true if the game has ended, false otherwise.
	 */
	public void updateGraphics(boolean check){
		this.getContentPane().removeAll();;
		
		int[][] board = model.getBoard();
		
		//Decorate background for the MancalaBoard
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
		
		//Top panel contains SETTING and UNDO buttons
		JPanel top = new JPanel();
		top.setOpaque(false);
		top.setLayout(new FlowLayout());
		
		JButton layoutButton = new JButton("Setting");
		layoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame settingWindow = new JFrame();
				settingWindow.setTitle("Setting");
				settingWindow.setLocationRelativeTo(MancalaBoard.this);
				
				JLabel question = new JLabel("Select a style board design:");
				question.setAlignmentX(JLabel.CENTER);
				settingWindow.add(question, BorderLayout.NORTH);
				    
				JPanel questionPanel = new JPanel();
				questionPanel.setLayout(new FlowLayout());
				   
				final JRadioButton styleA = new JRadioButton("Classic Layout");
				final JRadioButton styleB = new JRadioButton("Modern Layout");
				   
				questionPanel.add(styleA);
				questionPanel.add(styleB);
				settingWindow.add(questionPanel, BorderLayout.CENTER);
				
				ButtonGroup group = new ButtonGroup();
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
						settingWindow.dispose();
					}
				});
				
				JPanel buttonPanel = new JPanel();
				buttonPanel.add(button);
				
				settingWindow.add(buttonPanel, BorderLayout.SOUTH);
				settingWindow.pack();
				settingWindow.setVisible(true);
			}
		});
		
		//Add the SETTING button to the top panel
		top.add(layoutButton);
		
		JButton undoButton = new JButton("UNDO");
		undoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//WARNING window
				if (model.getUndoCounter() > 2) {
					JFrame warningWindow = new JFrame("Error");
					warningWindow.setSize(400, 100);
					
					JLabel message = new JLabel();
					message.setText("You have already used UNDO button 3 times for this turn.");
					message.setHorizontalAlignment(JLabel.CENTER);
					warningWindow.add(message);
					
					warningWindow.setVisible(true);
					warningWindow.setLocationRelativeTo(null);
					warningWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
				else if (!model.hasClickedPit()) {
					JFrame warningWindow = new JFrame("Error");
					warningWindow.setSize(300, 100);
					
					JLabel message = new JLabel();
					message.setText("<html>You already clicked the UNDO button.<br>Please select a pit in your side of the board!</html>");
					message.setHorizontalAlignment(JLabel.CENTER);
					warningWindow.add(message);
					
					warningWindow.setVisible(true);
					warningWindow.setLocationRelativeTo(null);
					warningWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
				else model.undo();
			}
		});
		
		//Add the UNDO button to the top panel
		top.add(undoButton);
		
		//Add the top panel to the North panel
		north.add(top, BorderLayout.NORTH);
		
		//PlayerB name label
		JLabel playerBLabel = new JLabel("PLAYER B");
		playerBLabel.setPreferredSize(new Dimension(width, UNIT_HEIGHT));
		playerBLabel.setHorizontalAlignment(JLabel.CENTER);
		layout.decoratePlayerLable(playerBLabel);
		
		//Check if this is Player B's turn. Change text color to Red if this is Player B's turn.
		if (model.getWhoseTurn() == 1)
			playerBLabel.setForeground(Color.RED);
		
		//Add PlayerB's name label to the North panel
		north.add(playerBLabel, BorderLayout.CENTER);
		
		//Add North panel to the MancalaBoard (JFrame)
		this.add(north, BorderLayout.NORTH);
		
		
		//PlayerA name label
		JLabel playerALabel = new JLabel("PLAYER A");
		playerALabel.setPreferredSize(new Dimension(width, UNIT_HEIGHT));
		playerALabel.setHorizontalAlignment(JLabel.CENTER);
		layout.decoratePlayerLable(playerALabel);
		//Check if this is Player A's turn
				if (model.getWhoseTurn() == 0)
					playerALabel.setForeground(Color.RED);
				
		//Add PlayerA's name label to MancalaBoard (JFrame)
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
		
		for (int i = 1; i <= 6; i++) {
			//Create the label
			JLabel pitLabel = new JLabel("B" + (7 - i));
			pitLabel.setPreferredSize(new Dimension(UNIT_WIDTH, 20));
			pitLabel.setHorizontalAlignment(JLabel.CENTER);
			layout.decoratePitLabel(pitLabel);
			namePanel.add(pitLabel);
			
			final int temp = i;
			//Create the PitButton
			PitButton pit = new PitButton(board[1][i]);
			
			pit.setPreferredSize(new Dimension(UNIT_WIDTH, UNIT_HEIGHT*4 - 70));
			pit.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (model.getWhoseTurn() == 1)
						model.update(1, 7 - temp);
				}
			});
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
		
		for (int i = 1; i <= 6; i++) {
			//Create the label
			JLabel pitLabel = new JLabel("A" + i);
			pitLabel.setPreferredSize(new Dimension(UNIT_WIDTH, 20));
			pitLabel.setHorizontalAlignment(JLabel.CENTER);
			layout.decoratePitLabel(pitLabel);
			namePanel.add(pitLabel);
			
			final int temp = i;
			//Create the PitButton
			PitButton pit = new PitButton(board[0][i]);
			
			pit.setPreferredSize(new Dimension(UNIT_WIDTH, UNIT_HEIGHT*4 - 70));
			pit.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (model.getWhoseTurn() == 0)
						model.update(0, temp);
				}
			});
			layout.decoratePit(pit);
			pitButtonPanel.add(pit);
		}
		
		panel.add(pitButtonPanel);
		panel.add(namePanel);
		
		this.add(panel, BorderLayout.CENTER);
		
		
		//Left Mancala
		JPanel leftPanel = new JPanel();
		leftPanel.setOpaque(false);
		leftPanel.setPreferredSize(new Dimension(UNIT_WIDTH*2, UNIT_HEIGHT*8));
		
		JLabel mancalaLabelB = new JLabel("MANCALA B");
		mancalaLabelB.setOpaque(false);
		mancalaLabelB.setPreferredSize(new Dimension(UNIT_WIDTH*2, 30));
		mancalaLabelB.setHorizontalAlignment(JLabel.CENTER);
		layout.decoratePitLabel(mancalaLabelB);
		leftPanel.add(mancalaLabelB);
		
		MancalaComponent mancalaLeft = new MancalaComponent(board[1][0], 20, 20);
		mancalaLeft.setPreferredSize(new Dimension(UNIT_WIDTH*2, UNIT_HEIGHT*8 - 35));
		layout.decoratePit(mancalaLeft);
		leftPanel.add(mancalaLeft);
		
		//Add the left panel to JFrame (MancalaBoard)
		this.add(leftPanel, BorderLayout.WEST);
		
		
		//Right Mancala
		JPanel rightPanel = new JPanel();
		rightPanel.setOpaque(false);
		rightPanel.setPreferredSize(new Dimension(UNIT_WIDTH*2, UNIT_HEIGHT*8));
		
		JLabel mancalaLabelA = new JLabel("MANCALA A");
		mancalaLabelA.setOpaque(false);
		mancalaLabelA.setPreferredSize(new Dimension(UNIT_WIDTH*2, 30));
		mancalaLabelA.setHorizontalAlignment(JLabel.CENTER);
		layout.decoratePitLabel(mancalaLabelA);
		rightPanel.add(mancalaLabelA);
		
		MancalaComponent mancalaRight = new MancalaComponent(board[0][0], 0, 20);
		mancalaRight.setPreferredSize(new Dimension(UNIT_WIDTH*2, UNIT_HEIGHT*8 - 35));
		layout.decoratePit(mancalaRight);
		rightPanel.add(mancalaRight);
		
		//Add the right panel to JFrame (MancalaBoard)
		this.add(rightPanel, BorderLayout.EAST);
		
		
		this.setVisible(true);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		//If the game has ended, display the WINNER and give user the 2 options: Play again or Quit
		if (check)
		{	
			JFrame winnerNotify = new JFrame("The Winner is Here!");
			winnerNotify.setSize(450, 150);
			winnerNotify.setLocationRelativeTo(null);
			
			String message = "";
			//Get the winner
			int winner = model.getWinner();
			if (winner == 0)
				message = "Player A won!";
			else if (winner == 1)
				message = "Player B won!";
			else
				message= "It's a draw!";
			
			JLabel winnerLabel = new JLabel();
			winnerLabel.setText(message);
			winnerLabel.setHorizontalAlignment(JLabel.CENTER);
			winnerLabel.setFont(new Font("Arial", Font.BOLD, 30));
			winnerLabel.setForeground(Color.RED);
			
			//Add the message to the frame
			winnerNotify.add(winnerLabel, BorderLayout.NORTH);
		
			//Add more information
			String detailMessage = "Player A's Mancala has: " + model.getMancalaStones(0) + " stones!   "
					+ "Player B's Mancala has: " + model.getMancalaStones(1) + " stones!";
			JLabel detailLabel = new JLabel();
			detailLabel.setText(detailMessage);
			detailLabel.setHorizontalAlignment(JLabel.CENTER);
			
			//Add the detail information to the frame
			winnerNotify.add(detailLabel, BorderLayout.CENTER);
			
			//Give options
			JPanel optionPanel = new JPanel();
			optionPanel.setLayout(new FlowLayout());
			   
			//This button is to restart the game. MancalaOptionMenu displays. All other windows are disposed.
			JButton reStartButton = new JButton("Play Again!");
			reStartButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
					winnerNotify.dispose();
					MancalaOptionMenu menu = new MancalaOptionMenu();
				}
			});
			
			//This button is to quit the game. Program is terminated.
			JButton quitButton = new JButton("QUIT");
			quitButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			   
			optionPanel.add(reStartButton);
			optionPanel.add(quitButton);
			
			//Add the options to the frame
			winnerNotify.add(optionPanel, BorderLayout.SOUTH);			
			
			winnerNotify.setVisible(true);
		}
		
		this.revalidate();
		this.repaint();
	}
}