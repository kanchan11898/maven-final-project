package com.buysmart.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.buysmart.dao.ProductDao;
import com.google.gson.Gson;
import com.buysmart.model.Product;

@Controller
public class UserControler {


	@Autowired
	ProductDao cdao;
	
	public UserControler()
	{}
	

	@RequestMapping(value = "/DisplayProduct", method = RequestMethod.GET)
	public ModelAndView showProductAdmin() 
	{
		
		ModelAndView mvc = new ModelAndView("UserProductView", "ProductAdmin", new Product());
		
		

		String products = cdao.Retrive();
		
	
		mvc.addObject("mylist3", products);
		return mvc;
	}
	

	@RequestMapping(value="/ProductDescription", method=RequestMethod.GET)
	public ModelAndView productDescription(@RequestParam("pid")int cid)
	{
		
       int data=3;
       String pocc=cdao.Retrive1(cid);
		ModelAndView mvc=new ModelAndView("proreg","pro",new Product());
		
		mvc.addObject("poc",pocc);
		mvc.addObject("checkk",data);
	   return mvc;
	}

	
	@RequestMapping("/showallimages")
	public ModelAndView RetriveImages(@RequestParam("pid")int cid)
	{
		System.out.println(cid);
		Product ok=cdao.DisplayImage(cid);
		Gson gsonlist=new Gson();
		String  rec=gsonlist.toJson(ok);
		ModelAndView ob=new ModelAndView("ShowAllimages","ProductAdmin",new Product());
		ob.addObject("details",rec);
		return ob;
		
	}
}
