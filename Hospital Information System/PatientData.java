
public class PatientData {
	// ������� ��� ���� �� ����� ����� �������� �� ���� ���� �����

	private Note note;
	private int drug;
	private boolean givenByPharmacist;

	public PatientData(Note note, int drug, boolean givenByPharmacist) {
		this.note = note;
		this.drug = drug;
		this.givenByPharmacist = givenByPharmacist;
	}// constructor

	// getters
	public Note getNote() {
		return note;
	}// getNote

	// setters
	public void setDrug(int drug) {// ����� ����� �� ������ ���� �� ���� ������
		this.drug = drug;
	}// setDrug

	public void setGivenByPharmacist(boolean givenByPharmacist) { // ����� ����� ��� ������ ����� �� ���� ���� �� ����
																	// ������
		this.givenByPharmacist = givenByPharmacist;
	}// setGivenByPharmacist

}// class report
