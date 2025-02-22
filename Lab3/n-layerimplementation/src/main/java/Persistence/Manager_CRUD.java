package Persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Helper.UserInfo;

public class Manager_CRUD {

    public static UserInfo read(String username, String password) {
        UserInfo manager = null;
        String sql = "SELECT managerID, username FROM Manager WHERE username=? AND password=?";

        try (Connection con = DBConfig.getCon();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                manager = new UserInfo();
                manager.setUserId(rs.getInt("managerID"));
                manager.setUsername(rs.getString("username"));
            }
        } catch (Exception e) {
            throw new RuntimeException("Login Query Error", e);
        }
        return manager;
    }
}
