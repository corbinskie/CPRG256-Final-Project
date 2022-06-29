package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        //any code before chain.doFilter will be executed before the servlet is loaded
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession userSession = httpRequest.getSession();
        String currentUserRole = (String) userSession.getAttribute("currentUserRole");

        if (!currentUserRole.contains("admin")) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect("inventory");
            return;
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
