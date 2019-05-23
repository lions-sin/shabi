package com.lk.javaweb.web.admin;

import com.lk.javaweb.dao.FoodStyleDao;
import com.lk.javaweb.dao.Impl.FoodStyleDaoImpl;
import com.lk.javaweb.enity.FoodStyle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "foodStyleServlet", urlPatterns = "/adminfs")
public class FoodStyleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String todo = request.getParameter("todo");
        FoodStyleDao dao = new FoodStyleDaoImpl();

        if(todo.equals("selectAll")){

            List<FoodStyle> foodStyleList = dao.select();

            request.setAttribute("foodStyleList",foodStyleList);

            request.getRequestDispatcher("foodstyle.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
