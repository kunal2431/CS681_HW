package umbcs681.uber;

import java.util.LinkedList;

public class ThreadUnsafeUber implements UberApp{

	private LinkedList<Driver> drivers;

	public ThreadUnsafeUber(){
		drivers = new LinkedList<Driver>();
	}

	public Driver BookCab() {
		if(drivers.isEmpty()){
			System.out.println("No Drivers Available");
			return null;
		}
		Driver dir = drivers.getFirst();
		drivers.removeFirst();
		return dir;
	}

	public void CancelCab(Driver dir) {
		drivers.add(dir);
		System.out.println("Cab Cancelled");
	}

	public void addDriver(Driver dir) {
		drivers.add(dir);
	}

	public boolean removeDriver(Driver dir) {
		if(drivers.contains(dir)){
			drivers.remove(dir);
			return true;
		}
		return false;
	}

	public LinkedList<Driver> getDrivers() {
		return drivers;
	}
}
