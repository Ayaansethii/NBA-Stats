package SearchLeagues.Helper;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "leagues")
@XmlAccessorType(XmlAccessType.FIELD)
public class LeaguesXML {

    @XmlElement(name = "league")
    private List<League> leagues;

    public LeaguesXML() {
        this.leagues = new ArrayList<>();
    }

    public List<League> getLeagues() {
        return leagues;
    }

    public void setLeagues(List<League> leagues) {
        this.leagues = leagues;
    }
}

