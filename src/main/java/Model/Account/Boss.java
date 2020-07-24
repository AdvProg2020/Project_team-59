package Model.Account;

public class Boss extends Manager{

    private static Boss ourInstance;
    private double minimumInWallet;

    public double getMinimumInWallet() {
        return minimumInWallet;
    }

    public void setMinimumInWallet(double minimumInWallet) {
        this.minimumInWallet = minimumInWallet;
    }

    private Boss(AccountInformation accountInformation, Role role) {
        super(accountInformation, role);
        minimumInWallet=0;
    }
    public Boss getInstance(AccountInformation accountInformation){
        if (ourInstance==null){
            Role role=Role.MANAGER;
            ourInstance=new Boss(accountInformation,role);

        }

        return ourInstance;

    }
}
