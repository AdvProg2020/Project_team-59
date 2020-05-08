package View.Menus;

import View.Requests.UserRequest;

public class LogInView extends Menu{
    private UserRequest userRequest;

    public LogInView(Menu menu) {
        this.headMenu = menu;
    }

    public void run(){
        String input;
        while(true){
            input = Menu.getInputFromUser();
            getRequestType(input.trim().toLowerCase());
            callAppropriateUserFunction(input);

        }
    }

    private void callAppropriateUserFunction( String input ){
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
