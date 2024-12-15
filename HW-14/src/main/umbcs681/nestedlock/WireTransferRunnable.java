package umbcs681.nestedlock;

import java.util.concurrent.atomic.AtomicBoolean;

public class WireTransferRunnable implements Runnable{
	private BankAccount source;
	private BankAccount destination;
	private double amount;

	private AtomicBoolean done = new AtomicBoolean(false);
	
	public WireTransferRunnable(BankAccount source, BankAccount destination, double amount) {
		this.source = source;
		this.destination = destination;
		this.amount = amount;
	}

	public void setDone(){
		done.set(true);
	}

	public void run(){
		for(int i = 0; i < 1; i++){
			if (done.get()) {
				System.out.println("Stopped WireTransferRunnable");
				break;
			}

			WireTransferHandler.transfer(source, destination, amount);

			try{
				Thread.sleep(2);
			}
			catch (InterruptedException ex){
				continue;
			}
		}

	}
}
