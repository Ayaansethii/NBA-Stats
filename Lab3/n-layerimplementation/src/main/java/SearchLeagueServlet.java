import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Business.LeagueManager;

@WebServlet("/SearchLeagueServlet")
public class SearchLeagueServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String searchQuery = request.getParameter("searchQuery");
        ArrayList<String> leagues = LeagueManager.searchLeagues(searchQuery);

        request.setAttribute("filteredLeagues", leagues);
        request.getRequestDispatcher("viewLeagues.jsp").forward(request, response);
    }
}
