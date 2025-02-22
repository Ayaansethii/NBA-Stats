package Business;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Helper.UserInfo;
import Persistence.Manager_CRUD;

@WebServlet("/LoginServlet")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserInfo manager = Manager_CRUD.read(username, password);

        if (manager != null) { // Valid login
            HttpSession session = request.getSession();
            session.setAttribute("managerID", manager.getUserId());
            session.setAttribute("username", manager.getUsername());

            response.sendRedirect("home.jsp"); // Redirect to home page
        } else { // Invalid login
            request.setAttribute("errorMessage", "Invalid Username or Password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
