package com.example.hotel.controller;

//import org.springframework.stereotype.Controller;
//
//@Controller
//public class UserController {
//
//}


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.example.hotel.service.UserService;
import com.example.hotel.domain.User;
import java.util.List;

import javax.servlet.http.HttpSession;
 
@Controller
public class UserController {
@Autowired
    private UserService service;
@Autowired
HttpSession session;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        List<User> listusers = service.listAll();
        model.addAttribute("listusers", listusers);
        
//        System.out.print("Get / ");
        for(int i = 0; i < listusers.size(); i++) {
        	 System.out.print(listusers.get(i));
        }
        User user1 = (User) session.getAttribute("user");
        if(user1 != null && user1.isIsadmin()) return "redirect:/adminHome"; 
        else  if(user1 != null) return "redirect:/userHome"; 
        return "index";
    }
 
    
    @GetMapping("/userLogin")
    public String add(Model model) {
    	 User user1 = (User) session.getAttribute("user");
         if(user1 != null && user1.isIsadmin()) return "redirect:/adminHome"; 
         else  if(user1 != null) return "redirect:/userHome"; 
         
        model.addAttribute("user", new User());
        model.addAttribute("error", "");
        String error ="";
        model.addAttribute("error", "");
       
        return "userLogin";
    }
    
    @GetMapping("/logout")
    public String logout() {
    	session.removeAttribute("user");
    	return "redirect:/"; 
    }
    
    
    @GetMapping("/userRegister")
    public String newRegister(Model model) {
    	 User user1 = (User) session.getAttribute("user");
         if(user1 != null && user1.isIsadmin()) return "redirect:/adminHome"; 
         else  if(user1 != null) return "redirect:/userHome"; 
        model.addAttribute("user", new User());
        model.addAttribute("error", "");
       
        return "userRegister";
    }
    
    @RequestMapping(value = "/newUserSave", method = RequestMethod.POST)
    public String registerUser(Model model, User user) {
    	
    	 User user1 = (User) session.getAttribute("user");
         if(user1 != null && user1.isIsadmin()) return "redirect:/adminHome"; 
         else  if(user1 != null) return "redirect:/userHome"; 
    	  if(user.getUsername().equals("") || user.getPassword().equals("")) {
    			model.addAttribute("error", "r");
        		return "userRegister"; 
    	  }
    	
    	else {
    		 service.save(user);
    		 session.setAttribute("user", service.loginUser(user));
    		model.addAttribute("error", "");
    		return "redirect:/userHome";
    		
    	}
     
//        return "redirect:/";
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUser( User user) {
        service.save(user);
        return "redirect:/";
    }
    
    @RequestMapping(value = "/validateUser", method = RequestMethod.POST)
    public String loginUser(Model model, User user) {
    	  
    	 User user1 = (User) session.getAttribute("user");
         if(user1 != null && user1.isIsadmin()) return "redirect:/adminHome"; 
         else  if(user1 != null) return "redirect:/userHome"; 
         
    	if( service.loginUser(user) != null ) {
    		session.setAttribute("user", service.loginUser(user));
    		 user1 = (User) session.getAttribute("user");
    		System.out.print("****************************************");
    		System.out.print(user1.getUsername());
    		System.out.print(user1.getUserid());
    		model.addAttribute("error", ""); return "redirect:/userHome";   }
    	else {
    		model.addAttribute("error", "l");
    		return "userLogin";
    	}
     
//        return "redirect:/";
    }
    
    
    @GetMapping("/adminLogin")
    public String addAdmin(Model model) {
    	 User user1 = (User) session.getAttribute("user");
         if(user1 != null && user1.isIsadmin()) return "redirect:/adminHome"; 
         else  if(user1 != null) return "redirect:/userHome"; 
        model.addAttribute("user", new User());
        model.addAttribute("error", "");
        String error ="";
        model.addAttribute("error", "");
       
        return "adminLogin";
    }
 
    @GetMapping("/adminRegister")
    public String newAdminRegister(Model model) {
    	 User user1 = (User) session.getAttribute("user");
         if(user1 != null && user1.isIsadmin()) return "redirect:/adminHome"; 
         else  if(user1 != null) return "redirect:/userHome"; 
        model.addAttribute("user", new User());
        model.addAttribute("error", "");
        return "adminRegister";
    }
    @RequestMapping(value = "/validateAdmin", method = RequestMethod.POST)
    public String loginAdmin(Model model, User user) {
    	 User user1 = (User) session.getAttribute("user");
         if(user1 != null && user1.isIsadmin()) return "redirect:/adminHome"; 
         else  if(user1 != null) return "redirect:/userHome"; 
    	  
    	if( service.loginAdmin(user) != null ) {
    		session.setAttribute("user", service.loginUser(user));
    		model.addAttribute("error", "");    return "redirect:/adminHome"; }
    	else {
    		
    		model.addAttribute("error", "al");
    		return "adminLogin"; 
    	}
     
//        return "redirect:/";
    }
 
    @RequestMapping(value = "/newAdminSave", method = RequestMethod.POST)
    public String registerAdmin(Model model, User user) {
    	
    	
    	 User user1 = (User) session.getAttribute("user");
         if(user1 != null && user1.isIsadmin()) return "redirect:/adminHome"; 
         else  if(user1 != null) return "redirect:/userHome"; 
         
    	  if(user.getUsername().equals("") || user.getPassword().equals("")) {
    			model.addAttribute("error", "ar");
        		return "adminRegister"; 
    	  }
    	
    	else {
    	
    		user.setIsadmin(true);
    		service.saveAdmin(user);

    		session.setAttribute("user", service.loginUser(user));
    		model.addAttribute("error", "");
    		   return "redirect:/adminHome";
    	}
     
//        return "redirect:/";
    }

 
    @RequestMapping("/edit/{id}")
    public ModelAndView showEditUsertPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("new");
        User std = service.get(id);
        mav.addObject("student", std);
        return mav;
        
    }
    @RequestMapping("/delete/{userid}")
    public String deletestudent(@PathVariable(name = "userid") int userid) {
        service.delete(userid);
        return "redirect:/";
    }
}