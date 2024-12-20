package umbcs681.deadlock;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccdeadlockTest{

    @Test
    //Test Case 1: Functional Test of ThreadSafeBankAccount2
    public void verify_ThreadSafeBankAccount2(){
        ThreadSafeBankAccount2 bankAccount = new ThreadSafeBankAccount2();
        DepositRunnable d1 = new DepositRunnable(bankAccount);
        WithdrawRunnable w1 = new WithdrawRunnable(bankAccount);
        DepositRunnable d2 = new DepositRunnable(bankAccount);
        WithdrawRunnable w2 = new WithdrawRunnable(bankAccount);
        DepositRunnable d3 = new DepositRunnable(bankAccount);
        WithdrawRunnable w3 = new WithdrawRunnable(bankAccount);
        DepositRunnable d4 = new DepositRunnable(bankAccount);
        WithdrawRunnable w4 = new WithdrawRunnable(bankAccount);
        Thread dep1 = new Thread( d1 );
        Thread wit1 = new Thread( w1 );
        Thread dep2 = new Thread( d2 );
        Thread wit2 = new Thread( w2 );
        Thread dep3 = new Thread( d3 );
        Thread wit3 = new Thread( w3 );
        Thread dep4 = new Thread( d4 );
        Thread wit4 = new Thread( w4 );
        dep1.start();
        wit1.start();
        dep2.start();
        wit2.start();
        dep3.start();
        wit3.start();
        dep4.start();
        wit4.start();
        try{
            Thread.sleep(1);
        }catch (InterruptedException e){}
        d1.setDone();
        w1.setDone();
        d2.setDone();
        w2.setDone();
        d3.setDone();
        w3.setDone();
        d4.setDone();
        w4.setDone();
        dep1.interrupt();
        wit1.interrupt();
        dep2.interrupt();
        wit2.interrupt();
        dep3.interrupt();
        wit3.interrupt();
        dep4.interrupt();
        wit4.interrupt();
        try {
            dep1.join(); // I used .join() here because I need to validate all threads after 2 step termination
            wit1.join();
            dep2.join();
            wit2.join();
            dep3.join();
            wit3.join();
            dep4.join();
            wit4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertFalse(dep1.isAlive());
        assertFalse(wit1.isAlive());
        assertFalse(dep2.isAlive());
        assertFalse(wit2.isAlive());
        assertFalse(dep3.isAlive());
        assertFalse(wit3.isAlive());
        assertFalse(dep4.isAlive());
        assertFalse(wit4.isAlive());
    }

}
