package com.dbs.web.beans;
import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;

import javax.persistence.OneToOne;
@Embeddable
public class ClientStocksUtility implements Serializable{
	@OneToOne
	@JoinColumn(name="Clientid")
	private Client client;
	@OneToOne
	@JoinColumn(name="Instrumentid")
	private Instrument instrument;
	public ClientStocksUtility() {
		super();
	}
	
	public ClientStocksUtility(Client client, Instrument instrument) {
		super();
		this.client = client;
		this.instrument = instrument;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Instrument getInstrument() {
		return instrument;
	}

	public void setInstrument(Instrument instrument) {
		this.instrument = instrument;
	}

	@Override
	public int hashCode() {
		return Objects.hash(client, instrument);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientStocksUtility other = (ClientStocksUtility) obj;
		return Objects.equals(client, other.client) && Objects.equals(instrument, other.instrument);
	}
}


