package umbcs681.obsthreadsafe;

public interface Observer<T> {
	public void update(Observable<T> sender, T event);
}
