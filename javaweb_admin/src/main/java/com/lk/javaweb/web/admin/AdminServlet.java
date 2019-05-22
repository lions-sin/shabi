package com.lk.javaweb.web.admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "adminServlet",urlPatterns = "/adminServlet")
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String todo = req.getParameter("todo");

        if (todo.equals("adminlogin")) {

            String adminEmail = req.getParameter("adminEmail");
            String adminPass = req.getParameter("adminPass");

            if (adminEmail.equals("admin@qq.com")) {
                resp.sendRedirect(req.getContextPath()+"/index.jsp");
            }
        }
    }


}
