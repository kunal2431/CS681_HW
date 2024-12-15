package umbcs681.nestedlock;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccNestedlockTest{

    @Test
    //Test Case 1: Functional Test of ThreadSafeBankAccount2
    public void verify_ThreadSafeBankAccount(){
        ThreadSafeBankAccount2 source = new ThreadSafeBankAccount2();
        ThreadSafeBankAccount2 destination = new ThreadSafeBankAccount2();
        DepositRunnable d1 = new DepositRunnable(source);
        WithdrawRunnable w1 = new WithdrawRunnable(source);
        WireTransferHandler wth = new WireTransferHandler();
        WireTransferRunnable trans1 = new WireTransferRunnable(wth, source, destination, 500);
        Thread dep1 = new Thread(d1);
        Thread wit1 = new Thread(w1);
        Thread transfer1 = new Thread(trans1);
        dep1.start();
        wit1.start();
        transfer1.start();
        assertTrue(source instanceof ThreadSafeBankAccount2);
    }

    @Test
    //Test Case 2: Functional Test of ThreadSafeBankAccount2
    public void verify_ThreadSafeBankAccount1(){
        ThreadSafeBankAccount2 source = new ThreadSafeBankAccount2();
        ThreadSafeBankAccount2 destination = new ThreadSafeBankAccount2();
        DepositRunnable d1 = new DepositRunnable(source);
        WithdrawRunnable w1 = new WithdrawRunnable(source);
        WireTransferHandler wth = new WireTransferHandler();
        WireTransferRunnable trans1 = new WireTransferRunnable(wth, source, destination, 500);
        Thread dep1 = new Thread(d1);
        Thread wit1 = new Thread(w1);
        Thread transfer1 = new Thread(trans1);
        dep1.start();
        wit1.start();
        transfer1.start();
        d1.setDone();
        dep1.interrupt();
        w1.setDone();
        wit1.interrupt();
        trans1.setDone();
        transfer1.interrupt();
        try{
            dep1.join(); // I used .join here for all the thread to complete its 2 step termination and then validate in main thread.
            wit1.join();
            transfer1.join();
        }
        catch (InterruptedException e){}
        assertFalse(dep1.isAlive());
        assertFalse(wit1.isAlive());
        assertFalse(transfer1.isAlive());
    }

}
