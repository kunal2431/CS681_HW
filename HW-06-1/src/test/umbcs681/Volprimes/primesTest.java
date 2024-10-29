package umbcs681.Volprimes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class primesTest{

    @Test
    //Test Case 1: Functional Test
    public void verify_cancellablePrimes(){
        RunnableCancellablePrimeGenerator gen = new RunnableCancellablePrimeGenerator(1,100);
        Thread thread = new Thread(gen);
        thread.start();
        gen.setDone();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(0, gen.getPrimes().size());
    }

    @Test
    //Test Case 2: Functional Test
    public void verify_cancellablePrimes2(){
        RunnableCancellablePrimeGenerator gen = new RunnableCancellablePrimeGenerator(1,100);
        Thread thread = new Thread(gen);
        thread.start();
        //gen.setDone();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(25, gen.getPrimes().size());
    }

    @Test
    //Test Case 3: Functional Test
    public void verify_primeGenerator(){
        RunnablePrimeGenerator gen1 = new RunnablePrimeGenerator(1, 100);
        RunnablePrimeGenerator gen2 = new RunnablePrimeGenerator(101, 200);
        Thread t1 = new Thread(gen1);
        Thread t2 = new Thread(gen2);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {}

        long primeNum = gen1.getPrimes().size() + gen2.getPrimes().size();
        assertEquals(46, primeNum);
    }

    @Test
    //Test Case 4: Functional Test
    public void verify_cancellablePrimes3(){
        RunnableCancellablePrimeGenerator gen1 = new RunnableCancellablePrimeGenerator(1, 100);
        RunnableCancellablePrimeGenerator gen2 = new RunnableCancellablePrimeGenerator(101, 200);
        Thread t1 = new Thread(gen1);
        Thread t2 = new Thread(gen2);
        t1.start();
        t2.start();
        gen2.setDone();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {}

        long primeNum = gen1.getPrimes().size() + gen2.getPrimes().size();
        assertEquals(25, primeNum);
        assertEquals(0, gen2.getPrimes().size());
        assertEquals(25, gen1.getPrimes().size());
    }

}
