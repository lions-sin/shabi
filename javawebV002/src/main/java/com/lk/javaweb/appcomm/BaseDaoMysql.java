package com.lk.javaweb.appcomm;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BaseDaoMysql<T> {
	public static Properties properties = null;
	private Connection conn = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	public BaseDaoMysql() {
		// 读取属性文件
		properties = new Properties();
		InputStream in = this.getClass().getResourceAsStream("/mysql.properties");
		try {
			properties.load(in);
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}

	}

	public Connection getConn() {
		Connection connection = null;
		try {
			Class.forName(properties.getProperty("driver"));
			connection = DriverManager.getConnection(
					properties.getProperty("url"),
					properties.getProperty("name"),
					properties.getProperty("pwd"));
		} catch (Exception err) {
			System.out.println("连接ConDB-->getCon()____JDBC错误!");
			err.printStackTrace();
			return null;
		}
		return connection;
	}

	// 关闭所有
	public void closeAll(ResultSet rs, PreparedStatement ps, Connection con) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 关闭所有
	public void closeAll(PreparedStatement ps, Connection con) {
		try {

			if (ps != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 查询全部数据 根据 sql条件
	 * @param myclass
	 * @param sql
	 * @param objs
	 * @return
	 */
	public List<T> findList(Class myclass, String sql, Object... objs) {
		List<Object> info = new ArrayList<Object>();

		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			if (objs != null) {
				for (int i = 0; i < objs.length; i++) { // 不等于空证明有传值
					ps.setObject(i + 1, objs[i]);
				}
			}
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData(); // 获得元数据
			int columnCount = rsmd.getColumnCount(); // 获得查询的表列数个数
			Class type = null;
			while (rs.next()) {
				Method method = null;// 声明Method对象

				Object obj = myclass.newInstance(); // 创建一个实例
				for (int i = 1; i <= columnCount; i++) {

					String cName = getSetMethodName(rsmd.getColumnName(i)); // 获取字段名字组合setXxx
					String clsName = rsmd.getColumnClassName(i); // 获取该字段类型
					Method m = myclass.getMethod(cName, Class.forName(clsName));// 通过方法名和返回类型获得对应方法
					m.invoke(obj, rs.getObject(rsmd.getColumnName(i)));// 执行setXxx相当于给obj对象赋值

				}
				info.add(obj);

			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			closeAll(rs, ps, conn);
		}

		return (List<T>) info;

	}
	/**
	 * 查询一个对象
	 * @param myclass
	 * @param sql
	 * @param objs
	 * @return
	 */
	public T findInfo(Class myclass, String sql, Object... objs) {

		Object newObj = null;
		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			if (objs != null) {
				for (int i = 0; i < objs.length; i++) { // 不等于空证明有传值
					ps.setObject(i + 1, objs[i]);
				}
			}
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();// 获得元数据
			int columnCount = rsmd.getColumnCount();// 获得查询的列数个数
			Class type = null;
			if (rs.next()) {
				Method method = null;
				Object obj = myclass.newInstance(); // 创建一个实例
				for (int i = 1; i <= columnCount; i++) {
					String cName = getSetMethodName(rsmd.getColumnName(i)); // 获取字段名字组合setXxx
					String clsName = rsmd.getColumnClassName(i);// 获取该字段类型
					Method m = myclass.getMethod(cName, Class.forName(clsName));// 通过方法名和返回类型获得对应方法
					m.invoke(obj, rs.getObject(rsmd.getColumnName(i)));// 执行setXxx相当于给obj对象赋值

				}
				newObj = obj;
			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			closeAll(rs, ps, conn);
		}
		return (T) newObj;
	}
	/**
	 * 添加，删除 ，修改
	 * @param sql
	 * @param objs
	 * @return
	 */
	public boolean update(String sql, Object... objs) {
		int resule = 0;
		conn = getConn();
		try {
			ps = conn.prepareStatement(sql);
			if (objs != null) {
				for (int i = 0; i < objs.length; i++) { // 不等于空证明有传值
					ps.setObject(i + 1, objs[i]);
				}
			}
			resule = ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(ps, conn);
		}
		return false;

	}

	/**
	 * 组装set方法
	 *
	 * @param columnName
	 *            字段名
	 * @return
	 */
	private static String getSetMethodName(String columnName) {
		return "set" + columnName.substring(0, 1).toUpperCase()
				+ columnName.substring(1);
	}



}
