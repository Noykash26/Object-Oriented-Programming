package animalSoduko;

import java.util.Scanner;

public class animalsoduko {

	static Scanner scan = new Scanner(System.in);
	private static int size = 6; // size of the board
	public static int row, col;
	public static animals val; // val represents the value of a cell from type animal
	// public static int minVal = 1, maxVal = 9; // the minimum and the maximum
	// 'val'
	public static int minIndex = 0, maxIndex = (size - 1);// the minimum and the maximum for row and col
	public static animals[][] board = new animals[size][size]; // soduko board
	public static boolean[][] positions = new boolean[size][size]; // TRUE = inputs, FALSE = cells which can be
																	// changed/empty
	public static animals[] AnimalsArr = new animals[9];

	public static void main(String[] args) {

		row = 0;
		col = 0;
		setAnimalsArr(AnimalsArr);
		Sudoku();
	}// main

	public static void Sudoku() {
		setSudoku();
		System.out.println("The given animals are:");
		printBoard();
		// if (isValidBoard()) {
		while (!(outOfBoard())) {
			if (CellIsFull()) {
				nextPosition();
			} // if cell is full

			else {
				if (canPlaceAnimal()) {
					placeAnimal();
					nextPosition();
				} // else if cell is empty

				else {
					backToLastPosition();
				} // if cell is empty but can not be filled
			}
		} // while
		// } // outer if
	}// sudoku

	private static void setAnimalsArr(animals[] a) { // a method the set the array with all the animals by order
		a[0] = new monkey();
		a[1] = new dog();
		a[2] = new cat();
		a[3] = new snake();
		a[4] = new lizard();
		a[5] = new turtle();
		a[6] = new shark();
		a[7] = new ton();
		a[8] = new whale();
	}// setAnimalsArr

	public static void setSudoku() { // a method that set the board for the Sudoku game
		String input;
		System.out.println("Welcome to FATMA ANIMAL SODUKO 2019. For starting, press enter");
		scan.nextLine();

		do {
			System.out.println("Please enter a given animal and its location.");
			input = scan.nextLine();
			if (isValidInput(input) && !input.equals("null-00")) {
				setBoard(input);
			} // if input is valid, set it on the board
			else if (!(input.equals("null-00")))
				System.out.println("The input is not valid. Please try again"); // if input is not valid
		} // do
		while (!(input.equals("null-00")));
	}// setSudoku

	/*
	 * public static boolean isValidBoard() {// a method that checks if the board is
	 * valid for (; row < board.length; row++) { for (; col < board[row].length;
	 * col++) { if (positions[row][col] == true) { val = board[row][col]; if
	 * (inRow() || inCol() || inSquare()) {
	 * System.out.println("Looking for a solution…");
	 * System.out.println("There is no valid solution"); return false; } // if } //
	 * if } // inner for } // outer for setVariables(); return true; }//
	 * isValidBoard
	 */

	/*
	 * public static void setVariables() { val = 1; row = 0; col = 0; }
	 */

	private static boolean isValidInput(String s) { // a method that checks if input is valid
		/*
		 * if (s.length() != 4) return false;
		 */
		if (s.charAt(s.length() - 1) - '0' < minIndex || s.charAt(s.length() - 1) - '0' > maxIndex) // for the col
			return false;
		if (s.charAt(s.length() - 2) - '0' < minIndex || s.charAt(s.length() - 2) - '0' > maxIndex) // for the row
			return false;
		/*
		 * if (s.charAt(2) != '-') return false; if (s.charAt(3) - '0' < minVal ||
		 * s.charAt(3) - '0' > maxVal) // for the val return false;
		 */

		return true;
	} // isValid

	private static void setBoard(String s) {// a method that places the input in the board
		int row = s.charAt(s.length() - 2) - '0';
		int col = s.charAt(s.length() - 1) - '0';

		animals animal = StringToObject(animalName(s));
		board[row][col] = animal;
		setPositions(row, col);
	}// setBoard

	private static void setPositions(int i, int j) { // a method that places the input in the positions board
		positions[i][j] = true;
	}// setPositions

	private static String animalName(String s) { // a method that returns a new string with the animal's name
		return (s.substring(0, s.length() - 3));
	}// animalName

