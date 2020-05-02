package View.Menus;

import java.util.Scanner;

public class Menu {
    private static Scanner scanner = new Scanner(System.in);
    protected Menu headMenu;
    private String input;

    public Menu() {
    }

    public void run(){

    }

    public static String getInputFromUser(){
        return scanner.nextLine();
    }

    public static String getCommandFromUser(){
        return scanner.nextLine().trim().toLowerCase();
    }
}
