package SearchLeagues.Persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;
import java.util.Collections;
import SearchLeagues.Helper.League;
import SearchLeagues.Helper.Team;
import java.sql.SQLException;

public class League_CRUD {

    // ✅ Optimized to return a Set<League> instead of just league names
    public static Set<League> searchForLeagues(String searchQuery) {
        Set<League> leagues = new HashSet<>();
        String sql = "SELECT L.leagueID, L.leagueName, T.teamID, T.teamName " +
                     "FROM League L " +
                     "LEFT JOIN Team_League TL ON L.leagueID = TL.leagueID " +
                     "LEFT JOIN Team T ON TL.teamID = T.teamID " +
                     "WHERE L.leagueName LIKE ?";

        try (Connection con = DBConfig.getCon();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, "%" + searchQuery + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String leagueID = String.valueOf(rs.getInt("leagueID"));  // Convert int to String
                String leagueName = rs.getString("leagueName");

                League league = new League(leagueID, leagueName);

                if (rs.getString("teamName") != null) {
                    Team team = new Team(rs.getString("teamName"));  // Only pass team name
                    league.addTeams(Collections.singletonList(team));  // Add as a single-element list
                }

                leagues.add(league);
            }

        } catch (Exception e) {
            throw new RuntimeException("❌ Error searching leagues: " + e.getMessage(), e);
        }
        return leagues;
    }
    public static void insertLeague(League league) throws SQLException {
        try (Connection con = DBConfig.getCon()) {
            String insertLeagueSQL = "INSERT INTO League (leagueID, leagueName) VALUES (?, ?)";
            try (PreparedStatement leagueStmt = con.prepareStatement(insertLeagueSQL)) {
                leagueStmt.setString(1, league.getLeagueID());
                leagueStmt.setString(2, league.getLeagueName());
                leagueStmt.executeUpdate();
            }

            String insertTeamSQL = "INSERT INTO Team (teamName) VALUES (?) ON DUPLICATE KEY UPDATE teamName=teamName";
            String insertTL = "INSERT INTO Team_League (leagueID, teamID) SELECT ?, teamID FROM Team WHERE teamName=?";
            for (Team team : league.getTeams()) {
                try (PreparedStatement teamStmt = con.prepareStatement(insertTeamSQL)) {
                    teamStmt.setString(1, team.getTeamName());
                    teamStmt.executeUpdate();
                }

                try (PreparedStatement tlStmt = con.prepareStatement(insertTL)) {
                    tlStmt.setString(1, league.getLeagueID());
                    tlStmt.setString(2, team.getTeamName());
                    tlStmt.executeUpdate();
                }
            }
        }
    }

}
