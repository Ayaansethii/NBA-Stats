import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Persistence.League_CRUD;

@WebServlet("/CreateLeagueServlet")
public class CreateLeague extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String leagueName = request.getParameter("leagueName");
        String username = (String) session.getAttribute("username");

        if (leagueName == null || leagueName.trim().isEmpty() || username == null) {
            request.setAttribute("errorMessage", "League name cannot be empty.");
        } else {
            int managerID = League_CRUD.getManagerID(username);
            if (managerID == -1) {
                request.setAttribute("errorMessage", "Error retrieving manager ID. Please log in again.");
            } else {
                boolean success = League_CRUD.addLeague(leagueName, managerID);
                request.setAttribute(success ? "successMessage" : "errorMessage", 
                                    success ? "League created successfully!" : "Failed to create league. Try again.");
            }
        }
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
}