	private static animals StringToObject(String s) {
		switch (s) {
		case "monkey":
			return new monkey();
		case "dog":
			return new dog();
		case "cat":
			return new cat();
		case "snake":
			return new snake();
		case "lizard":
			return new lizard();
		case "turtle":
			return new turtle();
		case "shark":
			return new shark();
		case "ton":
			return new ton();
		case "whale":
			return new whale();
		}// switch

		return null;
	}// StringToObject

	public static void printBoard() {// a method that prints the board to the screen
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] == null)
					System.out.print("null" + Spaces(numOfSpace("null")));
				else {
					String temp = board[i][j].toString(); // a temporary string with the animal's name
					System.out.print(temp + Spaces(numOfSpace(temp)));
				}//else
			} // inner for
			System.out.println();
		} // outer for

	}// printBoard

	private static int numOfSpace(String s) { // a method that calculates the number of spaces between the animals on
												// board
		return (10 - s.length());

	}// numOfSpace

	private static String Spaces(int numOfSpace) { // a method the receives the number of spaces and returns a string in
													// that length
		if (numOfSpace == 0)
			return "";

		else
			return (" " + Spaces(numOfSpace - 1));
	}// Spaces

	public static boolean outOfBoard() {// a method that checks if we are out of the board limits
		if (row == -1) {
			System.out.println("Looking for a solution…");
			System.out.println("There is no valid solution");
			return true;
		} // there is no solution for the sudoku

		if (row == size) {
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
		if (col == size - 1) {
			col = 0;
			row = row + 1;
		} else
			col++;

	}// nextPosition

	public static void previousPosition() {// the same method like the one above, but in reverse
		if (col == 0) {
			col = size - 1 ;
			row--;
		} // if
		else
			col--;
		
		val = board[row][col];

	}// previousPosition

	public static boolean canPlaceAnimal() {// a method that checks if an animal can be place in the cell
		val = board[row][col];
		int index;
		
		if (val == null) //if there's nothing in the cell, we need to start from the begging of the animal's array
			index = -1;
		else // if there is somthing in the cell, we need to start from the next index in the animal's array
			index = returnIndex(val, AnimalsArr);

		for (index += 1; index < AnimalsArr.length; index++) {
			if (AnimalsArr[index].isValid(board, row, col)) {
				val = AnimalsArr[index];
				return true;
			}
		} // for

		return false;
	}// canPlaceAnimal

	private static int returnIndex (animals val, animals[] a) { // a method that returns the currently index (in the
																// animal's array) of the animal that we want to put in
																// the soduko board
		for (int i = 0 ; i < a.length; i ++) {
			if (val.toString().equals(a[i].toString()))
				return i;
		}//for
		
		return -1; 
	}// returnIndex

	/*
	 * public static boolean inRow() {// a method that checks if the val is in a
	 * given row for (int j = 0; j < board[row].length; j++) { if (board[row][j] ==
	 * val && j != col) return true; } // for return false; }// inRow
	 */

	/*
	 * public static boolean inCol() {// a method that checks if the val is in a
	 * given coloumn for (int i = 0; i < board.length; i++) { if (board[i][col] ==
	 * val && i != row) return true; } // for return false; }// inRow
	 */

	/*
	 * public static boolean inSquare() {// a method that checks if the val is in a
	 * given square/area int boxrow = row - (row % 3); int boxcol = col - (col % 3);
	 * 
	 * for (int i = 0; i < 3; i++) { for (int j = 0; j < 3; j++) { if ((board[boxrow
	 * + i][boxcol + j] == val) && ((boxrow + i) != row) && ((boxcol + j) != col))
	 * return true; } // inner for } // outer for return false; }// inSquare
	 */

	public static void placeAnimal() {// a method that places a val in a specific cell
		board[row][col] = val;
	}// placeAnimal

	public static void backToLastPosition() {
		removeAnimal();
		boolean f = false;
		while (!f && row != -1) {
			previousPosition();
			if (row != -1 && positions[row][col] == false)
				f = true;
		} // while
	}// backToLastPosition

	public static void removeAnimal() {// a method that deletes a number from a cell
		board[row][col] = null;
	}// removeAnimal
	
}// animal soduko class
