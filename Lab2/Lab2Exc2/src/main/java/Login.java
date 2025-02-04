import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Hardcoded validation (Replace with database validation in future)
        if ("admin".equals(username) && "password".equals(password)) {
            // ✅ Create a session and store the username
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            // ✅ Redirect to home page
            response.sendRedirect("home.jsp");
        } else {
            // Pass error message and reload login page
            request.setAttribute("errorMessage", "Invalid Username or Password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
