package SearchLeagues.Persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;
import java.util.Collections;
import SearchLeagues.Helper.League;
import SearchLeagues.Helper.Team;

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
}
