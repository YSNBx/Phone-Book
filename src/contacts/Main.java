package contacts;

public class Main {
    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu(new PhoneBook());
        mainMenu.start();
    }
}
