import java.util.*;

public class Nurse extends Worker {

	private static int noteIndex = 1;// ����� ����� ����� �� ������ ����, ����� ��� ������

	private Patient currentPatient; // ������� ������ ������ ����� ��
	private Map<String, Integer> currentPatientData = new HashMap<String, Integer>();

	public Nurse(int id, ER myHospital) { // ����� ���� ����� �� ����
		super(id, myHospital);
	}// constructor

	public void run() {
		while (ERIsOpen()) {
			callPatient();

			if (currentPatient == null) {// ��� ����� �� ��� ����
				break;
			}//if
			
			try {
				Thread.sleep(((long) (1 + (Math.random() * 3))) * 1000); // ��� ������ ������ ��� 1-3 �����
			} // try

			catch (InterruptedException e) {

			} // catch

			takeMeasures();
			stateOfPatient();
			createNote();

			if (currentPatientData.get("state") > 6)
				seniorLine(); // �� ����� ���� �� ������� ����� �6, ���� ������ ���� �� ����� �����
			else
				juniorLine(); // ����, ���� ������ ���� �� ����� �����
		} // while

		System.out.println("nurse is done");
	}// run

	public void callPatient() {// take a patient from the nurses line
		currentPatient = myHospital.getNurseQueue().extract();
	}// callPatient

	public void takeMeasures() {// take blood pressure, body heat, weight and height
		currentPatientData.put("bloodPressure", takeBloodPressure());
		currentPatientData.put("bodyHeat", takeBodyHeat());
		currentPatientData.put("weight", takeWeight());
		currentPatientData.put("height", takeHeight());
	}// takeMeasures

	private int takeBloodPressure() { // ������� ��� ������ ��� ��
		double chance = Math.random();

		if (chance < 0.1) { // 10% chance
			return ((int) (Math.random() * 90)); // blood pressure < 90
		} // if

		if (chance < 0.9) {// 80% chance
			return (90 + (int)( (Math.random() * 50))); // 90 < blood pressure < 140
		} // if

		else { // 10% chance
			return (140 + (int)((Math.random() * 50))); // blood pressure > 140
		} // else

	}// takeBloodPressure

	private int takeBodyHeat() {// ������� ��� ������ ���
		double chance = Math.random();
		if (chance < 0.1) { // 10% chance
			return (int) (Math.random() * 38); // Body Heat < 38
		} // if

		if (chance < 0.8) {// 70% chance
			return (38 + (int) (Math.random())); // 38 < Body Heat < 39
		} // else

		else { // 20% chance
			return (39 + (int) (Math.random())); // 39 < Body Heat < 40
		} // else

	}// takeBodyHeat

	private int takeWeight() {// ������� ��� ������ ����
		return currentPatient.getWeight();
	}// takeWeight

	private int takeHeight() {// ������� ��� ������ ����
		return currentPatient.getHeight();
	}// takeHeight

	private int bloodPressureValue() { // calculated blood pressure value
		int bloodPressure = currentPatientData.get("bloodPressure");
		if (bloodPressure > 90 && bloodPressure < 140)
			return 4;

		else
			return 2;
	}// bloodPressureValue

	private int bodyHeatValue() { // calculated body heat value
		int bodyHeat = currentPatientData.get("bodyHeat");
		if (bodyHeat > 38 && bodyHeat < 39)
			return 0;

		else
			return 3;
	}// bloodPressureValue

	private int WeightHeightValue() { // calculated weight and height value
		int height = currentPatientData.get("height");
		int weight = currentPatientData.get("weight");
		return ((weight / height) * 4);

	}// WeightHeightValue

	public void stateOfPatient() {// calculate the state of the patient according to the measures
		int state = bloodPressureValue() + bodyHeatValue() + WeightHeightValue();

		if (state > 10)
			currentPatientData.put("state", 10);

		else
			currentPatientData.put("state", state);
	}// stateOfPatient

	public synchronized void createNote() {
		int index = noteIndex;
		int patientId = currentPatient.getId();
		int bodyHeat = currentPatientData.get("bodyHeat");
		int bloodPressure = currentPatientData.get("bloodPressure");
		int state = currentPatientData.get("state");
		int doctorId = -1;
		int nurseId = this.id;

		if (state < 6) { // �� ����� ���� ���� �6
			double chance = Math.random();
			if (chance < 0.1) // ������� �� 10% ����� ���� ������ ���� �� �������
				state = -1;
		} // if

		Note n = new Note(index, patientId, bodyHeat, bloodPressure, state, doctorId, nurseId);

		noteIndex++;

		currentPatient.setNote(n); // ����� ���� �������
	}// createNote

	public void seniorLine() {// send the patient to the line of the senior doctor
		myHospital.getSeniorQueue().insert(currentPatient);
	}// seniorLine

	public void juniorLine() {// send the patient to the line of the junior doctor
		myHospital.getJuniorQueue().insert(currentPatient);
	}// juniorLine

}// class Nurse
