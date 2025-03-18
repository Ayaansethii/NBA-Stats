package Endpoint;

import Business.CreateLeagueService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/leagues")
public class CreateLeagueEndpoint {
    // Handles HTTP requests and maps them to business logic to make a league
    
    private CreateLeagueService leagueService = new CreateLeagueService();

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Response createLeague(LeagueRequest request) {

        boolean success = leagueService.createLeague(request.getName(), request.getManagerID());
        if (success) {
            return Response.status(Response.Status.CREATED).entity("<message>League created successfully</message>").build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("<error>Error creating league</error>").build();
        }
    }
}
