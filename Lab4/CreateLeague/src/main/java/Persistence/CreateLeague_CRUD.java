package Persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.*;

public class CreateLeague_CRUD {

    public static boolean addLeague(String leagueName, int managerID) {
        boolean inserted = false;
        String sql = "INSERT INTO League (leagueName, managerID) VALUES (?, ?)";

        try (Connection con = DBConfig.getCon();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, leagueName);
            stmt.setInt(2, managerID);

            int rowsAffected = stmt.executeUpdate();
            inserted = rowsAffected > 0;
            System.out.println("League Inserted: " + leagueName);

        } catch (SQLIntegrityConstraintViolationException e) {
            // Handle duplicate league name error
            System.out.println("League already exists: " + leagueName);
            return false; // Return false instead of crashing

        } catch (Exception e) {
            System.out.println("Error inserting league: " + e.getMessage());
        }
        return inserted;
    }

    public static ArrayList<String> getAllLeagues() {
        ArrayList<String> leagues = new ArrayList<>();
        String sql = "SELECT leagueName FROM League";
        try (Connection con = DBConfig.getCon(); 
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                leagues.add(rs.getString("leagueName"));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving leagues", e);
        }
        return leagues;
    }
    
    
    /*
    
    // Test to see if Persistence class can access the DB
    // Check output terminal for result
    
    public static void main (String args[]){
        CreateLeague_CRUD test = new CreateLeague_CRUD();
        test.addLeague("Bobby",11);
    }
    */
}
