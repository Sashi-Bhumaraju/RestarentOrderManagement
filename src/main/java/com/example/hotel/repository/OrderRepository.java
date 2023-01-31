package com.example.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.hotel.domain.Orders;

 

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
 
	 @Query("SELECT e FROM Orders e WHERE DATE(e.time) = CURRENT_DATE")
	    List<Orders> findAllByToday();
	  
}