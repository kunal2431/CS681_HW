package umbcs681.nestedlock;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.atomic.*;

public class DepositRunnable implements Runnable{
	private BankAccount account;

	private AtomicBoolean done = new AtomicBoolean(false);

	public DepositRunnable(BankAccount account) {
		this.account = account;
	}

	public void setDone(){
		done.set(true);
	}


	public void run(){
		for(int i = 0; i < 10; i++){
			if (done.get()) {
				System.out.println("Stopped Deposit");
				break;
			}
			account.deposit(100);
			//Thread.sleep( Duration.ofSeconds(2) );
			//System.out.println(account.getBalance());
			try{
				Thread.sleep(2);
			}
			catch (InterruptedException ex){
				continue;
			}
		}

	}
}
