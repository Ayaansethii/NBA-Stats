package Endpoint;

import Helper.League;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import Business.CreateLeague;

/**
 * REST Web Service
 */
@Path("league")
public class CreateLeagueResource {

    @Context
    private UriInfo context;

    /*
     Creates a new GET */
    @GET
    @Produces(MediaType.APPLICATION_XML) // XML output  
    @Path("/{leagueID}") 
    public String getXml(@PathParam("leagueID") String leagueID) {
        System.out.println(leagueID);
        CreateLeague leagueBusiness = new CreateLeague();
        League league = leagueBusiness.getLeague(leagueID);
        if (league == null) {
            return("");
        }
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(League.class);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(league, sw);

            return (sw.toString());

        } catch (JAXBException ex) {
            Logger.getLogger(CreateLeagueResource.class.getName()).log(Level.SEVERE, null, ex);
            return ("error happened");
        }
    }

}
