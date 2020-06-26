package View.GUIMenu;

import Model.Account.Buyer;
import Model.Good.Good;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;


public class ShoppingCartGUI extends MenuGUI{

    private TableView<Map.Entry<Good, Integer>> cart;
    private HashMap<Good, Integer> cartmap;
    private Button addButton;
    private Button reduceButton;
    private Button purchase;
    private Buyer buyer;

    public ShoppingCartGUI(Stage window, MenuGUI menu, HashMap<Good, Integer> cartmap) {
        super(window, menu);

        this.cartmap = cartmap;

        //Number


        //Name column
        TableColumn<Map.Entry<Good, Integer>, String> nameColumn = new TableColumn<>("Product Name");
        nameColumn.setMaxWidth(200);
        nameColumn.setCellValueFactory(p -> new SimpleObjectProperty(p.getValue().getKey().getProductName()));


        //Price column
        TableColumn<Map.Entry<Good, Integer>, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setMaxWidth(200);
        priceColumn.setCellValueFactory(p -> new SimpleObjectProperty(p.getValue().getKey().getPrice()));

        //Quantity column
        TableColumn<Map.Entry<Good, Integer>, Double> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMaxWidth(200);
        quantityColumn.setCellValueFactory(p -> new SimpleObjectProperty(p.getValue().getValue()));

        //Total Amount of each products column
        /*
        TableColumn<Map.Entry<Good, Integer>, Double> amountColumn = new TableColumn<>("Total amount");
        amountColumn.setMaxWidth(200);
        quantityColumn.setCellValueFactory(p -> new SimpleObjectProperty((p.getValue().getValue()) * (p.getValue().getKey().getPrice())));
         */

        ObservableList<Map.Entry<Good, Integer>> items = FXCollections.observableArrayList(cartmap.entrySet());
        cart = new TableView<>();
        cart.setItems(items);
        cart.getColumns().addAll(nameColumn, priceColumn, quantityColumn);

        VBox vBox = new VBox();
        vBox.getChildren().add(cart);

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(20, 20, 20, 20));
        hBox.setSpacing(10);

        addButton = new Button("Add");
        reduceButton = new Button("Reduce");
        purchase = new Button("Purchase");

        hBox.getChildren().addAll(addButton, reduceButton, purchase);

        vBox.getChildren().add(hBox);

        addButtonFunctions();

        scene = new Scene(vBox, 500, 500);

        window.setOnCloseRequest(e -> {
            window.close();
            menu.display();
        });

    }

    private void addButtonFunctions(){
        ObservableList<Map.Entry<Good, Integer>> products,selectedProducts;
        products = cart.getItems();


        selectedProducts = cart.getSelectionModel().getSelectedItems();

        addButton.setOnAction(e -> {
        });

        purchase.setOnAction(e -> {
            new PurchasePageGUI(window, this.menu, buyer);
        });
    }

    @Override
    public void display() {
        window.setScene(scene);
        window.show();
    }
}
