package com.lk.javaweb.appcomm;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeansUtil {
	
	
	public static ApplicationContext  ac;
	
	
	static{//静态块
		String[] xml={"springjdbc.xml"};
		//String[] xml={"applicationContext.xml"};
		
		ac=new  ClassPathXmlApplicationContext(xml);
		
	
	}
	
	
	
	
	public static <T> T getBean(String beanid,Class<T>  tclass){
		
		return ac.getBean(beanid, tclass);
		
		
		
		
	}

	
	public static void main(String[] args) {
		
		
		
		String names[]= ac.getBeanDefinitionNames();
		
		for (String beanid : names) {
			System.out.println(beanid);
		}
		
		
		
		
	
		
		
		
		
		
		
	}

}
