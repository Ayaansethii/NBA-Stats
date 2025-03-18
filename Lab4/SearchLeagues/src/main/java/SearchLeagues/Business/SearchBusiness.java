package SearchLeagues.Business;

import SearchLeagues.Persistence.League_CRUD;
import java.util.*;
import SearchLeagues.Helper.League;
import SearchLeagues.Helper.Team;
import SearchLeagues.Helper.LeaguesXML;

public class SearchBusiness {

    public LeaguesXML getLeaguesByQuery(String query) {
        Set<League> leagues = League_CRUD.searchForLeagues(query);
        Map<String, League> allTeamLeagues = new HashMap<>();

        System.out.println("✅ Number of leagues found: " + leagues.size());

        for (League league : leagues) {
            if (allTeamLeagues.containsKey(league.getLeagueName())) {
                allTeamLeagues.get(league.getLeagueName()).addTeams(new ArrayList<>(league.getTeams())); 
            } else {
                allTeamLeagues.put(league.getLeagueName(), league);
            }
        }

        System.out.println("✅ Total Unique Leagues: " + allTeamLeagues.size());

        LeaguesXML bs = new LeaguesXML();
        bs.setLeagues(new ArrayList<>(allTeamLeagues.values()));

        return bs;
    }
}
