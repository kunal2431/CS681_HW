package umbcs681.deadlock;

public interface BankAccount {
	public void deposit(double amount);
	public void withdraw(double amount);
	public double getBalance();
}
