package com.dbs.web.beans;

import java.util.Objects;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Client {

	@Override
	public String toString() {
		return "Client [clientid=" + clientid + ", clientname=" + clientname + ", custodian=" + custodian
				+ ", transactionlimit=" + transactionlimit + ", remainingtransactionlimit=" + remainingtransactionlimit
				+ "]";
	}
	@Id
	private String clientid;
	private String clientname;
	@OneToOne
	@JoinColumn(name="custodianid")
	private Custodian custodian;
	private double transactionlimit;
	private double remainingtransactionlimit;
	
	public Client() {
		// TODO Auto-generated constructor stub
	}
	public double getRemainingtransactionlimit() {
		return remainingtransactionlimit;
	}
	public void setRemainingtransactionlimit(double remainingtransactionlimit) {
		this.remainingtransactionlimit = remainingtransactionlimit;
	}
	public void setCustodian(Custodian custodian) {
		this.custodian = custodian;
	}
	public String getClientid() {
		return clientid;
	}
	public void setClientid(String clientid) {
		this.clientid = clientid;
	}
	public String getClientname() {
		return clientname;
	}
	public void setClientname(String clientname) {
		this.clientname = clientname;
	}
	public Custodian getCustodian() {
		return custodian;
	}
	public void setCustodianid(Custodian custodian) {
		this.custodian = custodian;
	}
	public double getTransactionlimit() {
		return transactionlimit;
	}
	public void setTransactionlimit(double transactionlimit) {
		this.transactionlimit = transactionlimit;
	}
	@Override
	public int hashCode() {
		return Objects.hash(clientid, clientname, custodian, remainingtransactionlimit, transactionlimit);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(clientid, other.clientid) && Objects.equals(clientname, other.clientname)
				&& Objects.equals(custodian, other.custodian)
				&& Double.doubleToLongBits(remainingtransactionlimit) == Double
						.doubleToLongBits(other.remainingtransactionlimit)
				&& Double.doubleToLongBits(transactionlimit) == Double.doubleToLongBits(other.transactionlimit);
	}
	

}
