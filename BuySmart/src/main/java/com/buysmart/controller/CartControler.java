package com.buysmart.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.buysmart.dao.AddCartDao;
import com.buysmart.dao.OrderDetailsDAO;
import com.buysmart.dao.ProductDao;
import com.buysmart.dao.UserDao;
import com.google.gson.Gson;
import com.buysmart.model.AddCart;
import com.buysmart.model.Category;
import com.buysmart.model.OrderDetails;
import com.buysmart.model.Product;
import com.buysmart.model.User;
@Controller
public class CartControler {
	
	@Autowired
	ProductDao cdao;
	@Autowired
	AddCartDao acdao;
	@Autowired
	UserDao rdao;
	@Autowired
	OrderDetailsDAO oDAO;
	public CartControler()
	{}
	
	
	public int getGrandTotal(List<AddCart> li)
	{
		ListIterator<AddCart> itr=li.listIterator();
		int grandtotal=0;
		while(itr.hasNext())
		{
			AddCart cd1=(AddCart)itr.next();

			grandtotal=grandtotal+(cd1.getQty()*cd1.getPrice());
			
		}
		return grandtotal;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/Carddetails", method = RequestMethod.GET)
	public String CardDetails(@RequestParam("pid")int cid,HttpSession session,Model m) 
	{
		
		System.out.println("This is my id"+ " "+cid);
		Product ob=cdao.DisplayImage(cid);
		System.out.println("Thisssssssssss is my id"+ " "+cid);
		
		AddCart cd=new AddCart();
		cd.setPrice(ob.getPrice());
		cd.setProductid(ob.getProductid());
		cd.setStock(ob.getStock());
		cd.setProductname(ob.getProductname());
		
		
		System.out.println("okkkkkkkkkk is my id"+ " "+cid);
		System.out.println("My Session"+session.getId());
		System.out.println("hello"+cd);
		
		
		List<AddCart> li=new <AddCart>ArrayList();
		
		
		li.add(cd);
		session.setAttribute("mycart", li);
		int size=li.size();
		

		session.setAttribute("count",size);
		session.setAttribute("grandtotal",String.valueOf(getGrandTotal(li)));
		//((ModelAndView) m).addObject("cart", gs);
		   m.addAttribute("cartitm",li);
		System.out.println(session.getAttribute("mycart"));
		
		return "AddToCart";
	}
	@RequestMapping(value="/Removecart", method=RequestMethod.GET)
	public ModelAndView DeleteCart(@RequestParam("pid")int cid,HttpSession session)
	{
		System.out.println("This is my id"+ " "+cid);
		List<AddCart> ld=(ArrayList<AddCart>)session.getAttribute("mycart");
		ListIterator<AddCart> lit=(ListIterator<AddCart>) ld.listIterator();
		while(lit.hasNext())
		{
			AddCart d=lit.next();
		if(d.getProductid()==cid)
		{
			
			System.out.println(ld.indexOf(d));
			ld.remove(ld.indexOf(d));
			break;
		}
		}
		int size=ld.size();
		session.setAttribute("mycart",ld);
		session.setAttribute("count",size);
		ModelAndView mv = new ModelAndView("AddToCart", "addcart", new AddCart());
		
//		Gson gsonli = new Gson();
//		String gs = gsonli.toJson(ld);
//		mv.addObject("cart",gs);
		mv.addObject("cartitm",ld);
		session.setAttribute("grandtotal",String.valueOf(getGrandTotal(ld)));
		return mv;

	
		
	}
	
	@RequestMapping(value = "/HeyOrderConfirmation", method = RequestMethod.GET)
	public ModelAndView orderConfirm(HttpSession session)
	{
		@SuppressWarnings("unchecked")
		List<AddCart>	li	=  (ArrayList<AddCart>)session.getAttribute("mycart");
	

		Gson gsonli = new Gson();
		String gs = gsonli.toJson(li);
		session.setAttribute("cart", gs);
			ModelAndView  mv = new ModelAndView("OrderConfermation","addcart",new AddCart());
			mv.addObject("cart",gs);
			mv.addObject("useraddr",rdao.address((String)session.getAttribute("rid")));
			mv.addObject("email",rdao.emailid((String)session.getAttribute("rid")));
			mv.addObject("mobile",rdao.mobile((String)session.getAttribute("rid")));
			session.setAttribute("grandtotal",String.valueOf(getGrandTotal(li)));
			return mv;
	}
	@RequestMapping(value = "/AddToCartgoback", method = RequestMethod.GET)
	public ModelAndView  cartgoback(HttpSession session,Model m)
	{
		
		
		@SuppressWarnings("unchecked")
		List<AddCart>	li	=  (ArrayList<AddCart>)session.getAttribute("mycart");
	
//		Gson gsonli = new Gson();
//		String gs = gsonli.toJson(li);
//		session.setAttribute("cart", gs);
		ModelAndView mv = new ModelAndView("AddToCart", "addcart", new AddCart());
//		mv.addObject("cart", gs);
//		
//		m.addAttribute("cart",gs);
		mv.addObject("cartitm",li);
		System.out.println(session.getAttribute("mycart"));
		session.setAttribute("grandtotal",String.valueOf(getGrandTotal(li)));
		System.out.println("grandtotal");
		return mv;
			
	}
	@RequestMapping(value = "/PaymentConfirmed", method = RequestMethod.GET)
	public ModelAndView  paymentconfirm(HttpSession session) {
		List<AddCart>	li	=  (ArrayList<AddCart>)session.getAttribute("mycart");
		
		for(AddCart cd:li)
		{
			acdao.showCardDetails(cd);
		}
		ModelAndView mv = new ModelAndView("PaymentConfermation", "addcart", new AddCart());

		return mv;

	}
	@RequestMapping(value = "/thanku", method = RequestMethod.POST)
	public ModelAndView  Thanku(HttpSession session) {
//		List<AddCart>	li	=  (ArrayList<AddCart>)session.getAttribute("mycart");
//		UserDetails userdetails=rdao.display((String)session.getAttribute("Userid"));
//		
//		 
//	       String email=userdetails.getEmailid();
//	       String phon= userdetails.getMobile();
//	       String addr=userdetails.getAddress();
//	       String user= userdetails.getUsername();
//		
//	       
//	       String fullAddress= user +"\t"+addr +"\t"+phon;
//	       OrderDetails d= new OrderDetails();
//			d.setPaymentMode("Cash on Delivery");
//	       d.setUserId(session.getAttribute("Userid").toString());
//	       d.setOrderDate(new Date());
//	       d.setOrderStatus("processing");
//	       
//	       String f = session.getAttribute("grandtotal").toString();
//	       
//	       d.setGrandTotal(Integer.valueOf(f));
//	       d.setAddress(fullAddress);
//	       
//	       oDAO.addOrderDetails(d);
//	       System.out.print("Order saved");
//	       int ordId=d.getOrderId();
//	       for(AddCart cd:li)
//	       {
//	    	   cd.setOrderID(ordId);
//	    	   acdao.showCardDetails(cd);
//	    	  System.out.print("Cart detail saved");
//	    	   
//	       }
//	       String data=oDAO.getOrderDetail(d.getOrderId());
//	       ModelAndView mv= new ModelAndView("AfterConfirmed");
	       
			ModelAndView v= new ModelAndView("Thankyou","OrderDetails",new OrderDetails());
			//v.addObject("data",data);
			//System.out.println(data);
			return v ;
			
	    }
	@RequestMapping(value = "/updateqty", method = RequestMethod.GET)
	public String updateqty(@RequestParam("qty")int qty,@RequestParam("pid")int cid,HttpSession session,Model m) 
	{

		System.out.println("Quantity:"+qty);
		System.out.println("Product ID:"+cid);
		ArrayList<AddCart> li = (ArrayList<AddCart>) session.getAttribute("mycart");
		ListIterator<AddCart> lit = (ListIterator<AddCart>) li.listIterator();
		while (lit.hasNext()) 
		{
			AddCart d = lit.next();
			if (d.getProductid() == cid) 
			{
				d.setQty(qty);
				d.setTotal(d.getQty()*d.getPrice());
				lit.set(d);
				break;
			}
			
		}
//		Gson gsonli = new Gson();
//		String gs = gsonli.toJson(li);
//		session.setAttribute("cart", gs);
//		m.addAttribute("cart",gs);
		m.addAttribute("cartitm",li);
		session.setAttribute("grandtotal",String.valueOf(getGrandTotal(li)));
		
		return "AddToCart";
		

}
	@RequestMapping(value = "/GoToCart", method = RequestMethod.GET)
	public ModelAndView gotocart(HttpSession session) {

		ArrayList<AddCart> li = (ArrayList<AddCart>) session.getAttribute("mycart");

//		Gson gsonli = new Gson();
//		String gs = gsonli.toJson(li);
//		session.setAttribute("cart", gs);
		ModelAndView mv = new ModelAndView("AddToCart", "addcart", new AddCart());
		mv.addObject("cartitm", li);

		System.out.println(session.getAttribute("cart"));
		session.setAttribute("grandtotal",String.valueOf(getGrandTotal(li)));
		return mv;

	}	
	@RequestMapping(value = "/OrderConfirmed", method = RequestMethod.GET)
	public ModelAndView  orderconfirmed(HttpSession session) {
		List<AddCart>	li	=  (ArrayList<AddCart>)session.getAttribute("mycart");
		User userdetails=rdao.display((String)session.getAttribute("Userid"));
		
		 
	       String email=userdetails.getEmailid();
	       String phon= userdetails.getMobile();
	       String addr=userdetails.getAddress();
	       String user= userdetails.getUsername();
		
	       
	       String fullAddress= user +"\t"+addr +"\t"+phon;
	       OrderDetails d= new OrderDetails();
			d.setPaymentMode("Cash on Delivery");
	       d.setUserId(session.getAttribute("Userid").toString());
	       d.setOrderDate(new Date());
	       d.setOrderStatus("processing");
	       
	       String f = session.getAttribute("grandtotal").toString();
	       
	       d.setGrandTotal(Integer.valueOf(f));
	       d.setAddress(fullAddress);
	       
	       oDAO.addOrderDetails(d);
	       System.out.print("Order saved");
	       int ordId=d.getOrderId();
	       session.setAttribute("oid", ordId);
	       for(AddCart cd:li)
	       {
	    	   cd.setOrderID(ordId);
	    	   acdao.showCardDetails(cd);
	    	  System.out.print("Cart detail saved");
	    	   
	       }
	       String data=oDAO.getOrderDetail(d.getOrderId());
	       ModelAndView mv= new ModelAndView("AfterConfirmed");
	       
			//ModelAndView v= new ModelAndView("Thankyou","OrderDetails",new OrderDetails());
			mv.addObject("data",data);
			System.out.println(data);
			return mv ;
	}
	@RequestMapping(value = "/Last", method = RequestMethod.GET)
	public ModelAndView  lastpagedone(HttpSession session) {
		List<AddCart>	li	=  (ArrayList<AddCart>)session.getAttribute("mycart");
		 int oid=(Integer)session.getAttribute("oid");
		 
		 OrderDetails d= new OrderDetails();
	      String data=oDAO.getOrderDetail(oid);
				ModelAndView v= new ModelAndView("Last","OrderDetail",new OrderDetails());
			v.addObject("data",data);

			return v ;
		
		
	}


}
