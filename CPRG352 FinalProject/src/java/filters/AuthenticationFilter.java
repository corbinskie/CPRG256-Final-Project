package filters;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;

public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        //any code before chain.doFilter will be executed before the servlet is loaded
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        AccountService accServ = new AccountService();
        String email = (String) session.getAttribute("email");
        String responseMessage = "";
        request.setAttribute("responseMessage", responseMessage);

        try {
            User user = accServ.get(email);

            if (email == null) {
                responseMessage = "Please enter a valid email address!";
                request.setAttribute("responseMessage", responseMessage);

                HttpServletResponse httpResponse = (HttpServletResponse) response;
                httpResponse.sendRedirect("login");
                return;
            }
            if (user.getActive() == false) {
                responseMessage = "This account was deactivated!";
                request.setAttribute("responseMessage", responseMessage);

                HttpServletResponse httpResponse = (HttpServletResponse) response;
                httpResponse.sendRedirect("login");
                return;
            }

        } catch (Exception ex) {
            Logger.getLogger(AuthenticationFilter.class.getName()).log(Level.SEVERE, null, ex);
        }

        //this will call upon either the next filter in the chain or it will load the requested servlet
        chain.doFilter(request, response);
        //any code after chain.doFilter will be executed after the servlet is loaded, during the response
    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

}
