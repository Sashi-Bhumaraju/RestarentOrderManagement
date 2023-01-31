package com.example.hotel.controller;







import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.hotel.domain.Item;
import com.example.hotel.domain.Orders;
import com.example.hotel.domain.User;
import com.example.hotel.service.ItemService;
import com.example.hotel.service.OrderService;


@Controller
public class AdminHomeController {
	
	 @Autowired
	    private ItemService service;
	 @Autowired
	    private OrderService orderservice;
	 @Autowired
	 HttpSession session;

	 
	    @GetMapping("/adminHome")
	    public String adminHome(Model model) {
	    	User user = (User) session.getAttribute("user");
	    	 model.addAttribute("user", user);
	    	  List<Item> listitem = service.listAll();
	          model.addAttribute("listitem", listitem);
	          model.addAttribute("id", 0);
	          System.out.print("Get / ");
	          
	     
	        return "adminHome";
	    }
	   
	    @GetMapping("/adminHome/new/{id}")
	    public String newItem(Model model,@PathVariable(name = "id") int id) {
	    	model.addAttribute("id", 0);
	        model.addAttribute("item", new Item());
	        return "new";
	    }
	 
	    @RequestMapping(value = "/adminHome/saveItem/{id}", method = RequestMethod.POST)
	    public String saveItem( Item item,@PathVariable(name = "id") Long id) {
	    	if( id != 0) item.setItemid(id); 
	        service.save(item);
	        return "redirect:/adminHome";
	    }
	 
	    @RequestMapping("/adminHome/editItem/{id}")
	    public ModelAndView showEditStudentPage(@PathVariable(name = "id") int id) {
	        ModelAndView mav = new ModelAndView("new");
	        Item item = service.get(id);
	        mav.addObject("item", item);
	        mav.addObject("id", id);
	        return mav;
	        
	    }
	    @RequestMapping("/adminHome/deleteItem/{id}")
	    public String deletestudent(@PathVariable(name = "id") int id) {
	        service.delete(id);
	        return "redirect:/adminHome";
	    }
	    
	 
	    
//	    @RequestMapping("/userHome/userNewOrder/{itemname}/{itemcost}")
//	    public String userNewOrder(@PathVariable(name = "itemname") String itemname,@PathVariable(name = "itemcost") float itemcost, Model model) {
//	    	 Orders newOrder = new Orders(); 
//	    	 newOrder.setCost(itemcost);
//	    	 newOrder.setItemname(itemname);
//	    	 
//	    	 model.addAttribute("order", newOrder);
//	    	 model.addAttribute("icost", itemcost);
//	        return "userNewOrder";
//	    }
//	    
//	    @RequestMapping(value = "/saveNewOrder/{itemname}/{icost}", method = RequestMethod.POST)
//	    public String saveUser( @PathVariable(name = "itemname") String itemname,@PathVariable(name = "icost") float icost, Orders order) {
//	    	order.setItemname(itemname);
//	    	User user = (User) session.getAttribute("user");
//	    	float cost = icost * order.getPlates();
//	    	order.setCost(cost);
//	    	 order.setUserid(user.getUserid());
//	    	 order.setTime(new Date());
//	    	orderservice.save(order);
//	    	
//	    	List<Orders> l =	orderservice.listAllByTodayDate();
//	    	for(int i = 0; i< l.size(); i++) {;
//	    		Orders o = l.get(i);
//	    		System.out.println(o.getItemname());
//	    	}
//	    	
//	    	return "redirect:/userHome"; 
//	    }
//	    
//	    
	    @GetMapping("/adminHome/todaybills")
	    public String cart(Model model) {
	    	
	    	User user = (User) session.getAttribute("user");
	    	 model.addAttribute("user", user);
	    	 
	      	List<Orders> listitem =	orderservice.adminListAllByTodayDateByUser();
	      	float sum = 0;
	    	for(int i = 0; i< listitem.size(); i++) {;
	    		Orders o = listitem.get(i);
	    		System.out.println(o.getItemname());
	    		sum = sum + o.getCost();
	    	}	
	    	model.addAttribute("listitem", listitem);
	    	model.addAttribute("totalcost", sum);
	    	  
	    	
	          System.out.print("Get / ");
	          
	     
	        return "adminCart";
	    }
	    
	    @GetMapping("/adminHome/history")
	    public String history(Model model) {
	    	User user = (User) session.getAttribute("user");
	    	 model.addAttribute("user", user);
	    	 
	      	List<Orders> listitem =	orderservice.adminListAllBy30DaysByUser();
	      	float sum = 0;
	    	for(int i = 0; i< listitem.size(); i++) {;
	    		Orders o = listitem.get(i);
	    		System.out.println(o.getItemname());
	    		sum = sum + o.getCost();
	    	}	
	    	model.addAttribute("listitem", listitem);
	    	model.addAttribute("totalcost", sum);
	    	  
	    	
	          System.out.print("Get / ");
	          
	        return "adminHistory";
	    }
	    
//	    @GetMapping("/")
//	    public String viewHomePage(Model model) {
//	        List<Item> listitem = service.listAll();
//	        model.addAttribute("listitem", listitem);
//	        System.out.print("Get / ");	
//	        return "index";
//	    }
//
//	    @GetMapping("/new")
//	    public String add(Model model) {
//	        model.addAttribute("item", new Item());
//	        return "new";
//	    }
//
//	    @RequestMapping(value = "/save", method = RequestMethod.POST)
//	    public String saveStudent(@ModelAttribute("item") Item std) {
//	        service.save(std);
//	        return "redirect:/";
//	    }
//
//	    @RequestMapping("/edit/{id}")
//	    public ModelAndView showEditStudentPage(@PathVariable(name = "id") int id) {
//	        ModelAndView mav = new ModelAndView("new");
//	        com.example.hotel.domain.Item item = service.get(id);
//	        mav.addObject("item", item);
//	        return mav;
//	        
//	    }
//	    @RequestMapping("/delete/{id}")
//	    public String deletestudent(@PathVariable(name = "id") int id) {
//	        service.delete(id);
//	        return "redirect:/";
//	    }
}
