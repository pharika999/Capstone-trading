package com.dbs.web.beans;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Custodian {

	@Id
	private String custodianid;
	private String custodianname;
	private String password;
	public String getCustodianid() {
		return custodianid;
	}
	public void setCustodianid(String custodianid) {
		this.custodianid = custodianid;
	}
	public String getCustodianname() {
		return custodianname;
	}
	public void setCustodianname(String custodianname) {
		this.custodianname = custodianname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Custodian() {
		// TODO Auto-generated constructor stub
	}

}
