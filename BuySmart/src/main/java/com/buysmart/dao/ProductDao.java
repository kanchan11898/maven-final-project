package com.buysmart.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.buysmart.model.Product;
import com.google.gson.Gson;


@Repository
public class ProductDao {  //we keep all the methods in dao
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void insert(Product pro) {
		Session session =sessionFactory.openSession();
		
		System.out.println("Insert Method Called for ProductDetails");
		Transaction tx=session.getTransaction();
		tx.begin();
		session.save(pro);
		session.flush();
		tx.commit();
		session.close();
   }
	
	
	public String Retrive()
	{
	Session connection=sessionFactory.openSession();
	Transaction tx=connection.getTransaction();
	tx.begin();

	 List listdata = connection.createQuery("FROM Product").list();
	 Gson gson = new Gson();
	 String jsonNames = gson.toJson(listdata);
	 tx.commit();
	 connection.close();
     return jsonNames;

	}
	
	public String Retrive1(int cid)
	{
	Session connection=sessionFactory.openSession();
	Transaction tx=connection.getTransaction();
	tx.begin();

	Query qry = connection.createQuery("FROM Product p where p.productid= :java");
	qry.setParameter("java", cid);
	 List listdata = qry.list();
	 Gson gson = new Gson();
	 String jsonNames = gson.toJson(listdata);
	 tx.commit();
	 connection.close();
     return jsonNames;

	}

	public Product UpdateProduct(Integer cid) {
		Session session =sessionFactory.openSession();
		Transaction tx=session.getTransaction();
		tx.begin();
		Product ob=(Product)session.get(Product.class, cid);
		tx.commit();
		session.close();
		return ob;
	}
	
	public void Delete(Integer cid) {
		Session session =sessionFactory.openSession();
		Transaction tx=session.getTransaction();
		tx.begin();
		Product ob=(Product)session.get(Product.class, cid);
		System.out.println("This is ID"+ " "+ob.getProductname());
		session.delete(ob);
		session.flush();
		tx.commit();
		session.close();
		System.out.println("Delete Method Called");
}
	
	public void NowUpdateProduct(int cid,String cname,int cprice,int cstock,String cdesc,int catid1,int supid1) {
		Session session =sessionFactory.openSession();
		Transaction tx=session.getTransaction();
		tx.begin();
		Product ob=(Product)session.get(Product.class, cid);
		ob.setProductname(cname);
		ob.setPrice(cprice);
        ob.setStock(cstock);
       ob.setDescription(cdesc);
       ob.setCategoryid(catid1);
       ob.setSupplierid(supid1);
       
		session.update(ob);
		tx.commit();
		session.close();
		System.out.println("Update Method Called");
	
	}
	
	//For Display Click big images
	public Product DisplayImage(int ccid)
	{
	Session session=sessionFactory.openSession();
	session.beginTransaction();
	Product ok=(Product)session.get(Product.class,ccid);
	session.close();
	return ok;
		
		
	}

}	