package animalSoduko;

public class mammals extends animals {

	/*
	 * public mammals(int age, String gender) { super(age, gender); }// constructor
	 */

	public boolean isValid (animals[][] board, int row, int col) { 
		return (test1(board, row, col) && test2(board, row, col));
	}// isValid

	private boolean test1(animals[][] board, int row, int col) { // a method that checks if there is more than one mammal in a row
			for (int j = 0; j < board[row].length; j++) {
			if (board[row][j] instanceof mammals && j != col)
				return false;
		} // for
		return true;
	}// test1
	
	private boolean test2(animals[][] board, int row, int col) { // a method that checks if there is more than one mammal in a row
		int sumCell = row + col; 
		int subtractionCell = Math.abs(row-col);
		for ( int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (i+j == sumCell) { // check the left diagonals
					if (board[i][j]==null)
						continue;
					if (board[i][j] instanceof snake && ((i != row) && (j != col)))
						return false;
				}// if 
				
				if (Math.abs(i-j) == subtractionCell) {
					if (board[i][j]==null)
						continue;
					if (board[i][j] instanceof snake && ((i != row) && (j != col)))
						return false;
				} // if				
			}//inner for
		}//outer for
		
		return true;
	}//test2
}// class mammals
