package umbcs681.atomicRef;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccTest{

    @Test
    //Test Case 1: Functional Test
    public void verify_atomicRef(){
        ThreadUnsafeBankAccount bankAccount = new ThreadUnsafeBankAccount();
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

}
