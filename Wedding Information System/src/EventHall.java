
abstract class EventHall implements Comparable<EventHall>, Locatable {
	protected String name;
	protected Location location;
	protected int maxInvited;
	protected int minInvited;
	protected int foodRate;
	protected double dishPrice;

	public EventHall(String name, Location location, int maxInvited, int minInvited, int foodRate) {// constructor
		this.name = name;
		this.location = location;
		this.maxInvited = maxInvited;
		this.minInvited = minInvited;
		this.foodRate = foodRate;

	}// בנאי

	public static double getA(int foodRate) {// רמת האוכל
		return (foodRate / 10);
	}// getA

	public static double getB(Location location) {// המרחק ממרכז הארץ
		return (1 - (location.distanceFromCenter() / 100));
	}// getB

	public int compareTo(EventHall other) {// מימוש הממשק
		if (dishPrice > other.dishPrice)
			return 1;
		if (dishPrice < other.dishPrice)
			return -1;
		else
			return 0;
	}// CompareTo

	public Location getLocation() {
		return location;
	}// getLocation
	
	public int getmaxInvited() {
		return maxInvited;
	}// getmaxInvited
	
	public int getminInvited() {
		return minInvited;
	}// getminInvited
	
	public double getfoodRate() {
		return foodRate;
	}// getfoodRate
	
	public double getdishPrice() {
		return dishPrice;
	}// getdishPrice
	
}// class EventHall
