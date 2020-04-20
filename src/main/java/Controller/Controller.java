package Controller;

import Model.Account.Account;
import Model.Account.Manager;
import Model.Good.Characteristic;

import java.util.ArrayList;

public class Controller {
    private Manager coreManager;
    private Account currentAccount;
    private boolean filteringIsPossible;//not sure
    private boolean hasDigestHappened;//not sure

    public void initializer(){
        //TODO initializes core manager
    }

    public void terminator(){
        //TODO terminates program by saving all data
    }

    public void goToOffsPage(){
        //TODO opens offs page
    }

    public void goToPtoductspage(){
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
