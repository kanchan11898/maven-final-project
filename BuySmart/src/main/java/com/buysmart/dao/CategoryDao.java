package com.buysmart.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.buysmart.model.Category;
import com.google.gson.Gson;


@Repository
public class CategoryDao {  //we keep all the methods in dao
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void insert(Category cat) {
		Session session =sessionFactory.openSession();
		
		System.out.println("Insert Method Called for SupplierDetails");
		Transaction tx=session.getTransaction();
		tx.begin();
		session.save(cat);
		session.flush();
		tx.commit();
		session.close();
   }
	public String Retrive()
	{
		Session connection=sessionFactory.openSession();
		Transaction tx=connection.getTransaction();
		tx.begin();
	
		 List data = connection.createQuery("FROM Category").list();
		 Gson gson = new Gson();
		 String jsonNames = gson.toJson(data);
		 tx.commit();
		 connection.close();
	     return jsonNames;

	}
	
	
	public Category UpdateCategory(Integer cid) {
		Session session =sessionFactory.openSession();
		Transaction tx=session.getTransaction();
		tx.begin();
		Category ob=(Category)session.get(Category.class, cid);
		tx.commit();
		session.close();
		return ob;
	}
}	