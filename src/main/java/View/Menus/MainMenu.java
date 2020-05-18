package View.Menus;

import Controller.Controller;
import View.Requests.UserRequest;


public class MainMenu extends Menu{
    private UserRequest userRequest;
    Controller controller;

    public MainMenu( Controller controller ) {
        this.controller = controller;
    }

    public void run(){
        String input;
        do{
            input = Menu.getInputFromUser();
            getRequestType(input.trim().toLowerCase());
            callAppropriateFunction( input );
        }while(!input.trim().toLowerCase().equals("back"));
    }

    private void callAppropriateFunction( String input ){
        if (userRequest.equals(UserRequest.CREAT_ACCOUNT) ){
            new CreatAccountMenu(this).run(input.split(" "));
        }
        else if (userRequest.equals(UserRequest.LOG_IN) ){
            new LogInView(this , false).run(input.split(" "));
        }
        else if (userRequest.equals(UserRequest.GO_TO_OFFS)){
            new OffsView(this ).run();
        }
        else if (userRequest.equals(UserRequest.OPEN_ACCOUNTS_PAGE)){
            openAccountsPage();
        }
    }

    public void openAccountsPage(){
        try {
            Controller.openAccountsPage(this);
        }
        catch (Exception e){
            new LogInView(this , false);
        }
    }

    private void getRequestType( String input ){
        String command = input.toLowerCase().trim();
        if ( command.startsWith("creat account")){
            userRequest = UserRequest.CREAT_ACCOUNT;
        }
        else if ( command.startsWith("login")){
            userRequest = UserRequest.LOG_IN;
        }
        else if ( command.equals("offs")){
            userRequest = UserRequest.GO_TO_OFFS;
        }
        else if ( command.equals("go to accounts page")){
            userRequest = UserRequest.OPEN_ACCOUNTS_PAGE;
        }
    }

    private void help(){
        //TODO
    }
}
