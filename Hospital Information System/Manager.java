import java.util.Vector;

public class Manager extends Worker {

	private Vector <PatientData> report;
	
	public Manager (int id, ER myHospital) {
		super(id, myHospital);
		report = new Vector <PatientData>();
	}//constructor
	
	public void run () {
		try {
			Thread.sleep(myHospital.getTimeToEnd() * 1000); // המנהל הולך לישון לכל היום
		} // try

		catch (InterruptedException e) {

		} // catch
		
		while (myHospital.getCurrentNumOfPatient() != 0) {//כל עוד ישנם מטופלים בחדר המיון למרות שהיום נגמר, המנהל עוד לא יעדכן שבית החולים נסגר. זאת על מנת למנוע מצב שבו העובדים מפסיקים את עבודתם למרות שיש מטופלים בתורים 
						
		}//while
		
		updateWorkers();//בנקודת זמן זו, יודעים שגם היום נגמר וגם אין יותר מטופלים בתורים
		
		myHospital.getNurseQueue().wake(); //על המנהל להעיר את כל העובדים אשר ממתינים שיעירו אותם
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
	
	public synchronized void recivingData (PatientData p) {//מידע אודות פציינט יתווסף לוקטור על ידי הרופא 
		report.add(p);	
	}//recivingData
	
	//getters
	public Vector <PatientData> getReport () {
		return report;
	}//getReport
	
}//class Manager 
