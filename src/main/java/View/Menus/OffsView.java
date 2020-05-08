package View.Menus;

import View.Requests.UserRequest;

public class OffsView extends Menu{
    private UserRequest userRequest;

    public OffsView(Menu menu) {
        this.headMenu = menu;
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
