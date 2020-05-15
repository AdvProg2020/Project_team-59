package View.Menus;

import Controller.SellerController;
import Model.Account.Manager;
import Model.Account.Seller;
import Model.Discount.Sale;
import Model.Discount.SaleState;
import Model.Good.Good;
import View.Requests.SellerRequest;
import View.Requests.UserRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SellerView extends Menu{
    private Seller seller;
    private SellerRequest sellerRequest;
    private UserRequest userRequest;


    public SellerView(Menu menu , Seller seller) {
        this.headMenu = menu;
        this.seller = seller;
    }

    public void run(){
        String input;
        do{
            input = Menu.getInputFromUser();
            getRequestType(input.trim().toLowerCase());
            callFunctionAccordingToRequestType();
        }while(!input.trim().equalsIgnoreCase("back"));
    }

    private void callFunctionAccordingToRequestType(){
        if(sellerRequest.equals(SellerRequest.VIEW_SELLERS_SALES)){
            showSellersSales();
        }
    }

    private void showSellersSales(){
        ArrayList<Sale> sellersSales = SellerController.getSellersSales(this.seller);
        for (Sale sale : sellersSales) {
            System.out.println(sale.getSaleId());
        }
        String input;
        do{
            input = Menu.getInputFromUser();
            getViewSalesRequestType(input.trim().toLowerCase());
            callViewSalesFunctionAccordingToRequestType(input.trim());
        }while(!input.trim().equalsIgnoreCase("back"));
    }

    private void getViewSalesRequestType(String command){
        if( command.startsWith("view")){
            sellerRequest = SellerRequest.VIEW_SALE;
        }
        else if ( command.startsWith("edit")){
            sellerRequest = SellerRequest.EDIT_SALE;
        }
        else if ( command.equals("add off")){
            sellerRequest = SellerRequest.ADD_SALE;
        }
        else{
            sellerRequest = SellerRequest.VIEW_SALE_HELP;
        }
    }

    private void callViewSalesFunctionAccordingToRequestType(String input){
        String[] inputSplit = input.split(" ");
        if(sellerRequest.equals(SellerRequest.VIEW_SALE)){
            viewSale(inputSplit[2]);
        }
        else if ( sellerRequest.equals(SellerRequest.ADD_SALE)){
            addSaleWithExceptionHandler();
        }
    }

    public void addSaleWithExceptionHandler(){
        try{
            addSale();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void addSale()throws Exception{
        Date startingDate = getDateFromUser();
        Date endingDate = getDateFromUser();

        //TODO check local date and compare it to system date

        if( startingDate.compareTo(endingDate) < 0 || startingDate.compareTo(endingDate)== 0 ){
            throw new Exception("this sale would never happen, please try again from beginning");
        }
        ArrayList<Good> inSaleGoods = getInSaleGoods();
        double offPercent = getOffPercent();
        SellerController.sendCreatSaleApplication(inSaleGoods , SaleState.PENDING , startingDate , endingDate , offPercent );
    }

    private ArrayList<Good> getInSaleGoods(){
        String input;
        ArrayList<Good> inSaleGoods = new ArrayList<>();
        do{
            System.out.println("enter Id of the product you want to add");
            input = Menu.getInputFromUser().trim();
            try {
                if(!inSaleGoods.contains(Manager.getGoodById(input))){
                    inSaleGoods.add(Manager.getGoodById(input));
                }
                else{
                    System.out.println("you have added this product before");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("if you dont want to add any other product, enter \"end\", any other input means that you want to add another product");
            input = Menu.getInputFromUser();
        }while(!input.trim().toLowerCase().equals("end"));
        return inSaleGoods;
    }

    private Double getOffPercent(){
        double offPercent;
        while (true) {
            System.out.println("Type a number as off percent(should be a number between 0 and 100) : ");
            try {
                offPercent = Double.parseDouble(Menu.getInputFromUser());
                if(offPercent > 0 && offPercent < 100){
                    return offPercent;
                }
                System.out.println("a percent is a number between 0 and 100, ding dong :/ ");
            } catch (NumberFormatException ignore) {
                System.out.println("Invalid input");
            }
        }
    }
    private Date getDateFromUser(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        Date date2=null;
        while(date2 == null) {
            System.out.println("enter a date, format should be like : \"dd-MMM-yyyy HH:mm:ss\"");
            String date = Menu.getInputFromUser();
            try {
                date2 = dateFormat.parse(date);
            } catch (ParseException e) {
                System.out.println("date format is invalid");
            }
        }
        return date2;
    }

    private void viewSale(String saleId){
        try {
            showSellersSale(SellerController.getSellersSale(seller , saleId));
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void showSellersSale(Sale sale){
        System.out.println(sale.getSaleId());
        System.out.println("starting date : " + sale.getStartingDate() + " , ending date : " + sale.getEndingDate() + " , sale percent : " + sale.getOffPercent() + "%" );
        System.out.println("in sale products : ");
        for (Good inSaleGood : sale.getInSaleGoods()) {
            System.out.println("product name : " + inSaleGood.getProductName() + " , product Id : " + inSaleGood.getProductId());
        }
    }

    private void getRequestType(String command){
        if(command.startsWith("view offs")){
            sellerRequest = SellerRequest.VIEW_SELLERS_SALES;
        }
    }

    private void help(){
        //TODO
    }
}
