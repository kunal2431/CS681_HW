package umbcs681.uber;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class CancelCabRunnable implements Runnable{
	private UberApp account;

	private Driver driver;

	private AtomicBoolean done = new AtomicBoolean(false);

	public CancelCabRunnable(UberApp account, Driver driver) {
		this.account = account;
		this.driver = driver;
	}

	public void setDone(){
		done.set(true);
	}


	public void run(){
		for(int i =0; i < 1; i++){
			if (done.get()) {
				System.out.println("Stopped CancelCab");
				break;
			}
			account.CancelCab(driver);
			try{
				Thread.sleep(2);
			}
			catch (InterruptedException ex){
				continue;
			}
		}

	}
}
