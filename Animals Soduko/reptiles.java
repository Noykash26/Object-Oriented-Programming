package animalSoduko;

public class reptiles extends animals {

	/*public reptiles (int age, String gender) {
		super(age,gender);
	}//constructor*/
	
	public boolean isValid (animals [][] board, int row, int col) {// a method that checks if there is more than one reptile in a column
		for (int i = 0; i < board.length; i++) {
			if (board[i][col] instanceof reptiles && i != row)
				return false;
		}//for
		return true;
	}//isValid
}//class reptiles


