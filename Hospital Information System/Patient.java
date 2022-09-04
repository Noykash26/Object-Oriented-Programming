
public class Patient implements Runnable, Comparable<Patient>, Priorityable {

	private String firstName;
	private String lastName;
	private int age;
	private int height;
	private int weight;
	private int id;
	private int arrival; // זמן הגעה למיון
	private Note measures; // פתק מהאחיות
	private Prescription drugs; // מרשם
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
		this.measures = null; // שדה זה ישונה על ידי האחות
		this.drugs = null; // שדה זה ישונה על ידי הרופא
		this.finishTreatment = false;// שדה זה ישונה כאשר הטיפול יסתיים
		this.myHospital = myHospital;
	}// constructor

	public void run() { // מימוש הממשק ראנאייבל
		if (arrival < myHospital.getTimeToEnd()) {
			try {
				Thread.sleep(this.arrival * 1000); // זמן ההגעה מסומלץ על ידי שינה
			} // try

			catch (InterruptedException e) {

			} // catch

			insertMyselfToLine();
		} // אם זמן ההגעה של המטופל גדול מזמן סגירת המיון, הוא לא נכנס מלכתחילה למיון
	}// run

	public void insertMyselfToLine() {
		myHospital.getNurseQueue().insert(this);
		myHospital.increaseCurrentNumOfPatient(); // המטופל מוסיף את עצמו לתור האחיות ומעדכן את מספר המטופלים במיון
	}// insertMyselfToLine

	public int getPriority() {// מימוש הממשק פריוריטיאייבל
		return measures.getState();
	}// getPriority

	public int compareTo(Patient other) {// ממשק קומפרבל אשר משתמש בהשוואה לפי העדיפות
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
	public void setNote(Note measures) {// עבור האחות שיוצרת את הפתק לפציינט
		this.measures = measures;
	}// setNote

	public void setPrescription(Prescription drugs) {// עבור הרופא שיוצר את המרשם לפציינט
		this.drugs = drugs;
	}// setNote

	public void setFinishTreatment() { // עבור הרופאים או הרוקחים אשר יסיימו את הטיפול בפציינט
		this.finishTreatment = true;
	}// setFinishTreatment

	
}// class Patient
