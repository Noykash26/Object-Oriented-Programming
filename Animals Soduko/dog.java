package animalSoduko;

public class dog extends mammals {

/*	public dog (int age, String gender) {
		super(age, gender);
	}//constructor*/
	
	public String toString() {
		return "dog";
	}//toString
	
	public boolean isValid (animals [][] board, int row, int col) {// a method that checks if there is a cat in a column
		for (int i = 0; i < board.length; i++) {
			if (board[i][col] instanceof cat && i != row)
				return false;
		}//for
		return true;
	}//isValid
	
}//class dog
