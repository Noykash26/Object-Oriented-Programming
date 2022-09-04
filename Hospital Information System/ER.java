import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class ER {

	// המנהל של חדר המיון
	private Manager manager;

	// וקטור מטופלים
	private Vector<Patient> PatientsList;

	// וקטור של טרדים
	private Vector<Thread> threads;

	// תורים במיון
	private UnboundedQueue<Patient> NurseQueue;
	private UnboundedQueue<Patient> PharmacistQueue;
	private BoundedQueue<Patient> JuniorQueue;
	private PriorityQueue<Patient> SeniorQueue;

	// קלט מהמשתמש
	//private int numOfNurse;// קלט מהמשתמש על מספר האחיות
	private int timeToEnd;// קלט מהמשתמש על שעת סגירת המיון

	// משתני מחלקה
	private int currentNumOfPatient; // מספר המטופלים אשר נמצאים בתוך המיון
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

	private void initializeThreadVector() {// פונקציה אשר מאתחלת וקטור של טרדים
		threads = new Vector<Thread>();
	}// initializeThreadVector

	private void initializePatients(String patients) throws IOException {// פונקציה אשר מאתחלת את המטופלים לוקטור של
																			// מטופלים ולאחר מכן הופכת אותם לטרדים
																			// ומוסיפה אותם לוקטור של טרדים
		PatientsList = PatientBuilder(patients);
		for (int i = 0; i < PatientsList.size(); i++) {// מעבר על וקטור של מטופלים
			Patient p = PatientsList.get(i);// חילוץ של מטופל
			Thread t = new Thread(p);// הפיכת המטופל לטרד
			threads.add(t);// הוספת הטרד לוקטור של טרדים
		} // for
	}// initializePatients

	private Vector<Patient> PatientBuilder(String patients) throws IOException {// פונקציה אשר קוראת מקובץ ומחזירה וקטור
																				// של פציינטים
		Vector<Patient> PatientList = new Vector<Patient>();
		Vector<String[]> divideVector = new Vector<String[]>();
		divideVector = getText(patients);// קריאה לפונקצית עזר לקריאה מטקסט
		for (int i = 1; i < divideVector.size(); i++) {// מעבר על כל האיברים בוקטור ושמירה במשתנים
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

	private Vector<String[]> getText(String text) throws IOException {// פונקצית עזר אשר קוראת מטקסט ומחזירה וקטור
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
		int maxId = maxId(PatientsList);// על מנת לא ליצור כפל בתעודות הזהות, מצאנו את תעודת הזהות המקסימלית והמשכנו משם

		// אתחול אחיות
		for (int i = 1; i <= numOfNurse; i++) {
			Nurse n = new Nurse(maxId + i, this);// אתחול מופע של אחות
			Thread t1 = new Thread(n);// הפיכתה לטרד
			threads.add(t1);// הוספת הטרד לוקטור של טרדים
		} // for

		// אתחול רופאים זוטרים
		maxId += numOfNurse; // עדכון הערך של תעודת הזהות המקסימלית
		for (int i = 1; i <= 3; i++) {
			JuniorDoctor j = new JuniorDoctor(maxId + i, this);
			Thread t2 = new Thread(j);
			threads.add(t2);
		} // for

		// אתחול רופא בכיר
		maxId += 3;
		SeniorDoctor s = new SeniorDoctor(maxId + 1, this);
		Thread t3 = new Thread(s);
		threads.add(t3);

		// אתחול רוקחים
		maxId += 1;
		for (int i = 1; i <= 2; i++) {
			Pharmacist p = new Pharmacist(maxId + i, this);
			Thread t4 = new Thread(p);
			threads.add(t4);
		} // for

		// אתחול מנהל
		maxId += 2;
		manager = new Manager(maxId + 1, this);
		Thread t5 = new Thread(manager);
		threads.add(t5);

	}// initializedWorkers

	private int maxId(Vector<Patient> patients) {// פונקציה אשר מחזירה את מספר תעודת הזהות הגדול ביותר מבין המטופלים
													// במערכת
		int max = 0;
		for (int i = 0; i < patients.size(); i++) {
			if (patients.get(i).getId() > max)
				max = patients.get(i).getId();
		} // for

		return max;
	}// maxId

	private void initializeQueue() {// פונקציה אשר מאתחלת את התורים במיון
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
	
	public void setERIsOpen() {// לשימוש המנהל
		ERIsOpen = false;
	}// setDayIsOver

}// class ER
