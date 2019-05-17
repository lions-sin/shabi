package com.lk.javaweb.dao.test;

import com.lk.javaweb.dao.FoodStyleDao;
import com.lk.javaweb.dao.Impl.FoodStyleDaoImpl;
import com.lk.javaweb.enity.FoodStyle;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FoodStyleDaoImplTest {

    @Test
    public void select() {

        FoodStyleDao dao = new FoodStyleDaoImpl();

        List<FoodStyle> select = dao.select();

        for (FoodStyle foodStyle : select) {
            System.out.println(foodStyle);
        }
    }
}