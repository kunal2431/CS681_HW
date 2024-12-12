package umbcs681.uber;

import java.util.LinkedList;

public interface UberApp {
	public Driver BookCab();
	public void CancelCab(Driver dir);
	public void addDriver(Driver dir);
	public boolean removeDriver(Driver dir);
	public LinkedList<Driver> getDrivers();
}
