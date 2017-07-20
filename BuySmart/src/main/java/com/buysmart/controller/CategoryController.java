package com.buysmart.controller;



import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.buysmart.dao.CategoryDao;
import com.buysmart.model.Category;


@Controller
public class CategoryController {
	
	@Autowired
	CategoryDao cdao;
	public CategoryController()
	{}
	
	@RequestMapping(value ="/categorysignup",method=RequestMethod.GET)
	public ModelAndView showcategory() 
	{	
		
		ModelAndView mvc=new ModelAndView("catreg","category",new Category());
		String categeries=cdao.Retrive();
		mvc.addObject("mylist",categeries);
		return mvc;
	}

	@RequestMapping(value="/InsertCategory", method=RequestMethod.POST)
	public ModelAndView addcategory(Category category) 
	{
		
		ModelAndView m1=new ModelAndView("catreg","category",new Category());
		
		
		cdao.insert(category);
		boolean data=false;
		String categeries=cdao.Retrive();
		m1.addObject("checkk",data);
		m1.addObject("mylist",categeries);
		return m1;
	}
	
	
	 
	  
	 
}
