package Controller;

import Model.Account.Account;
import Model.Account.Buyer;
import Model.Account.Seller;
import Model.Good.Good;

public class BuyerController extends AccountController{
    private Buyer loggedInBuyer;

    public BuyerController(Account loggedInAccount, Buyer loggedInBuyer) {
        super(loggedInAccount);
        this.loggedInBuyer = loggedInBuyer;
    }

    public Buyer getLoggedInBuyer() {
        return loggedInBuyer;
    }

    public void setLoggedInBuyer(Buyer loggedInBuyer) {
        this.loggedInBuyer = loggedInBuyer;
    }

    public void digest(Good good ){
        //TODO prints good's related information and enables ability to add to cart and select seller
    }

    public void addToCart(Good good , Seller seller , int amount ){
        //TODO adds to user/buyers cart with seller and amount
    }

    public void selectSeller( Good good ){
        //TODO check if seller exists then weather seller sells product, each need dedicated function
    }

    public void showAttributes( Good good ){
        //TODO print goods attributes
    }

    public void compare( Good good1 , String good2ProductId ){
        //TODO print two goods attributes for comparison
    }

    public void addComment( String title , String insidetTxt , Good good ){
        //TODO sends an application to manager then can be added to goods comments
    }

    public void rateProduct( String productId , int rating ){
        //TODO check if account is a buyer and has bought and then send application to manager
    }

    public void viewCart(){
        //TODO shows buyer's cart goods
    }

    public void showOrder(){
        //TODO shows buyer's cart goods // not sure
    }

    public static void purchase( Buyer buyer , double totalPrice){
        buyer.setBalance(buyer.getBalance() - totalPrice);
    }

    public void showTotalPrice(){
        //TODO prints total price of buyers cart
    }

    public void increaseProduct( String productId ){
        //TODO increases product in user/buyer cart
    }

    public void decreaseProduct( String productId ){
        //TODO decreases product in user/buyer cart
    }

    public void viewOrders(){
        //TODO prints buyers buyLogs
    }

    public void viewOrder( String orderId ){
        //TODO prints order
    }
}
