package View;

import Controller.Controller;
import Model.Account.AccountInformation;
import Model.Account.Manager;
import Model.Account.Role;
import Model.Good.Category;
import Model.Good.Good;
import javafx.application.Application;
import javafx.css.Size;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

/**
 * JavaFX App
 */
public class App extends Application {
    Scene mainscene,signScene,goodScene,offScene,productScene,createManager;
    @Override
    public void start(Stage stage) throws FileNotFoundException {
        final Controller controller = Controller.getInstance();
        Text text=new Text("Digi Kala");
        text.setFont(new Font("Helvetica" ,20));
        stage.setTitle(text.getText());
        FileInputStream input = new FileInputStream("D:\\Maktab\\projects\\images\\background.png");
        Image image = new Image(input);
        BackgroundFill background_fill = new BackgroundFill(Color.WHITESMOKE,
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background2 = new Background(background_fill);
        ImageView backimageView = new ImageView(image);
        stage.getIcons().add(image);

        /*                                                           main scene                                          */
        GridPane maingridPane=new GridPane();
        Button signInButton=new Button("sign in / sign up ");
        signInButton.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
        signInButton.setOnAction(e->stage.setScene(signScene));
        Button goodButton=new Button(" Products List ");
        goodButton.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
        goodButton.setOnAction(e->stage.setScene(goodScene));
        Button offButton=new Button(" Offs List ");
        offButton.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
        offButton.setOnAction(e->stage.setScene(offScene));


        maingridPane.setMinSize(700,480);
        maingridPane.setAlignment(Pos.CENTER);
        maingridPane.setVgap(20);
        maingridPane.setHgap(20);

        maingridPane.setBackground(background2);
        maingridPane.add(signInButton,2,3);
        maingridPane.add(offButton,1,4);
        maingridPane.add(goodButton,3,4);
        mainscene=new Scene(maingridPane);


        /*                                                     end main scene                                                           */

        /*                                                   create manager scene                                                       */
        Text usernameText=new Text("user name : ");
        usernameText.setFont(Font.font ("Verdana", 10));
        Label usernamelabel=new Label(usernameText.getText());
        TextField usernameField=new TextField();
        Text passText=new Text("pass word : ");
        passText.setFont(Font.font ("Verdana", 10));
        Label passlabel=new Label(passText.getText());
        PasswordField passField=new PasswordField();
        Text submitText=new Text("Submit");
        submitText.setFont(Font.font("Verdana" , 15));
        Button submitButton=new Button(submitText.getText());


        GridPane gridCreateManagr=new GridPane();
        gridCreateManagr.setMinSize(480 ,320);
        gridCreateManagr.add(usernamelabel , 0 , 0);
        gridCreateManagr.add(usernameField , 1 , 0);
        gridCreateManagr.add(passlabel , 0 , 1);
        gridCreateManagr.add(passField , 1 , 1);
        gridCreateManagr.add(submitButton, 1 , 2);

        gridCreateManagr.setAlignment(Pos.BASELINE_CENTER);
        gridCreateManagr.setVgap(20);
        gridCreateManagr.setHgap(20);
        gridCreateManagr.setPadding(new Insets(100 ));

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(usernameField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridCreateManagr.getScene().getWindow(),
                            "Form Error!", "Please enter your name");
                    return;
                }
                if(passField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridCreateManagr.getScene().getWindow(),
                            "Form Error!", "Please enter a password");
                    return;
                }

                showAlert(Alert.AlertType.CONFIRMATION, gridCreateManagr.getScene().getWindow(),
                        "Registration Successful!", "Welcome " + usernameField.getText());
                controller.createManager(usernameField.getText(),passField.getText());
                stage.setScene(mainscene);

            }
        });


        createManager=new Scene(gridCreateManagr);

        /*                                                    end create manager scene                                                           */

        /*                                                        sign in scene                                                             */
        GridPane signGridPane=new GridPane();
        Text signText=new Text("sign Page...");
        signGridPane.add(signText,0,0);
        signGridPane.setMinSize(480 ,320);
        signScene=new Scene(signGridPane);

        /*                                                        end of sign in scene                                                      */

        /*      products scene          */
        GridPane goodGridPane=new GridPane();
        Text goodText=new Text("Products Page...");

        ComboBox sortcomboBox = new ComboBox();
        sortcomboBox.setPromptText("sort by");
        sortcomboBox.getItems().add(" Price DEC");
        sortcomboBox.getItems().add(" Price ASC");
        sortcomboBox.getItems().add(" visiting Times");
        sortcomboBox.setAccessibleText("Sort");
        goodGridPane.add(sortcomboBox,9,1);



        ComboBox categorycomboBox = new ComboBox();
        categorycomboBox.setPromptText("category");
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

        goodGridPane.setMinSize(700,480);
        goodScene=new Scene(goodGridPane);
        /*    end of products scene          */


        /*AccountInformation accountInformation=new AccountInformation("fa" , "jdks" , "as","sadjk" , "033","13");
        Manager manager=new Manager(accountInformation,Role.MANAGER);*/

        if (controller.isManager()){

            stage.setScene(mainscene);
            stage.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");

            alert.setHeaderText("No Manager exists");
            alert.setContentText("please enter manager information!");

            alert.showAndWait();
            stage.setScene(createManager);
            stage.show();
        }




        /*                                      ******          products page              *******                          */

        /*                                                       offs page                                    */
        GridPane offGridPane=new GridPane();
        Text offText=new Text("offs Page...");
        offGridPane.add(offText,1,1);
        offGridPane.setMinSize(700,480);
        offScene=new Scene(offGridPane);



        /*                                                       product stage                                    */
        GridPane productPane=new GridPane();
        Text productText=new Text("product Page...");
        productPane.add(productText,1,1);
        productPane.setMinSize(640,480);
        productScene=new Scene(productPane);
    }
    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }


    public static void main(String[] args) {

        launch();
    }

}