package Model.Account;

import Model.Discount.OffTicket;
import Model.Good.Category;
import Model.Good.Characteristic;
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

    public static Good getGoodById(String productId) throws Exception{
        for (Good good : allGoodsList) {
            if ( good.getProductId().equals(productId) ){
                return good;
            }
        }
        throw new Exception("product not found");
    }

    public Account getSellerById(String sellerUsername) throws Exception{
        for (Account account : accountList) {
            if( account.getAccountInformation().getUsername().equals(sellerUsername) && account instanceof Seller )
                return account;
        }
        throw new Exception("seller not found");
    }

    public OffTicket getOffTicketById( String offTicketId ){
        for (OffTicket offTicket : offTicketList) {
            if ( offTicket.getOffTicketId().equals(offTicketId) )
                return offTicket;
        }
        //TODO throw exception instead of down return statement
        return null;
    }

    public static Category getCategoryByName(String categoryName) throws Exception{
        for (Category category : categoryList) {
            if(category.getCategoryName().equals(categoryName)){
                return category;
            }
            for (Category subCategory : category.getSubCategories()) {
                if(subCategory.getCategoryName().equals(categoryName)){
                    return subCategory;
                }
            }
        }
        throw new Exception("category not found");
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

    public void addApplication( Application application ){
        applications.add( application );
    }

    public static boolean categoryExists(String categoryName){
        for (Category category : categoryList) {
            if ( category.getCategoryName().equals(categoryName) ){
                return true;
            }
        }
        return false;
    }

    public static void creatCategory(String categoryName, ArrayList<Characteristic> characteristics , ArrayList<Good> goodsInCategory){
        categoryList.add( new Category(categoryName , characteristics , goodsInCategory) );
    }


}
