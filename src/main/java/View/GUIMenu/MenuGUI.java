package View.GUIMenu;

import View.Menus.Menu;
import com.sun.javafx.stage.EmbeddedWindow;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class MenuGUI {
    protected Stage window;
    protected Scene scene;
    protected Scene headScene;

    public MenuGUI(Stage window, Scene headScene) {
        this.window = window;
        this.headScene = headScene;
    }

    public void display(){
        window.setScene(scene);
        window.show();
    }
}
