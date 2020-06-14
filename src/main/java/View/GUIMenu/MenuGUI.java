package View.GUIMenu;

import com.sun.javafx.stage.EmbeddedWindow;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class MenuGUI {
    protected Stage window;
    protected Scene scene;

    public MenuGUI(Stage window) {
        this.window = window;
    }

    public void display(){
        window.setScene(scene);
        window.show();
    }
}
