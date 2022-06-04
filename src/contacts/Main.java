package contacts;

import java.time.LocalDate;
import java.time.LocalDateTime;

import contacts.components.PhoneBook;
import contacts.ui.MainMenu;
import contacts.uicontroller.MenuController;

public class Main {
    public static void main(String[] args) {
        System.out.println(LocalDateTime.now());
        MenuController controller = new MenuController();
        controller.setInterface(new MainMenu(new PhoneBook(), controller));
        controller.execute();
    }
}
