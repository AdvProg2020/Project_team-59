package View.Menus;

import Controller.Controller;
import Model.Account.Account;
import Model.Account.Buyer;
import Model.Account.Manager;
import Model.Account.Seller;
import View.Requests.UserRequest;

public class LogInView extends Menu{
    private UserRequest userRequest;

    public LogInView(Menu menu) {
        this.headMenu = menu;
    }

    public void run( String[] splitInput ){
        if ( !Controller.usernameExists(splitInput[1]) ){
            System.out.println("no user with such username exists");
            this.headMenu.run();
        }
        System.out.println("please enter your password: ");
        Account account = Manager.getAccountByUsername(splitInput[1]);
        String input = Menu.getInputFromUser();
        if ( account.passwordIsCorrect(input) ){
            goToAccountsPage(account);
        }
        else{
            System.out.println("password is incorrect");
            this.headMenu.run();
        }
    }

    private void goToAccountsPage(Account account){
        Controller.setCurrentAccount(account);
        if ( account instanceof Buyer ){
            addUserCartToAccount( account );
            new BuyerView( this.headMenu ).run();
        }
        else if ( account instanceof Seller){
            new SellerView( this.headMenu ).run();
        }
        else {
            new ManagerView( this.headMenu ).run();
        }
    }

    private void addUserCartToAccount(Account account){
        Buyer buyer = (Buyer)account;
        buyer.addItemsToCart(Controller.getCurrentUser().getCart());
    }

    private void help(){
        //TODO
    }
}
