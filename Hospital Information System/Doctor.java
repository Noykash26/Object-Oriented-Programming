import java.util.HashMap;
import java.util.Map;

abstract class Doctor extends Worker {// ���� ���� ����� ���� ���� ���� ��� ������

	protected Patient currentPatient;
	protected Map<String, Integer> currentPatientData = new HashMap<String, Integer>();

	public Doctor(int id, ER myHospital) {// ���� ����� �� ����
		super(id, myHospital);
	}// constructor

	abstract public void run(); // ����� ������ ���� �� ����� ��������

	public boolean measuresReasonable() { // ��� ������ ������ ���� ������� ��������?
		if (currentPatient.getNote().getState() == -1)
			return false;

		return true;
	}// measuresReasonable

	public void treatmentType() { // ����� ��� ������, ������ �� ��� ����� ���� ��� ��� 1-5 ���� ������
		int state = currentPatient.getNote().getState();
		int treatmentType = state + (int) (1 + (Math.random() * 5));
		currentPatientData.put("treatmentType", treatmentType);
		currentPatient.getNote().setTreatmentType(treatmentType);//����� ��� ��� ������ ����
	}// treatmentType

	public void addIdToNote() {// ����� ����� ����� �� ����� ���� �� �������
		currentPatient.getNote().setDoctorId(this.id);
	}// addToNote

	public void sendCopy() {// ����� ���� ����� ������
		Note n = new Note(currentPatient.getNote());
		PatientData reportForManager = new PatientData(n, -1, false);
		myHospital.getManager().recivingData(reportForManager);
	}// sendCopy

	public boolean needDrug() {// ��� ������� ���� ������?
		double chance = Math.random();

		if (chance < 0.5) // 50% chance
			return true; // give the patient drugs

		else
			return false; // do not give the patient drugs
	}// needDrug

	public void createPrescription() {// ����� ���� ���� �������
		Note n = currentPatient.getNote();// ���� �� ������� ������� �����
		int drug = drugType();// ��� ������ ������ ����� ���
		Prescription p = new Prescription(n, drug);
		currentPatient.setPrescription(p);// ����� ����� ���� �� �������

	}// createPrescription

	private int drugType() {// ����� ��� ������
		int treatmentType = currentPatientData.get("treatmentType");
		int drugType = treatmentType + (int) (1 + (Math.random() * 4));
		currentPatientData.put("drug", drugType);

		return drugType;
	}// drugType

	public void pharmacistLine() { // �� ������� ���� �����, �� ����� ����� ���� ���� �� �����
		myHospital.getPharmacistQueue().insert(currentPatient);
	}// pharmacistLine

	public void nursesLine() {// �� ������ ������ ���� ���� ��������, �� ����� ����� ���� ���� �� ������ �����
		myHospital.getNurseQueue().insert(currentPatient);
	}// nursesLine

	public void endTreatment() {// �� ������� �� ���� �����, ����� ���� ���� �����
		currentPatient.setFinishTreatment();
		myHospital.decreaseCurrentNumOfPatient();
	}// endTreatment
	
}// Doctor class
