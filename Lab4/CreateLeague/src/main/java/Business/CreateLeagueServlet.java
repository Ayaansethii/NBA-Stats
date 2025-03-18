package Business;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/createLeagueServlet")
public class CreateLeagueServlet extends HttpServlet {
    private CreateLeagueService leagueService = new CreateLeagueService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String leagueName = request.getParameter("leagueName");
        int managerID = Integer.parseInt(request.getParameter("managerID"));

        boolean success = leagueService.createLeague(leagueName, managerID);
        if (success) {
            response.getWriter().write("<html><body><h1>League created successfully</h1></body></html>");
        } else {
            response.getWriter().write("<html><body><h1>Error creating league</h1></body></html>");
        }
    }
}