package View.Menus;

import Controller.Controller;
import View.Requests.UserRequest;

import java.util.ArrayList;

public class MainMenu extends Menu{
    private UserRequest userRequest;
    Controller controller;

    public MainMenu( Controller controller ) {
        this.controller = controller;
    }

    public void run(){
        String input;
        while(true){
            input = Menu.getInputFromUser();
            getRequestType(input.trim().toLowerCase());
            callAppropriateFunction( input );
        }
    }

    private void callAppropriateFunction( String input ){
        if ( userRequest.equals(UserRequest.CREAT_ACCOUNT) ){
            new CreatAccountMenu(this).run(input.split(" "));
        }
    }

    private void getRequestType( String input ){
        if ( input.startsWith("creat account")){
            userRequest = UserRequest.CREAT_ACCOUNT;
        }
    }

    private void help(){
        //TODO
    }
}
