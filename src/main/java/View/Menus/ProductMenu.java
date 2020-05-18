package View.Menus;

import Controller.BuyerController;
import Controller.Controller;
import Model.Account.*;
import Model.Good.Characteristic;
import Model.Good.Comment;
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
        else if (command.equals("attributes")){
            userRequest = UserRequest.SHOW_ATTRIBUTES;
        }
        else if (command.equals("Comments")){
            userRequest = UserRequest.SHOW_COMMENTS;
        }
    }

    private void callDigestAppropriateFunction(String[] inputSplit){
        if ( userRequest .equals(UserRequest.ADD_TO_CART) ){
            addProductToCart(inputSplit[3]);
        }
        else if (userRequest.equals(UserRequest.SELECT_SELLER)){
            selectSeller(inputSplit[2]);
        }
        else if (userRequest.equals(UserRequest.SHOW_ATTRIBUTES)){
            printGoodAttributes();
        }
        else if (userRequest.equals(UserRequest.SHOW_COMMENTS)){
            showComments();
        }
    }

    private void showComments(){
        for (Comment comment : goodToBeShown.getComments()) {
            if(comment.hasBought(loggedInAccount , goodToBeShown)){
                System.out.println( "title : " + comment.getTitle() + "verified purchase" + "\u2713" );
            }
            else{
                System.out.println( "title : " + comment.getTitle() );
            }
            System.out.println( "content : " + comment.getContent());
        }
        String input;
        do{
            input = Menu.getInputFromUser();
            getCommentRequest(input.trim().toLowerCase());
            callCommentFunctionRequest();
        }while(!input.equalsIgnoreCase("back"));
    }

    private void getCommentRequest(String command){
        if(command.equals("Add comment")){
            userRequest = UserRequest.ADD_COMMENT;
        }
        else if(!command.equals("back")){
            userRequest = UserRequest.COMMENT_HELP;
        }
    }

    private void callCommentFunctionRequest(){
        if(userRequest.equals(UserRequest.ADD_COMMENT)){
            addComment();
        }
    }

    private void addComment(){
        String title;
        String content;
        System.out.println("Please enter a title : ");
        title = Menu.getInputFromUser();
        System.out.println("Please enter the comment content : ");
        content = Menu.getInputFromUser();
        goodToBeShown.addComment(new Comment(goodToBeShown , loggedInAccount , title , content));
    }

    private void printGoodAttributes(){
        System.out.println("category attributes : ");
        for (Characteristic characteristic : goodToBeShown.getCategory().getCharacteristics()) {
            System.out.println(characteristic.getCharacteristicName() + " , " + characteristic.getCharacteristicExplanation());
        }
        System.out.println("good attributes : ");
        for (Characteristic characteristic : goodToBeShown.getCharacteristics()) {
            System.out.println(characteristic.getCharacteristicName() + " , " + characteristic.getCharacteristicExplanation());
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
