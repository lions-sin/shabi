package com.lk.javaweb.web;

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

@WebServlet(name = "FoodStyleServlet", urlPatterns = "/fs")
public class FoodStyleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String todo = request.getParameter("todo");
        FoodStyleDao dao = new FoodStyleDaoImpl();

        if(todo!=null){
            //加载产品类
            if(todo.equals("initFs")){
                List<FoodStyle> foodStyleList = dao.select();

                //单次请求域中的内容
                request.setAttribute("foodStyleList",foodStyleList);
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
