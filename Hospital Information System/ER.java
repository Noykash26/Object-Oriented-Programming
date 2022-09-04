import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class ER {

	// ����� �� ��� �����
	private Manager manager;

	// ����� �������
	private Vector<Patient> PatientsList;

	// ����� �� �����
	private Vector<Thread> threads;

	// ����� �����
	private UnboundedQueue<Patient> NurseQueue;
	private UnboundedQueue<Patient> PharmacistQueue;
	private BoundedQueue<Patient> JuniorQueue;
	private PriorityQueue<Patient> SeniorQueue;

	// ��� �������
	//private int numOfNurse;// ��� ������� �� ���� ������
	private int timeToEnd;// ��� ������� �� ��� ����� �����

	// ����� �����
	private int currentNumOfPatient; // ���� �������� ��� ������ ���� �����
	private Boolean ERIsOpen;

	public ER(String patients, int numOfNurse, int timeToEnd) throws IOException {
		initializeThreadVector();
		initializePatients(patients);
		initializedWorkers(numOfNurse);
		initializeQueue();

		this.timeToEnd = timeToEnd;
		currentNumOfPatient = 0;
		ERIsOpen = true;
	}// constructor

	public void startER() {
		for (int i = 0; i < threads.size(); i++) {
			threads.get(i).start();
		} // for
	}// startER

	private void initializeThreadVector() {// ������� ��� ������ ����� �� �����
		threads = new Vector<Thread>();
	}// initializeThreadVector

	private void initializePatients(String patients) throws IOException {// ������� ��� ������ �� �������� ������ ��
																			// ������� ����� ��� ����� ���� ������
																			// ������� ���� ������ �� �����
		PatientsList = PatientBuilder(patients);
		for (int i = 0; i < PatientsList.size(); i++) {// ���� �� ����� �� �������
			Patient p = PatientsList.get(i);// ����� �� �����
			Thread t = new Thread(p);// ����� ������ ����
			threads.add(t);// ����� ���� ������ �� �����
		} // for
	}// initializePatients

	private Vector<Patient> PatientBuilder(String patients) throws IOException {// ������� ��� ����� ����� ������� �����
																				// �� ��������
		Vector<Patient> PatientList = new Vector<Patient>();
		Vector<String[]> divideVector = new Vector<String[]>();
		divideVector = getText(patients);// ����� �������� ��� ������ �����
		for (int i = 1; i < divideVector.size(); i++) {// ���� �� �� ������� ������ ������ �������
			String firstName = divideVector.get(i)[0];
			String lastName = divideVector.get(i)[1];
			int age = Integer.parseInt(divideVector.get(i)[2]);
			int height = Integer.parseInt(divideVector.get(i)[3]);
			int weight = Integer.parseInt(divideVector.get(i)[4]);
			int id = Integer.parseInt(divideVector.get(i)[5]);
			int arrivalTime = Integer.parseInt(divideVector.get(i)[6]);

			Patient p = new Patient(firstName, lastName, age, height, weight, id, arrivalTime, this);

			PatientList.add(p);
		} // for

		return PatientList;
	}// PatientBuilder

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

	private void initializedWorkers(int numOfNurse) {
		int maxId = maxId(PatientsList);// �� ��� �� ����� ��� ������� �����, ����� �� ����� ����� ��������� ������� ���

		// ����� �����
		for (int i = 1; i <= numOfNurse; i++) {
			Nurse n = new Nurse(maxId + i, this);// ����� ���� �� ����
			Thread t1 = new Thread(n);// ������ ����
			threads.add(t1);// ����� ���� ������ �� �����
		} // for

		// ����� ������ ������
		maxId += numOfNurse; // ����� ���� �� ����� ����� ���������
		for (int i = 1; i <= 3; i++) {
			JuniorDoctor j = new JuniorDoctor(maxId + i, this);
			Thread t2 = new Thread(j);
			threads.add(t2);
		} // for

		// ����� ���� ����
		maxId += 3;
		SeniorDoctor s = new SeniorDoctor(maxId + 1, this);
		Thread t3 = new Thread(s);
		threads.add(t3);

		// ����� ������
		maxId += 1;
		for (int i = 1; i <= 2; i++) {
			Pharmacist p = new Pharmacist(maxId + i, this);
			Thread t4 = new Thread(p);
			threads.add(t4);
		} // for

		// ����� ����
		maxId += 2;
		manager = new Manager(maxId + 1, this);
		Thread t5 = new Thread(manager);
		threads.add(t5);

	}// initializedWorkers

	private int maxId(Vector<Patient> patients) {// ������� ��� ������ �� ���� ����� ����� ����� ����� ���� ��������
													// ������
		int max = 0;
		for (int i = 0; i < patients.size(); i++) {
			if (patients.get(i).getId() > max)
				max = patients.get(i).getId();
		} // for

		return max;
	}// maxId

	private void initializeQueue() {// ������� ��� ������ �� ������ �����
		NurseQueue = new UnboundedQueue<Patient>(this);
		PharmacistQueue = new UnboundedQueue<Patient>(this);
		JuniorQueue = new BoundedQueue<Patient>(this, 8);
		SeniorQueue = new PriorityQueue<Patient>(this);
	}// initializeQueue

	// getters
	public Manager getManager() {
		return manager;
	}// getManager

	public UnboundedQueue<Patient> getNurseQueue() {
		return NurseQueue;
	}// getNurseQueue

	public UnboundedQueue<Patient> getPharmacistQueue() {
		return PharmacistQueue;
	}// getPharmacistQueue

	public BoundedQueue<Patient> getJuniorQueue() {
		return JuniorQueue;
	}// getJuniorQueue

	public PriorityQueue<Patient> getSeniorQueue() {
		return SeniorQueue;
	}// getSeniorQueue

	public int getTimeToEnd() {
		return timeToEnd;
	}// getTimeToEnd

	public synchronized int getCurrentNumOfPatient() {
		return currentNumOfPatient;
	}// getCurrentNumOfPatient

	public boolean getERIsOpen() {
		return ERIsOpen;
	}// getDayIsOver

	// setters
	public synchronized void increaseCurrentNumOfPatient() {
		currentNumOfPatient++;
	}//increaseCurrentNumOfPatient
	
	public synchronized void decreaseCurrentNumOfPatient() {
		currentNumOfPatient--;
	}//decreaseCurrentNumOfPatient
	
	public void setERIsOpen() {// ������ �����
		ERIsOpen = false;
	}// setDayIsOver

}// class ER
