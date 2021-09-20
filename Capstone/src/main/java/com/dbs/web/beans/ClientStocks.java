package com.dbs.web.beans;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;



@Entity
public class ClientStocks {

	@EmbeddedId
	private ClientStocksUtility cskid;
	private int quantity;

	public ClientStocks() {
		// TODO Auto-generated constructor stub
	}
	
	public ClientStocksUtility getCskid() {
		return cskid;
	}

	public void setCskid(ClientStocksUtility cskid) {
		this.cskid = cskid;
	}
	
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

}
