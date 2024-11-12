package bank;

import bank.account.Account_Class;

public class Customer_Class {
    private int customerId;
    private String name;
    private Account_Class account;

    Customer_Class(int customerId, String cname, Account_Class account){
        this.customerId = customerId;
        this.name = cname;
        this.account = account;
    }

    public int getCustomerId(){
        return customerId;
    }

    public String getName(){
        return name;
    }
}