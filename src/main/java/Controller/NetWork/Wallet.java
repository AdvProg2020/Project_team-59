package Controller.NetWork;

import Model.Account.Boss;

import java.util.ArrayList;

public class Wallet {
    private double balance;
    private BankAccount bankAccount;
    private String userName;
    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Wallet(double balance) {
        this.balance = balance;
    }

    public void addBalance(double money){
        if (bankAccount.getBalance() < money){
            System.out.println("cant sharj not enough money");
        }
        else {
            bankAccount.setBalance(bankAccount.getBalance()-money);
            increase("superMarket",money);
            this.balance=balance+money;
        }
    }
    public void removeBalance(double money){
        Boss boss = null;
        boss=boss.getInstance(null);
        double minimumWallet=boss.getMinimumInWallet();
        if (balance-minimumWallet>money){
            decreaseMoney(money ,userName );
        }
    }
    public void increase(String userName,double money){
        Bank bank=new Bank();
        ArrayList<BankAccount>bankAccounts=new ArrayList<>();
        Bank.BankImpl bank1=new Bank.BankImpl();
        bankAccounts=bank1.getAccounts();
        for (BankAccount bankAccount:bankAccounts) {
            if (bankAccount.getUserName().equals(userName)){
                bankAccount.setBalance(bankAccount.getBalance()-money);
                balance+=money;
            }
        }
    }
    public void decreaseMoney(double money,String userName){
        Bank bank=new Bank();
        ArrayList<BankAccount>bankAccounts=new ArrayList<>();
        Bank.BankImpl bank1=new Bank.BankImpl();
        bankAccounts=bank1.getAccounts();
        for (BankAccount bankAccount:bankAccounts) {
            if (bankAccount.getUserName().equals(userName)){
                bankAccount.setBalance(bankAccount.getBalance()+money);
                balance-=money;
            }
        }
    }
}
