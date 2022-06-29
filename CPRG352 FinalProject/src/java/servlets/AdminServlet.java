package servlets;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Category;
import models.Role;
import models.User;
import services.AccountService;
import services.InventoryService;

public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession userSession = request.getSession();
        AccountService accServ = new AccountService();
        InventoryService invServ = new InventoryService();
        String action = request.getParameter("action");
        String responseMessage = "";
        String addOrEdit = "Add";
        userSession.setAttribute("addOrEdit", addOrEdit);

        try {
            List<User> usersList = accServ.getAll();
            request.setAttribute("usersList", usersList);
            List<Role> roleList = accServ.getAllRoles();
            request.setAttribute("roleList", roleList);
            List<Category> categoryList = invServ.getAll();
            request.setAttribute("categoryList", categoryList);
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
        return;

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession userSession = request.getSession();
        AccountService accServ = new AccountService();
        InventoryService invServ = new InventoryService();
        String action = request.getParameter("action");
        String responseMessage = "";
        String addOrEdit = (String) userSession.getAttribute("addOrEdit");
        request.setAttribute("addOrEdit", userSession.getAttribute(addOrEdit));

        if (action != null && action.equals("deleteUser")) {
            String userToDelete = request.getParameter("deleteUserButton");

            try {
                accServ.delete(userToDelete);
            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                List<User> usersList = accServ.getAll();
                request.setAttribute("usersList", usersList);
                List<Role> roleList = accServ.getAllRoles();
                request.setAttribute("roleList", roleList);
            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            responseMessage = "User Deleted!";
            request.setAttribute("responseMessage", responseMessage);

            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
            return;

        }

        if (action != null && action.equals("editUser")) {
            String userToEdit = request.getParameter("editUserButton");
            addOrEdit = "Edit";
            userSession.setAttribute("addOrEdit", addOrEdit);
            request.setAttribute("addOrEdit", userSession.getAttribute(addOrEdit));

            try {
                User user = accServ.get(userToEdit);
                request.setAttribute("userForEdit", user);
            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                List<User> usersList = accServ.getAll();
                request.setAttribute("usersList", usersList);
                List<Role> roleList = accServ.getAllRoles();
                request.setAttribute("roleList", roleList);
            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
            return;

        }

        if (action != null && action.equals("save") && addOrEdit.equals("Edit")) {
            String emailForEdit = request.getParameter("addEmail");
            String passwordForEdit = request.getParameter("addPassword");
            String fNameForEdit = request.getParameter("addFName");
            String lNameForEdit = request.getParameter("addLName");
            int roleForEdit = Integer.parseInt(request.getParameter("addRole"));
            boolean activityForEdit = Boolean.parseBoolean(request.getParameter("editActivity"));

            try {
                accServ.update(emailForEdit, activityForEdit, roleForEdit, passwordForEdit, fNameForEdit, lNameForEdit);
            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                List<User> usersList = accServ.getAll();
                request.setAttribute("usersList", usersList);
                List<Role> roleList = accServ.getAllRoles();
                request.setAttribute("roleList", roleList);
            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            addOrEdit = "Add";
            userSession.setAttribute("addOrEdit", addOrEdit);

            responseMessage = "User Edited!";
            request.setAttribute("responseMessage", responseMessage);

            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
            return;
        }

        if (action != null && action.equals("save") && addOrEdit.equals("Add")) {
            String emailForEdit = request.getParameter("addEmail");
            String passwordForEdit = request.getParameter("addPassword");
            String fNameForEdit = request.getParameter("addFName");
            String lNameForEdit = request.getParameter("addLName");
            int roleForEdit = Integer.parseInt(request.getParameter("addRole"));
            boolean activityForEdit = Boolean.parseBoolean(request.getParameter("editActivity"));

            try {
                accServ.insertAsAdmin(emailForEdit, activityForEdit, roleForEdit, passwordForEdit, fNameForEdit, lNameForEdit);
            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                List<User> usersList = accServ.getAll();
                request.setAttribute("usersList", usersList);
                List<Role> roleList = accServ.getAllRoles();
                request.setAttribute("roleList", roleList);
            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            responseMessage = "User Added!";
            request.setAttribute("responseMessage", responseMessage);

            getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
            return;

        }

    }

}
