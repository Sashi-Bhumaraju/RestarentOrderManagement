//package com.example.hotel.service;
//
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserService {
//
//}


package com.example.hotel.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.hotel.domain.User;
import com.example.hotel.repository.UserRepository;
 
@Service
public class UserService {
	
	@Autowired
    private UserRepository repo;
	public List<User> listAll() {
        return repo.findAll();
    }
    
    public void save(User user) {
        repo.save(user);
    }
    
    public User loginUser(User user) {
    	List<User> listusers = listAll();
    	  for(int i = 0; i < listusers.size(); i++) {
    		  User u = listusers.get(i) ; 
    		  System.out.print(u.getUsername() + " " +u.getPassword()+u.isIsadmin());
    		  System.out.println("/////////////////////////");
    		  if(( u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword()) ) && u.isIsadmin() == false) {
    			  return u;
    		  }
         	 System.out.print(listusers.get(i));
         }
        return null;
    }
    
    public User get(long userid) {
        return repo.findById(userid).get();
    }
    
    public void delete(long userid) {
        repo.deleteById(userid);
    }
    public User loginAdmin(User user) {
    	List<User> listusers = listAll();
    	  for(int i = 0; i < listusers.size(); i++) {
    		  User u = listusers.get(i) ; 
    		  System.out.print(u.getUsername() + " " +u.getPassword()+u.isIsadmin());
    		  System.out.println("/////////////////////////");
    		  if(( u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword()) ) && u.isIsadmin() == true) {
    			  return u;
    		  }
         	 System.out.print(listusers.get(i));
         }
        return null;
    }

	public void saveAdmin(User user) {
		// TODO Auto-generated method stub
		  user.setIsadmin(true);
		  repo.save(user);
		
	}


 
}