package Business;

import Helper.League;
import Persistence.CreateLeague_CRUD;

public class CreateLeague { 
    // Business logic for creating a league (no constraints other than unique leagueID - accounted for in SQL)
    // Only other constraint: managerID must exist -> However, it is assumed manager is already authenticated at this point
    
    public boolean createLeague(String leagueName, String managerID) { // method to create new league
        
        return CreateLeague_CRUD.addLeague(leagueName, managerID);
    }
    //returns true if league created, else false
    
    // Retrieves league from DB based on ID
    public League getLeague(String leagueID){
        League league = CreateLeague_CRUD.getLeague(Integer.parseInt(leagueID));
        return league;
    }
    
}