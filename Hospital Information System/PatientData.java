
public class PatientData {
	// אובייקט שבו שמור כל המידע אודות המטופלים של אותו היום במיון

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
	public void setDrug(int drug) {// הרוקח יעדכן את התרופה בדוח של מנהל המשמרת
		this.drug = drug;
	}// setDrug

	public void setGivenByPharmacist(boolean givenByPharmacist) { // הרוקח יעדכן האם התרופה ניתנה על ידיו בדוח של מנהל
																	// המשמרת
		this.givenByPharmacist = givenByPharmacist;
	}// setGivenByPharmacist

}// class report
