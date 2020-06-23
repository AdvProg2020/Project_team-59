package View;

import Controller.Controller;
import Model.Good.Category;
import Model.Good.Good;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

/**
 * JavaFX App
 */
public class App extends Application {
    Scene mainscene,signScene,goodScene,offScene,productScene;
    @Override
    public void start(Stage stage) {
        final Controller controller = Controller.getInstance();
        /*     main page    */
        Button signInButton=new Button("sign in / sign up ");
        signInButton.setOnAction(e->stage.setScene(signScene));
        Button goodButton=new Button(" Products List ");
        goodButton.setOnAction(e->stage.setScene(goodScene));
        Button offButton=new Button(" Offs List ");
        offButton.setOnAction(e->stage.setScene(offScene));
        Text mainText=new Text("Main Page...");
        GridPane maingridPane=new GridPane();
        maingridPane.setMinSize(340,240);
        //maingridPane.setAlignment(Pos.CENTER);
        maingridPane.setVgap(20);
        maingridPane.setHgap(20);

        maingridPane.add(mainText,1,1);
        maingridPane.add(signInButton,2,3);
        maingridPane.add(offButton,1,4);
        maingridPane.add(goodButton,3,4);
        mainscene=new Scene(maingridPane);
        /*     set the first page    */
        stage.setTitle("Digi Kala");
        stage.setScene(mainscene);
        stage.show();
        /*     sign in or sign up page     */
        GridPane signGridPane=new GridPane();
        Text signText=new Text("sign Page...");
        signGridPane.add(signText,0,0);
        signGridPane.setMinSize(480 ,320);
        signScene=new Scene(signGridPane);

        /*                                                products page                                        */
        GridPane goodGridPane=new GridPane();
        Text goodText=new Text("Products Page...");
        Text sortText=new Text("sort by :");
        goodGridPane.add(sortText,7,1);
        ComboBox sortcomboBox = new ComboBox();
        sortcomboBox.getItems().add(" Price DEC");
        sortcomboBox.getItems().add(" Price ASC");
        sortcomboBox.getItems().add(" visiting Times");
        sortcomboBox.setAccessibleText("Sort");
        goodGridPane.add(sortcomboBox,8,1);

        Text categoryText=new Text("category:");
        goodGridPane.add(categoryText,9,1);
        ComboBox categorycomboBox = new ComboBox();
        Collection<Category>categories= controller.getCategories();
        for (Category category:categories) {
            categorycomboBox.getItems().add(category.getCategoryName());
        }


        goodGridPane.add(categorycomboBox,10,1);
        Collection<Good> goods =controller.getgoods();
        int i=2;
        for (Good good:goods) {

            Text textName=new Text();
            textName.setText(good.getProductName());
            goodGridPane.add(textName , 2 , i);

            Text textPrice=new Text();
            textPrice.setText(String.valueOf(good.getPrice()));
            goodGridPane.add(textPrice , 3 , i);

            Text textCharacteristic=new Text();
            textCharacteristic.setText(good.getCharacteristics());
            goodGridPane.add(textCharacteristic , 4 , i);

            Button moreButton=new Button("more");
            goodGridPane.add(moreButton,5,i);

            moreButton.setOnAction(e->stage.setScene(productScene));
            i++;
        }

        goodGridPane.setVgap(20);
        goodGridPane.setHgap(20);
        Button backButton=new Button("BACK");
        goodGridPane.add(backButton,1,1);
        backButton.setOnAction(e-> stage.setScene(mainscene));

        goodGridPane.add(goodText,5,1);

        TextField searchField=new TextField();
        searchField.setPromptText("search");
        searchField.setMaxWidth(120);

        goodGridPane.add(searchField,11,1);

        goodGridPane.setMinSize(750,480);
        goodScene=new Scene(goodGridPane);
        /*                                                       offs page                                    */
        GridPane offGridPane=new GridPane();
        Text offText=new Text("offs Page...");
        offGridPane.add(offText,1,1);
        offGridPane.setMinSize(640,480);
        offScene=new Scene(offGridPane);



        /*     product stage    */
        GridPane productPane=new GridPane();
        Text productText=new Text("product Page...");
        productPane.add(productText,1,1);
        productPane.setMinSize(640,480);
        productScene=new Scene(productPane);
    }

    public static void main(String[] args) {
        launch();
    }

}