package Controller;

import Model.Account.*;
import Model.Good.Characteristic;
import View.Menus.CreatAccountMenu;

import java.util.ArrayList;

public class Controller {
    private static Manager coreManager;
    private Account currentAccount;
    private boolean filteringIsPossible;//not sure
    private boolean hasDigestHappened;//not sure

    public Controller() {
    }

    public static void initializer(){
        //TODO initializes core manager
    }

    public static void creatAdmin( AccountInformation accountInformation ){
        coreManager = new Manager( accountInformation , Role.MANAGER );
    }

    public static void sendCreatAccountApplication( ArrayList<String> input ){
        coreManager.addApplication(new Application( input , ApplicationType.CREAT_ACCOUNT ));
    }

    public static boolean usernameExists(String username){
        return coreManager.getAccountByUsername(username) != null;
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
