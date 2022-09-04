import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class eventHallMatcher {

	private Vector<Couple> CouplesList;
	private Vector<EventHall> EventHallList;

	public eventHallMatcher(String couples, String invited, String eventHalls)
			throws IOException, missingChoiceForEventHallException, ancientEventHallException, noEventInWinterException,
			exceededQuotaException {// constructor

		this.CouplesList = CoupleBuilder(couples);
		System.out.println(CouplesList);
		this.EventHallList = EventHallBuilder(eventHalls);
		System.out.println(EventHallList);
		invitedListBuilder(invited);
		System.out.println(CouplesList.get(1).getlistOfInvited());

	}// eventHallMatcher

	private Vector<Couple> CoupleBuilder(String couples) throws IOException, missingChoiceForEventHallException {
		Vector<Couple> CouplesList = new Vector<Couple>();
		Vector<String[]> divideVector = new Vector<String[]>();
		divideVector = getText(couples);// ����� �������� ��� ������ �����
		for (int i = 1; i < divideVector.size(); i++) {// ���� �� �� ������� ������ ������ �������
			int id1 = Integer.parseInt(divideVector.get(i)[0]);
			String firstName1 = divideVector.get(i)[1];
			String lastName1 = divideVector.get(i)[2];
			int id2 = Integer.parseInt(divideVector.get(i)[3]);
			String firstName2 = divideVector.get(i)[4];
			String lastName2 = divideVector.get(i)[5];
			double maxPerMeal = Double.parseDouble(divideVector.get(i)[6]);
			double latitude = Double.parseDouble(divideVector.get(i)[7]);
			double longitude = Double.parseDouble(divideVector.get(i)[8]);
			boolean indoor = Boolean.valueOf(divideVector.get(i)[9]);
			boolean outdoor = Boolean.valueOf(divideVector.get(i)[10]);

			Person groom = new Person(id1, firstName1, lastName1, new Location(latitude, longitude));
			Person bride = new Person(id2, firstName2, lastName2, new Location(latitude, longitude));

			CouplesList.add(new Couple(groom, bride, maxPerMeal, indoor, outdoor));
		} // for

		return CouplesList;
	}// CoupleBuilder-������� ��� ����� ����� ������� ����� �� �����

	private Vector<EventHall> EventHallBuilder(String eventHalls)
			throws ancientEventHallException, noEventInWinterException, IOException {
		Vector<EventHall> EventHallList = new Vector<EventHall>();
		Vector<String[]> divideVector = new Vector<String[]>();
		divideVector = getText(eventHalls);// ����� �������� ��� ������ �����
		for (int i = 1; i < divideVector.size(); i++) {// ���� �� �� ������� ������ ������ �������
			String Name = divideVector.get(i)[0];
			double Latitude = Double.parseDouble(divideVector.get(i)[1]);
			double Longitude = Double.parseDouble(divideVector.get(i)[2]);
			int max_amount = Integer.parseInt(divideVector.get(i)[3]);
			int min_amount = Integer.parseInt(divideVector.get(i)[4]);
			int food_level = Integer.parseInt(divideVector.get(i)[5]);

			if (divideVector.get(i)[6].isEmpty()) { // �� �� 6 ���, ����� ����� ����
				int year_build = Integer.parseInt(divideVector.get(i)[8]);
				EventHallList.add(new Indoor(Name, new Location(Latitude, Longitude), max_amount, min_amount,
						food_level, year_build));
			} // if

			else {
				int open_month = Integer.parseInt(divideVector.get(i)[6]);
				int close_month = Integer.parseInt(divideVector.get(i)[7]);
				EventHallList.add(new Outdoor(Name, new Location(Latitude, Longitude), max_amount, min_amount,
						food_level, open_month, close_month));
			} // else

		} // for
		return EventHallList;
	}// EventHallBuilder-������� ��� ����� ����� ������� ����� �� ������ �������
		// ������ �������

	private Vector<String[]> getText(String text) throws IOException {// ������� ��� ��� ����� ����� ������� �����
		Vector<String[]> divideVector = new Vector<String[]>();
		BufferedReader inFile = null;
		try {
			FileReader fr = new FileReader(text);
			inFile = new BufferedReader(fr);
			String str;
			while ((str = inFile.readLine()) != null) {
				String[] divied = str.split("\t");
				divideVector.add(divied);
			}
		} // try

		catch (FileNotFoundException exception) {
			System.out.println("The file " + text + " was not found.");
		} // catch

		catch (IOException exception) {
			System.out.println(exception);
		} // catch

		finally {
			inFile.close();
		} // finally

	
		return divideVector;
	}// getText

	private void invitedListBuilder(String invited) throws IOException, exceededQuotaException {
		Vector<String[]> divideVector = new Vector<String[]>();
		divideVector = getText(invited);// ����� �������� ��� ������ �����
		for (int i = 1; i < divideVector.size(); i++) {// ���� �� �� ������� ������ ������ �������
			int id = Integer.parseInt(divideVector.get(i)[0]);
			String firstName = divideVector.get(i)[1];
			String lastName = divideVector.get(i)[2];
			double latitude = Double.parseDouble(divideVector.get(i)[3]);
			double longitude = Double.parseDouble(divideVector.get(i)[4]);
			int invitedBy = Integer.parseInt(divideVector.get(i)[5]);

			Person guest = new Person(id, firstName, lastName, new Location(latitude, longitude));
			for (int j = 1; i < CouplesList.size(); j++) {// ���� ����� �� ����� �� �����
				if ((CouplesList.get(i).getgroom().getid() == invitedBy)
						|| (CouplesList.get(i).getbride().getid() == invitedBy))
					CouplesList.get(i).addListOfInvited(guest);
			} // inner for
		} // outerfor
	}// invitedListBuilder-������� ��� ����� ����� ����� ��� ��� ����� �������

	public static double avgDistanceOfListFromLocation(Vector<? extends Locatable> list, Locatable o1) {// ������� ���
																										// ����� ��
																										// ����� ������
																										// �� ���� ���
																										// ����� �������
																										// ���� ����� ��
																										// �����
		double sum = 0;
		for (int i = 0; i < list.size(); i++) {
			double temp = list.get(i).getLocation().distance(o1.getLocation().getx(), o1.getLocation().gety());
			sum += temp;
		} // for
		return (sum / list.size());
	}// avgDistanceOfListFromLocation

	public Vector<EventHall> potentialEventHalls(Couple c) {// ���� ������ ������
		Vector<EventHall> results = new Vector<EventHall>();
		for (int i = 0; i < EventHallList.size(); i++) {
			if (c.getnumOfGuests() < EventHallList.get(i).getmaxInvited()
					&& c.getnumOfGuests() > EventHallList.get(i).getminInvited()) {// ����� ����� �������� �����
																					// �������� �� ����
				results.add(EventHallList.get(i));
				continue;
			} // if

			if (EventHallList.get(i).getdishPrice() < c.getmaxPerMeal()) {// ���� ��� ���� ������ ����� �������� �� ����
				results.add(EventHallList.get(i));
				continue;
			} // if

			if ((c.getindoor() == true) && (EventHallList.get(i) instanceof Indoor)) {// �� ���� ����� ���� �����
				results.add(EventHallList.get(i));
				continue;
			} // if

			if ((c.getoutdoor() == true) && (EventHallList.get(i) instanceof Outdoor)) {// �� ���� ����� �� �������
				results.add(EventHallList.get(i));
				continue;
			} // if
		} // for

		return results;
	}// potentialEventHalls

	public void printAvgDistanceOfCouples() {// ���� ������� �� ����� ������� ��������� ��� ������ ������ ��� �����
												// ������ �� �������� ��� ��� ��������
		for (int i = 0; i < CouplesList.size(); i++) {// ����� ���� �� ����� ������
			Person groom = CouplesList.get(i).getgroom();
			Person bride = CouplesList.get(i).getbride();
			System.out.println("For the couple " + groom + " and " + bride + ":");

			Vector<EventHall> results;
			results = potentialEventHalls(CouplesList.get(i));// ����� ����� �� ������ ����������� ���� ���� �����

			for (int j = 0; j < results.size(); j++) {
				EventHall hall = results.get(i); // ����� ����� ������ �� ������� ����������� ������� ���� ���
				double distance = avgDistanceOfListFromLocation(CouplesList.get(i).getlistOfInvited(), hall);// ����
																												// �����
																												// �� ��
																												// ��������
																												// ��
																												// ����
																												// ������
																												// �����
				System.out.println("The average distance of the guests from event hall " + hall + " is " + distance);
			} // innerfor
		} // outerfor
	}// printAvgDistanceOfCouples

	public static Comparable minInList(Vector<? extends Comparable> list) {// ������� ��� ����� ����� ����� �� ������
																			// ��� ������ ������� �� ����� ��������
																			// ������
		Comparable min = list.elementAt(0);
		for (int i = 1; i < list.size(); i++) {
			int temp = list.get(i).compareTo(min);
			if (temp == -1)
				min = list.get(i);
		} // for
		return min;
	}// minInList

	public void cheapestEventHall() {
		for (int i = 0; i < CouplesList.size(); i++) {// ����� ���� �� ����� ������
			Person groom = CouplesList.get(i).getgroom();
			Person bride = CouplesList.get(i).getbride();

			Vector<EventHall> results;
			results = potentialEventHalls(CouplesList.get(i));// ����� ����� �� ������ ����������� ���� ���� �����

			if (results.size() != 0) {
				EventHall min = (EventHall) minInList(results);
				System.out.println("Cheapest event hall for " + groom + " and " + bride + " is " + min);
			} // if the list of the potential eventhalls is not empty

			else
				System.out.println("No match for couple " + groom + " and " + bride);
		} // for
	}// cheapestEventHall

	public static <T> T MaxInList (Vector <T> list, Comparator c){
		T max = list.elementAt(0);
		for (int i = 1; i < list.size(); i++) {
			if(c.compare(list.get(i), max) >= 0)
				max = list.get(i);			
		} // for
		return max;
	}//MaxInList
	
	public void bestFoodEventHall() {
		for (int i = 0; i < CouplesList.size(); i++) {// ����� ���� �� ����� ������
			Person groom = CouplesList.get(i).getgroom();
			Person bride = CouplesList.get(i).getbride();

			Vector<EventHall> results;
			results = potentialEventHalls(CouplesList.get(i));// ����� ����� �� ������ ����������� ���� ���� �����

			if (results.size() != 0) {
				EventHall maxFoodRate = MaxInList(results, new foodRateComparator());
				System.out.println("The recommended event hall for  " + groom + " and " + bride + " is " + maxFoodRate);
			} // if the list of the potential eventhalls is not empty

			else
				System.out.println("No match for couple " + groom + " and " + bride);
		} // for
	}//bestFoodEventHall
	
}// class eventHallMatcher
