package com.sitanems.springInterface;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContextFactory {
	private static ClassPathXmlApplicationContext ctx = null;
	static {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	public static ApplicationContext getApplicationContext()
	{
		return ctx;
	}
}
