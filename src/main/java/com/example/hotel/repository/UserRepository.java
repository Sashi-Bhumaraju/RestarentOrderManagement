package com.example.hotel.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.hotel.domain.User;
 
 
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
 
}