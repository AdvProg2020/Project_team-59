package Controller;

import Model.Account.Seller;
import Model.Discount.SaleState;
import Model.Good.Category;
import Model.Good.Characteristic;
import Model.Good.Good;

import java.util.ArrayList;
import java.util.Date;

public class SellerController extends AccountController {
    private Seller loggedInSeller;

    public SellerController(Seller loggedInSeller) {
        super(loggedInSeller);
        this.loggedInSeller = loggedInSeller;
    }

    public void setLoggedInSeller(Seller loggedInSeller) {
        this.loggedInSeller = loggedInSeller;
    }

    public Seller getLoggedInSeller() {
        return loggedInSeller;
    }

    public void addSale(String saleId, ArrayList<Good> inSaleGoods, SaleState saleState, Date startingDate, Date endingDate, double offPercent ){
        //TODO sends new application to manager
    }

    public void editSale(){
        //TODO sends application to manager for editing sale
    }

    public void viewSales(){
        //TODO shows all sales
    }

    public void removeProduct(){
        //TODO removes product from offerings
    }

    public void addProduct(String productId, String productName, Category category, Category subCategory, ArrayList<Characteristic> characteristics ){
        //TODO sends application to manager to add new product and adds it to sellers list if accepted
    }

    public void editProduct( String productId, String productName, Category category, Category subCategory, ArrayList<Characteristic> characteristics ){
        //TODO sends application to manager to edit product and adds it to sellers list if accepted
    }

    public void viewProductBuyers( String productId ){
        //TODO prints all buyers of a product
    }

    public void viewSellersSales(){
        //TODO prints all sellers sales
    }

    public void viewSalesHistory(){
        //TODO prints sellers sales history
    }

    public void viewCompanyInformation(){
        //TODO prints sellers company information
    }


}
