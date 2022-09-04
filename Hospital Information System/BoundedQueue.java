
public class BoundedQueue<T> extends UnboundedQueue<T> {// ��� ���� ��� ��� �� ��� �� ���� �� ����� �� �����
	private int maxSize;

	public BoundedQueue(ER myHospital, int maxSize) {
		super(myHospital);
		this.maxSize = maxSize;
	}// constructor

	public synchronized void insert(T t) {
		while (queue.size() >= maxSize)
			try {
				wait();
			} catch (InterruptedException e) {
			}

		queue.add(t);
		notifyAll();
	}// insert

	public synchronized T extract() {
		while (queue.isEmpty() && myHospital.getERIsOpen()) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		} // while
		if (!queue.isEmpty()) {// ���� ����, �� ���� "����" ���� ��� ������, ����� ���� ����, ��� ����� ����
			// ����� �� ����� ������ �����. �� ��� ��� ������ ����� ������� ������ ���� ���,
			// ����� ��� ���� �� ����� ����.
			T item = queue.get(0);
			queue.remove(item);
			notifyAll();
			return item;
		} // if

		return null;// ��� ����� �� ��� ���
	}// extract

}// class BoundedQueue
