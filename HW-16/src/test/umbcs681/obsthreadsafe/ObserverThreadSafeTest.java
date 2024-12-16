package umbcs681.obsthreadsafe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ObserverThreadSafeTest{

    @Test
    //Test Case 1: Functional Test of ObserverThreadSafeTest
    public void verify_ObserverThreadSafe(){
        StockQuoteObservable observable = new StockQuoteObservable();
        LineChartObserver l1 = new LineChartObserver();
        observable.addObserver(l1);
        DataHandler d1 = new DataHandler(observable, "Apple", 250);
        Thread t = new Thread(d1);
        t.start();
        try{
            t.join(); // I used .join here only to validate the event later in main thread,
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals("Apple", l1.getEvent().ticker());
        assertEquals(250, l1.getEvent().quote());
    }

    @Test
    //Test Case 2: Functional Test of ObserverThreadSafeTest
    public void verify_ObserverThreadSafe1(){
        StockQuoteObservable observable = new StockQuoteObservable();
        LineChartObserver l1 = new LineChartObserver();
        ThreeDObserver to = new ThreeDObserver();
        TableObserver tb = new TableObserver();
        observable.addObserver(l1);
        observable.addObserver(to);
        observable.addObserver(tb);
        DataHandler d1 = new DataHandler(observable, "Amazon", 180);
        Thread t1 = new Thread(d1);
        Thread t2 = new Thread(d1);
        Thread t3 = new Thread(d1);
        Thread t4 = new Thread(d1);
        Thread t5 = new Thread(d1);
        Thread t6 = new Thread(d1);
        Thread t7 = new Thread(d1);
        Thread t8 = new Thread(d1);
        Thread t9 = new Thread(d1);
        Thread t10 = new Thread(d1);
        Thread t11 = new Thread(d1);
        Thread t12 = new Thread(d1);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();
        t11.start();
        t12.start();
        try{
            t1.join(); // I used .join here only to validate the event later in main thread,
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals("Amazon", l1.getEvent().ticker());
        assertEquals(180, l1.getEvent().quote());
    }

    @Test
    //Test Case 3: Functional Test of ObserverThreadSafeTest
    public void verify_ObserverThreadSafe2(){
        StockQuoteObservable observable = new StockQuoteObservable();
        LineChartObserver l1 = new LineChartObserver();
        ThreeDObserver to = new ThreeDObserver();
        TableObserver tb = new TableObserver();
        observable.addObserver(l1);
        observable.addObserver(to);
        observable.addObserver(tb);
        DataHandler d1 = new DataHandler(observable, "Amazon", 180);
        DataHandler d2 = new DataHandler(observable, "Boeing", 240);
        DataHandler d3 = new DataHandler(observable, "Wipro", 350);
        DataHandler d4 = new DataHandler(observable, "Amtrak", 85);
        DataHandler d5 = new DataHandler(observable, "Tesla", 55);
        DataHandler d6 = new DataHandler(observable, "Nike", 220);
        DataHandler d7 = new DataHandler(observable, "Walt", 175);
        DataHandler d8 = new DataHandler(observable, "Ford", 60);
        DataHandler d9 = new DataHandler(observable, "United", 78);
        DataHandler d10 = new DataHandler(observable, "Delta", 155);
        DataHandler d11 = new DataHandler(observable, "Southwest", 157);
        DataHandler d12 = new DataHandler(observable, "Spirit", 45);
        Thread t1 = new Thread(d1);
        Thread t2 = new Thread(d2);
        Thread t3 = new Thread(d3);
        Thread t4 = new Thread(d4);
        Thread t5 = new Thread(d5);
        Thread t6 = new Thread(d6);
        Thread t7 = new Thread(d7);
        Thread t8 = new Thread(d8);
        Thread t9 = new Thread(d9);
        Thread t10 = new Thread(d10);
        Thread t11 = new Thread(d11);
        Thread t12 = new Thread(d12);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();
        t11.start();
        t12.start();
        d1.setDone();
        t1.interrupt();
        d2.setDone();
        t2.interrupt();
        d3.setDone();
        t3.interrupt();
        d4.setDone();
        t4.interrupt();
        d5.setDone();
        t5.interrupt();
        d6.setDone();
        t6.interrupt();
        d7.setDone();
        t7.interrupt();
        d8.setDone();
        t8.interrupt();
        d9.setDone();
        t9.interrupt();
        d10.setDone();
        t10.interrupt();
        d11.setDone();
        t11.interrupt();
        d12.setDone();
        t12.interrupt();
        try{
            t1.join(); // I used .join here only to validate the event later in main thread,
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertFalse(t1.isAlive());
    }

}
