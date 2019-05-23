package com.lk.javaweb.dao.Impl;

import com.lk.javaweb.appcomm.JDBCRowMapperBuilder;
import com.lk.javaweb.dao.FoodDao;
import com.lk.javaweb.enity.Food;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository(value = "foodDaoImpl_admin")
public class FoodDaoImpl_admin implements FoodDao {

    @Resource(name="jdbc")
    JdbcTemplate jdbcTemplate;
    /**
     * 前台产品查询
     *
     * @param FoodStyleId
     * @param LunchId
     * @return
     */
    @Override
    public List<Food> select(Integer FoodStyleId, Integer LunchId) {
        return null;
    }

    /**
     * 后台产品查询
     *
     * @param FoodStyleId
     * @param LunchId
     * @param pageNum
     * @param pageSize
     * @return
     */
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

        sb.append(" limit "+((pageNum-1)*pageSize)+" , "+pageSize);

        String sql = sb.toString();
        System.out.println(sql);

        JDBCRowMapperBuilder<Food> JDBCRowMapperBuilder = new JDBCRowMapperBuilder<Food>(Food.class);
        List<Food> foodList = jdbcTemplate.query(sql, JDBCRowMapperBuilder);
        return foodList;
    }
}
