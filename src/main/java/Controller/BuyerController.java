package Controller;

import Model.Account.Account;
import Model.Account.Buyer;
import Model.Account.Seller;
import Model.Discount.OffTicket;
import Model.Good.Good;
import Model.log.BuyLog;
import Model.log.Log;
import Model.log.ShipmentState;
import Model.Good.Rating;
import Model.log.BuyLog;
import Model.log.ShipmentState;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

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

    public void addComment( String title , String insideText , Good good ){
        //TODO sends an application to manager then can be added to goods comments
    }

    public void rateProduct( String productId , int rating ){
        //TODO check if account is a buyer and has bought and then send application to manager
        ArrayList<BuyLog> buyLogs =loggedInBuyer.getBuyLog() ;
        for (BuyLog buyLog : buyLogs)
        {
            ArrayList<Good> goods ;
            goods = buyLog.getGoodsExchanged();
            for (Good good : goods)
            {
                if (good.getProductId().equals(productId))
                {
                    Rating rating1 = new Rating(loggedInBuyer , rating , good) ;
                    good.addRating (rating1) ;
                    return ;
                }
            }
        }
        System.out.println("you must buy a product then you can rate it.") ;

    }

    public void viewCart(){
        //TODO shows buyer's cart goods
    }

    public void showOrder(){
        //TODO shows buyer's cart goods // not sure
    }

    public static int generateRandomNumber(int min , int max){
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static void purchase( Buyer buyer , double totalPrice) throws Exception {
        if(totalPrice > buyer.getBalance()){
            throw new Exception("insufficient funds");
        }
        buyer.setBalance(buyer.getBalance() - totalPrice);
        ArrayList<Good> tmpCart = new ArrayList<>();
        buyer.getCart().keySet().forEach(p -> tmpCart.add(p));
        Seller seller = tmpCart.get(0).getSellers().get(0);
        buyer.getBuyLog().add(new BuyLog(null, new Date(), totalPrice, buyer.getCartValue()-totalPrice, tmpCart, ShipmentState.PAID, seller.getAccountInformation().getName()));
        buyer.getCart().clear();
        if(totalPrice > 1000000){
            //buyer.getOffTickets().add(new OffTicket(new Date() , addDaysToADate(new Date()) ,  generateRandomNumber(0 , 90) , generateRandomNumber(5000 , 1000000) , generateRandomNumber(1,5) , new ArrayList<Buyer>(Arrays.asList(buyer))));
        }
    }

    public static Date addDaysToADate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,generateRandomNumber(1,7));
        calendar.add(Calendar.MONTH,generateRandomNumber(0,1));
        return calendar.getTime();
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
        ArrayList<BuyLog> buyLogs =loggedInBuyer.getBuyLog() ;
        for (BuyLog buyLog : buyLogs)
        {
            System.out.println("Date :" + buyLog.getDate () +"logId :" + buyLog.getLogId());
        }
    }

    public void viewOrder( String orderId ){
        //TODO prints order
        BuyLog buyLog = loggedInBuyer.getBuyLogById (orderId) ;
        System.out.println("Date :" + buyLog.getDate());
        System.out.println("Paid money :"+ buyLog.getMoneyExchanged());
        System.out.println("Discount amount :" + buyLog.getDiscountAmount());
        System.out.println("Buyed goods :");
        ArrayList<Good> buyedGoods = buyLog.getGoodsExchanged () ;
        for (Good good : buyedGoods) {
            System.out.print("     " + good.getProductName());

        }
        System.out.println("Shipment state :");
        ShipmentState shipmentState = buyLog.getShipmentState () ;
        if (shipmentState.equals(ShipmentState.PAID))
            System.out.println("Paid");
        else if (shipmentState.equals(ShipmentState.READY_TO_BE_SENT))
            System.out.println("Ready to be sent");
        else if (shipmentState.equals(ShipmentState.SENT))
            System.out.println("Sent");
        else if (shipmentState.equals(ShipmentState.ARRIVED_TO_COUMER))
            System.out.println("Arrived");
        System.out.println("Seller name :" + buyLog.getSellerName());



    }
}
