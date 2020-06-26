package Main;

import Controller.Controller;
import Model.Account.AccountInformation;
import Model.Account.Buyer;
import Model.Account.Role;
import Model.Good.Good;
import View.GUIMenu.LoginAndSignUp;
import View.GUIMenu.PurchasePageGUI;
import View.GUIMenu.RegistraitionMenuGUI;
import View.GUIMenu.ShoppingCartGUI;
import View.Menus.*;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage stage) throws IOException {

        HashMap<Good, Integer> cart = new HashMap<>();
        cart.put(new Good(20.0, "bnana"), 5);
        cart.put(new Good(20.0, "orange"), 1);
        cart.put(new Good(15.0, "sad"), 10);
        cart.put(new Good(0.9, "asdasd"), 12);

        Buyer buyer = new Buyer(new AccountInformation("iman", "iman", "alipour", "asdasd@asd.ads", "2131231", "a"), Role.BUYER);
        buyer.addItemsToCart(cart);

        new ShoppingCartGUI(stage, null, buyer).display();

    }

    public static void startProgram(){
        Controller controller = new Controller();
        startProgram();
        if ( !new File("database.ifs").exists() ){
            new CreatAccountMenu( null ).creatAdmin();
        }
        else{
            Controller.initializer();
        }
    }
}
