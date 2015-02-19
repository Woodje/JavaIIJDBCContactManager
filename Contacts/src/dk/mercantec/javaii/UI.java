package dk.mercantec.javaii;

import java.util.Scanner;

/**
 * Created by code on 17/02/15.
 */
public class UI {
    private ContactsDatabase contacts;
    public UI(ContactsDatabase contacts) {
        
        this.contacts = contacts;

        this.contacts.createTables();
        
    }

    public Menu MainMenu() {
        Menu menu = new Menu();
        menu.Add("List contacts", new MenuCallback() {
            public void Invoke() {
                ListContactsMenu().Show();
            }
        });
        menu.Add("Add contact", new MenuCallback() {
            public void Invoke() {
                AddContactsMenu().Show();
            }
        });
        menu.Add("Quit", new MenuCallback() {
            public void Invoke() {
                System.exit(0);
            }
        });

        return menu;
    }

    public Menu ListContactsMenu() {
        Menu menu = new Menu();

        for (final BaseContact contact : contacts.getContacts()) {
            menu.Add(contact.getName(), new MenuCallback() {
                public void Invoke() {
                    System.out.println(contact.toString());
                }
            });
        }
        menu.Add("Back", new MenuCallback() {
            public void Invoke() { }
        });

        return menu;
    }

    public Menu AddContactsMenu() {
        Menu menu = new Menu();
        menu.Add("Internal", new MenuCallback() {
            public void Invoke() {
                AddInternalContact();
            }
        });
        menu.Add("External", new MenuCallback() {
            public void Invoke() {
                AddExternalContact();
            }
        });
        menu.Add("Back", new MenuCallback() {
            public void Invoke() { }
        });

        return menu;
    }

    public void AddExternalContact() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter name: ");
        String name = in.nextLine();
        System.out.println("Enter phone number: ");
        String phone = in.nextLine();
        System.out.println("Enter email: ");
        String email = in.nextLine();
        System.out.println("Enter company name: ");
        String company = in.nextLine();

        contacts.setContact(new Internal(name, phone, email, company));
    }

    public void AddInternalContact() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter name: ");
        String name = in.nextLine();
        System.out.println("Enter phone number: ");
        String phone = in.nextLine();
        System.out.println("Enter email: ");
        String email = in.nextLine();
        System.out.println("Enter department name: ");
        String department = in.nextLine();

        contacts.setContact(new External(name, phone, email, department));
    }
}
