public class JuniorDoctor extends Doctor {

	public JuniorDoctor(int id, ER myHospital) { // ����� ���� ����� �� ����
		super(id, myHospital);
	}// constructor

	public void run() {
		while (ERIsOpen()) {
			callPatient();
			if (currentPatient == null)// ��� ����� �� ��� ����
				break;

			if (measuresReasonable()) {// �� ������ �������� ����� ����� �� ������ �������
				try {
					Thread.sleep((currentPatient.getNote().getState()) * 1000); // ��� ������ ������ ��� ����� ���� ��
																				// �������
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
