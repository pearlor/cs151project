
/**
 * This is the tester for the Mancala Board 
 */

public class MancalaBoardTester {
		
	    public static void main(String[] args) {
	    	MancalaBoard gui = new MancalaBoard();

	        javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                gui.initialize();
	            }
	        });
	    }
	}