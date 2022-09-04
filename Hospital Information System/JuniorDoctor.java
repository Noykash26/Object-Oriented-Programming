public class JuniorDoctor extends Doctor {

	public JuniorDoctor(int id, ER myHospital) { // הבנאי קורא לבנאי של הסבא
		super(id, myHospital);
	}// constructor

	public void run() {
		while (ERIsOpen()) {
			callPatient();
			if (currentPatient == null)// מצב קיצון של סוף היום
				break;

			if (measuresReasonable()) {// אם המדדים הגיוניים הרופא יתחיל את הטיפול בפציינט
				try {
					Thread.sleep((currentPatient.getNote().getState()) * 1000); // זמן הטיפול יסומלץ לפי חומרת מצבו של
																				// הפציינט
				} // try

				catch (InterruptedException e) {

				} // catch

				treatmentType();
				addIdToNote();
				sendCopy();
				if (needDrug()) {
					createPrescription();
					pharmacistLine();
				} // if doctor has decided to give a drug to the patient
				else {
					endTreatment();
				}
				} // if

			else { // if not, send the patient to the nurses line to take measures again
				nursesLine();
			} // else
		} // while

		System.out.println("junior doctor is done");
	}// run

	public void callPatient() { // take a patient from the junior line
		currentPatient = myHospital.getJuniorQueue().extract();
	}// callPatient

}// class JuniorDoctor
