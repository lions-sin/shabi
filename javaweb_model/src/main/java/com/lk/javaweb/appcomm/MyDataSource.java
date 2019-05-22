package com.lk.javaweb.appcomm;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * 这是一个数据库连接池
 * 一开始先往池子里放10个连接
 * <p>
 * 1.先创建10个连接
 * 2.来的程序通过getConnection获取连接
 * 3.用完之后，使用addBack归还连接
 * 4.扩容
 */
public class MyDataSource implements DataSource {

    BaseDaoMysql jdbc = new BaseDaoMysql();

    List<Connection> list = new ArrayList<Connection>();

    public MyDataSource() {
        for (int i = 0; i < 10; i++) {
            Connection conn = jdbc.getConn();
            list.add(conn);
        }
    }

    //该链接池对外公布获取连接的方法
    @Override
    public Connection getConnection() throws SQLException {
        //扩容
        if (list.size() == 0) {
            for (int i = 0; i < 5; i++) {
                Connection conn = jdbc.getConn();
                list.add(conn);
            }
        }
        //始终移除第一个连接
        Connection conn = list.remove(0);

        return conn;
    }

    public void addBack(Connection conn) {
        list.add(conn);
    }
    //----------------------------------------------------------------------------

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
