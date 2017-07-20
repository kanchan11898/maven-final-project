package com.buysmart.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.buysmart.dao.SupplierDao;
import com.buysmart.model.Supplier;


@Controller
public class SupplierController {
	
	@Autowired
	SupplierDao sdao;
	public SupplierController()
	{}
	
	@RequestMapping(value ="/suppliersignup",method=RequestMethod.GET)
	public ModelAndView showUserdetails() 
	{	
		
		ModelAndView mvc=new ModelAndView("supreg","sup",new Supplier());
		return mvc;
	}

	@RequestMapping(value="/InsertSupplier", method=RequestMethod.POST)
	public ModelAndView addUser(Supplier sup) 
	{
		
		ModelAndView m1=new ModelAndView("supreg","sup",new Supplier());
		
		sdao.insert(sup);
		
		return m1;
	}
	
	 
	  
	 
}
