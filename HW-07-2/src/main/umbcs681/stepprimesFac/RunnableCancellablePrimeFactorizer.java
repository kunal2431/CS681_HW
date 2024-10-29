package umbcs681.stepprimesFac;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeFactorizer extends RunnablePrimeFactorizer {
	volatile boolean done = false;
	private ReentrantLock lock = new ReentrantLock();
	
	public RunnableCancellablePrimeFactorizer(long dividend, long from, long to) {
		super(dividend, from, to);
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

	public void generatePrimeFactors() {
		long divisor = from;
		while( dividend != 1 && divisor <= to ){
			lock.lock();
			try {
				if (done) {
					System.out.println("Stopped generating prime factors.");
					this.factors.clear();
					break;
				}
				if (divisor > 2 && isEven(divisor)) {
					divisor++;
					continue;
				}
				if (dividend % divisor == 0) {
					factors.add(divisor);
					dividend /= divisor;
				} else {
					if (divisor == 2) {
						divisor++;
					} else {
						divisor += 2;
					}
				}
				//System.out.println(divisor);
			}
			finally {
				lock.unlock();
			}
			try{
				Thread.sleep(3);
			}
			catch (InterruptedException ex){
				continue;
			}
		}
	}

	public static void main(String[] args) {
		RunnableCancellablePrimeFactorizer gen = new RunnableCancellablePrimeFactorizer(36, 2, (long)Math.sqrt(36));
		Thread thread = new Thread(gen);
		thread.start();
		gen.setDone();
		thread.interrupt();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		gen.getPrimeFactors().forEach( (Long prime)-> System.out.print(prime + ", ") );
		System.out.println("\n" + gen.getPrimeFactors().size() + " prime factors are found.");
	}
}
