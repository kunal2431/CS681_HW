package umbcs681.bankRead;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ReadRunnable implements Runnable{
	private BankAccount account;
	private ReentrantLock lock = new ReentrantLock();

	private AtomicBoolean done = new AtomicBoolean(false);
	
	public ReadRunnable(BankAccount account) {
		this.account = account;
	}

	public void setDone(){
		done.set(true);
	}

	public void run(){
		for(int i = 0; i < 10; i++){
			lock.lock();
			try {
				if (done.get()) {
					System.out.println("Stopped ReadRunnable");
					break;
				}
				else{
					account.getBalance();
				}

			}
			finally {
				lock.unlock();
			}
			try{
				Thread.sleep(2);
			}
			catch (InterruptedException ex){
				continue;
			}
		}

	}
}
