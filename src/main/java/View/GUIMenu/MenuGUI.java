package View.GUIMenu;

import View.Menus.Menu;
import com.sun.javafx.stage.EmbeddedWindow;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class MenuGUI {
    protected Stage window;
    protected Scene scene;
    protected MenuGUI menu;

    public MenuGUI(Stage window, MenuGUI menu) {
        this.window = window;
        this.menu = menu;
    }

    public void display(){
        window.setScene(scene);
        window.show();
    }
}
