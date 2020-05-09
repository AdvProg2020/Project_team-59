package View.Menus;

import Controller.Controller;
import Model.Account.Account;
import Model.Account.Manager;
import View.Requests.UserRequest;

public class LogInView extends Menu{
    private UserRequest userRequest;

    public LogInView(Menu menu) {
        this.headMenu = menu;
    }

    public void run( String[] splitInput ){
        if ( !Controller.usernameExists(splitInput[1]) ){
            System.out.println("no user with such username exists");
            this.headMenu.run();
        }
        System.out.println("please enter your password: ");
        Account account = Manager.getAccountByUsername(splitInput[1]);
        String input = Menu.getInputFromUser();
        if ( account.passwordIsCorrect(input) ){
            Controller.setCurrentAccount(account);
        }
        else{
            System.out.println("password is incorrect");
        }
        this.headMenu.run();
    }

    private void help(){
        //TODO
    }
}
