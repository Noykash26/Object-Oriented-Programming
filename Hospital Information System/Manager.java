import java.util.Vector;

public class Manager extends Worker {

	private Vector <PatientData> report;
	
	public Manager (int id, ER myHospital) {
		super(id, myHospital);
		report = new Vector <PatientData>();
	}//constructor
	
	public void run () {
		try {
			Thread.sleep(myHospital.getTimeToEnd() * 1000); // ����� ���� ����� ��� ����
		} // try

		catch (InterruptedException e) {

		} // catch
		
		while (myHospital.getCurrentNumOfPatient() != 0) {//�� ��� ���� ������� ���� ����� ����� ����� ����, ����� ��� �� ����� ���� ������ ����. ��� �� ��� ����� ��� ��� ������� ������� �� ������ ����� ��� ������� ������ 
						
		}//while
		
		updateWorkers();//������ ��� ��, ������ ��� ���� ���� ��� ��� ���� ������� ������
		
		myHospital.getNurseQueue().wake(); //�� ����� ����� �� �� ������� ��� ������� ������ ����
		myHospital.getJuniorQueue().wake();
		myHospital.getSeniorQueue().wake();
		myHospital.getPharmacistQueue().wake();
		
		//putInSQL();
		
		System.out.println("manager is done");
	}//run
	
	public void updateWorkers () {  
		myHospital.setERIsOpen();
	}//updateWorkers
	
	public void putInSQL () {
		
	}//putInSQL
	
	public synchronized void recivingData (PatientData p) {//���� ����� ������ ������ ������ �� ��� ����� 
		report.add(p);	
	}//recivingData
	
	//getters
	public Vector <PatientData> getReport () {
		return report;
	}//getReport
	
}//class Manager 
