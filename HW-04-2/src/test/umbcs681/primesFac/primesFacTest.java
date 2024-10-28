package umbcs681.primesFac;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class primesFacTest{

    @Test
    //Test Case 1: Functional Test
    public void verify_cancellablePrimesFac(){
        RunnableCancellablePrimeFactorizer gen = new RunnableCancellablePrimeFactorizer(36, 2, (long)Math.sqrt(36));
        Thread thread = new Thread(gen);
        thread.start();
        gen.setDone();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(0, gen.getPrimeFactors().size());
    }

    @Test
    //Test Case 2: Functional Test
    public void verify_cancellablePrimesFac1(){
        RunnableCancellablePrimeFactorizer gen = new RunnableCancellablePrimeFactorizer(36, 2, (long)Math.sqrt(36));
        Thread thread = new Thread(gen);
        thread.start();
        //gen.setDone();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(4, gen.getPrimeFactors().size());
    }

    @Test
    //Test Case 3: Functional Test
    public void verify_cancellablePrimesFac2(){

        // Factorization of 84 with two threads
        System.out.println("Factorization of 84");
        LinkedList<RunnableCancellablePrimeFactorizer> runnables = new LinkedList<RunnableCancellablePrimeFactorizer>();
        LinkedList<Thread> threads = new LinkedList<>();

        runnables.add( new RunnableCancellablePrimeFactorizer(84, 2, (long)Math.sqrt(84)/2 ));
        runnables.add( new RunnableCancellablePrimeFactorizer(84, 1+(long)Math.sqrt(84)/2, (long)Math.sqrt(84) ));

        Thread thread = new Thread(runnables.get(0));
        threads.add(thread);
        System.out.println("Thread #" + thread.threadId() +
                " performs factorization in the range of " + runnables.get(0).getFrom() + "->" + runnables.get(0).getTo());

        thread = new Thread(runnables.get(1));
        threads.add(thread);
        System.out.println("Thread #" + thread.threadId() +
                " performs factorization in the range of " + runnables.get(1).getFrom() + "->" + runnables.get(1).getTo());

        threads.forEach( (t)->t.start() );
        runnables.get(1).setDone();
        //runnables.get(0).setDone();
        threads.forEach( (t)->{	try{t.join();}
        catch(InterruptedException e){e.printStackTrace(); }} );

        LinkedList<Long> factors = new LinkedList<>();
        runnables.forEach( (factorizer) -> factors.addAll(factorizer.getPrimeFactors()) );
        //System.out.println("Final result: " + factors + "\n");
        assertEquals(3, factors.size());
        //assertEquals(1, factors.size());

        runnables.clear();
        threads.clear();
    }



}
