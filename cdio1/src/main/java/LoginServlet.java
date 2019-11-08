import entitites.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login/*")
public class LoginServlet extends HttpServlet {

    UserDAO userDao = new UserDAO();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {

        try {

            User user = new User();


            user = userDao.authenticateUser(Integer.valueOf(request.getParameter("cpr")),
                    request.getParameter("password"));

            if (user.isValid()) {

                HttpSession session = request.getSession(true);
                session.setAttribute("currentSessionUser", user);
                session.setAttribute("currentUserId", user.getCpr());
                ServletContext servletContext = getServletContext();
                RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/appointment");
                requestDispatcher.forward(request, response);
               // response.sendRedirect("appointment-page.jsp"); //logged-in page
            } else{}
               // response.sendRedirect("invalidLogin.jsp"); //error page
        } catch (Throwable theException) {
            System.out.println(theException);
        }
    }
}
