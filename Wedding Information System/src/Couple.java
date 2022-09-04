import java.util.Vector;

public class Couple implements Locatable {

	private Person groom;
	private Person bride;
	private Vector<Person> listOfInvited;
	private double maxPerMeal;
	private boolean indoor;
	private boolean outdoor;

	public Couple(Person p1, Person p2, double maxPerMeal, boolean indoor, boolean outdoor)
			throws missingChoiceForEventHallException {// constructor
		groom = p1;
		bride = p2;

		this.maxPerMeal = maxPerMeal;

		if (indoor == false && outdoor == false) {// אם הזוג לא בחר העדפה לאולם אירועים נגיב באגרסיביות
			throw new missingChoiceForEventHallException("An event hall type must be chosen");
		} // if

		else {
			this.indoor = indoor;
			this.outdoor = outdoor;
		} // else
	}// Couple

	public void addListOfInvited(Person guest) throws exceededQuotaException { // a method that adds a person to the
																				// couple's list
		if (listOfInvited.size() >= 500)// אי אפשר להוסיף מוזמנים החל מהמוזמן ה-501
			throw new exceededQuotaException("It's not possible to have more than 500 guests");
		else
			listOfInvited.add(guest);
	}// addListOfInvited

	public Person getgroom() {
		return groom;
	}// getgroom

	public Person getbride() {
		return bride;
	}// getbride
	
	public Vector <Person> getlistOfInvited () {
		return listOfInvited;
	}//getlistOfInvited

	public Location getLocation() {
		return groom.getLocation();
	}// getLocation

	public int getnumOfGuests() {
		return listOfInvited.size();
	}// getnumOfGuests
	
	public double getmaxPerMeal() {
		return maxPerMeal;
	}// getmaxPerMeal
	
	public boolean getindoor() {
		return indoor;
	}// getindoor
	
	public boolean getoutdoor() {
		return outdoor;
	}// getoutdoor
}// class Couple
