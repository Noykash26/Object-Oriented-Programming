package animalSoduko;

public class cat extends mammals {

	/*public cat (int age, String gender) {
		super(age,gender);
	}//constructor*/
	
	public String toString() {
		return "cat";
	}//toString
	
	public boolean isValid (animals [][] board, int row, int col) {// a method that checks if there is a dog or another cat in a column
		for (int i = 0; i < board.length; i++) {
			if ((board[i][col] instanceof cat || board[i][col] instanceof dog) && i != row)
				return false;
		}//for
		return true;
	}//isValid
	
}//class cat
