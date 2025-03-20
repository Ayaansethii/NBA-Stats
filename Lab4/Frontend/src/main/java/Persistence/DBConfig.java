package Persistence;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConfig {
    private static String URL;
    private static String USERNAME;
    private static String PASSWORD;

    static {
        Properties props = new Properties();
        try (InputStream input = DBConfig.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("config.properties not found in classpath");
            }
            props.load(input);

            URL = props.getProperty("db.url");
            USERNAME = props.getProperty("db.username");
            PASSWORD = props.getProperty("db.password");

        } catch (Exception e) {
            throw new RuntimeException("Could not load database configuration", e);
        }
    }

    public static Connection getCon() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            throw new RuntimeException("Database Connection Error", e);
        }
    }
    
    /*
    //Checks if DB connection works (config.properties file has all the correct info)
    // If there is an error when running this file, the config.properties file must be editted
    
    public static void main (String args[]){
        Connection con = DBConfig.getCon();
    }
    */
}
