package Controller;

import Model.Account.AccountInformation;
import Model.Account.Manager;
import Model.Good.Category;
import Model.Good.Characteristic;
import Model.Good.Good;

import java.util.ArrayList;

public class ManagerController extends AccountController{
    private Manager loggedInManager;

    public ManagerController(Manager loggedInManager) {
        super(loggedInManager);
        this.loggedInManager = loggedInManager;
    }

    public Manager getLoggedInManager() {
        return loggedInManager;
    }

    public void setLoggedInManager(Manager loggedInManager) {
        this.loggedInManager = loggedInManager;
    }

    public void manageUsers(){
        //TODO check if account is manager then allow it to use methods
    }

    public void viewUser( String userId ){
        //TODO check if current account is manager and continue
    }

    public void deleteUser( String userId ){
        //TODO check if current account is manager and continue
    }

    public void creatNewManager(AccountInformation accountInformation){
        //TODO instantly creates new manager if current is manager
    }

    public void manageAllProducts(){
        //TODO check if account is manager then allow it to use methods
    }

    public void removeProduct( String productId){
        //TODO check if account is manager then remove good, remember to remove good from all sellers as well
    }

    public void viewDiscountCodes(){
        //TODO check if account is manager then allow it to use methods
    }

    public void viewDiscountCode( String offTicketId ){
        //TODO
    }

    public void editDiscountCode( String offTicketId ){
        //TODO
    }

    public void removeDiscountCode( String offTicketId ){
        //TODO
    }

    public void viewRequests(){
        //TODO check if account is manager then allow it to use methods
    }

    public void viewRequestDetails( String requestId ){
        //TODO check if account is manager then allow it to use methods
    }

    public void acceptRequest( String requestId ){
        //TODO
    }

    public void rejectRequest( String requestId ){
        //TODO
    }

    public void manageProducts(){
        //TODO
    }

    public void manageCategories(){
        //TODO check if account is manager then allow it to use methods
    }

    public void editCategory( String categoryName ){
        //TODO enables manager to edit category
    }

    public void addCategory(String categoryName, ArrayList<Characteristic> characteristics, Category parentCategory, ArrayList<Good> goodsInCategory ){
        //TODO manager creat's new category
    }

    public void removeCategory( String categoryName ){
        //TODO manager deletes category
    }
}
