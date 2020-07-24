package Controller.NetWork;

public class BankAccount {
    private static int id=0;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String accountNumber;
    private double balance;
    private Token token;
    public BankAccount(String firstName, String lastName, String userName, String password) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.accountNumber="627381111678"+id;
        this.id=id++;
        this.token=new Token();
    }

    public Token getingToken(){
        return token;
    }
    public String getToken() {
        return token.getTokenString();
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String bankAccount) {
        this.accountNumber = bankAccount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        BankAccount.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
