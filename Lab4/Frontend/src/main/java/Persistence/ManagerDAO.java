package Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Helper.Manager;

public class ManagerDAO {
    
    // Retrieve manager by username
    public Manager getManagerByUsername(String username) {
        Manager manager = null;
        String sql = "SELECT id, username, password FROM Account WHERE username = ?";
        
        try (Connection con = DBConfig.getCon();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){ //iterating through query result (should yeild one manager since usernames are unique)
                int id = rs.getInt("id");
                String uname = rs.getString("username");
                String pwd = rs.getString("password");
                manager = new Manager(id, uname, pwd);
            }
            rs.close();
        } catch(Exception e){
            e.printStackTrace();
        }
        return manager;
    }
    
    /*
    //Checks if DB connection works (config.properties file has all the correct info)
    // If there is an error when running this file, the config.properties file must be editted
    
    public static void main (String args[]){
        Connection con = DBConfig.getCon();
    }
    */
}
