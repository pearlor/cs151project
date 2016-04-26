public class MancalaTester 
{
	public static void main(String[] args)
	{
		MancalaModel m = new MancalaModel(4);
		String bFormat = "B 6 5 4 3 2 1\n1 2 3 4 5 6 A";
		
		System.out.println(bFormat + "\n");
		
		m.printBoard();
		System.out.println("\n");
		
		//1
		m.distribute(0, 1);
		m.printBoard();
		System.out.println("\n");
		//2
		m.distribute(1, 1);
		m.printBoard();
		System.out.println("\n");
		//3
		m.distribute(0, 6);
		m.printBoard();
		System.out.println("\n");
		//4
		m.distribute(1, 2);
		m.printBoard();
		System.out.println("\n");
		//5
		m.distribute(0, 2);
		m.printBoard();
		System.out.println("\n");
		//6
		m.distribute(0, 1);
		m.printBoard();
		System.out.println("\n");
		//7
		m.distribute(1, 1);
		m.printBoard();
		System.out.println("\n");
	}
}
