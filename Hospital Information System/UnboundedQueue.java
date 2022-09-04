import java.util.Queue;
import java.util.Vector;

public class UnboundedQueue<T> {

	protected Vector<T> queue;
	protected ER myHospital;

	public UnboundedQueue(ER myHospital) {
		queue = new Vector<T>();
		this.myHospital = myHospital;
	}// constructor

	public synchronized void insert(T t) {
		queue.add(t);
		notifyAll();
	}// insert

	public synchronized T extract() {
		while (queue.isEmpty() && myHospital.getERIsOpen()) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}//while
		if (!queue.isEmpty()) {// ���� ����, �� ���� "����" ���� ��� ������, ����� ���� ����, ��� ����� ����
								// ����� �� ����� ������ �����. �� ��� ��� ������ ����� ������� ������ ���� ���,
								// ����� ��� ���� �� ����� ����.
			T item = queue.get(0);
			queue.remove(item);
			return item;
		} // if
		
		return null;// ��� ����� �� ��� ���
		
		}// extract
	
	public Vector <T> getQueue () {
		return queue;
	}//getQueue
	
	public synchronized void wake() {// ������� ������ �����, ��� ���� �� �� ������� �������� ��� ������ (��� �� ����
						// ��� ����)
		notifyAll();
	}// wake

}// class UnboundedQueue
