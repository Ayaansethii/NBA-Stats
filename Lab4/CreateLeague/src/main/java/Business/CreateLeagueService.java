package Business;

import Persistence.CreateLeague_CRUD;

public class CreateLeagueService { 
    // Business logic for creating a league (no constraints other than unique leagueID - accounted for in SQL)
    // Only other constraint: managerID must exist -> However, it is assumed manager is already authenticated at this point
    
    public boolean createLeague(String leagueName, int managerID) { // method to create new league
        
        return CreateLeague_CRUD.addLeague(leagueName, managerID);
    }
    //returns true if league created, else false
}