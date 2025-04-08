/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frontend;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
 
@WebServlet(name = "FrontEnd", urlPatterns = {"/FrontEnd"})
public class FrontEnd extends HttpServlet {
    private Authenticate auth;
    
    public FrontEnd() {
        auth = new Authenticate();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve login credentials from login.jsp
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Use Authenticate to check credentials and get a JWT token
        String token = auth.authenticate(username, password);
        
        System.out.println(token);
        
        if(token != null){
            // Store token and username in session
            HttpSession session = request.getSession();
            session.setAttribute("authToken", token);
            session.setAttribute("username", username);
            response.sendRedirect("frontpageWithLogin.jsp");
        } else {
            request.setAttribute("error", "Invalid credentials");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}