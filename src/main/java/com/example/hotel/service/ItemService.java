package com.example.hotel.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hotel.domain.Item;
import com.example.hotel.repository.ItemRepository;


@Service
public class ItemService {
	
	@Autowired
    private ItemRepository repo;
	
	public List<Item> listAll() {
        return repo.findAll();
    }
     
    public void save(Item item) {
        repo.save(item);
    }
     
    public Item get(long id) {
        return repo.findById(id).get();
    }
     
    public void delete(long id) {
        repo.deleteById(id);
    }

}
