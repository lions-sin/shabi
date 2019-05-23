package com.lk.javaweb.web.admin;

import com.lk.javaweb.appcomm.BeansUtil;
import com.lk.javaweb.dao.FoodDao;
import com.lk.javaweb.dao.Impl.FoodDaoImpl;
import com.lk.javaweb.dao.Impl.FoodDaoImpl_admin;
import com.lk.javaweb.enity.Food;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "foodServlet_admin", urlPatterns = "/food_admin")
public class FoodServlet_admin extends HttpServlet {

    FoodDao foodDao = BeansUtil.getBean("foodDaoImpl_admin", FoodDaoImpl_admin.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String todo = request.getParameter("todo");

        if (todo.equals("selectByPage")) {

            String pageNumStr = request.getParameter("pageNum");
            Integer FoodStyleId = 0;
            Integer LunchId = 0;
            Integer pageNum = 1;
            Integer pageSize = 6;

            if (pageNumStr != null) {
                pageNum = Integer.valueOf(pageNumStr);
            }

            List<Food> foodList = foodDao.select(FoodStyleId, LunchId, pageNum, pageSize);

            request.setAttribute("foodList",foodList);

            request.getRequestDispatcher("food.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
