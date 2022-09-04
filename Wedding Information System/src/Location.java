
public class Location {

	private double x;
	private double y;

	public Location(double latitude, double longitude) { // constructor
		this.x = latitude;
		this.y = longitude;
	}// Location

	public double distanceFromCenter() {// ���� ������ �� ����� �������� �� ������ ������
		return (Math.sqrt(Math.pow((this.x - 0), 2) + Math.pow((this.y - 0), 2)));
	}// distanceFromCenter

	public double distance(double x, double y) {// ���� ������ �� ����� �������� ��� ������ ������ ���� ������
		return (Math.sqrt(Math.pow((this.x - x), 2) + Math.pow((this.y - y), 2)));
	}// distance

	public double getx() {
		return this.x;
	}// getx

	public double gety() {
		return this.y;
	}// gety
}// class location
