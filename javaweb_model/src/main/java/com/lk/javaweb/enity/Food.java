package com.lk.javaweb.enity;

import lombok.Data;

@Data
public class Food {

    private Integer foodId;
    private String foodName;
    private String foodIntroduce;
    private Double foodPrice;
    private Integer foodStyleId;
    private Integer foodCount;
    private Integer lunchId;
    private String imagesPath;
}
