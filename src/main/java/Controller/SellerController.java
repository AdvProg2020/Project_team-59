package Controller;

import Model.Application.Application;
import Model.Application.ApplicationType;
import Model.Account.Manager;
import Model.Account.Seller;
import Model.Application.CreatAccountApplication;
import Model.Application.CreatSaleApplication;
import Model.Discount.Sale;
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

    public static void sendCreatSaleApplication(ArrayList<Good> inSaleGoods, SaleState saleState, Date startingDate, Date endingDate, double offPercent ){
        Manager.addApplication(new CreatSaleApplication( ApplicationType.CREAT_SALE , inSaleGoods , SaleState.PENDING , startingDate , endingDate , offPercent ));
    }

    public void editSale(){
        //TODO sends application to manager for editing sale
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

    public static ArrayList<Sale> getSellersSales(Seller seller){
        return seller.getSaleList();
    }

    public static Sale getSellersSale(Seller seller , String saleId) throws Exception{
        for (Sale sale : seller.getSaleList()) {
            if(Integer.toString(sale.getSaleId()).equals(saleId)){
                return sale;
            }
        }
        throw new Exception("this sale either does not exist, or is not this sellers sale");
    }

    public void viewSalesHistory(){
        //TODO prints sellers sales history
    }

    public void viewCompanyInformation(){
        //TODO prints sellers company information
    }


}
