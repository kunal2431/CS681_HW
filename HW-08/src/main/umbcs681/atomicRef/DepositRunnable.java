package umbcs681.atomicRef;

import java.time.Duration;

public class DepositRunnable implements Runnable{
	private BankAccount account;
	
	public DepositRunnable(BankAccount account) {
		this.account = account;
	}
	
	public void run(){
		try{
			for(int i = 0; i < 10; i++){
				account.deposit(100);
				Thread.sleep( Duration.ofSeconds(2) );
				//System.out.println(account.getBalance());
			}
		}catch(InterruptedException exception){}
	}
}
