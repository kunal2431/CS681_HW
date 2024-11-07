package umbcs681.Volprimes;

//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeGenerator extends RunnablePrimeGenerator {
	private volatile boolean done = false;
	//private ReentrantLock lock = new ReentrantLock();
	
	public RunnableCancellablePrimeGenerator(long from, long to) {
		super(from, to);
	}
	
	public void setDone(){
			done = true;
	}

	public void generatePrimes(){
		for (long n = from; n <= to; n++) {
			// Stop generating prime numbers if done==true
			if (done) {
				System.out.println("Stopped generating prime numbers.");
				this.primes.clear();
				break;
			}
			if (isPrime(n)) {
				this.primes.add(n);
			}
		}
	}

}
