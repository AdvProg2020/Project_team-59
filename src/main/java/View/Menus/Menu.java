package View.Menus;

import java.util.Scanner;

public class Menu {
    //scanner ro public tarif kardm
    public static Scanner scanner = new Scanner(System.in);
    protected Menu headMenu;
    private String input;

    public Menu() {
    }

    public void run(){

    }

    public static String getInputFromUser(){
        return scanner.nextLine().trim();
    }
}
