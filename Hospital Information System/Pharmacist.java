import java.util.Vector;

public class Pharmacist extends Worker {

	private Patient currentPatient;

	public Pharmacist(int id, ER myHospital) {
		super(id, myHospital);
	}// constructor

	public void run() {
		while (ERIsOpen()) {
			callPatient();

			if (currentPatient == null)// ��� ����� �� ��� ����
				break;

			try {
				Thread.sleep(((long) (2 + (Math.random() * 2))) * 1000); // ��� ������ ������ ��� 2-4 �����
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

	public void updatePrescription() { // ����� ������ ���� ������ �� ������
		currentPatient.getPrescription().setGivenByPharmacist();
	}// addToPrescription

	public void sendCopy() {// ����� ���� �� ����� �����

		Vector<PatientData> report = myHospital.getManager().getReport(); // ������ �� ����� ����� �������� ��� �����
		int size = report.size(); // ���� ������
		for (int i = 0; i < size; i++) {
			if (report.get(i).getNote().getpatientId() == currentPatient.getId()) { // �� ����� ����� �� ������ ������
																					// ���� ������ ����� �� ������
																					// ������ ����� ����� - �� �����
																					// ����� �� ����� ����������
				int drug = currentPatient.getPrescription().getDrug();// ����� �� ��� ������ ������ �� ������ ������
				report.get(i).setDrug(drug);// ����� ����� ������ ����� �������� �� ������
				report.get(i).setGivenByPharmacist(true);// ����� ����
				
				currentPatient.getPrescription().setGivenByPharmacist();//����� ��� ������ ������
				break;
			} // if
		} // for
	}// sendCopy

	public void sendHome() {
		currentPatient.setFinishTreatment();
		myHospital.decreaseCurrentNumOfPatient();
	}// sendHome

}// Pharmacist class
