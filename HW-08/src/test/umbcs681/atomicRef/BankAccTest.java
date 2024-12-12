package umbcs681.atomicRef;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccTest{

    @Test
    //Test Case 1: Functional Test
    public void verify_atomicRef(){
        AtomicBankAccount bankAccount = new AtomicBankAccount();
        Thread dep = new Thread( new DepositRunnable(bankAccount) );
        Thread wit = new Thread( new WithdrawRunnable(bankAccount) );
        dep.start();
        wit.start();
        try {
            dep.join();    // I used .join() here because I need to validate/assert final balance after both threads finish its execution
            wit.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(0, bankAccount.getBalance());
    }

    @Test
    //Test Case 2: Functional Test
    public void verify_atomicRef2(){
        AtomicBankAccount bankAccount = new AtomicBankAccount();
        DepositRunnable d = new DepositRunnable(bankAccount);
        WithdrawRunnable w = new WithdrawRunnable(bankAccount);
        Thread dep = new Thread( d );
        Thread wit = new Thread( w);
        dep.start();
        wit.start();
        w.setDone();
        wit.interrupt();
        try {
            dep.join();
            wit.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    //Test Case 3: Functional Test
    public void verify_atomicRef3(){
        AtomicBankAccount bankAccount = new AtomicBankAccount();
        DepositRunnable d = new DepositRunnable(bankAccount);
        WithdrawRunnable w = new WithdrawRunnable(bankAccount);
        Thread dep = new Thread( d );
        Thread wit = new Thread( w);
        dep.start();
        wit.start();
        d.setDone();
        dep.interrupt();
        try {
            dep.join();
            wit.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
