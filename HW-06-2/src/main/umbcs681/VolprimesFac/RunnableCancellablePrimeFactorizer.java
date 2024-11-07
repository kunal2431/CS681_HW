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

}
