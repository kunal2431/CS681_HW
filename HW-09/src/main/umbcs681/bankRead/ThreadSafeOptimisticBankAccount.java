package umbcs681.bankRead;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadSafeOptimisticBankAccount implements BankAccount{
	private double balance = 0;
	//private AtomicReference<Double> balance = new AtomicReference<>(0.0);
	//private ReentrantLock lock = new ReentrantLock();
	private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

	public void deposit(double amount){
		rwLock.writeLock().lock();
		try{
			System.out.println("Lock obtained");
			System.out.print("Current balance (d): " + balance);
			balance = balance + amount;
			System.out.println(", New balance (d): " + balance);
		}finally{
			rwLock.writeLock().unlock();
			System.out.println("Lock released");
		}
	}
	
	public void withdraw(double amount){
		rwLock.writeLock().lock();
		try{
			System.out.println("Lock obtained");
			System.out.print("Current balance (w): " + balance);
			balance = balance - amount;
			System.out.println(", New balance (w): " + balance);
		}finally{
			rwLock.writeLock().unlock();
			System.out.println("Lock released");
		}
	}

	public double getBalance() {
		rwLock.readLock().lock();
		try{
			return this.balance;
		}
		finally {
			rwLock.readLock().unlock();
		}
	}
	
/*	public static void main(String[] args){
		ThreadSafeOptimisticBankAccount bankAccount = new ThreadSafeOptimisticBankAccount();
		new Thread( new DepositRunnable(bankAccount) ).start();
		new Thread( new WithdrawRunnable(bankAccount) ).start();
	}*/
}
