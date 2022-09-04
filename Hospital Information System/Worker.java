
abstract class Worker implements Runnable {

	protected int id;
	protected ER myHospital;

	public Worker(int id, ER myHospital) {
		this.id = id;
		this.myHospital = myHospital;
	}// constructor

	abstract public void run();

	public boolean ERIsOpen() {
		return myHospital.getERIsOpen();
	}// dayIsOver
}// class Worker
