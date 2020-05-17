package Controller;

import Model.Account.*;
import Model.Application.ApplicationType;
import Model.Application.CreatAccountApplication;
import Model.Discount.OffTicket;
import Model.Good.Characteristic;

import java.util.ArrayList;
import java.util.Date;

public class Controller {
    private static Manager coreManager;
    private static Account currentAccount;
    private static User currentUser;
    private boolean filteringIsPossible;//not sure
    private boolean hasDigestHappened;//not sure

    public Controller() {
    }

    public static void initializer(){
        //TODO initializes core manager
        //TODO sets up current user
    }

    public static void creatAdmin( AccountInformation accountInformation ){
        coreManager = new Manager( accountInformation , Role.MANAGER );
        currentUser = new User();
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void sendCreatAccountApplication(String userName , String name , String lastName , String email , String phoneNumber , String password , Role role ){
        Manager.addApplication(new CreatAccountApplication( ApplicationType.CREAT_ACCOUNT , userName, name, lastName, email, phoneNumber, password, role ));
    }

    public static double useOffTicket(String offTicketId , Buyer buyer)throws Exception{
        OffTicket offTicket;
        for (OffTicket temporaryOffTicket : buyer.getOffTickets()) {
            if(temporaryOffTicket.getOffTicketId().equals(offTicketId)){
                offTicket = temporaryOffTicket;
                return useOffThicketIfPossible(buyer , offTicket);
            }
            else{
                throw new Exception("you dont owe such off ticket");
            }
        }
        return buyer.getCartValue();
    }

    public static double useOffThicketIfPossible(Buyer buyer , OffTicket offTicket) throws Exception{
        if(offTicket.getEndingDate().compareTo(new Date()) < 0){
            throw new Exception("off ticket is expired");
        }
        else if(offTicket.getTimesCanBeUsed() < 1){
            throw new Exception("no more usages are available for this off ticket");
        }
        else {
            offTicket.setTimesCanBeUsed(offTicket.getTimesCanBeUsed()-1);
            return (1 - (offTicket.getOffAmount() / 100)) * buyer.getCartValue();
        }
    }

    public static void setCurrentAccount(Account account) {
        currentAccount = account;
    }

    public static boolean usernameExists(String username){
        return Manager.getAccountByUsername(username) != null;
    }

    public static void terminator(){
        //TODO terminates program by saving all data
    }

    public void goToOffsPage(){
        //TODO opens offs page
    }

    public void goToProductPage(){
        //TODO opens products page
    }

    public void showCategories(){
        //TODO prints categories
    }

    public void showProduct( String productId ){
        //TODO opens products page and increase goods times visited
    }

    public void showCurrentFilters(){
        //TODO prints current filters
    }

    public void filtering(){
        //TODO ?
    }

    public void showAvailableFilters(){
        //TODO prints possible filters
    }

    public void filter(Characteristic characteristic){
        //TODO filters by characteristic and adds it to filters
    }

    public void disableFilter( Characteristic characteristic ){
        //TODO disables filter
    }

    public void showCurrentSorts(){
        //TODO prints currentSorts
    }

    public void sorting(){
        //TODO ?
    }

    public void showAvailableSorts(){
        //TODO prints possible sorts
    }

    public void sorter(Characteristic characteristic){
        //TODO sorts by characteristic
    }

    public void disableSort( Characteristic characteristic ){
        //TODO disables sort and resets it to "times visited"
    }

    public void showProducts( ArrayList<Characteristic> filters , Characteristic sort ){
        //TODO filters then sorts then prints products
    }

    public void viewProduct( String productId ){
        //TODO show product
    }
}
