import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

 /**
  * This class displays the menu screen that lists
  * the options for the user to select before starting the game
  */
public class MancalaOptionMenu  {
    
   private JFrame mainFrame;
   private JLabel Stones_Label;
   private JLabel Style_Label;
   private JPanel controlPanel;
   private JRadioButton styleA;
   private JRadioButton styleB;
   private JRadioButton stonesA;
   private JRadioButton stonesB;

   public MancalaOptionMenu(){
      prepareGUI();
   }

   public static void main(String[] args){
	   MancalaOptionMenu  MancalaOptionMenu = new MancalaOptionMenu();      
	   MancalaOptionMenu.showButtons();
   }

   /**
    * Creates the frame of the menu screen
    */
   private void prepareGUI(){
      mainFrame = new JFrame("Mancala Game Menu");
      mainFrame.setSize(400,400);
      mainFrame.setLayout(new GridLayout(3, 1));
      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });    
      Stones_Label = new JLabel("", JLabel.LEFT);        
      Style_Label = new JLabel("",JLabel.LEFT);    

      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());

      mainFrame.add(Stones_Label);
      mainFrame.add(controlPanel);
      mainFrame.add(Style_Label);
      mainFrame.setVisible(true);  
   }
    
  /**
   * Displays the buttons 
   */
   private void showButtons(){

      Stones_Label.setText("Select number of stones per pit:"); 
      Style_Label.setText("Select a style board design:");
       
      JButton javaButton = new JButton("Start Game");
      
      javaButton.setHorizontalTextPosition(SwingConstants.LEFT);   
      
      styleA = new JRadioButton("Classic Layout");
      styleB = new JRadioButton("Modern Layout");
      
      
      JPanel stonesPanel = new JPanel();
      stonesPanel.setLayout(new BoxLayout(stonesPanel, BoxLayout.Y_AXIS));
      stonesA = new JRadioButton("3 stones");
      stonesB = new JRadioButton("4 stones");

      controlPanel.add(stonesA);
      controlPanel.add(stonesB);
      controlPanel.add(styleA);
      controlPanel.add(styleB);
      controlPanel.add(javaButton);
           
      
      mainFrame.setVisible(true);  
   }
}