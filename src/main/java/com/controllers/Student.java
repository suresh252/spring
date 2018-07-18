package com.controllers;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.omg.CORBA.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import com.pojo.User;

@Controller
public class Student {
	@RequestMapping(value = "/register")
	public String save(User user) {
		System.out.println("register success");

		System.out.println(user.getName());
		System.out.println(user.getMail());
		System.out.println(user.getMobile());
		System.out.println(user.getPassword());
		try {
			Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
			SessionFactory sessionFactory = configuration.buildSessionFactory();
			Session session = sessionFactory.openSession();
			System.out.println("open");
			Serializable save = session.save(user);

			session.beginTransaction().commit();

		} catch (Exception e) {
			System.out.println("error solved");
			e.printStackTrace();

		}
		return "login";
	}

	@RequestMapping(value = "/loginUser", method = RequestMethod.POST)
	public String LoginMail(User user, Model model) {

		System.out.println(user.getMail());
		System.out.println(user.getPassword());

		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();

		// User user=(User)session.get("mail.User", user.getMail());

		Query query = session.createQuery("from User where mail=:mail AND password=:password");
		query.setParameter("mail", user.getMail());
		query.setParameter("password", user.getPassword());
		List<User> list = query.list();
		System.out.println(list);
		if (list.isEmpty()) {
			model.addAttribute("sms", "Invalid details");
			return "login";
		}

		Query queryAll = session.createQuery("from User");
		List<User> userlist = queryAll.list();
		model.addAttribute("userlist", userlist);
		for (User user1 : userlist) {
			System.out.println(
					user1.getName() + "--" + user1.getMail() + "--" + user1.getMobile() + "--" + user1.getPassword());

		}
		model.addAttribute("userName", user.getMail());
		return "profile";
	}

	@RequestMapping(value = "/deleteUser")
	public String deleteUser(@RequestParam("mail") String email, Model model) {
		System.out.println("Mail is :" + email);
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();

		Query query = session.createQuery("delete from User where mail=:mail");
		query.setParameter("mail", email);
		int Result = query.executeUpdate();
		session.beginTransaction().commit();
		System.out.println("--" + Result);

		Query queryAll = session.createQuery("from User");
		List<User> userlist = queryAll.list();
		model.addAttribute("userlist", userlist);
		System.out.println("--" + Result);
		return "profile";
	}

	@RequestMapping(value = "/editUser")
	public String editUser(@RequestParam("mail") String email, Model model) {
		System.out.println("Mail is :" + email);
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();

		Query query = session.createQuery(" from User where mail=:mail");
		query.setParameter("mail", email);
		List<User> user = query.list();
		System.out.println(user.size());
		model.addAttribute("user", user.get(0));
		return "update";

	}
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String updateUser(User user, Model model) {
		Session session = new Configuration().configure().buildSessionFactory().openSession();
		session.saveOrUpdate(user);
		session.beginTransaction().commit();
		Query query = session.createQuery("from User");
		List<User> list = query.list();
	model.addAttribute("userlist", list);

		return "profile";
	}
	
	@RequestMapping(value="/DeleteAll", method=RequestMethod.POST)
	public String DeleteAll(@RequestParam("DeleteMultiple")String mailList, Model model) {
		
		System.out.println(mailList);
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();

		String[] mails=mailList.split(",");
		for (String mail : mails) {
			Query query = session.createQuery(" delete from User where mail=:mail");
			query.setParameter("mail", mail);
			int result = query.executeUpdate();
			session.beginTransaction().commit();
			System.out.println(result);
			
			
		}
		
		Query queryAll= session.createQuery("from User");
		List<User> list = queryAll.list();
		model.addAttribute("userlist", list);

		
		return"profile";
	}
}
