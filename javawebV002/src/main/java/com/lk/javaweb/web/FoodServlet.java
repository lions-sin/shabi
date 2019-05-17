package com.lk.javaweb.web;

import com.lk.javaweb.dao.FoodDao;
import com.lk.javaweb.dao.Impl.FoodDaoImpl;
import com.lk.javaweb.enity.Food;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FoodServlet" ,urlPatterns = {"/food"})
public class FoodServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        request.setCharacterEncoding("utf-8");

        String todo = request.getParameter("todo");

        FoodDao dao = new FoodDaoImpl();


        if(todo.equals("select")){

            List<Food> foodList = dao.select(2, 0);

            request.setAttribute("foodList",foodList);

            request.getRequestDispatcher("").forward(request,response);
        }else if(todo.equals("initFood")){

            Integer foodStyleId=1;
            List<Food> foodList = dao.select(foodStyleId, 0);

            request.setAttribute("foodList",foodList);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
