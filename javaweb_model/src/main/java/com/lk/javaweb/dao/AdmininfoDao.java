package com.lk.javaweb.dao;

import com.lk.javaweb.enity.AdminInfo;

import java.util.List;

public interface AdmininfoDao {

    /**
     * 后台管理登录
     * @param adminEmail
     * @param adminPass
     * @return
     */
    public AdminInfo login(String adminEmail, String adminPass);

    /**
     * 查询全部管理员
     * @return
     */
    public List<AdminInfo> select();

    /**
     * 更新管理员
     * @param ai
     */
    public void update(AdminInfo ai);

    /**
     * 增加管理员
     * @param ai
     */
    public void save (AdminInfo ai);
}
