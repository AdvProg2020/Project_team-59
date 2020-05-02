package View.Menus;

import Controller.Controller;
import Model.Account.AccountInformation;
import Model.Account.Role;

import java.util.regex.Pattern;


public class CreatAccountMenu extends Menu{
    private String username;
    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String passWord;

    public CreatAccountMenu(Menu menu) {
        this.headMenu = menu;
    }

    @Override
    public void run(){

    }

    public void creatAdmin(){
        getAdminInformation();
        Controller.creatAdmin( new AccountInformation( this.username , this.name , this.lastName , this.email , this.phoneNumber , this.passWord ) );
    }

    public void getAdminInformation(){
        getUserUsername();
        getUserPassword();
        getUserName();
        getUserLastName();
        getUserEmail();
        getUserPhoneNumber();
    }

    public void getUserUsername(){
        System.out.println("please enter a username");
        this.username = Menu.getInputFromUser().trim();
    }

    public void getUserPassword(){
        System.out.println("please enter a password");
        this.passWord = Menu.getInputFromUser().trim();
    }

    public void getUserName(){
        System.out.println("please enter your name");
        this.name = Menu.getInputFromUser().trim();
        if ( name.matches(".*\\d.*") ){
            System.out.println("what the... whose name has digits in it....? any ways....");
        }
    }

    public void getUserLastName(){
        System.out.println("please enter your last name");
        this.lastName = Menu.getInputFromUser().trim();
        if ( lastName.matches(".*\\d.*") ){
            System.out.println("what the... whose last name has digits in it....? any ways....");
        }
    }

    public void getUserEmail(){
        int flag = 0;
        do {
            if( flag == 1 ){
                System.out.println("this email is not valid.... who are you trying too fool huh??");
            }
            flag = 1;
            System.out.println("please enter your email");
            this.email = Menu.getInputFromUser().trim();
        }while( !emailIsValid(this.email) );
    }

    public void getUserPhoneNumber(){
        int flag = 0;
        do {
            if( flag == 1 ){
                System.out.println("in with galaxy, phone numbers have characters you said?");
            }
            flag = 1;
            System.out.println("please enter your phone number");
            this.phoneNumber = Menu.getInputFromUser().trim();
        }while( !phoneNumber.matches("^\\d+$") );
    }

    public static boolean emailIsValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}
