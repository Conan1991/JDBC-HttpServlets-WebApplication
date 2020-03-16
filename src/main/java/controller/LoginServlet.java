package controller;

import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "loginServlet", urlPatterns = {"/login", "/"})
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserDao userDao;

    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

        userDao = new UserDao(jdbcURL, jdbcUsername, jdbcPassword);
    }

    public boolean login(String name, String password) {
        return userDao.login(name, password);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//            response.sendRedirect("login.jsp");
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (username.isEmpty() || password.isEmpty()) {
//            res.sendRedirect("login.jsp");
            req.getRequestDispatcher("login.jsp").forward(req, res);
        } else {
            if (login(username, password)) {
                req.getSession().setAttribute("user", userDao.getUser(username));
                res.sendRedirect("/main");
            } else {
                res.sendRedirect("/login");
            }
        }
    }
}
