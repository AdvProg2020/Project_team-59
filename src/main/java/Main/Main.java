package Main;

import Controller.Controller;
import View.GUIMenu.LoginAndSignUp;
import View.GUIMenu.RegistraitionMenuGUI;
import View.Menus.*;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage stage) throws IOException {
        new RegistraitionMenuGUI(stage, new LoginAndSignUp(stage, null)).display();
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
