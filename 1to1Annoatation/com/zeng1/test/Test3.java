package com.zeng1.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zeng1.model.Article;
import com.zeng1.model.ArticleContent;

public class Test3 {
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
	//测试级联关系映射注解配置：一对一单向关联
//	@Test
	public void test1(){
		//测试级联添加
//		Article article = new Article();
//		article.setTitle("title");
//		ArticleContent articleContent = new ArticleContent();
//		articleContent.setContent("content");
//		article.setArticleContent(articleContent);//建立映射关系
//		session.save(article);
		//测试级联删除
//		Article article = (Article) session.get(Article.class,1);
//		session.delete(article);
	}
	//测试一对一双向关联,双方共同维护关系
	@Test
	public void test2(){
		//测试article级联添加
//		Article article = new Article();
//		article.setTitle("title");
//		ArticleContent articleContent = new ArticleContent();
//		articleContent.setContent("content");
//		article.setArticleContent(articleContent);
//		session.save(article);
		//测试articleContent级联添加
//		Article article = new Article();
//		article.setTitle("title");
//		ArticleContent articleContent = new ArticleContent();
//		articleContent.setContent("content");
//		articleContent.setArticle(article);
//		session.save(articleContent);
		//测试article级联删除
//		Article article = (Article) session.get(Article.class,1);
//		session.delete(article);
		//测试articleContent级联删除
		ArticleContent articleContent = (ArticleContent) session.get(ArticleContent.class, 1);
		session.delete(articleContent);
	}
	
	@After//每一个被注解Test方法在调用后都会调用此方法一次
	public void teardown(){
		transaction.commit();//提交事务，主要为了防止在测试中已提交事务，这里又重复提交
		session.clear();
		session.close();
		sessionFactory.close();
	}
	@After//在类销毁时调用一次
	public void after(){
	}

}
