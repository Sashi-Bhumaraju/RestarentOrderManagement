package com.example.hotel.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Item {
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long itemid;
	private String itemname;
    private int itemcost;
	public Long getItemid() {
		return itemid;
	}
	public void setItemid(Long itemid) {
		this.itemid = itemid;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public int getItemcost() {
		return itemcost;
	}
	public void setItemcost(int itemcost) {
		this.itemcost = itemcost;
	}
	public Item(Long itemid, String itemname, int itemcost) {
		super();
		this.itemid = itemid;
		this.itemname = itemname;
		this.itemcost = itemcost;
	}
    
	public Item() {
		super();
	}
    
}
