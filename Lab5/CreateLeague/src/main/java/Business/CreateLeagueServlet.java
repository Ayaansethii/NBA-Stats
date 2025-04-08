package Business;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import Helper.League;

@WebServlet("/createLeagueServlet")
public class CreateLeagueServlet extends HttpServlet {
    private CreateLeague leagueService = new CreateLeague(); //object that creates leagues

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // get user input
        String leagueName = request.getParameter("leagueName");
        String managerID = request.getParameter("managerID");

        // formatting for responses after league creation attempt
        response.setContentType("text/xml");
        response.setCharacterEncoding("UTF-8");
        
        boolean success = leagueService.createLeague(leagueName, managerID); // league is created: returns true is successful
        if (success) {
            
            League Lreq = new League(); // class to get League attributes as XML
            Lreq.setName(leagueName);
            Lreq.setManagerID(managerID);
            
            response.getWriter().write("<response><message>League created successfully</message><league><name>" + Lreq.getName() + "</name><managerID>" + Lreq.getManagerID() + "</managerID></league></response>");
        } else {
            response.getWriter().write("<response><message>Error creating league</message></response>");
        }
    }
}