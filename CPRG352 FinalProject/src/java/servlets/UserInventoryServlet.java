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
import models.Item;
import models.User;
import services.AccountService;
import services.InventoryService;

public class UserInventoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession userSession = request.getSession();
        InventoryService invServ = new InventoryService();
        AccountService accServ = new AccountService();
        String action = request.getParameter("action");
        String responseMessage = "";
        userSession.setAttribute("responseMessage", responseMessage);
        String addOrEdit = "Add";
        request.setAttribute("addOrEdit", addOrEdit);

        String email = (String) userSession.getAttribute("email");

        try {
            User user = accServ.get(email);
            request.setAttribute("user", user);
            List<Item> itemList = invServ.getAll((String) userSession.getAttribute("email"));
            request.setAttribute("itemList", itemList);
            List<Category> categoriesList = invServ.getAll();
            request.setAttribute("categoriesList", categoriesList);
        } catch (Exception ex) {
            Logger.getLogger(UserInventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
        return;

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession userSession = request.getSession();
        InventoryService invServ = new InventoryService();
        AccountService accServ = new AccountService();
        String email = (String) userSession.getAttribute("email");
        String action = request.getParameter("action");
        String responseMessage = "";
        String addOrEdit = (String) userSession.getAttribute("addOrEdit");
        request.setAttribute("addOrEdit", userSession.getAttribute(addOrEdit));

        try {
            User user = accServ.get(email);
            request.setAttribute("user", user);
        } catch (Exception ex) {
            Logger.getLogger(UserInventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (action != null && action.equals("save") && addOrEdit.equals("Add")) {

            int category = Integer.parseInt(request.getParameter("add/EditCategory"));
            String nameInput = request.getParameter("add/EditName");
            double priceInput = Double.parseDouble(request.getParameter("add/EditPrice"));

            try {
                invServ.insert(email, category, nameInput, priceInput);
            } catch (Exception ex) {
                Logger.getLogger(UserInventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                List<Item> itemList = invServ.getAll((String) userSession.getAttribute("email"));
                request.setAttribute("itemList", itemList);
                List<Category> categoriesList = invServ.getAll();
                request.setAttribute("categoriesList", categoriesList);
            } catch (Exception ex) {
                Logger.getLogger(UserInventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            responseMessage = "Item added!";
            request.setAttribute("responseMessage", responseMessage);

            getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
            return;

        }

        if (action != null && action.equals("delete")) {
            int itemId = Integer.parseInt(request.getParameter("deleteButton"));

            try {
                invServ.delete(itemId);
            } catch (Exception ex) {
                Logger.getLogger(UserInventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                List<Item> itemList = invServ.getAll((String) userSession.getAttribute("email"));
                request.setAttribute("itemList", itemList);
                List<Category> categoriesList = invServ.getAll();
                request.setAttribute("categoriesList", categoriesList);
            } catch (Exception ex) {
                Logger.getLogger(UserInventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            addOrEdit = "Add";
            userSession.setAttribute("addOrEdit", addOrEdit);

            responseMessage = "Item deleted!";
            request.setAttribute("responseMessage", responseMessage);

            getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
            return;
        }

        if (action != null && action.equals("edit")) {
            addOrEdit = "Edit";
            userSession.setAttribute("addOrEdit", addOrEdit);
            request.setAttribute("addOrEdit", userSession.getAttribute("addOrEdit"));

            int itemId = Integer.parseInt(request.getParameter("editButton"));

            try {
                Item item = invServ.getFromID(itemId);
                request.setAttribute("itemForEdit", item);
                invServ.delete(itemId);
            } catch (Exception ex) {
                Logger.getLogger(UserInventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                List<Item> itemList = invServ.getAll((String) userSession.getAttribute("email"));
                request.setAttribute("itemList", itemList);
                List<Category> categoriesList = invServ.getAll();
                request.setAttribute("categoriesList", categoriesList);
            } catch (Exception ex) {
                Logger.getLogger(UserInventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
            return;

        }

        if (action != null && action.equals("save") && addOrEdit.equals("Edit")) {
            int category = Integer.parseInt(request.getParameter("add/EditCategory"));
            String nameInput = request.getParameter("add/EditName");
            double priceInput = Double.parseDouble(request.getParameter("add/EditPrice"));

            try {
                invServ.insert(email, category, nameInput, priceInput);
            } catch (Exception ex) {
                Logger.getLogger(UserInventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                List<Item> itemList = invServ.getAll((String) userSession.getAttribute("email"));
                request.setAttribute("itemList", itemList);
                List<Category> categoriesList = invServ.getAll();
                request.setAttribute("categoriesList", categoriesList);
            } catch (Exception ex) {
                Logger.getLogger(UserInventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            addOrEdit = "Add";
            userSession.setAttribute("addOrEdit", addOrEdit);

            responseMessage = "Item edited!";
            request.setAttribute("responseMessage", responseMessage);

            getServletContext().getRequestDispatcher("/WEB-INF/inventory.jsp").forward(request, response);
            return;

        }
    }

}
