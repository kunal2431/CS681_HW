package umbcs681.primes;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeGenerator extends RunnablePrimeGenerator {
	private boolean done = false;
	private ReentrantLock lock = new ReentrantLock();
	
	public RunnableCancellablePrimeGenerator(long from, long to) {
		super(from, to);
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

	public void generatePrimes(){
		for (long n = from; n <= to; n++) {
			// Stop generating prime numbers if done==true
			lock.lock();
			try {
				if (done) {
					System.out.println("Stopped generating prime numbers.");
					this.primes.clear();
					break;
				}
				if (isPrime(n)) {
					this.primes.add(n);
				}
			}
			finally {
				lock.unlock();
			}
		}
	}
	
}
