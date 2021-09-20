package com.dbs.web.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.dbs.web.beans.Client;
import com.dbs.web.beans.ClientStocks;
import com.dbs.web.beans.ClientStocksUtility;
import com.dbs.web.repository.ClientStocksRepository;

@Service
public class ClientStocksService {

	/*
	 * @Autowired private ClientStocksRepository repo; public boolean
	 * insertClientStock(ClientStocks cs) { if(this.repo.existsById(cs.getCskid()))
	 * return false; try { this.repo.save(cs);
	 * 
	 * }catch (IllegalArgumentException e) { throw new
	 * IllegalArgumentException("ClientStocks is empty"); } return true; }
	 * 
	 * }
	 */
	@Autowired
	private ClientStocksRepository repo;
	public long getCount()
	{
		return this.repo.count();
	}
	public boolean insertClientStock(ClientStocks cs)
	{
		if(this.repo.existsById(cs.getCskid()))
			return false;
		try {
			this.repo.save(cs);
		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Client Stocks is empty");
		}
		return true;
	}
	public boolean updateClientStock(ClientStocks cs)
	{
		if(!this.repo.existsById(cs.getCskid()))
			return false;
		try {
			this.repo.save(cs);
		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Client Stocks is empty");
		}
		return true;
	}
	public boolean deleteClientStock(ClientStocksUtility cid)
	{
		if( !this.repo.findById(cid).isPresent())
			return false;
		try {
			this.repo.deleteById(cid);
		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Client Stocks is empty");
		}
		return true;
	}
	public ClientStocks getClientStockById(ClientStocksUtility cid)
	{
		return this.repo.findById(cid)
				.orElseThrow(()->  new EntityNotFoundException("Client Stocks does not exist with id "+cid));
	}
	public List<ClientStocks> getClientStocks()
	{
		List<ClientStocks> clientStocks = new ArrayList<ClientStocks>();
		this.repo.findAll().forEach(cs->clientStocks.add(cs));
		return clientStocks;
	}
	
	public Map<String,Integer> getAllClientsAndQuantity(){
		List<ClientStocks> clientStocks= getClientStocks();
		Map<String,Integer> map=new HashMap<>();
		for(ClientStocks c:clientStocks) {
			String clientid=c.getCskid().getClient().getClientid();
			if(map.containsKey(clientid))
				map.put(clientid,map.get(clientid)+c.getQuantity());
			else
				map.put(clientid, c.getQuantity());
		}
		return map;
	}
	public int getTotalQuantity() {
		List<ClientStocks> clientStocks=getClientStocks();
		int total=0;
		for(ClientStocks c:clientStocks)
			total+=c.getQuantity();
		return total;	
	}
	
}

