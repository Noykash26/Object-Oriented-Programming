
public class BoundedQueue<T> extends UnboundedQueue<T> {// תור חסום הוא סוג של תור לא חסום עם מגבלה על הכמות
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
		if (!queue.isEmpty()) {// בסוף היום, אם עובד "תקוע" בתוך אחד התורים, המנהל מעיר אותו, ואז העובד צריך
			// לסיים את פעולת ההוצאה מהתור. על מנת שלא תיווצר שגיאה בניסיון להוצאה מתור ריק,
			// נוודא שוב שאכן יש אנשים בתור.
			T item = queue.get(0);
			queue.remove(item);
			notifyAll();
			return item;
		} // if

		return null;// מצב קיצון של סוף יום
	}// extract

}// class BoundedQueue
