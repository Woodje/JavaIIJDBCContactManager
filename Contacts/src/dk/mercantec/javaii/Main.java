package dk.mercantec.javaii;

import java.sql.*;

public class Main {

    public static void main(String[] args) {
        
        UI ui = new UI(new ContactsDatabase());
        
        while (true)
            ui.MainMenu().Show();

    }
}
