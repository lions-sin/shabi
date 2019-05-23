package com.lk.javaweb.dao.Impl;

import com.lk.javaweb.appcomm.BaseDaoMysql;
import com.lk.javaweb.dao.FoodDao;
import com.lk.javaweb.enity.Food;
import org.springframework.stereotype.Repository;

import java.util.List;


public class FoodDaoImpl extends BaseDaoMysql<Food> implements FoodDao {

    @Override
    public List<Food> select(Integer FoodStyleId, Integer LunchId) {

        StringBuffer sb = new StringBuffer();


        sb.append(" select * from food where 1=1");
        if (FoodStyleId > 0) {
            sb.append(" and foodStyleId = " + FoodStyleId);
        }
        if (LunchId > 0) {
            sb.append(" and lunchId= " + LunchId);
        }

        String sql = sb.toString();

        List<Food> list = findList(Food.class, sql);

        return list;
    }

    @Override
    public List<Food> select(Integer FoodStyleId, Integer LunchId, Integer pageNum, Integer pageSize) {

        StringBuffer sb = new StringBuffer();

        sb.append(" select * from food where 1=1");
        if (FoodStyleId > 0) {
            sb.append(" and foodStyleId = " + FoodStyleId);
        }
        if (LunchId > 0) {
            sb.append(" and lunchId= " + LunchId);
        }

        sb.append(" limit "+((pageNum-1)*pageSize)+","+pageSize);

        String sql = sb.toString();

        List<Food> list = findList(Food.class, sql);

        return list;
    }
}
