package com.lk.javaweb.dao;


import com.lk.javaweb.enity.Food;

import java.util.List;

public interface FoodDao {

    public List<Food>  select(Integer FoodStyleId, Integer LunchId);
}
