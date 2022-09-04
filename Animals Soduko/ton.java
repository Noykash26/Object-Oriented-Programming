package animalSoduko;

public class ton extends fish {

	/*public ton (int age, String gender) {
		super(age,gender);
	}//constructor*/
	
	public String toString() {
		return "ton";
	}//toString
	
	public boolean isValid (animals [][] board, int row, int col) {// a method that checks if there is more than one ton in a diagonal
		int sumCell = row + col; 
		int subtractionCell = Math.abs(row-col);
		for ( int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (i+j == sumCell) { // check the left diagonals
					if (board[i][j]==null)
						continue;
					if (board[i][j] instanceof ton && ((i != row) && (j != col)))
						return false;
				}// if 
				
				if (Math.abs(i-j) == subtractionCell) {
					if (board[i][j]==null)
						continue;
					if (board[i][j] instanceof ton && ((i != row) && (j != col)))
						return false;
				} // if				
			}//inner for
		}//outer for
		
		return true;
	} //isValid
	
}//class ton