package View.Menus;

import Controller.BuyerController;
import Model.Account.Account;
import Model.Account.Buyer;
import Model.Account.Manager;
import Model.Account.Seller;
import Model.Good.Good;
import View.Requests.BuyerRequest;
import View.Requests.UserRequest;

import java.util.ArrayList;

public class ProductMenu extends Menu{
    private Good goodToBeShown;
    private BuyerRequest buyerRequest;
    private UserRequest userRequest;
    private Account loggedInAccount;
    private Seller selectedSeller;

    public ProductMenu(Menu menu , Good good , Account account) {
        this.headMenu = menu;
        this.goodToBeShown = good;
        this.loggedInAccount = account;
    }

    public void run(){
        String input;
        do{
            input = Menu.getInputFromUser();
            getRequestType(input.trim().toLowerCase());
            if( userRequest != null ) {
                callAppropriateUserFunction(input);
            }
            else{
                //TODO
            }
        }while(!input.trim().toLowerCase().equals("back"));
    }

    private void callAppropriateUserFunction( String input ){
        if ( userRequest.equals(UserRequest.CREAT_ACCOUNT) ){
            new CreatAccountMenu(this).run(input.split(" "));
        }
        else if ( userRequest.equals(UserRequest.LOG_IN) ){
            new LogInView(this , false).run(input.split(" "));
        }
        else if ( userRequest.equals(UserRequest.DIGEST)){
            digest();
        }
    }

    private void digest(){
        printDigestion();
        String input;
        do{
            input = Menu.getInputFromUser();
            getDigestRequestType(input.trim().toLowerCase());
            callDigestAppropriateFunction(input.trim().split(" "));
        }while(!input.toLowerCase().equals("back"));
    }

    private void getDigestRequestType(String command){
        if(command.equals("add to cart")){
            userRequest = UserRequest.ADD_TO_CART;
        }
        else if (command.startsWith("select seller")){
            userRequest = UserRequest.SELECT_SELLER;
        }
    }

    private void callDigestAppropriateFunction(String[] inputSplit){
        if ( userRequest .equals(UserRequest.ADD_TO_CART) ){
            addProductToCart(inputSplit[3]);
        }
        else if (userRequest.equals(UserRequest.SELECT_SELLER)){
            selectSeller(inputSplit[2]);
        }
    }

    private void selectSeller(String sellerUsername){
        try {
            this.selectedSeller = Manager.getSellerByUsername(sellerUsername);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void addProductToCart(String goodAmountAsString){
        try{
            int goodAmount = Integer.parseInt(goodAmountAsString);
            BuyerController.addToCart(this.loggedInAccount , this.goodToBeShown , this.selectedSeller , goodAmount);
        }
        catch (Exception e){
            System.out.println("this parameter should be an integer");
        }
    }

    private void printDigestion(){
        System.out.println("product name : " + goodToBeShown.getProductName() + "\ncategory : " + goodToBeShown.getCategory().getCategoryName() );
        try {
            if (Manager.getCategoryOfSubCategoryByName(goodToBeShown.getCategory().getCategoryName()) != null) {
                System.out.println("parent category : " + Manager.getCategoryOfSubCategoryByName(goodToBeShown.getCategory().getCategoryName()).getCategoryName());
            }
        }
        catch (NullPointerException e){
            System.out.println("this good does not belong to any category :-O :( ): O-:\nERROR:403");
        }
        System.out.println("description : " + goodToBeShown.getGoodDescription() + "\nprice : " + goodToBeShown.getPrice() + "\nrating : " + goodToBeShown.getAverageRating());
    }

    private void callAppropriateBuyerFunction(){
        //TODO
    }

    private void getRequestType( String input ){
        String command = input.toLowerCase().trim();
        if ( command.startsWith("creat account")){
            userRequest = UserRequest.CREAT_ACCOUNT;
        }
        else if ( command.startsWith("login")){
            userRequest = UserRequest.LOG_IN;
        }
        else if ( command.equals("digest")){
            userRequest = UserRequest.DIGEST;
        }
    }


    private void help(){
        //TODO
    }
}
