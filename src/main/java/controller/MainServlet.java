package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "mainServlet", urlPatterns = "/main")
public class MainServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        ///User user = req.getParameter("user");
        //res.sendRedirect("main.jsp");
        req.getRequestDispatcher("main.jsp").forward(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

//        String username = req.getParameter("user");
//
//        PrintWriter out = res.getWriter();
//        out.println("do psot Hello user: " + username);
    }
}