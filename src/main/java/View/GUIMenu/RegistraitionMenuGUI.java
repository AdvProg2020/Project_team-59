package View.GUIMenu;

import Controller.Controller;
import Model.Account.Manager;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.lang.invoke.VarHandle;
import java.net.URL;

public class RegistraitionMenuGUI extends MenuGUI {

    //private URL cssFile = getClass().getResource("/Users/imanalipour/Documents/programming/java/AP-Project2020-team-59-git/src/main/resources/CssFiles/LoginMenu.css");

    public RegistraitionMenuGUI(Stage window, MenuGUI menu) {
        super(window, menu);
        window.setTitle("Register");

        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(10,50,50,50));

        HBox hb = new HBox();
        hb.setPadding(new Insets(20,20,20,30));

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20,20,20,20));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        Label lblUserName = new Label("Username");
        final TextField txtUserName = new TextField();
        Label lblPassword = new Label("Password");
        final PasswordField pf = new PasswordField();

        Label name = new Label("Name");
        final TextField txtName = new TextField();
        Label lastName = new Label("Last name");
        final TextField txtLastName = new TextField();
        Label companyInformation = new Label("Company Information");
        final TextField txtCompanyInformation = new TextField();

        Label email = new Label("Email");
        final TextField txtEmail = new TextField();
        Label lblNumber = new Label("PhoneNumber");
        final TextField txtNumber = new TextField();

        Label role = new Label("Role");
        ComboBox<String> choiceBox = new ComboBox<>();
        choiceBox.getItems().addAll("Buyer", "Seller");
        choiceBox.setValue("Buyer");
        if(Controller.getCurrentAccount() instanceof Manager){
            choiceBox.setValue("Admin");
            choiceBox.setDisable(true);
        }

        Button btnLogin = new Button("Register");
        final Label lblMessage = new Label();
        Hyperlink linkToRegistrationMenu = new Hyperlink("Or Login");
        linkToRegistrationMenu.setOnAction(e -> new LoginAndSignUp(window, menu).display());

        gridPane.add(lblUserName, 0, 0);
        gridPane.add(txtUserName, 1, 0);
        gridPane.add(lblPassword, 0, 1);
        gridPane.add(pf, 1, 1);

        gridPane.add(name, 0, 2);
        gridPane.add(txtName, 1, 2);
        gridPane.add(lastName, 0, 3);
        gridPane.add(txtLastName, 1, 3);

        gridPane.add(email, 0, 4);
        gridPane.add(txtEmail, 1, 4);
        gridPane.add(lblNumber, 0, 5);
        gridPane.add(txtNumber, 1, 5);

        gridPane.add(btnLogin, 2, 7);
        gridPane.add(lblMessage, 1, 8);
        gridPane.add(linkToRegistrationMenu, 2, 8);

        choiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            if(newValue.equals("Seller")){

                gridPane.getChildren().remove(linkToRegistrationMenu);
                gridPane.getChildren().remove(btnLogin);

                gridPane.add(companyInformation, 0, 7);
                gridPane.add(txtCompanyInformation, 1, 7);

                gridPane.add(btnLogin, 2, 8);
                gridPane.add(linkToRegistrationMenu, 2, 9);

            }
            else if (!newValue.equals("Seller")){
                gridPane.getChildren().remove(companyInformation);
                gridPane.getChildren().remove(txtCompanyInformation);
                gridPane.getChildren().remove(linkToRegistrationMenu);
                gridPane.getChildren().remove(btnLogin);

                gridPane.add(btnLogin, 2, 7);
                gridPane.add(linkToRegistrationMenu, 2, 8);
            }
        });

        gridPane.add(choiceBox, 0, 6);

        Reflection r = new Reflection();
        r.setFraction(0.7f);
        gridPane.setEffect(r);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(5);
        dropShadow.setOffsetY(5);

        Text text = new Text("Registration page");
        text.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
        text.setEffect(dropShadow);

        hb.getChildren().add(text);

        //Add ID's to Nodes
        bp.setStyle("-fx-background-color:  #e8e8e8");
        gridPane.setStyle("-fx-background-color:  linear-gradient(lightgray, gray);\n-fx-border-color: white;-fx-border-radius: 20;\n" +
                "    -fx-padding: 10 10 10 10;\n" +
                "    -fx-background-radius: 20;");
        btnLogin.setStyle("-fx-background-radius: 30, 30, 29, 28;\n" +
                "    -fx-padding: 3px 10px 3px 10px;\n" +
                "    -fx-background-color: linear-gradient(orange, orangered );");
        text.setStyle("-fx-fill:  linear-gradient(orange , orangered);");
        linkToRegistrationMenu.setStyle("-fx-color: white;");
        linkToRegistrationMenu.setFont(Font.font("Verdana", FontPosture.ITALIC, 10));

        //Action for btnLogin
        //btnLogin.setOnAction();

        bp.setTop(hb);
        bp.setCenter(gridPane);

        scene = new Scene(bp, 500, 500);
        //scene.getStylesheets().add(cssFile.toExternalForm());


        window.setOnCloseRequest(e -> {
            window.close();
            menu.display();
        });

    }

    @Override
    public void display() {
        window.setScene(scene);
        window.show();
    }
}
