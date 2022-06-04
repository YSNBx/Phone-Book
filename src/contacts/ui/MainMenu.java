package contacts.ui;

import contacts.components.Contact;
import contacts.uicontroller.MenuController;
import contacts.components.PhoneBook;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainMenu implements SuperInterface {
    private final PhoneBook phoneBook;
    private final Scanner scanner;
    private final MenuController controller;

    public MainMenu(PhoneBook phoneBook, MenuController controller) {
        this.phoneBook = phoneBook;
        this.scanner = new Scanner(System.in);
        this.controller = controller;
    }

    @Override
    public void start() {
        while (true) {
            System.out.println("Enter action (add, remove, edit, count, list, exit)");
            String command = scanner.nextLine();

            if (command.equals("exit")) {
                break;
            }
            this.evaluate(command);
        }
    }

    public void evaluate(String command) {
        switch (command) {
            case "add":
                this.addRecord();
                break;
            case "remove":
                this.removeRecord();
                break;
            case "edit":
                this.editRecord();
                break;
            case "count":
                this.countRecords();
                break;
            case "list":
                this.listRecords();
                break;
            default:
                System.out.println("Wrong input! Try again.");
        }
    }

    private void addRecord() {
        this.phoneBook.addContact(this.getUserInput());
    }

    private void removeRecord() {
        if (this.phoneBook.getPhoneBook().isEmpty()) {
            System.out.println("No records to remove!");
            return;
        }
        this.listRecords();
        this.getInputToRemove();
    }

    private void editRecord() {
        if (this.phoneBook.getPhoneBook().isEmpty()) {
            System.out.println("No records to edit!");
            return;
        }
        this.listRecords();
        this.getInputToEdit();
    }

    private void countRecords() {
        System.out.println("The Phone Book has " + this.phoneBook.getContactsCount() + " records.");
    }

    private void listRecords() {
        if (this.phoneBook.getPhoneBook().isEmpty()) {
            System.out.println("This Phone Book has no records!");
            return;
        }
        for (int i = 0; i < this.phoneBook.getContactsCount(); i++) {
            System.out.println(i + 1 + ". " + this.phoneBook.getPhoneBook().get(i));
        }
    }

    public Contact getUserInput() {
        System.out.println("Enter the name: ");
        String name = scanner.nextLine();
        System.out.println("Enter the surname: ");
        String surname = scanner.nextLine();
        System.out.println("Enter the number: ");
        String phoneNumber = scanner.nextLine();

        if (this.checkNumberValidity(phoneNumber)) {
            System.out.println("Record has been added.");
            return new Contact(name, surname, phoneNumber);
        }

        System.out.println("Wrong number format!\nRecord has been added.");
        return new Contact(name, surname, "[no number]");
    }

    public boolean checkNumberValidity(String phoneNumber) {
        Pattern pattern = Pattern.compile("^\\+?(\\(\\w+\\)|\\w+[-\\s]\\(\\w{2,}\\)|\\w+)([-\\s]\\w{2,})*");
        Matcher matcher = pattern.matcher(phoneNumber);

        return matcher.matches();
    }

    public void getInputToEdit() {
        System.out.println("Select a record: ");
        int recordNumber = Integer.parseInt(scanner.nextLine());
        System.out.println("Select a field (name, surname, number): ");
        String field = scanner.nextLine();

        this.editRecord(recordNumber, field);
    }

    public void editRecord(int recordNumber, String field) {
        if (field.equals("name")) {
            System.out.println("Enter name: ");
            this.phoneBook.getPhoneBook().get(recordNumber - 1).setName(scanner.nextLine());
            System.out.println("Selected record has been updated!");
        } else if (field.equals("surname")) {
            System.out.println("Enter surname: ");
            this.phoneBook.getPhoneBook().get(recordNumber - 1).setSurname(scanner.nextLine());
            System.out.println("Selected record has been updated!");
        } else if (field.equals("number")) {
            System.out.println("Enter number: ");
            String phoneNumber = scanner.nextLine();

            if (this.checkNumberValidity(phoneNumber)) {
                this.phoneBook.getPhoneBook().get(recordNumber - 1).setPhoneNumber(phoneNumber);
                System.out.println("Selected record has been updated!");
            } else {
                System.out.println("Wrong number format!");
                this.phoneBook.getPhoneBook().get(recordNumber - 1).setPhoneNumber("[no number]");
            }
        } else {
            System.out.println("Wrong input. Try again. ");
            this.getInputToEdit();
        }
    }

    public void getInputToRemove() {
        System.out.println("Select a record: ");
        this.phoneBook.getPhoneBook().remove(Integer.parseInt(scanner.nextLine()) - 1);
    }
}
