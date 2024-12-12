package umbcs681.uber;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.atomic.*;

public class BookCabRunnable implements Runnable{
	private UberApp account;

	private AtomicBoolean done = new AtomicBoolean(false);

	private AtomicReference<Driver> dir;

	public BookCabRunnable(UberApp account) {
		this.account = account;
	}

	public void setDone(){
		done.set(true);
	}


	public void run(){
		for(int i =0; i < 1; i++){
			if (done.get()) {
				System.out.println("Stopped BookCab");
				break;
			}
			dir = new AtomicReference<Driver>(account.BookCab());
			if(dir.get() == null)
				System.out.println("No drivers/cab Available");
			else{
				System.out.println("Cab Booked; driver name is: " + dir.get().getName());
			}
			try{
				Thread.sleep(2);
			}
			catch (InterruptedException ex){
				continue;
			}
		}

	}

	public Driver getBookedDriver(){return dir.get();}
}
