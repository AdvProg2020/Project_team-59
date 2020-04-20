package Model.Account;

import Model.Discount.OffTicket;

import java.util.ArrayList;

public abstract class Account {
    private AccountInformation accountInformation;
    private Role role;
    private ArrayList<OffTicket> offTickets;
    private double balance;

    public Account(AccountInformation accountInformation, Role role) {
        this.accountInformation = accountInformation;
        this.role = role;
        this.balance = 0;
    }

    public void giveOffTicket( OffTicket offTicket ){
        this.offTickets.add(offTicket);
    }

    public void takeBackOffTicket( OffTicket offTicket ){
        this.offTickets.remove(offTicket);
        //TODO throw exception.....
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ArrayList<OffTicket> getOffTickets() {
        return offTickets;
    }

    public AccountInformation getAccountInformation() {
        return accountInformation;
    }

    public Role getRole() {
        return role;
    }

    public double getBalance() {
        return balance;
    }
}
