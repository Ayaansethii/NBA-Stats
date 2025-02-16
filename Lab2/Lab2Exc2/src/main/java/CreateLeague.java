/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author student
 */
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession; 

@WebServlet("/CreateLeagueServlet")
public class CreateLeague extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String leagueName = request.getParameter("leagueName");

        if (leagueName != null && !leagueName.trim().isEmpty()) {
            ArrayList<String> leagues = (ArrayList<String>) session.getAttribute("leagues");
            if (leagues == null) {
                leagues = new ArrayList<String>();
            }
            
            if (leagues.contains(leagueName)){
                request.setAttribute("errorMessage", "League already exists. Please select different league name.");
            }
            else{
            leagues.add(leagueName);
            session.setAttribute("leagues", leagues);
            }
            request.setAttribute("successMessage", "League created successfully!");
        } else {
            request.setAttribute("errorMessage", "League name cannot be empty.");
        }

        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
}
