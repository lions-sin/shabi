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

        if(todo.equals("select")){

            select(request, response);

        }else if(todo.equals("initFood")){

            initFood(request);

        }else if(todo.equals("selectFoodByfoodStyleId")){

            //查询产品列表
            FoodDao dao = new FoodDaoImpl();
            String foodStyleId_Str=request.getParameter("foodStyleId");
            Integer foodStyleId=1;

            if(foodStyleId_Str!=null){
                foodStyleId = Integer.valueOf(foodStyleId_Str);
            }

            List<Food> foodList = dao.select(foodStyleId,0);

            request.setAttribute("foodList",foodList);

            request.getRequestDispatcher("caidanyinpin.jsp").forward(request,response);
        }
    }

    private void initFood(HttpServletRequest request) {
        FoodDao dao = new FoodDaoImpl();
        Integer foodStyleId=1;
        List<Food> foodList = dao.select(foodStyleId, 0);

        request.setAttribute("foodList",foodList);
    }

    private void select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FoodDao dao = new FoodDaoImpl();
        List<Food> foodList = dao.select(2, 0);

        request.setAttribute("foodList",foodList);

        request.getRequestDispatcher("").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
