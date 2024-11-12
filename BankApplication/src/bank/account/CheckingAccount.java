package bank.account;

public class CheckingAccount extends Account_Class {
    private double overDraftLimit;


    public CheckingAccount(int accountNumber, double balance, int overDraftLimit) {
        super(accountNumber, balance);
        this.overDraftLimit = overDraftLimit;
    }

    public double getOverDraftLimit(){
        return overDraftLimit;
    }

    public void setOverDraftLimit(double overDraftLimit) {
        this.overDraftLimit = overDraftLimit;
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance + overDraftLimit) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount + ". Remaining balance: " + balance);

        } else if (amount > balance + overDraftLimit) {
            System.out.println("Withdrawal exceeds overdraft limit.");
        }
    }
}