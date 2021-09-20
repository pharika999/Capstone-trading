package com.dbs.web.beans;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class TradeHistory {

	public TradeHistory(BuyOrder bid, SellOrder sid, LocalDate tradedate, double tradeamount) {
		super();
		this.bid = bid;
		this.sid = sid;
		this.tradedate = tradedate;
		this.tradeamount = tradeamount;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int tradeid;
	@OneToOne
	@JoinColumn(name="buyerorder_id")
	@JsonManagedReference
	private BuyOrder bid;
	@OneToOne
	@JoinColumn(name="sellerorder_id")
	@JsonManagedReference
	private SellOrder sid;
	@Override
	public String toString() {
		return "TradeHistory [tradeid=" + tradeid + ", bid=" + bid + ", sid=" + sid + ", tradedate=" + tradedate
				+ ", tradeamount=" + tradeamount + "]";
	}
	private LocalDate tradedate;
	private double tradeamount;
	private int quantity;
	private double price;
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public TradeHistory(BuyOrder bid, SellOrder sid, LocalDate tradedate, double tradeamount, int quantity,double price) {
		super();
		this.bid = bid;
		this.sid = sid;
		this.tradedate = tradedate;
		this.tradeamount = tradeamount;
		this.quantity = quantity;
		this.price=price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public TradeHistory() {
		// TODO Auto-generated constructor stub
	}
	public int getTradeid() {
		return tradeid;
	}
	public void setTradeid(int tradeid) {
		this.tradeid = tradeid;
	}
	public BuyOrder getBid() {
		return bid;
	}
	public void setBid(BuyOrder bid) {
		this.bid = bid;
	}
	public SellOrder getSid() {
		return sid;
	}
	public void setSid(SellOrder sid) {
		this.sid = sid;
	}
	public LocalDate getTradedate() {
		return tradedate;
	}
	public void setTradedate(LocalDate tradedate) {
		this.tradedate = tradedate;
	}
	public double getTradeamount() {
		return tradeamount;
	}
	public void setTradeamount(double tradeamount) {
		this.tradeamount = tradeamount;
	}
	

}
