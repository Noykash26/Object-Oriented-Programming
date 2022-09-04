
public class Prescription extends Note {

	private int drug;
	private boolean givenByPharmacist;

	public Prescription(Note n, int drug) {

		super(n);
		this.drug = drug;
		givenByPharmacist = false;
	}// constructor

	public Prescription(Prescription p) {
		super(p.index, p.patientId, p.bodyHeat, p.bloodPressure, p.state, p.doctorId, p.nurseId);
		this.drug = p.drug;
		this.givenByPharmacist = p.givenByPharmacist;
	}// copy constructor

	// getters
	public int getDrug() {
		return drug;
	}// getDrug

	// setters
	public void setDrug(int drug) {
		this.drug = drug;
	}//setDrug
	
	public void setGivenByPharmacist() {
		this.givenByPharmacist = true;
	}// setGivenByPharmacist

}// class Prescription
