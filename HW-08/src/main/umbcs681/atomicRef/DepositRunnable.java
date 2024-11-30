package umbcs681.atomicRef;

import java.util.concurrent.locks.ReentrantLock;

public class DepositRunnable implements Runnable{
	private BankAccount account;

	private volatile boolean done = false;
	private ReentrantLock lock = new ReentrantLock();
	
	public DepositRunnable(BankAccount account) {
		this.account = account;
	}

	public void setDone(){
		lock.lock();
		try {
			done = true;
		}
		finally {
			lock.unlock();
		}
	}
	
	public void run(){
		for(int i = 0; i < 10; i++){
			lock.lock();
			try {
				if (done) {
					System.out.println("Stopped Deposit");
					break;
				}
				else{
					account.deposit(100);
					//Thread.sleep( Duration.ofSeconds(2) );
					//System.out.println(account.getBalance());
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
