package umbcs681.bankRead;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankReadTest{

    @Test
    //Test Case 1: Functional Test
    public void verify_PessimisticBank(){
        ThreadSafeBankAccount bankAccount = new ThreadSafeBankAccount();
        Thread dep = new Thread( new DepositRunnable(bankAccount) );
        Thread wit = new Thread( new WithdrawRunnable(bankAccount) );
        dep.start();
        wit.start();
        try {
            dep.join();
            wit.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(0, bankAccount.getBalance());
    }

    @Test
    //Test Case 2: Functional Test
    public void verify_PessimisticBank2(){
        ThreadSafeBankAccount bankAccount = new ThreadSafeBankAccount();
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
    public void verify_PessimisticBank3(){
        ThreadSafeBankAccount bankAccount = new ThreadSafeBankAccount();
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

    @Test
    //Test Case 4: Functional Test of ThreadSafeOptimisticBankAccount
    public void verify_OptimisticBankAccount(){
        ThreadSafeOptimisticBankAccount bankAccount = new ThreadSafeOptimisticBankAccount();
        Thread dep1 = new Thread( new DepositRunnable(bankAccount) );
        Thread wit1 = new Thread( new WithdrawRunnable(bankAccount) );
        dep1.start();
        wit1.start();
        try {
            dep1.join();
            wit1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(0, bankAccount.getBalance());
    }

    @Test
    //Test Case 5: Functional Test of ThreadSafeOptimisticBankAccount
    public void verify_OptimisticBankAccount2(){
        ThreadSafeOptimisticBankAccount bankAccount = new ThreadSafeOptimisticBankAccount();
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
    //Test Case 6: Functional Test of ThreadSafeOptimisticBankAccount
    public void verify_OptimisticBankAccount3(){
        ThreadSafeOptimisticBankAccount bankAccount = new ThreadSafeOptimisticBankAccount();
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

    @Test
    //Test Case 7: Functional Test of ReadRunnable
    public void verify_OptimisticBankAccount4(){
        ThreadSafeOptimisticBankAccount bankAccount = new ThreadSafeOptimisticBankAccount();
        DepositRunnable d = new DepositRunnable(bankAccount);
        WithdrawRunnable w = new WithdrawRunnable(bankAccount);
        ReadRunnable r = new ReadRunnable(bankAccount);
        Thread dep = new Thread( d );
        Thread wit = new Thread( w);
        Thread rd1 = new Thread( r);
        Thread rd2 = new Thread( r);
        Thread rd3 = new Thread( r);
        Thread rd4 = new Thread( r);
        Thread rd5 = new Thread( r);
        Thread rd6 = new Thread( r);
        Thread rd7 = new Thread( r);
        dep.start();
        wit.start();
        rd1.start();
        rd2.start();
        rd3.start();
        rd4.start();
        rd5.start();
        rd6.start();
        rd7.start();
        try {
            dep.join();
            wit.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(0, bankAccount.getBalance());
    }



}
