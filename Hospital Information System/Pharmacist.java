import java.util.Vector;

public class Pharmacist extends Worker {

	private Patient currentPatient;

	public Pharmacist(int id, ER myHospital) {
		super(id, myHospital);
	}// constructor

	public void run() {
		while (ERIsOpen()) {
			callPatient();

			if (currentPatient == null)// מצב קיצון של סוף היום
				break;

			try {
				Thread.sleep(((long) (2 + (Math.random() * 2))) * 1000); // זמן הטיפול יסומלץ בין 2-4 שניות
			} // try

			catch (InterruptedException e) {

			} // catch

			updatePrescription();
			sendCopy();
			sendHome();
		} // while
		
		System.out.println("pharmacist is done");
		
	}// run

	public void callPatient() { // take a patient from the pharmacist line
		currentPatient = myHospital.getPharmacistQueue().extract();
	}// callPatient

	public void updatePrescription() { // לציין שהרוקח הביא למטופל את התרופה
		currentPatient.getPrescription().setGivenByPharmacist();
	}// addToPrescription

	public void sendCopy() {// שליחת עותק של המרשם למנהל

		Vector<PatientData> report = myHospital.getManager().getReport(); // הוקטור של המידע אודות המטופלים אצל המנהל
		int size = report.size(); // גודל הוקטור
		for (int i = 0; i < size; i++) {
			if (report.get(i).getNote().getpatientId() == currentPatient.getId()) { // אם תעודת הזהות של המטופל הנוכחי
																					// שווה לתעודת הזהות של המטופל
																					// שפרטיו נמסרו למנהל - על הרוקח
																					// לעדכן את השדות הרלוונטיים
				int drug = currentPatient.getPrescription().getDrug();// שליפה של סוג התרופה מהמרשם של המטופל הנוכחי
				report.get(i).setDrug(drug);// אתחול משתנה התרופה באיבר הרלוונטי של הוקטור
				report.get(i).setGivenByPharmacist(true);// עדכון השדה
				
				currentPatient.getPrescription().setGivenByPharmacist();//שינוי שדה במטופל המקורי
				break;
			} // if
		} // for
	}// sendCopy

	public void sendHome() {
		currentPatient.setFinishTreatment();
		myHospital.decreaseCurrentNumOfPatient();
	}// sendHome

}// Pharmacist class
