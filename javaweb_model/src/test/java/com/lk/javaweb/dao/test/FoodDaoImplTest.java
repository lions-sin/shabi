package com.lk.javaweb.dao.test;

import com.lk.javaweb.dao.FoodDao;
import com.lk.javaweb.dao.Impl.FoodDaoImpl;
import com.lk.javaweb.enity.Food;
import org.junit.Test;

import java.util.List;

public class FoodDaoImplTest {

    FoodDao dao = new FoodDaoImpl();

    @Test
    public void select() {

        List<Food> foodList = dao.select(2, 0);

        for (Food foods : foodList) {
            System.out.println(foods);
        }
    }
}