package View.Menus;

import Model.Account.Manager;
import Model.Good.Category;
import View.Requests.ManagerRequest;
import View.Requests.UserRequest;

import java.util.ArrayList;

public class ManagerView extends Menu{
    private ManagerRequest managerRequest;
    private UserRequest userRequest;


    public ManagerView( Menu menu ) {
        this.headMenu = menu;
    }

    public void run(){
        printCategories();
        String input;
        while(!((input = Menu.getInputFromUser()).trim().equalsIgnoreCase("back"))){
            getRequestType(input);
            callAppropriateMethod();
        }
    }

    public void manageCategories(){
        String input;
        while(!((input = Menu.getInputFromUser()).trim().equalsIgnoreCase("back"))){
            getRequestManageRequestType(input);
            callAppropriateManageRequestMethod(input);
        }
    }

    private void callAppropriateManageRequestMethod(String input){
        try{
            String[] inputSplit = input.split(" ");
            if (managerRequest.equals(ManagerRequest.REMOVE_CATEGORY)){
               Manager.removeCategory(inputSplit[1]);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void printCategories(){
        ArrayList<Category> categoryList = Manager.getCategoryList();
        for (Category category : categoryList) {
            System.out.println(category.getCategoryName());
            printSubCategories(category);
        }
    }

    private void printSubCategories(Category category){
        ArrayList<Category> categoryList = category.getSubCategories();
        for (Category subCategory : categoryList) {
            System.out.println("\t" + subCategory.getCategoryName());
        }
    }

    public void getRequestManageRequestType(String input){
        if ( input.trim().toLowerCase().startsWith("edit")){
            managerRequest = ManagerRequest.EDIT_CATEGORY;
        }
        else if ( input.trim().toLowerCase().startsWith("add")){
            managerRequest = ManagerRequest.ADD_CATEGORY;
        }
        else if ( input.trim().toLowerCase().startsWith("remove")){
            managerRequest = ManagerRequest.REMOVE_CATEGORY;
        }
    }

    private void getRequestType( String input ){
        if ( input.equalsIgnoreCase("manage categories")){
            managerRequest = ManagerRequest.MANAGE_CATEGORIES;
            manageCategories();
        }
    }

    public void callAppropriateMethod(){
        if ( managerRequest.equals(ManagerRequest.MANAGE_CATEGORIES)){
            manageCategories();
        }
    }

    private void help(){
        //TODO
    }
}
