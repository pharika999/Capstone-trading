package com.dbs.web.beans;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class BuyOrder {

	@Id
	private String bid;
	@OneToOne
	@JoinColumn(name="instrumentid")
	//@JsonBackReference
	private Instrument instrumentid;
	@OneToOne
	@JoinColumn(name="clientid")
	//@JsonBackReference
	private Client clientid;
	private String status;
	private int quantity;
	private int remainingquantity;
	private int price;
	private LocalDate orderdate;
	
	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public Instrument getInstrumentid() {
		return instrumentid;
	}

	public void setInstrumentid(Instrument instrumentid) {
		this.instrumentid = instrumentid;
	}

	public Client getClientid() {
		return clientid;
	}

	public void setClientid(Client clientid) {
		this.clientid = clientid;
	}

	public String isStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quanity) {
		this.quantity = quanity;
	}

	public int getRemainingquantity() {
		return remainingquantity;
	}

	public void setRemainingquantity(int remainingquantity) {
		this.remainingquantity = remainingquantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public LocalDate getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(LocalDate orderdate) {
		this.orderdate = orderdate;
	}

	public BuyOrder() {
		// TODO Auto-generated constructor stub
	}

}
