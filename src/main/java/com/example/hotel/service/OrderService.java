package com.example.hotel.service;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hotel.domain.Orders;
import com.example.hotel.domain.User;
import com.example.hotel.repository.OrderRepository;


@Service
public class OrderService {
	
	@Autowired
    private OrderRepository repo;
	
	public List<Orders> listAll() {
        return repo.findAll();
    }
	
	public List<Orders> listAllByTodayDate() {
        return repo. findAllByToday();
    }
     
	
	public List<Orders> listAllByTodayDateByUser(User user) {
		
		List<Orders> list = repo. findAllByToday();
		List<Orders> result = new LinkedList<Orders>();
		int useid = user.getUserid();
		for(int i = 0; i < list.size(); i++) {
			Orders u = list.get(i);
			if( u.getUserid() == useid ) {
				result.add(u);
			}
		}
        return result;
    }
	
	public List<Orders> adminListAllByTodayDateByUser() {
		
		List<Orders> list = repo. findAllByToday();
		List<Orders> result = new LinkedList<Orders>();
		
		for(int i = 0; i < list.size(); i++) {
			Orders u = list.get(i);
			
				result.add(u);
			
		}
        return result;
    }
	
	
	
	public List<Orders> listAllBy30DaysByUser(User user) {
		List<Orders> list = repo.findAll();
		List<Orders> result = new LinkedList<Orders>();
		int useid = user.getUserid();
		Date thirtyDaysAgo = new Date(System.currentTimeMillis() - (30L * 24L * 60L * 60L * 1000L));
		for(int i = 0; i < list.size(); i++) {
			Orders u = list.get(i);
			
			if( u.getUserid() == useid && thirtyDaysAgo.compareTo(u.getTime()) == -1 ) {
				result.add(u);
			}
		}
        return result;
    }
	
	public List<Orders> adminListAllBy30DaysByUser() {
		List<Orders> list = repo.findAll();
		List<Orders> result = new LinkedList<Orders>();
		
		Date thirtyDaysAgo = new Date(System.currentTimeMillis() - (30L * 24L * 60L * 60L * 1000L));
		for(int i = 0; i < list.size(); i++) {
			Orders u = list.get(i);
			if(thirtyDaysAgo.compareTo(u.getTime()) == -1 ) {
				result.add(u);
			}
		}
        return result;
    }
     
     
    public void save(Orders order) {
        repo.save(order);
    }
     
    public Orders get(long orderid) {
        return repo.findById(orderid).get();
    }
     
    public void delete(long orderid) {
        repo.deleteById(orderid);
    }

}
