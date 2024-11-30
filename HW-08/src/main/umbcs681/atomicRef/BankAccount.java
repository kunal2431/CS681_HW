package umbcs681.atomicRef;

public interface BankAccount {
	public void deposit(double amount);
	public void withdraw(double amount);
	public double getBalance();
}
