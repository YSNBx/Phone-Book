package contacts.components;

import java.util.ArrayList;
import java.util.List;

public class PhoneBook {
    private List<Contact> contacts;

    public PhoneBook() {
        this.contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        this.contacts.add(contact);
    }

    public List<Contact> getPhoneBook() {
        return this.contacts;
    }

    public int getContactsCount() {
        return this.contacts.size();
    }
}
