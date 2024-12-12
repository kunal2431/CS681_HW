package umbcs681.atomicRef;

import java.util.concurrent.atomic.*;

public class AtomicBankAccount implements BankAccount{
	//private double balance = 0;
	private AtomicReference<Double> balance = new AtomicReference<>(0.0);

	public void deposit(double amount){
		System.out.print("Current balance (d): " + balance);
		balance.accumulateAndGet(amount,
				(result, balance)-> result + balance );
		//balance = balance + amount;
		System.out.println(", New balance (d): " + balance);
	}
	
	public void withdraw(double amount){
		System.out.print(" Current balance (w): " + balance);
		balance.accumulateAndGet(amount,
				(result, balance)-> result - balance );
		//balance = balance - amount;
		System.out.println(", New balance (w): " + balance);
	}

	public double getBalance() { return balance.get(); }
	
/*	public static void main(String[] args){
		ThreadUnsafeBankAccount bankAccount = new ThreadUnsafeBankAccount();
		new Thread( new DepositRunnable(bankAccount) ).start();
		new Thread( new WithdrawRunnable(bankAccount) ).start();
	}*/
}
