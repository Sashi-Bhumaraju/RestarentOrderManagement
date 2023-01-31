package com.example.hotel.domain;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Orders {
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int orderid;
	private String itemname;
	private Date time ;
    private int userid;
    private float cost;
    private int plates;
    
   

	
	




	public int getOrderid() {
		return orderid;
	}









	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}









	public String getItemname() {
		return itemname;
	}









	public void setItemname(String itemname) {
		this.itemname = itemname;
	}









	public Date getTime() {
		return time;
	}









	public void setTime(Date time) {
		this.time = time;
	}









	public int getUserid() {
		return userid;
	}









	public void setUserid(int userid) {
		this.userid = userid;
	}









	public float getCost() {
		return cost;
	}









	public void setCost(float cost) {
		this.cost = cost;
	}









	public int getPlates() {
		return plates;
	}









	public void setPlates(int plates) {
		this.plates = plates;
	}









	public Orders(int orderid, String itemname, Date time, int userid, float cost, int plates) {
		super();
		this.orderid = orderid;
		this.itemname = itemname;
		this.time = time;
		this.userid = userid;
		this.cost = cost;
		this.plates = plates;
	}









	public Orders() {

	}
	

}