
public class Note {

	protected int index;
	protected int patientId;
	protected int bodyHeat;
	protected int bloodPressure;
	protected int state;
	protected int doctorId;
	protected int nurseId;
	protected int treatmentType;

	public Note(int index, int patientId, int bodyHeat, int bloodPressure, int state, int doctorId, int nurseId) {
		this.index = index;
		this.patientId = patientId;
		this.bodyHeat = bodyHeat;
		this.bloodPressure = bloodPressure;
		this.state = state;
		this.doctorId = doctorId;
		this.nurseId = nurseId;
		this.treatmentType = -1;
	}// constructor

	public Note(Note n) {
		this.index = n.index;
		this.patientId = n.patientId;
		this.bodyHeat = n.bodyHeat;
		this.bloodPressure = n.bloodPressure;
		this.state = n.state;
		this.doctorId = n.doctorId;
		this.nurseId = n.nurseId;
		this.treatmentType = n.treatmentType;
	}// copy constructor

	// getters
	public int getpatientId() { 
		return patientId;
	}// getId
	
	public int getDoctorId() {
		return doctorId; 
	}//getDoctorId
	
	public int getState() {
		return state;
	}// getState
	
	public int getTreatmentType ( ) {
		return treatmentType;
	}//getTreatmentType

	// setters
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}// setDoctorId
	
	public void setTreatmentType (int treatmentType) {
		this.treatmentType = treatmentType;
	}//setTreatmentType
	
	//for SQL
	public String getIdInString () {
		return Integer.toString(patientId);
	}//getIdInString
	
	public String getDoctorIdInString () {
		return Integer.toString(doctorId);
	}//getDoctorId
	
	public int getTreatment () {
		return treatmentType;
	}//getTreatment
}// class note
