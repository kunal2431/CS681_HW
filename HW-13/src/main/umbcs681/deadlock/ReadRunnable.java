package umbcs681.deadlock;

import java.util.concurrent.atomic.AtomicBoolean;

public class ReadRunnable implements Runnable{
	private BankAccount account;

	private AtomicBoolean done = new AtomicBoolean(false);
	
	public ReadRunnable(BankAccount account) {
		this.account = account;
	}

	public void setDone(){
		done.set(true);
	}

	public void run(){
		while(true){
			if (done.get()) {
				System.out.println("Stopped ReadRunnable");
				break;
			}

			account.getBalance();

			try{
				Thread.sleep(2);
			}
			catch (InterruptedException ex){
				continue;
			}
		}

	}
}
