package com.lk.javaweb.dao.Impl;

import com.lk.javaweb.appcomm.JDBCRowMapperBuilder;
import com.lk.javaweb.dao.AdmininfoDao;
import com.lk.javaweb.enity.AdminInfo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository(value = "admininfoDaoImpl")
public class AdmininfoDaoImpl implements AdmininfoDao {

    @Resource(name="jdbc")//注入了springjdbc
    JdbcTemplate jdbcTemplate;
    /**
     * 后台管理登录
     *
     * @param adminEmail
     * @param adminPass
     * @return
     */
    @Override
    public AdminInfo login(String adminEmail, String adminPass) {
        String sql="select * from admininfo where adminEmail=? and adminPass=?";
        String[] args = {adminEmail,adminPass};
        AdminInfo adminInfo = jdbcTemplate.queryForObject(sql, new JDBCRowMapperBuilder<>(AdminInfo.class), args);
        return adminInfo;
    }

    /**
     * 查询全部管理员
     *
     * @return
     */
    @Override
    public List<AdminInfo> select() {

        String sql="select * from admininfo";
        List<AdminInfo> adminInfoList = jdbcTemplate.query(sql, new JDBCRowMapperBuilder<>(AdminInfo.class));
        return adminInfoList;
    }

    /**
     * 更新管理员
     *
     * @param ai
     */
    @Override
    public void update(AdminInfo ai) {

    }

    /**
     * 增加管理员
     *
     * @param ai
     */
    @Override
    public void save(AdminInfo ai) {
        String sql = "insert into admininfo (adminName,adminPass,adminStyle,adminEmail) values(?,?,?,?)";
        jdbcTemplate.update(sql,ai.getAdminName(),ai.getAdminPass(),ai.getAdminStyle(),ai.getAdminEmail());

    }
}
