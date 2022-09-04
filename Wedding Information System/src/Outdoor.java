
public class Outdoor extends EventHall {
	private int openMonth;
	private int closeMonth;

	public Outdoor(String name, Location location, int maxInvited, int minInvited, int foodRate, int openMonth,
			int closeMonth) throws noEventInWinterException {// constructor
		super(name, location, maxInvited, minInvited, foodRate);
		dishPrice = getA(foodRate) * getB(location) * getC(openMonth, closeMonth) * 700;

		if (openMonth > closeMonth) // �� �� ������� ���� ������� 11,12 ���� ����������
			throw new noEventInWinterException("There is no option for an event in the winter");

		if (openMonth <= closeMonth) {
			if (closeMonth >= 11)
				throw new noEventInWinterException("There is no option for an event in the winter");
			else {
				this.openMonth = openMonth;
				this.closeMonth = closeMonth;
			} // else
		} // if
	}// Outdoor constructor

	public static double getC(int openMonth, int closeMonth) {// ���� ������� ���� ����
		if (openMonth < closeMonth)
			return (((closeMonth - openMonth) + 1) / 9);
		else
			return 1 / 9;
	}// getC

}// class outdoor
