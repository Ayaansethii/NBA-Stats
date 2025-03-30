package Persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.*;

import Helper.League;

public class CreateLeague_CRUD {

    public static boolean addLeague(String leagueName, String managerID) {
        boolean inserted = false;
        String sql = "INSERT INTO League (leagueName, managerID) VALUES (?, ?)";

        try (Connection con = DBConfig.getCon();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, leagueName);
            stmt.setInt(2, Integer.parseInt(managerID));

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
    
    public static League getLeague(int leagueID) {
        League league = new League();
        String sql = "SELECT * FROM League WHERE leagueID = ?";
        
        try (Connection con = DBConfig.getCon(); 
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setInt(1, leagueID);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                league.setLeagueID(rs.getString("leagueID"));
                league.setName(rs.getString("leagueName"));
                league.setManagerID(rs.getString("managerID"));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving league", e);
        }
        return league;
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
        //test.addLeague("Bobby",11);
        League l = test.getLeague(2);
        System.out.println(l.getLeagueID() + " " + l.getName() + " " + l.getManagerID());
    }
    */
    
}
