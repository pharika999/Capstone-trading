package com.dbs.web.beans;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Instrument {

	@Id
	private String instrumentid;
	private String instrumentname;
	private	double facevalue;
	private LocalDate expirydate;
	private int minquantity;
	public String getInstrumentid() {
		return instrumentid;
	}
	public void setInstrumentid(String instrumentid) {
		this.instrumentid = instrumentid;
	}
	public String getInstrumentname() {
		return instrumentname;
	}
	public void setInstrumentname(String instrumentname) {
		this.instrumentname = instrumentname;
	}
	public double getFacevalue() {
		return facevalue;
	}
	public void setFacevalue(double facevalue) {
		this.facevalue = facevalue;
	}
	public LocalDate getDate() {
		return this.expirydate;
	}
	public void setDate(LocalDate date) {
		this.expirydate = date;
	}
	public int getMinquantity() {
		return minquantity;
	}
	public void setMinquantity(int minquantity) {
		this.minquantity = minquantity;
	}
	public Instrument() {
		// TODO Auto-generated constructor stub
	}

}
