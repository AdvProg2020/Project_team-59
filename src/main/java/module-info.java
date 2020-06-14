module AP.Project.team {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;

    opens Controller;
    opens Model.Account;
    opens Model.Application;
    opens Model.Discount;
    opens Model.Good;
    opens Model.log;
    opens View.GUIMenu;
    opens View.Menus;
    opens View.Requests;
    opens Main;
}