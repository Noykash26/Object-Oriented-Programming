import java.util.Collections;

public class PriorityQueue<T extends Priorityable> extends UnboundedQueue<T> {

	public PriorityQueue(ER myHospital) {
		super(myHospital);
	}// constructor

	public synchronized void insert(T t) {
		queue.add(t);
		queue.sort(null);
		Collections.reverse(queue);
		notifyAll();
		
	}// insert

}// class PriorityQueue
