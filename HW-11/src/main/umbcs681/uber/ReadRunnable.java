package umbcs681.uber;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ReadRunnable implements Runnable{
	private UberApp account;

	private AtomicBoolean done = new AtomicBoolean(false);

	public ReadRunnable(UberApp account) {
		this.account = account;
	}

	public void setDone(){
		done.set(true);
	}


	public void run(){
		for(int i =0; i < 1; i++){
			if (done.get()) {
				System.out.println("Stopped ReadRunnable");
				break;
			}
			account.getDrivers();
			try{
				Thread.sleep(2);
			}
			catch (InterruptedException ex){
				continue;
			}
		}

	}
}
