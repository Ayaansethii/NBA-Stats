package Persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class League_CRUD {

    public static boolean addLeague(String leagueName, int managerID) {
        String sql = "INSERT INTO League (leagueName, managerID) VALUES (?, ?)";
        try (Connection con = DBConfig.getCon();
             PreparedStatement stmt = con.prepareStatement(sql)) {
             
            stmt.setString(1, leagueName);
            stmt.setInt(2, managerID);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            throw new RuntimeException("Error inserting league", e);
        }
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

    public static int getManagerID(String username) {
        String sql = "SELECT managerID FROM Manager WHERE username = ?";
        try (Connection con = DBConfig.getCon();
             PreparedStatement stmt = con.prepareStatement(sql)) {
             
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return rs.next() ? rs.getInt("managerID") : -1;
        } catch (Exception e) {
            throw new RuntimeException("Error fetching manager ID", e);
        }
    }

    public static ArrayList<String> searchLeagues(String searchQuery) {
        ArrayList<String> leagues = new ArrayList<>();
        String sql = "SELECT leagueName FROM League WHERE leagueName LIKE ?";
        try (Connection con = DBConfig.getCon();
             PreparedStatement stmt = con.prepareStatement(sql)) {
             
            stmt.setString(1, "%" + searchQuery + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                leagues.add(rs.getString("leagueName"));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error searching leagues", e);
        }
        return leagues;
    }

    public static boolean deleteLeague(String leagueName) {
        String sql = "DELETE FROM League WHERE leagueName = ?";
        try (Connection con = DBConfig.getCon();
             PreparedStatement stmt = con.prepareStatement(sql)) {
             
            stmt.setString(1, leagueName);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            throw new RuntimeException("Error deleting league", e);
        }
    }

    public static boolean updateLeagueName(String oldName, String newName) {
        String sql = "UPDATE League SET leagueName = ? WHERE leagueName = ?";
        try (Connection con = DBConfig.getCon();
             PreparedStatement stmt = con.prepareStatement(sql)) {
             
            stmt.setString(1, newName);
            stmt.setString(2, oldName);
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            throw new RuntimeException("Error updating league name", e);
        }
    }
}
