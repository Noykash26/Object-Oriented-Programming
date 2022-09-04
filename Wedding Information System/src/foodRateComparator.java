
public class foodRateComparator implements Comparator {//קומפרטור שמשווה בין שני אובייקטים לפי רמת האוכל

	public int compare(Object o1, Object o2) {
		double a = ((EventHall) o1).getfoodRate();
		double b = ((EventHall) o2).getfoodRate();
		return ((int) (a - b));
	}// compare
}// foodRateComparator
