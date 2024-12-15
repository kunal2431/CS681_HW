package umbcs681.nestedlock;

import java.util.concurrent.locks.ReentrantLock;

public interface BankAccount {
	public void deposit(double amount);
	public void withdraw(double amount);
	public double getBalance();
	public ReentrantLock getLock();
}
