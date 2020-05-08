package View.Menus;

import View.Requests.BuyerRequest;
import View.Requests.UserRequest;

public class Productmenu extends Menu{
    private BuyerRequest buyerRequest;
    private UserRequest userRequest;

    public Productmenu(Menu menu) {
        this.headMenu = menu;
    }

    public void run(){
        String input;
        while(true){
            input = Menu.getInputFromUser();
            getRequestType(input.trim().toLowerCase());
            if( userRequest != null ) {
                callAppropriateUserFunction(input);
            }
            else{
                //TODO
            }
        }
    }

    private void callAppropriateUserFunction( String input ){
        if ( userRequest.equals(UserRequest.CREAT_ACCOUNT) ){
            new CreatAccountMenu(this).run(input.split(" "));
        }
    }

    private void callAppropriateBuyerFunction(){
        //TODO
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
