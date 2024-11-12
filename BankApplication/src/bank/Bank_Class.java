package bank;

import bank.account.Account_Class;

import java.util.HashMap;
import java.util.Map;

public class Bank_Class {
    public Map<Integer, Customer_Class> customers_Bank_Class_Instance_Variable;
    public Map<Integer, Account_Class> accounts_Bank_Class_Instance_Variable;

    public Bank_Class() {
        customers_Bank_Class_Instance_Variable = new HashMap<>();
        accounts_Bank_Class_Instance_Variable = new HashMap<>();
    }

    public void createCustomer(String name, Account_Class account) {
        int customerId = customers_Bank_Class_Instance_Variable.size() + 1;
        Customer_Class customer = new Customer_Class(customerId, name, account);

        customers_Bank_Class_Instance_Variable.put(customerId, customer);
        accounts_Bank_Class_Instance_Variable.put(account.getAccountNumber(), account);

        System.out.println("Customer created: " + name + " with account number: " + account.getAccountNumber());
    }

    public void transferMoney(int fromAccount, int toAccount, double amount) {
        Account_Class from = accounts_Bank_Class_Instance_Variable.get(fromAccount);
        Account_Class to = accounts_Bank_Class_Instance_Variable.get(toAccount);

        System.out.println(from);
        System.out.println(to);

        if (from != null && to != null) {
            if (from.getBalance() >= amount) {

                from.withdraw(amount);
                to.deposit(amount);
                System.out.println("Transferred " + amount + " from account " + fromAccount + " to account " + toAccount);
            } else {
                System.out.println("Insufficient funds in account " + fromAccount);
            }
        } else {
            System.out.println("One or both account numbers are invalid.");
        }
    }

    public void viewTransactionHistory(int accountNumber) {
        Account_Class account = accounts_Bank_Class_Instance_Variable.get(accountNumber);

        if (account != null) {
            // Assuming we had a list of transactions in the Account class
            System.out.println("Transaction history for account " + accountNumber + ": (Not yet implemented)");

        } else {
            System.out.println("Account number " + accountNumber + " not found.");
        }
    }

    public static void main(String[] args) {
        Bank_Class o = new Bank_Class();

        o.transferMoney(1,2, 20);

    }
}