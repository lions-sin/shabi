package com.lk.javaweb.dao.test;

import com.lk.javaweb.appcomm.BeansUtil;
import com.lk.javaweb.dao.AdmininfoDao;
import com.lk.javaweb.enity.AdminInfo;
import org.junit.Test;


public class AdmininfoDaoImplTest {

    AdmininfoDao admininfoDao = BeansUtil.getBean("admininfoDaoImpl",AdmininfoDao.class);

    @Test
    public void login() {

        AdminInfo login = admininfoDao.login("admin@qq.com", "123123");
        System.out.println(login);
    }

    @Test
    public void select() {

        for (AdminInfo admininfo:admininfoDao.select()) {
            System.out.println(admininfo);
        }
    }

    @Test
    public void update() {
    }

    @Test
    public void save() {
    }
}