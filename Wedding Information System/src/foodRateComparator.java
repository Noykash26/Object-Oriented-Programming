
public class foodRateComparator implements Comparator {//�������� ������ ��� ��� ��������� ��� ��� �����

	public int compare(Object o1, Object o2) {
		double a = ((EventHall) o1).getfoodRate();
		double b = ((EventHall) o2).getfoodRate();
		return ((int) (a - b));
	}// compare
}// foodRateComparator
