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

public class UserAccountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession userSession = request.getSession();
        String email = (String) userSession.getAttribute("email");
        AccountService accServ = new AccountService();
        String responseMessage = "";

        try {
            User user = accServ.get(email);
            request.setAttribute("userForEdit", user);
        } catch (Exception ex) {
            Logger.getLogger(UserAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/account.jsp").forward(request, response);
        return;

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession userSession = request.getSession();
        String email = (String) userSession.getAttribute("email");
        AccountService accServ = new AccountService();
        String responseMessage = "";

        boolean activity = Boolean.parseBoolean(request.getParameter("editActivity"));
        String password = request.getParameter("editPassword");
        String firstName = request.getParameter("editFName");
        String lastName = request.getParameter("editLName");

        try {
            accServ.update(email, activity, password, firstName, lastName);
        } catch (Exception ex) {
            Logger.getLogger(UserAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        responseMessage = "Account Updated!";
        request.setAttribute("responseMessage", responseMessage);

        if (activity == false) {
            responseMessage = "Successfully deactivated account!";
            userSession.setAttribute("responseMessage", responseMessage);

            response.sendRedirect("login");
            return;
        } else {
            responseMessage = "Account updated!";
            userSession.setAttribute("responseMessage", responseMessage);

            response.sendRedirect("account");
            return;
        }
    }

}
