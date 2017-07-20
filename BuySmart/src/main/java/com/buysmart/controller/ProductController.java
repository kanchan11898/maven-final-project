package com.buysmart.controller;



import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.buysmart.dao.CategoryDao;
import com.buysmart.dao.ProductDao;
import com.buysmart.dao.SupplierDao;
import com.buysmart.model.Product;


@Controller
public class ProductController {
	
	@Autowired
	ProductDao pdao;
	
	@Autowired
	CategoryDao cdao;
	
	@Autowired
	SupplierDao sdao;
	public ProductController()
	{}
	
	@RequestMapping(value ="/productsignup",method=RequestMethod.GET)
	public ModelAndView showUserdetails() 
	{	
		int data=1;
		ModelAndView mvc=new ModelAndView("proreg","pro",new Product());
		String products = pdao.Retrive();
		String categories = cdao.Retrive();
		String suppliers = sdao.Retrive();
		mvc.addObject("mylist1", categories);
		mvc.addObject("mylist2", suppliers);
		mvc.addObject("mylist3", products);
		mvc.addObject("checkk", data);

		return mvc;
	}

	@RequestMapping(value="/InsertProduct", method=RequestMethod.POST)
	public ModelAndView addUser(@ModelAttribute("pro")Product pro,BindingResult result) 
	{
		
		ModelAndView m1=new ModelAndView("proreg","pro",new Product());
		
		pdao.insert(pro);
		
		String path="C:\\Users\\USER\\workspace\\BuySmart\\src\\main\\webapp\\resources\\My_Design\\";
		path=path+String.valueOf(pro.getProductid())+".jpg";
		File f=new File(path);
	
		MultipartFile filedet=pro.getPimage();
		
		if(!filedet.isEmpty())
		{
			try
			{
			  byte[] bytes=filedet.getBytes();
			  System.out.println(bytes.length);
			  FileOutputStream fos=new FileOutputStream(f);
              			BufferedOutputStream bs=new BufferedOutputStream(fos);
              			bs.write(bytes);
              			bs.close();
              			fos.close();
             			 System.out.println("File Uploaded Successfully");
			}
			catch(Exception e)
			{
				System.out.println("Exception Arised"+e);
			}
		}
		else
		{
			System.out.println("File is Empty not Uploaded");
			
		}
		  int data=1;
		 String categories = cdao.Retrive();
		String suppliers = sdao.Retrive();
		String products = pdao.Retrive();
		 m1.addObject("checkk",data);
		m1.addObject("mylist3", products);
		m1.addObject("mylist1", categories);
		m1.addObject("mylist2", suppliers);
		

		
		return m1;
	}
	
	
	
	@RequestMapping(value="/plzUpdatedproduct", method=RequestMethod.GET)
	public ModelAndView plzUpdatedProduct(@RequestParam("pid")int cid)
	{
		Product poc=pdao.UpdateProduct(cid);
       int data=2;
		ModelAndView mvc=new ModelAndView("proreg","pro",poc);
		String categoryes = cdao.Retrive();
		String suppliers = sdao.Retrive();
		String productsss=pdao.Retrive();
		mvc.addObject("mylist3",productsss);
		mvc.addObject("mylist1", categoryes);
		mvc.addObject("mylist2", suppliers);
		mvc.addObject("checkk",data);
	   return mvc;
	}
	
	
	
	@RequestMapping(value="/ProductDescription", method=RequestMethod.GET)
	public ModelAndView productDescription(@RequestParam("pid")int cid)
	{
		
       int data=3;
       String pocc=pdao.Retrive1(cid);
		ModelAndView mvc=new ModelAndView("proreg","pro",new Product());
		
		mvc.addObject("poc",pocc);
		mvc.addObject("checkk",data);
	   return mvc;
	}
	
	
	
	@RequestMapping(value="/Deleteproduct", method=RequestMethod.GET)
	public ModelAndView DeleteProduct(@RequestParam("pid")int cid)
	{
	
		pdao.Delete(cid);
		ModelAndView mvc=new ModelAndView("proreg","pro",new Product());
		String products=pdao.Retrive();
		String categoryes = cdao.Retrive();
		String suppliers = sdao.Retrive();
		int data=1;
		mvc.addObject("mylist1", categoryes);
		mvc.addObject("mylist2", suppliers);
		mvc.addObject("mylist3",products);
		mvc.addObject("checkk",data);
		return mvc;
	}
	
	@RequestMapping(value="/plzNowUpdatedproducts", method=RequestMethod.POST)
	public ModelAndView plzNowUpdatedProducts(HttpServletRequest request)
	{
		  String cid=request.getParameter("productid");                                                 
		String cname=request.getParameter("productname");
		String cprice=request.getParameter("price");
		String cstock=request.getParameter("stock");    
        String cdesc=request.getParameter("description");
        String catid=request.getParameter("categoryid");
        String supid=request.getParameter("supplierid");
                              
		int data=1;
		int cid1=Integer.parseInt(cid);
		int price=Integer.parseInt(cprice);
		int stock=Integer.parseInt(cstock);
		int catid1=Integer.parseInt(catid);
		int supid1=Integer.parseInt(supid);
		pdao.NowUpdateProduct(cid1,cname,price,stock,cdesc,catid1,supid1);
		ModelAndView mvc=new ModelAndView("proreg","pro",new Product());
		String products=pdao.Retrive();
		String categoryes = cdao.Retrive();
		String suppliers = sdao.Retrive();
		mvc.addObject("mylist3",products);
		mvc.addObject("mylist1", categoryes);
		mvc.addObject("mylist2", suppliers);
		mvc.addObject("checkk",data);
	   return mvc;
	}

	 
	  
	 
}
