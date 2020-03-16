package controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter("/*")
public class FilterServlet implements Filter {
    private HttpServletRequest httpRequest;

    private static final String[] loginRequiredURLs = {"/main"};

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        httpRequest = (HttpServletRequest) request;

        HttpSession session = httpRequest.getSession(false);


        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);

        if(session!= null)
            System.out.println(session.getAttribute("user") != null);

        String loginURI = httpRequest.getContextPath() + "/login";
        //boolean isLoginRequest = httpRequest.getRequestURI().equals(loginURI);
        //boolean isLoginPage = httpRequest.getRequestURI().endsWith("login.jsp");

        if (isLoggedIn) {
            // the user is already logged in and he's trying to login again
            // then forward to the homepage
            httpRequest.getRequestDispatcher("/main.jsp").forward(request, response);
            System.out.println("case 1");

        } else if (isLoginRequired()) {
            // the user is not logged in, and the requested page requires
            // authentication, then forward to the login page
            String loginPage = "/login.jsp";
            RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(loginPage);
            System.out.println("case 2");
            dispatcher.forward(request, response);
        } else {
            // for other requested pages that do not require authentication
            // or the user is already logged in, continue to the destination
            System.out.println("case 3");
            chain.doFilter(request, response);
        }
    }


    private boolean isLoginRequired() {
        String requestURL = httpRequest.getRequestURL().toString();
        System.out.println(requestURL);
        for (String loginRequiredURL : loginRequiredURLs) {
            if (requestURL.contains(loginRequiredURL)) {
                return true;
            }
        }

        return false;
    }

    public void destroy() {
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }

}
