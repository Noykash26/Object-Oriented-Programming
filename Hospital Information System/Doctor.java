import java.util.HashMap;
import java.util.Map;

abstract class Doctor extends Worker {// רופא זוטר ורופא בכיר בעלי אותו סדר פעולות

	protected Patient currentPatient;
	protected Map<String, Integer> currentPatientData = new HashMap<String, Integer>();

	public Doctor(int id, ER myHospital) {// קורא לבנאי של האבא
		super(id, myHospital);
	}// constructor

	abstract public void run(); // הבנים יצטרכו לממש את הממשק ראנאייבל

	public boolean measuresReasonable() { // האם המדדים שהאחות נתנה לפציינט הגיוניים?
		if (currentPatient.getNote().getState() == -1)
			return false;

		return true;
	}// measuresReasonable

	public void treatmentType() { // קביעת סוג הטיפול, יסומלץ על ידי הוספת מספר שלם בין 1-5 למצב המטופל
		int state = currentPatient.getNote().getState();
		int treatmentType = state + (int) (1 + (Math.random() * 5));
		currentPatientData.put("treatmentType", treatmentType);
		currentPatient.getNote().setTreatmentType(treatmentType);//עדכון שדה סוג הטיפול בפתק
	}// treatmentType

	public void addIdToNote() {// הוספת תעודת הזהות של הרופא לפתק של הפציינט
		currentPatient.getNote().setDoctorId(this.id);
	}// addToNote

	public void sendCopy() {// שליחת עותק למנהל המשמרת
		Note n = new Note(currentPatient.getNote());
		PatientData reportForManager = new PatientData(n, -1, false);
		myHospital.getManager().recivingData(reportForManager);
	}// sendCopy

	public boolean needDrug() {// האם הפציינט צריך תרופות?
		double chance = Math.random();

		if (chance < 0.5) // 50% chance
			return true; // give the patient drugs

		else
			return false; // do not give the patient drugs
	}// needDrug

	public void createPrescription() {// יצירת מרשם עבור הפציינט
		Note n = currentPatient.getNote();// הפתק של הפציינט שהאחיות הכינו
		int drug = drugType();// סוג התרופה שהרופא החליט לתת
		Prescription p = new Prescription(n, drug);
		currentPatient.setPrescription(p);// הוספת המרשם לשדה של הפציינט

	}// createPrescription

	private int drugType() {// קביעת סוג התרופה
		int treatmentType = currentPatientData.get("treatmentType");
		int drugType = treatmentType + (int) (1 + (Math.random() * 4));
		currentPatientData.put("drug", drugType);

		return drugType;
	}// drugType

	public void pharmacistLine() { // אם הפציינט צריך תרופה, על הרופא לשלוח אותו לתור של הרוקח
		myHospital.getPharmacistQueue().insert(currentPatient);
	}// pharmacistLine

	public void nursesLine() {// אם המדדים שהאחות נתנה אינם הגיוניים, על הרופא לשלוח אותו לתור של האחיות בשנית
		myHospital.getNurseQueue().insert(currentPatient);
	}// nursesLine

	public void endTreatment() {// אם הפציינט לא צריך תרופה, הרופא שולח אותו הביתה
		currentPatient.setFinishTreatment();
		myHospital.decreaseCurrentNumOfPatient();
	}// endTreatment
	
}// Doctor class
