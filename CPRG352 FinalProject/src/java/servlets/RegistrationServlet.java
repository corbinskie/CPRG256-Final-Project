package servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.AccountService;

public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/WEB-INF/registration.jsp").forward(request, response);
        return;

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession userSession = request.getSession();
        AccountService accServ = new AccountService();
        String action = request.getParameter("action");
        String responseMessage = "";

        if (action != null && action.equals("register")) {
            String emailInput = request.getParameter("registerEmail");
            String passwordInput = request.getParameter("registerPassword");
            String fNameInput = request.getParameter("registerFName");
            String lNameInput = request.getParameter("registerLName");
            userSession.setAttribute("email", emailInput);

            try {
                accServ.insert(emailInput, fNameInput, lNameInput, passwordInput);
            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            responseMessage = "Welcome! Thank you for registering!";
            userSession.setAttribute("responseMessage", responseMessage);

            response.sendRedirect("inventory");
            return;

        }

    }
}
