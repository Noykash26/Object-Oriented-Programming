import java.util.Scanner;

public class Suduko1 {

	static Scanner scan = new Scanner(System.in);
	public static int row, col, val; // val represents the value of a cell
	public static int minVal = 1, maxVal = 9; // the minimum and the maximum 'val'
	public static int minIndex = 0, maxIndex = 8;// the minimum and the maximum for row and col
	public static int[][] board = new int[9][9]; // soduko board
	public static boolean[][] positions = new boolean[9][9]; // TRUE = inputs, FALSE = cells which can be changed/empty

	public static void main(String[] args) {

		row = 0;
		col = 0;
		Sudoku();
	}// main

	public static void Sudoku() {
		setSudoku();
		System.out.println("The given numbers are:");
		printBoard();
		if (isValidBoard()) {
			while (!(outOfBoard())) {
				if (CellIsFull()) {
					nextPosition();
				} // if cell is full

				else {
					if (canPlaceNumber()) {
						placeNumber();
						nextPosition();
					} // else if cell is empty

					else {
						backToLastPosition();
					} // if cell is empty but can not be filled
				}
			} // while
		} // outer if
	}// sudoku

	public static void setSudoku() { // a method that set the board for the Sudoku game
		String input;
		System.out.println("Welcome to FATMA SODUKO 2019. For starting, press enter");
		scan.nextLine();

		do {
			System.out.println("Please enter a given number and its location.");
			input = scan.nextLine();
			if (isValid(input) && !input.equals("00-0")) {
				setBoard(input);
			} // if input is valid
			else if (!(input.equals("00-0")))
				System.out.println("The input is not valid. Please try again"); // if input is not valid
		} // do
		while (!(input.equals("00-0")));
	}// setSudoku

	public static boolean isValidBoard() {// a method that checks if the board is valid
		for (; row < board.length; row++) {
			for (; col < board[row].length; col++) {
				if (positions[row][col] == true) {
					val = board[row][col];
					if (inRow() || inCol() || inSquare()) {
						System.out.println("Looking for a solution…");
						System.out.println("There is no valid solution");
						return false;
					} // if
				} // if
			} // inner for
		} // outer for
		setVariables();
		return true;
	}// isValidBoard

	public static void setVariables() {
		val = 1;
		row = 0;
		col = 0;
	}

	public static boolean isValid(String s) { // a method that checks if input is valid
		if (s.length() != 4)
			return false;
		if (s.charAt(0) - '0' < minIndex || s.charAt(0) - '0' > maxIndex) // for the row
			return false;
		if (s.charAt(1) - '0' < minIndex || s.charAt(1) - '0' > maxIndex) // for the col
			return false;
		if (s.charAt(2) != '-')
			return false;
		if (s.charAt(3) - '0' < minVal || s.charAt(3) - '0' > maxVal) // for the val
			return false;

		return true;
	} // isValid

	public static void setBoard(String s) {// a method that places the input in the board
		int row = s.charAt(0) - '0';
		int col = s.charAt(1) - '0';
		int num = s.charAt(3) - '0';

		board[row][col] = num;
		setPositions(row, col);
	}// setBoard

	public static void setPositions(int i, int j) { // a method that places the input in the positions board
		positions[i][j] = true;
	}// setPositions

	public static void printBoard() {// a method that prints the board to the screen
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + "\t");
			} // inner for
			System.out.println();
		} // outer for

	}// printBoard

	public static boolean outOfBoard() {// a method that checks if we are out of the board limits
		if (row == -1) {
			System.out.println("Looking for a solution…");
			System.out.println("There is no valid solution");
			return true;
		} // there is no solution for the sudoku

		if (row == 9) {
			System.out.println("Looking for a solution…");
			System.out.println("The solution is:");
			printBoard();
			return true;
		} // there is a solution for the Sudoku

		return false;
	}// outOfBoard

	public static boolean CellIsFull() {
		return (positions[row][col]);
	}// CellIsFull

	public static void nextPosition() {// a method that move the position by one according to the location in the board
		if (col == 8) {
			col = 0;
			row = row + 1;
		} else
			col++;

	}// nextPosition

	public static void previousPosition() {// the same method like the one above, but in reverse
		if (col == 0) {
			col = 8;
			row--;
		} // if
		else
			col--;
		val = board[row][col];

	}// previousPosition

	public static boolean canPlaceNumber() {// a method that checks if a val can be place in the cell
		val = board[row][col];
		for (val += 1; val <= maxVal; val++) {
			if ((!inRow() && !inCol() && !inSquare()))
				return true;
		} // for

		return false;
	}// canPlaceNumber

	public static boolean inRow() {// a method that checks if the val is in a given row
		for (int j = 0; j < board[row].length; j++) {
			if (board[row][j] == val && j != col)
				return true;
		} // for
		return false;
	}// inRow

	public static boolean inCol() {// a method that checks if the val is in a given coloumn
		for (int i = 0; i < board.length; i++) {
			if (board[i][col] == val && i != row)
				return true;
		} // for
		return false;
	}// inRow

	public static boolean inSquare() {// a method that checks if the val is in a given square/area
		int boxrow = row - (row % 3);
		int boxcol = col - (col % 3);

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if ((board[boxrow + i][boxcol + j] == val) && ((boxrow + i) != row) && ((boxcol + j) != col))
					return true;
			} // inner for
		} // outer for
		return false;
	}// inSquare

	public static void placeNumber() {// a method that places a val in a specific cell
		board[row][col] = val;
	}// placeNumber

	public static void backToLastPosition() {
		removeNumber();
		boolean f = false;
		while (!f && row != -1) {
			previousPosition();
			if (row != -1 && positions[row][col] == false)
				f = true;
		} // while
	}// backToLastPosition

	public static void removeNumber() {// a method that deletes a number from a cell
		board[row][col] = 0;
	}// removeNumber
}// class
