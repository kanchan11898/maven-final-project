package com.buysmart.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.buysmart.model.Supplier;
import com.google.gson.Gson;


@Repository
public class SupplierDao {  //we keep all the methods in dao
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void insert(Supplier sup) {
		Session session =sessionFactory.openSession();
		
		System.out.println("Insert Method Called for SupplierDetails");
		Transaction tx=session.getTransaction();
		tx.begin();
		session.save(sup);
		session.flush();
		tx.commit();
		session.close();
   }
	
	public String Retrive()
	{
		Session connection=sessionFactory.openSession();
		Transaction tx=connection.getTransaction();
		tx.begin();
	
		 List listdata = connection.createQuery("FROM Supplier").list();
		 Gson gson = new Gson();
		 String jsonNames = gson.toJson(listdata);
		 tx.commit();
		 connection.close();
	     return jsonNames;

	}
	

}	