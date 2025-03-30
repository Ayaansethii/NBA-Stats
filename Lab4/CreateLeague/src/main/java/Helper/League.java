package Helper;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "league") // Setting XML root element name
@XmlAccessorType(XmlAccessType.FIELD) // JAXB directly accesses fields of class
public class League {
    
    // Data to be stored and displayed in XML
    private String leagueID;
    private String name;
    private String managerID;
    
    public League (String leagueID, String name, String managerID){
        this.leagueID = leagueID;
        this.name = name;
        this.managerID = managerID;
    }
    
    public League (){ // Default constructor
        // Required by JAXB to create instances of class when unmarshalling (XML to Object)
        // JAXB requires no argument constructor to instantiate class
        //  - for JAXBContext.newInstance(League.class) in CreateLeagueResource class
        this.leagueID = "";
        this.name = "";
        this.managerID = "";
    }

    public String getLeagueID() {
        return leagueID;
    }

    public void setLeagueID(String leagueID) {
        this.leagueID = leagueID;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManagerID() {
        return managerID;
    }

    public void setManagerID(String managerID) {
        this.managerID = managerID;
    }
}
