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
		if (!queue.isEmpty()) {// בסוף היום, אם עובד "תקוע" בתוך אחד התורים, המנהל מעיר אותו, ואז העובד צריך
								// לסיים את פעולת ההוצאה מהתור. על מנת שלא תיווצר שגיאה בניסיון להוצאה מתור ריק,
								// נוודא שוב שאכן יש אנשים בתור.
			T item = queue.get(0);
			queue.remove(item);
			return item;
		} // if
		
		return null;// מצב קיצון של סוף יום
		
		}// extract
	
	public Vector <T> getQueue () {
		return queue;
	}//getQueue
	
	public synchronized void wake() {// פונקציה לשימוש המנהל, הוא יעיר את כל העובדים שממתינים בכל התורים (תור לא חסום
						// הוא האבא)
		notifyAll();
	}// wake

}// class UnboundedQueue
