package umbcs681.uber;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.LinkedList;

public class ThreadSafeUber implements UberApp{

	private ReentrantLock lock = new ReentrantLock();

	private LinkedList<Driver> drivers;

	public ThreadSafeUber(){
		drivers = new LinkedList<Driver>();
	}

	public Driver BookCab() {
		lock.lock();
		try{
			if(drivers.isEmpty()){
				System.out.println("No Drivers Available");
				return null;
			}
			Driver dir = drivers.getFirst();
			drivers.removeFirst();
			return dir;
		}finally{
			lock.unlock();
			System.out.println("Lock released");
		}
	}

	public void CancelCab(Driver dir) {
		lock.lock();
		try{
			System.out.println("Lock obtained");
			drivers.add(dir);
			System.out.println("Cab Cancelled");
		}finally{
			lock.unlock();
			System.out.println("Lock released");
		}

	}

	public void addDriver(Driver dir) {
		lock.lock();
		try{
			System.out.println("Lock obtained");
			drivers.add(dir);
		}finally{
			lock.unlock();
			System.out.println("Lock released");
		}
	}

	public boolean removeDriver(Driver dir) {
		lock.lock();
		try{
			System.out.println("Lock obtained");
			if(drivers.contains(dir)){
				drivers.remove(dir);
				return true;
			}
			return false;
		}finally{
			lock.unlock();
			System.out.println("Lock released");
		}
	}

	public LinkedList<Driver> getDrivers() {
		lock.lock();
		try{
			System.out.println("Lock obtained");
			return drivers;
		}finally{
			lock.unlock();
			System.out.println("Lock released");
		}
	}
}
