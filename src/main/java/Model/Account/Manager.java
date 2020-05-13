package Model.Account;

import Model.Discount.OffTicket;
import Model.Good.Category;
import Model.Good.Good;

import java.util.ArrayList;

public class Manager extends Account{
    private static ArrayList<OffTicket> offTicketList = new ArrayList<>();
    private static  ArrayList<Account> accountList = new ArrayList<>();
    private static ArrayList<Category> categoryList = new ArrayList<>();
    private static ArrayList<Good> allGoodsList = new ArrayList<>();
    private static ArrayList<Application> applications = new ArrayList<>();
    private static ArrayList<Manager> allManagers = new ArrayList<>();

    public Manager(AccountInformation accountInformation, Role role) {
        super(accountInformation, Role.MANAGER);
        allManagers.add(this);
        accountList.add(this);
    }

    public static Account getAccountByUsername(String username){
        for (Account account : accountList) {
            if (account.getAccountInformation().getUsername().equals(username)){
                return account;
            }
        }
        return null;
    }

    public static ArrayList<Category> getCategoryList() {
        return categoryList;
    }

    public Good getGoodById(String productId){
        for (Good good : allGoodsList) {
            if ( good.getProductId().equals(productId) ){
                return good;
            }
        }
        //TODO throw exception instead of down return statement
        return null;
    }

    public Account getSellerById(String sellerUsername){
        for (Account account : accountList) {
            if( account.getAccountInformation().getUsername().equals(sellerUsername) && account instanceof Seller )
                return account;
        }
        //TODO throw exception instead of down return statement
        return null;
    }

    public OffTicket getOffTicketById( String offTicketId ){
        for (OffTicket offTicket : offTicketList) {
            if ( offTicket.getOffTicketId().equals(offTicketId) )
                return offTicket;
        }
        //TODO throw exception instead of down return statement
        return null;
    }

    public static Category getCategoryBuName( String categoryName ){
        for (Category category : categoryList) {
            if(category.getCategoryName().equals(categoryName))
                return category;
        }
        return null;
    }

    public static Category getCategoryOfSubCategoryByName(String name){
        for (Category category : categoryList) {
            for (Category subCategory : category.getSubCategories()) {
                if ( subCategory.getCategoryName().equals(name)){
                    return category;
                }
            }
        }
        return null;
    }

    private static void removeSubCategory(String subCategoryName) throws Exception {
        Category category = getCategoryOfSubCategoryByName(subCategoryName);
        category.getSubCategories().removeIf(subCategory -> subCategory.getCategoryName().equals(subCategoryName));
        throw new Exception("no category with such name");
    }

    public void addApplication( Application application ){
        applications.add( application );
    }

    public static void removeCategory(String categoryName) throws Exception {
        Category category = getCategoryBuName(categoryName);
        if ( category != null ){
            categoryList.remove(category);
        }
        else{
            removeSubCategory(categoryName);
        }

    }

}
