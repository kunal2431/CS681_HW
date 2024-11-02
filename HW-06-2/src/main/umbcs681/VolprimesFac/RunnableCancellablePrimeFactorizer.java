package umbcs681.VolprimesFac;

//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeFactorizer extends RunnablePrimeFactorizer {
	private volatile boolean done = false;
	//private ReentrantLock lock = new ReentrantLock();
	
	public RunnableCancellablePrimeFactorizer(long dividend, long from, long to) {
		super(dividend, from, to);
	}
	
	public void setDone(){
		done = true;
	}

	public void generatePrimeFactors() {
		long divisor = from;
		while( dividend != 1 && divisor <= to ){
			if (done) {
				System.out.println("Stopped generating prime factors.");
				this.factors.clear();
				break;
			}
			if( divisor > 2 && isEven(divisor)) {
				divisor++;
				continue;
			}
			if(dividend % divisor == 0) {
				factors.add(divisor);
				dividend /= divisor;
			}else {
				if(divisor==2){ divisor++; }
				else{ divisor += 2; }
			}

		}
	}

	public static void main(String[] args) {
		RunnableCancellablePrimeFactorizer gen = new RunnableCancellablePrimeFactorizer(36, 2, (long)Math.sqrt(36));
		Thread thread = new Thread(gen);
		thread.start();
		gen.setDone();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		gen.getPrimeFactors().forEach( (Long prime)-> System.out.print(prime + ", ") );
		System.out.println("\n" + gen.getPrimeFactors().size() + " prime factors are found.");
	}
}
