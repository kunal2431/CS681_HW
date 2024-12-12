package umbcs681.uber;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UberTest{

    @Test
    //Test Case 1: Functional Test of ThreadSafeUber
    public void verify_UberTest(){
        ThreadSafeUber user = new ThreadSafeUber();
        Driver d1 = new Driver("John", 56216);
        Driver d2 = new Driver("Will", 85472);
        user.addDriver(d1);
        user.addDriver(d2);
        BookCabRunnable bk = new BookCabRunnable(user);
        Thread b = new Thread( bk  );
        b.start();
        try {
            b.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(1, user.getDrivers().size());
        Thread c = new Thread( new CancelCabRunnable(user, bk.getBookedDriver()) );
        c.start();
        try {
            c.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(2, user.getDrivers().size());
    }

    @Test
    //Test Case 2: Functional Test of ThreadSafeUber
    public void verify_UberTest1(){
        ThreadSafeUber user = new ThreadSafeUber();
        Driver d1 = new Driver("John", 56216);
        Driver d2 = new Driver("Will", 85472);
        user.addDriver(d1);
        user.addDriver(d2);
        BookCabRunnable bk = new BookCabRunnable(user);
        Thread b = new Thread( bk );
        b.start();
        try {
            b.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(1, user.getDrivers().size());
        CancelCabRunnable can = new CancelCabRunnable(user, bk.getBookedDriver());
        Thread c = new Thread( can );
        c.start();
        can.setDone();
        c.interrupt();
        assertEquals(1, user.getDrivers().size());
    }

    @Test
    //Test Case 3: Functional Test of ThreadUnsafeUber
    public void verify_UberTest2(){
        ThreadUnsafeUber user = new ThreadUnsafeUber();
        Driver d1 = new Driver("John", 56216);
        Driver d2 = new Driver("Will", 85472);
        user.addDriver(d1);
        user.addDriver(d2);
        BookCabRunnable bk = new BookCabRunnable(user);
        Thread b = new Thread( bk  );
        b.start();
        try {
            b.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread c = new Thread( new CancelCabRunnable(user, bk.getBookedDriver()) );
        c.start();
    }

    @Test
    //Test Case 4: Functional Test of ThreadUnsafeUber
    public void verify_UberTest3(){
        ThreadUnsafeUber user = new ThreadUnsafeUber();
        Driver d1 = new Driver("John", 56216);
        Driver d2 = new Driver("Will", 85472);
        user.addDriver(d1);
        user.addDriver(d2);
        BookCabRunnable bk = new BookCabRunnable(user);
        Thread b = new Thread( bk );
        b.start();
        try {
            b.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CancelCabRunnable can = new CancelCabRunnable(user, bk.getBookedDriver());
        Thread c = new Thread( can );
        c.start();
        can.setDone();
        c.interrupt();
    }

    @Test
    //Test Case 5: Functional Test of ReadRunnable
    public void verify_UberTest4(){
        ThreadSafeUber user = new ThreadSafeUber();
        Driver d1 = new Driver("John", 56216);
        Driver d2 = new Driver("Will", 85472);
        user.addDriver(d1);
        user.addDriver(d2);
        ReadRunnable rd = new ReadRunnable(user);
        Thread t1 = new Thread(rd);
        t1.start();
    }

}
