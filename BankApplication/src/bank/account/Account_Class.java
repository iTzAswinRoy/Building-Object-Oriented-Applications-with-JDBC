package bank.account;

public abstract class Account_Class {
    private int accountNumber;
    protected double balance;

    public Account_Class(int accountNumber, double balance){
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public double getBalance(){
        return balance;
    }

    public int getAccountNumber(){
        return accountNumber;
    }

    public void deposit(double amount) {
        if (amount >= 0) {
            balance += amount;
        }
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public void applyInterestRate() {}
}