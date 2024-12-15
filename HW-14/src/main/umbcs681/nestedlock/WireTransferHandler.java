package umbcs681.nestedlock;

import java.time.Duration;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Random;

public class WireTransferHandler{

	public WireTransferHandler(){

	}

	public void transfer(BankAccount source, BankAccount destination, double amount){
		Random random = new Random();
		while(true){
			if( source.getLock().tryLock() ){
				try{
					if( destination.getLock().tryLock() ){
						try{
							if( source.getBalance() < amount ){
								System.out.println("Sufficent Funds unavailable");
							}
							else{
								source.withdraw(amount);
								destination.deposit(amount);
							}
							return;
						}finally{
							destination.getLock().unlock();
						}
					}
				}finally{
					source.getLock().unlock();
				}
			}
			try{
				Thread.sleep( Duration.ofMillis(random.nextInt(50)));
			}
			catch (InterruptedException ex){
				continue;
			}
		}
	}
}
