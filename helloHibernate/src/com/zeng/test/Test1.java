package com.zeng.test;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Test;

import com.zeng.model.User;

public class Test1 {
	@Test
	public void test1(){
		//解析我们在hibernate.cfg.xml中的配置
		Configuration configuration = new Configuration().configure();
		//创建服务注册类,进一步注册初始化我们配置文件中的属性
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		//创建我们的数据库访问会话工厂
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		
		/****上面是配置准备，下面开始我们的数据库操作******/
		Session session = sessionFactory.openSession();//从会话工厂获取一个session
		
		Transaction transaction = session.beginTransaction();//开启一个新的事务
		User user = new User();
		user.setName("zengh");
		session.save(user);
		transaction.commit();//提交事务
	}
}
