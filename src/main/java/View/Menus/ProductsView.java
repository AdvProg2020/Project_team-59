package View.Menus;

import Controller.Controller;
import View.Requests.UserRequest;

public class ProductsView extends Menu{
    private UserRequest userRequest;

    public ProductsView(Menu menu) {
        this.headMenu = menu;
    }

    public void run(){
        String input;
        do{
            input = Menu.getInputFromUser();
            getRequestType(input.trim().toLowerCase());
            callAppropriateUserFunction(input);

        }while(!input.trim().equalsIgnoreCase("back"));
    }

    private void callAppropriateUserFunction( String input ){
        if ( userRequest.equals(UserRequest.CREAT_ACCOUNT) ){
            new CreatAccountMenu(this).run(input.split(" "));
        }
       else if ( userRequest.equals(UserRequest.LOG_IN) ){
            new LogInView(this , true).run(input.split(" "));
        }
       else if ( userRequest.equals(UserRequest.PURCHASE)){
            purchase();
        }
    }

    private void purchase(){
        try {
            Controller.purchase();
        }
        catch ( Exception e ){
            System.out.println(e.getMessage());
            new LogInView(this , true);
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
        else if ( command.equals("purchase")){
            userRequest = UserRequest.PURCHASE;
        }
    }

    private void help(){
        //TODO
    }
}
