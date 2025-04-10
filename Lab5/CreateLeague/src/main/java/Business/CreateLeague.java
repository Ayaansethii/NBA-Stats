package Business;

import Helper.League;
import Persistence.CreateLeague_CRUD;
import java.io.IOException;

public class CreateLeague {

    public boolean createLeague(String leagueName, String managerID) {
        boolean created = CreateLeague_CRUD.addLeague(leagueName, managerID);

        if (created) {
            String message = "LEAGUE_CREATED:" + leagueName + ":" + managerID;
            try {
                Messaging.sendMessage(message);
            } catch (IOException e) {
                System.out.println("Failed to send KubeMQ message: " + e.getMessage());
            }
        }

        return created;
    }

    public League getLeague(int leagueID) {
        return CreateLeague_CRUD.getLeague(leagueID);
    }

    public League getLeague(String leagueID) {
        return getLeague(Integer.parseInt(leagueID));
    }
}
