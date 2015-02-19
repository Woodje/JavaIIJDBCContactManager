package dk.mercantec.javaii;

import java.util.ArrayList;

/**
 * NO LONGER IN USE. ContactsDatabase is used instead. (SQLite)
 * @See ContactsDatabase
 */
public class Contacts {
    private ArrayList<BaseContact> contacts = new ArrayList<BaseContact>();

    public ArrayList<BaseContact> getContacts() {
        return contacts;
    }

    public void AddInternalContact(String name, String phone, String email, String department) {
        contacts.add(new Internal(name, phone, email, department));
    }

    public void AddExternalContact(String name, String phone, String email, String company) {
        contacts.add(new External(name, phone, email, company));
    }
}
