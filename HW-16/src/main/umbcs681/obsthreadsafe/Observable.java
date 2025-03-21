package umbcs681.obsthreadsafe;

import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class Observable<T> {
	private ConcurrentLinkedQueue<Observer<T>> observers = new ConcurrentLinkedQueue<>();
	
	public void addObserver(Observer<T> o) {
		observers.add(o);
	}

	public void clearObservers() {
		observers.clear();
	}
	public ConcurrentLinkedQueue<Observer<T>> getObservers(){
		return observers;
	}
	
	public int countObservers() {
		return observers.size();
		
	}
	public void removeObserver(Observer<T> o) {
		observers.remove(o);
	}

	public void notifyObservers(T event) {
		/*for(Observer<T> ob: observers) {
			ob.update(this, event);
		}*/
		observers.forEach( (observer)->{observer.update(this, event);} );
	}
	
}
