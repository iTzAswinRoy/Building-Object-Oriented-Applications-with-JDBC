import bank.Bank_Class;
import bank.account.Account_Class;
import bank.account.CheckingAccount;
import bank.account.SavingsAccount;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Bank_Class bank = new Bank_Class();
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Bank Automation Menu ---");
            System.out.println("1. Create Savings Account");
            System.out.println("2. Create Checking Account");
            System.out.println("3. Deposit Money");
            System.out.println("4. Withdraw Money");
            System.out.println("5. Transfer Money");
            System.out.println("6. Apply Interest (Savings Account only)");
            System.out.println("7. View Transaction History");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter customer name: ");
                    scanner.nextLine(); // Consume newline
                    String name = scanner.nextLine();

                    System.out.print("Enter initial balance: ");
                    double balance = scanner.nextDouble();

                    System.out.print("Enter interest rate: ");
                    double interestRate = scanner.nextDouble();

                    Account_Class savingsAccount = new SavingsAccount(bank.customers_Bank_Class_Instance_Variable.size() + 1, balance, interestRate);
                    bank.createCustomer(name, savingsAccount);
                }

                case 2 -> {
                    System.out.print("Enter customer name: ");
                    scanner.nextLine(); // Consume newline

                    String name = scanner.nextLine();

                    System.out.print("Enter initial balance: ");
                    double balance = scanner.nextDouble();

                    System.out.print("Enter overdraft limit: ");
                    int overdraftLimit = scanner.nextInt();

                    Account_Class checkingAccount = new CheckingAccount(bank.customers_Bank_Class_Instance_Variable.size() + 1, balance, overdraftLimit);
                    bank.createCustomer(name, checkingAccount);
                }

                case 3 -> {
                    System.out.print("Enter account number: ");
                    int accountNumber = scanner.nextInt();

                    System.out.print("Enter amount to deposit: ");
                    double amount = scanner.nextDouble();
                    Account_Class account = bank.accounts_Bank_Class_Instance_Variable.get(accountNumber);

                    if (account != null) {
                        account.deposit(amount);
                    } else {
                        System.out.println("Account not found.");
                    }
                }

                case 4 -> {
                    System.out.print("Enter account number: ");
                    int accountNumber = scanner.nextInt();

                    System.out.print("Enter amount to withdraw: ");
                    double amount = scanner.nextDouble();

                    Account_Class account = bank.accounts_Bank_Class_Instance_Variable.get(accountNumber);

                    if (account != null) {
                        account.withdraw(amount);
                    } else {
                        System.out.println("Account not found.");
                    }
                }

                case 5 -> {
                    System.out.print("Enter source account number: ");
                    int fromAccount = scanner.nextInt();

                    System.out.print("Enter destination account number: ");
                    int toAccount = scanner.nextInt();

                    System.out.print("Enter amount to transfer: ");
                    double amount = scanner.nextDouble();

                    bank.transferMoney(fromAccount, toAccount, amount);
                }

                case 6 -> {
                    System.out.print("Enter account number: ");
                    int accountNumber = scanner.nextInt();
                    Account_Class account = bank.accounts_Bank_Class_Instance_Variable.get(accountNumber);
                    account.applyInterestRate();
                }

                case 7 -> {
                    System.out.print("Enter account number: ");
                    int accountNumber = scanner.nextInt();

                    bank.viewTransactionHistory(accountNumber);
                }

                case 8 -> exit = true;

                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
        System.out.println("Thank you for using the bank automation system.");
    }
}