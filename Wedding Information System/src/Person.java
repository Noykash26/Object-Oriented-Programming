
public class Person implements Locatable {

	private int id;
	private String firstName;
	private String lastName;
	private Location location;

	public Person(int id, String firstName, String lastName, Location location) {// constructor
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.location = location;
	}// Person

	public int getid() {
		return this.id;
	}// getbride

	public Location getLocation() {
		return location;
	}// getLocation

}// class Person
