package Model.Application;

import Model.Account.Role;

public class CreatAccountRequest extends Request {
    private String username;
    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String passWord;
    private Role role;
    private String companyInformation;

    public CreatAccountRequest(ApplicationType applicationType, String username, String name, String lastName, String email, String phoneNumber, String passWord, Role role, String companyInformation) {
        super(applicationType);
        this.username = username;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.passWord = passWord;
        this.role = role;
        this.companyInformation = companyInformation;
    }

    public CreatAccountRequest(ApplicationType applicationType, String username, String name, String lastName, String email, String phoneNumber, String passWord, Role role) {
        super(applicationType);
        this.username = username;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.passWord = passWord;
        this.role = role;
    }
}
