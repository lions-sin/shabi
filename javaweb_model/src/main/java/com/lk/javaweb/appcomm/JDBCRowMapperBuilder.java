package com.lk.javaweb.appcomm;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.jdbc.core.RowMapper;

/**
 * 实体类和数据库表字段关联上
 * @param <T>
 */
public class JDBCRowMapperBuilder<T> implements RowMapper<T> {
	String[] objName = null;
	Map<String, String> map = new HashMap<String, String>();

	T t;

	public JDBCRowMapperBuilder(Class<T> clazz) {
		// TODO Auto-generated constructor stub

		try {
			t = clazz.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 处理实体类所对应的属性名 与表不一致 参数顺序需要与 实体类顺序一致
	 * 
	 * @param propertyName
	 * @return
	 */
	public JDBCRowMapperBuilder<T> setColumnName(String... propertyName) {

		objName = propertyName;
		for (String string : propertyName) {
			System.out.println("propertyName-----" + propertyName);
		}
		return this;
	}

	public T getT() {
		return t;
	}

	public JDBCRowMapperBuilder<T> setT(T t) {
		this.t = t;
		return this;
	}

	@Override
	public T mapRow(ResultSet re, int index) throws SQLException {
		// TODO Auto-generated method stub
		Class clazz;
		T tclass = null;

		try {
			tclass = newTclass(t.getClass());
			String className = t.getClass().getName();

			clazz = Class.forName(className);
			Field[] fields = clazz.getDeclaredFields(); // 根据Class对象获得属性
														// 私有的也可以获得
			/*
			 * for (Field f : fields) { //map.put(f.getName(), f.getName());
			 * Object value = null; try{ value = re.getObject(f.getName());
			 * }catch (Exception e) { // TODO: handle exception } TSet(tclass,
			 * f.getName(), value); //System.out.println(f.getType().getName());
			 * // 打印每个属性的类型名字 }
			 */

			if (objName != null
					&& objName.length == re.getMetaData().getColumnCount()) {
				int i = 0;
				System.out.println("re.getMetaData().getColumnCount():"
						+ re.getMetaData().getColumnCount());
				System.out.println("objName.length" + objName.length);
				for (Field f : fields) {
					if (i < objName.length) {
						System.out.println(i);
						System.out.println("f.getName():" + f.getName());
						System.out.println("objName:" + objName[i]);
						map.put(f.getName(), objName[i]);
						i++;
					}else{
						map.put(f.getName(), f.getName());	
					}
					// System.out.println(f.getType().getName()); // 打印每个属性的类型名字
				}
			} else {
				for (Field f : fields) {
					map.put(f.getName(), f.getName());
					// System.out.println(f.getType().getName()); // 打印每个属性的类型名字
				}
			}

			Set<String> keySet = map.keySet();
			for (Field f : fields) {
				Object value = null;
				try {
					value = re.getObject(map.get(f.getName()));
					// System.out.println(value);
				} catch (Exception e) {
					// TODO: handle exception
				}
				TSet(tclass, f.getName(), value);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} // 根据类名获得其对应的Class对象 写上你想要的类名就是了 注意是全名 如果有包的话要加上 比如java.Lang.String
		catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// int columnCount = re.getMetaData().getColumnCount();
		return tclass;
	}

	private T newTclass(Class<? extends Object> class1)
			throws InstantiationException, IllegalAccessException {
		// TODO Auto-generated method stub
		T a = (T) class1.newInstance();
		return a;
	}

	private void TSet(T t, String propertyName, Object value) {
		try {
			Field field = t.getClass().getDeclaredField(propertyName);
			field.setAccessible(true);
			field.set(t, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
