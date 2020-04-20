package Model.Account;

public class AccountInformation {
    private String username;
    private String name;
    private String  lastname;
    private String email;
    private int phoneNumber;
    private String passWord;

    public void emailIsValid( String email ){
        // TODO IMPLEMENT change type to boolean...
    }

    public void phoneNumberIsValid( String phoneNumber ){
        // TODO IMPLEMENT change type to boolean...
    }

    public AccountInformation(String username, String name, String lastname, String email, String phoneNumber, String passWord) {
        this.username = username;
        this.name = name;
        this.lastname = lastname;
        //this.email = email;
        //this.phoneNumber = phoneNumber;
        this.passWord = passWord;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
