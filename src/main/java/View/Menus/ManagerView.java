package View.Menus;

import Model.Account.Manager;
import Model.Good.Category;
import Model.Good.Characteristic;
import Model.Good.Good;
import View.Requests.ManagerRequest;
import View.Requests.UserRequest;

import java.lang.reflect.Array;
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
            callAppropriateManageCategoryMethod(input);
        }
    }

    private void callAppropriateManageCategoryMethod(String input){
        String[] inputSplit = input.split(" ");
        if (managerRequest.equals(ManagerRequest.REMOVE_CATEGORY)){
            try {
                Manager.removeCategory(inputSplit[1]);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        else if(managerRequest.equals(ManagerRequest.ADD_CATEGORY)){
            creatCategory(inputSplit[1]);
        }

    }

    private void creatCategory(String categoryName){
        if (Manager.categoryExists(categoryName)){
            System.out.println("category already exists with this name.");
            return;
        }
        ArrayList<Characteristic> categoryCharacteristics = getCategoryCharacteristics();
        ArrayList<Good> categoryGoods = getCategoryGoods();
        Manager.creatCategory(categoryName,categoryCharacteristics,categoryGoods);
    }

    public ArrayList<Good> getCategoryGoods(){
        ArrayList<Good> categoryGoods = new ArrayList<>();
        String productId;
        do{
            System.out.println("please enter goods Id");
            productId = Menu.getInputFromUser().trim();
            if ( Manager.getGoodById(productId) != null ){
                categoryGoods.add(Manager.getGoodById(productId));
            }
            else{
                System.out.println("no product exists with this Id");
            }
            System.out.println("if you want to add another one, enter Y or y, any other input ends this part");
            productId = Menu.getInputFromUser();
        }while(productId.trim().equalsIgnoreCase("y"));
        return categoryGoods;
    }

    public ArrayList<Characteristic> getCategoryCharacteristics(){
        ArrayList<Characteristic> categoryCharacteristics = new ArrayList<>();
        String title;
        String description;
        do{
            System.out.println("please enter title of characteristic");
            title = Menu.getInputFromUser();
            System.out.println("please enter description of characteristic");
            description = Menu.getInputFromUser();
            categoryCharacteristics.add(new Characteristic(title,description));
            System.out.println("if you want to add another one, enter Y or y, any other input ends this part");
            title = Menu.getInputFromUser();
        }while(title.trim().equalsIgnoreCase("y"));
        return categoryCharacteristics;
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
