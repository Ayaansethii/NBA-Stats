package SearchLeagues.Endpoint;

import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import SearchLeagues.Helper.LeaguesXML;
import SearchLeagues.Business.SearchBusiness;

@Path("search")  // << Ensures correct URL mapping
public class SearchResource {

    @Context
    private UriInfo context;

    public SearchResource() {}

    @GET
    @Path("/{query}")   // << Allows /search/NBA
    @Produces(MediaType.APPLICATION_XML + ";charset=utf-8")
    public String getXml(@PathParam("query") String query) {
        SearchBusiness search = new SearchBusiness();
        LeaguesXML leagues = search.getLeaguesByQuery(query);

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(LeaguesXML.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(leagues, sw);

            return sw.toString();

        } catch (JAXBException ex) {
            Logger.getLogger(SearchResource.class.getName()).log(Level.SEVERE, "❌ Error during XML conversion", ex);
            return "<error>XML processing failed</error>";
        }
    }
}
