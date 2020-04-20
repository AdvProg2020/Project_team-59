package View.Menus;

import java.util.Scanner;

public class Menu {
    private static Scanner scanner = new Scanner(System.in);
    private String input;

    public Menu() {
    }

    private static String getInputFromUser(){
        return scanner.nextLine();
    }

    private static String getCommandFromUser(){
        return scanner.nextLine().trim().toLowerCase();
    }
}
