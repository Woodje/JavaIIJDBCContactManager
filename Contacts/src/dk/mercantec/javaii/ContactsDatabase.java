package dk.mercantec.javaii;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Woodje on 19-02-2015.
 */
public class ContactsDatabase {

    private Connection databaseConnection;
    
    private Statement databaseStatement;
    
    private boolean contactFound;

    private String connectToContactDatabase()
    {

        try
        {
            
            Class.forName("org.sqlite.JDBC");
            
            databaseConnection = DriverManager.getConnection("jdbc:sqlite:contact.db");
            
        }
        catch (Exception e)
        {
            
            return e.getClass().getName() + ": " + e.getMessage();
            
        }
        
        return "Connection successfully established";
        
    }

    public String createTables()
    {
        try
        {
            
            connectToContactDatabase();

            databaseStatement = databaseConnection.createStatement();

            String sql = "CREATE TABLE internal(name TEXT, phone TEXT, email TEXT, department TEXT);";

            databaseStatement.executeUpdate(sql);

            sql = "CREATE TABLE external(name TEXT, phone TEXT, email TEXT, company TEXT);";

            databaseStatement.executeUpdate(sql);

            databaseStatement.close();
            
            databaseConnection.close();
            
        }
        catch (Exception e)
        {
            
            return e.getClass().getName() + ": " + e.getMessage();
            
        }
        
        return "Tables successfully created";
        
    }
    
    public String setContact(BaseContact baseContact) {

        try
        {

            connectToContactDatabase();

            databaseStatement = databaseConnection.createStatement();

            String sql;

            if (baseContact instanceof Internal) {

                sql = "INSERT INTO internal(name, phone, email, department) VALUES('" +
                        baseContact.getName() + "', '" +
                        baseContact.getPhone() + "', '" +
                        baseContact.getEmail() + "', '" +
                        ((Internal) baseContact).getDepartment() + "');";

            }
            else {

                sql = "INSERT INTO external(name, phone, email, company) VALUES('" +
                        baseContact.getName() + "', '" +
                        baseContact.getPhone() + "', '" +
                        baseContact.getEmail() + "', '" +
                        ((External) baseContact).getCompany() + "');";

            }

            databaseStatement.executeUpdate(sql);

            databaseStatement.close();

            databaseConnection.close();

        }
        catch (Exception e)
        {

            return e.getClass().getName() + ": " + e.getMessage();

        }

        return "Contact successfully added to the database";

    }
    
    public ArrayList<BaseContact> getContacts() {

        ArrayList<BaseContact> contacts = new ArrayList<BaseContact>();
        
        try
        {
            connectToContactDatabase();

            databaseStatement = databaseConnection.createStatement();

            String sql;
            
            sql = "SELECT * FROM internal;";

            ResultSet queryResult = databaseStatement.executeQuery(sql);

            while(queryResult.next()) {
                
                contacts.add(new Internal(  queryResult.getString("name"),
                                            queryResult.getString("phone"),
                                            queryResult.getString("email"),
                                            queryResult.getString("department")));
                
            }
            
            sql = "SELECT * FROM external;";

            queryResult.close();
            
            queryResult = databaseStatement.executeQuery(sql);

            while(queryResult.next()) {

                contacts.add(new Internal(  queryResult.getString("name"),
                                            queryResult.getString("phone"),
                                            queryResult.getString("email"),
                                            queryResult.getString("company")));

            }

            queryResult.close();
            
            databaseStatement.close();
            
            databaseConnection.close();
            
        }
        catch (Exception e)
        {
            
            contacts = null;
            
        }
        
        return contacts;
    }
    
}
