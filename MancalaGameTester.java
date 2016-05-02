import java.awt.*;
import javax.swing.*;

public class MancalaGameTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		MancalaOptionMenu menu = new MancalaOptionMenu();
		*/
		
		MancalaModel model = new MancalaModel(4);
		MancalaLayoutManager layout = new ModernLayout();
		MancalaBoard board = new MancalaBoard(model, layout, 1200, 700);
	}

}