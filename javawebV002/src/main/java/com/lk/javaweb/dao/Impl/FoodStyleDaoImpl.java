package com.lk.javaweb.dao.Impl;

import com.lk.javaweb.appcomm.BaseDaoMysql;
import com.lk.javaweb.dao.FoodStyleDao;
import com.lk.javaweb.enity.FoodStyle;

import java.util.List;

public class FoodStyleDaoImpl extends BaseDaoMysql<FoodStyle> implements FoodStyleDao {
    @Override
    public List<FoodStyle> select() {
        return findList(FoodStyle.class,"select * from  FoodStyle");
    }
}
