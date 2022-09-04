
public class SeniorDoctor extends Doctor {

	public SeniorDoctor(int id, ER myHospital) { // ����� ���� ����� �� ����
		super(id, myHospital);
	}// constructor

	public void run() {
		while (ERIsOpen()) {
			callPatient();

			if (currentPatient == null)// ��� ����� �� ��� ����
				break;

			
				try {
					Thread.sleep(6 * 1000); // ��� ������ ������� ���� ����� 6 �����
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
			
		} // while

		System.out.println("senior doctor is done");
	}// run

	public void callPatient() { // take a patient from the junior line
		currentPatient = myHospital.getSeniorQueue().extract();
	}// callPatient

}// class SeniorDoctor
