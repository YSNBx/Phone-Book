package contacts;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu(new PhoneBook());
        mainMenu.start();
    }
}
