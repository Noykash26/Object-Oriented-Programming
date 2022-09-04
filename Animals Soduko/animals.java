package animalSoduko;

public class animals {

	protected int age;
	protected String gender;
	
	public animals () {
		age = 0;
		gender = "";
	}
	
	public animals (int age, String gender) {
		this.age = age;
		this.gender = gender; 
	}//constructor
	
	public boolean isValid (animals [][] board, int row, int col) {
		return true;
	}//isValid
	
	public String toString() {
		return "";
	}//toString
	
}//class animals
