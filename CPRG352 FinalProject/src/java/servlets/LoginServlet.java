package servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;
import services.InventoryService;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession userSession = request.getSession();
        String action = request.getParameter("action");
        String responseMessage = "";
        request.setAttribute("responseMessage", responseMessage);

        if (action != null && action.equals("logout")) {

            userSession.invalidate();

            responseMessage = "You have successfully logged out!";
            request.setAttribute("responseMessage", responseMessage);

            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }

        if (action != null && action.equals("register")) {

            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            return;
        }

        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        return;

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession userSession = request.getSession();
        AccountService accServ = new AccountService();
        InventoryService invServ = new InventoryService();

        String responseMessage = "";

        String emailInput = request.getParameter("email");
        String passwordInput = request.getParameter("password");
        request.setAttribute("email", emailInput);
        request.setAttribute("password", passwordInput);

        try {
            User user = accServ.login(emailInput, passwordInput);
            String currentUserRole = user.getRole().getRoleName();
            userSession.setAttribute("currentUserRole", currentUserRole);
            userSession.setAttribute("email", emailInput);

            if (currentUserRole.contains("admin")) {
                response.sendRedirect("admin");
                return;
            } else {
                response.sendRedirect("inventory");
                return;
            }

        } catch (Exception ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);

            responseMessage = "Invalid Username/Password!";
            request.setAttribute("responseMessage", responseMessage);

            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
    }
}
