package com.lk.javaweb.dao.test;

import com.lk.javaweb.appcomm.BeansUtil;
import com.lk.javaweb.dao.FoodDao;
import com.lk.javaweb.dao.Impl.FoodDaoImpl_admin;
import com.lk.javaweb.enity.Food;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FoodDaoImpl_adminTest {

    FoodDao dao = BeansUtil.getBean("foodDaoImpl_admin", FoodDaoImpl_admin.class);

    @Test
    public void select() {

        List<Food> foodList = dao.select(0, 0, 1, 6);
        for (Food food : foodList) {
            System.out.println(food);
        }
    }
}