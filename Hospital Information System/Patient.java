
public class Patient implements Runnable, Comparable<Patient>, Priorityable {

	private String firstName;
	private String lastName;
	private int age;
	private int height;
	private int weight;
	private int id;
	private int arrival; // ��� ���� �����
	private Note measures; // ��� �������
	private Prescription drugs; // ����
	private boolean finishTreatment;
	private ER myHospital;

	public Patient(String firstName, String lastName, int age, int height, int weight, int id, int arrival,
			ER myHospital) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.height = height;
		this.weight = weight;
		this.id = id;
		this.arrival = arrival;
		this.measures = null; // ��� �� ����� �� ��� �����
		this.drugs = null; // ��� �� ����� �� ��� �����
		this.finishTreatment = false;// ��� �� ����� ���� ������ ������
		this.myHospital = myHospital;
	}// constructor

	public void run() { // ����� ����� ��������
		if (arrival < myHospital.getTimeToEnd()) {
			try {
				Thread.sleep(this.arrival * 1000); // ��� ����� ������ �� ��� ����
			} // try

			catch (InterruptedException e) {

			} // catch

			insertMyselfToLine();
		} // �� ��� ����� �� ������ ���� ���� ����� �����, ��� �� ���� �������� �����
	}// run

	public void insertMyselfToLine() {
		myHospital.getNurseQueue().insert(this);
		myHospital.increaseCurrentNumOfPatient(); // ������ ����� �� ���� ���� ������ ������ �� ���� �������� �����
	}// insertMyselfToLine

	public int getPriority() {// ����� ����� �������������
		return measures.getState();
	}// getPriority

	public int compareTo(Patient other) {// ���� ������� ��� ����� ������� ��� �������
		if (this.getPriority() > other.getPriority())
			return 1;

		else if (this.getPriority() < other.getPriority())
			return -1;

		return 0;
	}// compareTo

	// getters
	public int getId() {
		return id;
	}// getId

	public int getHeight() {
		return height;
	}// getHeight

	public int getWeight() {
		return weight;
	}// getWeight

	public Note getNote() {
		return (new Note(measures));
	}// getNote

	public Prescription getPrescription() {
		return (new Prescription(drugs));
	}// getPrescription

	// setters
	public void setNote(Note measures) {// ���� ����� ������ �� ���� �������
		this.measures = measures;
	}// setNote

	public void setPrescription(Prescription drugs) {// ���� ����� ����� �� ����� �������
		this.drugs = drugs;
	}// setNote

	public void setFinishTreatment() { // ���� ������� �� ������� ��� ������ �� ������ �������
		this.finishTreatment = true;
	}// setFinishTreatment

	
}// class Patient
