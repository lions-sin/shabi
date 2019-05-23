package com.lk.javaweb.dao;


import com.lk.javaweb.enity.Food;

import java.util.List;

public interface FoodDao {

    /**
     * 前台产品查询
     * @param FoodStyleId
     * @param LunchId
     * @return
     */
    public List<Food>  select(Integer FoodStyleId, Integer LunchId);

    /**
     * 后台产品查询
     * @param FoodStyleId
     * @param LunchId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List<Food>  select(Integer FoodStyleId, Integer LunchId, Integer pageNum, Integer pageSize);
}
