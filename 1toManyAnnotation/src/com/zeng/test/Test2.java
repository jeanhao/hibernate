package com.zeng.test;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zeng.model.Article;
import com.zeng.model.User;

public class Test2 {
	private ApplicationContext ac;
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	@BeforeClass//在测试类初始化时调用此方法，完成静态对象的初始化
	public static void before(){
	}
	@Before//每一个被注解Test方法在调用前都会调用此方法一次
	public void setup(){//建立针对我们当前测试方法的的会话和事务
		ac = new ClassPathXmlApplicationContext("spring-datasource.xml");
		sessionFactory = (SessionFactory) ac.getBean("sessionFactory");
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}
	//测试级联关系映射注解配置：多对一单向关联
//	@Test
	public void test1(){
		//测试级联添加
//		User user = new User();
//		user.setName("name");
//		Article article = new Article();
//		article.setContent("content");
//		article.setUser(user);//建立级联关系
//		session.save(article);
		
		//测试级联删除
		Article article = (Article) session.get(Article.class,3);
		session.delete(article);
	}
	//测试一对多单向关联
//	@Test
	public void test2(){
		//测试级联添加
		User user = new User();
		user.setName("oneObject");
		Set<Article> articles = new HashSet<Article>();
		for(int i = 0 ; i < 3;i ++){
			Article article = new Article();
			article.setContent("moreContent" + i) ;
			articles.add(article);
		}
		user.setArticles(articles);//建立关联关系
		session.save(user);
		
		//测试级联删除
//		User user = (User) session.get(User.class, 1);
//		session.delete(user);
		
//		测试orphanRemoval去除”孤儿“
//		User user = (User) session.get(User.class,2);
//		Article article = user.getArticles().iterator().next();
//		user.getArticles().remove(article);
//		session.update(user);
	}
	//测试一对多多对一双向关联
	@Test
	public void test3(){
		//测试放弃维护的一方添加数据
//		User user = new User();
//		user.setName("oneObject");
//		Set<Article> articles = new HashSet<Article>();
//		for(int i = 0 ; i < 3;i ++){
//			Article article = new Article();
//			article.setContent("moreContent" + i) ;
//			articles.add(article);
//		}
//		user.setArticles(articles);//建立关联关系
//		session.save(user);
		//测试放弃维护的一方获取多方数据
//		User user = (User) session.get(User.class, 1);
//		System.out.println("获取用户对应的文章数据："+user.getArticles());
		//测试主维护的多方添加数据
		User user = new User();
		user.setName("oneObject1");
		for(int i = 0 ; i < 3;i ++){
			Article article = new Article();
			article.setContent("moreContent1" + i) ;
			article.setUser(user);//有article来建立关联关系
			session.save(article);//持久化
		}
	}
	@After//每一个被注解Test方法在调用后都会调用此方法一次
	public void teardown(){
		if(transaction.isActive()){//如果当前事务尚未提交，则
			transaction.commit();//提交事务，主要为了防止在测试中已提交事务，这里又重复提交
		}
		session.clear();
		session.close();
		sessionFactory.close();
	}
	@After//在类销毁时调用一次
	public void after(){
	}

}
