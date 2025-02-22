package Business;

import Persistence.League_CRUD;
import java.util.ArrayList;

public class LeagueManager {

    public static ArrayList<String> searchLeagues(String searchQuery) {
        if (searchQuery == null || searchQuery.trim().isEmpty()) {
            return new ArrayList<>(); 
        }
        return League_CRUD.searchLeagues(searchQuery);
    }
}
