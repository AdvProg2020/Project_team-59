package View.Menus;

import Model.Account.Buyer;
import View.Requests.BuyerRequest;
import View.Requests.UserRequest;

public class BuyerView extends Menu {
    private BuyerRequest buyerRequest;
    private UserRequest userRequest;
    private Buyer buyer;


    public BuyerView( Menu menu , Buyer buyer ) {
        this.headMenu = menu;
        this.buyer = buyer;
    }

    public void run(){
        String input;
        do{
            input = Menu.getInputFromUser().trim();
            getRequestType(input.toLowerCase());
            callAppropriateFunction();
        }while(!input.toLowerCase().equals("back"));
    }

    private void getRequestType(String command){
        if(command.equals("view cart")){
            buyerRequest = BuyerRequest.VIEW_CART;
        }
    }

    private void callAppropriateFunction(){
        if(buyerRequest.equals(BuyerRequest.VIEW_CART)){
            viewCart();
        }
    }

    private void viewCart(){
        String input;
        do{
            input = Menu.getInputFromUser();
            getViewCartRequest(input.toLowerCase());
            callAppropriateViewCartFunction();
        }while(!input.equals("back"));
    }

    private void getViewCartRequest(String command){
        if(command.equals("purchase")){
            buyerRequest = BuyerRequest.PURCHASE;
        }
    }

    private void callAppropriateViewCartFunction(){
        if(buyerRequest.equals(BuyerRequest.PURCHASE)){
            purchase();
        }
    }

    private void purchase(){
        new InformationReceiver(this).run(buyer);
    }

    private void help(){
        //TODO
    }
}
