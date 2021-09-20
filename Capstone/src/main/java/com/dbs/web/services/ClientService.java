package com.dbs.web.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.web.beans.Client;
import com.dbs.web.repository.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepo;

	public Client findClientById(String id)
	{
		try {
			Optional<Client> client = this.clientRepo.findById(id);

			return client.orElseThrow(()->{

				return new EntityNotFoundException("Client with "+id + " does not exist");
			});

		}catch(IllegalArgumentException e )
		{
			return null;
		}
	}
	
	public List<Client> getClientsByCustodianId(String custId)
	{
		 List<Client> clients = new ArrayList<Client>();
	     this.clientRepo.findAllByCustodianCustodianid(custId).forEach(c->clients.add(c));
	     return clients;
	}
}
