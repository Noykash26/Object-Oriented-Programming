
public class Indoor extends EventHall {

	private int yearOfFounding;

	public Indoor(String name, Location location, int maxInvited, int minInvited, int foodRate, int yearOfFounding)
			throws ancientEventHallException {
		super(name, location, maxInvited, minInvited, foodRate);
		dishPrice = getA(foodRate) * getB(location) * getD(yearOfFounding) * 700;

		if (yearOfFounding > 1989)
			this.yearOfFounding = yearOfFounding;
		else
			throw new ancientEventHallException("Halls that were built after 1989 are not valid"); // אם אולם האירועים
																									// נבנה לאחר שנת
																									// 1989 נגיב
																									// באגרסיביות
	}// constructor

	public static double getD(int yearOfFounding) {// פקטור שנת בניית האולם
		return (1 - ((2019 - yearOfFounding) / 30));
	}// getD

}
