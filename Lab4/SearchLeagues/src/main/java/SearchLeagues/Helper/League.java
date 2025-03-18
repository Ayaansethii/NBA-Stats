package SearchLeagues.Helper;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "league")
@XmlAccessorType(XmlAccessType.FIELD)
public class League {

    private String leagueID;
    private String leagueName;

    @XmlElementWrapper(name = "teams")  // Ensures all teams are wrapped inside <teams>
    @XmlElement(name = "team")
    private List<Team> teams;

    public League() {
        this.teams = new ArrayList<>();
    }

    public League(String leagueID, String leagueName) {
        this.leagueID = leagueID;
        this.leagueName = leagueName;
        this.teams = new ArrayList<>();
    }

    public String getLeagueID() {
        return leagueID;
    }

    public void setLeagueID(String leagueID) {
        this.leagueID = leagueID;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public List<Team> getTeams() {
        return teams;
    }

    // ✅ Fix: Add a method to add multiple teams
    public void addTeams(List<Team> newTeams) {
        this.teams.addAll(newTeams);
    }

    public void addTeam(Team team) {
        this.teams.add(team);
    }

    @Override
    public String toString() {
        return "League{" +
                "leagueID='" + leagueID + '\'' +
                ", leagueName='" + leagueName + '\'' +
                ", teams=" + teams +
                '}';
    }
}
