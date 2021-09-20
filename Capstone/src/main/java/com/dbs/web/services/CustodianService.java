package com.dbs.web.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.web.beans.Client;
import com.dbs.web.beans.Custodian;
import com.dbs.web.repository.CustodianRepository;

@Service
public class CustodianService {
	
	@Autowired
	private CustodianRepository custRepo;
	
	public Custodian findCustodianById(String id)
	{
		try {
			Optional<Custodian> custodian = this.custRepo.findById(id);

			return custodian.orElseThrow(()->{

				return new EntityNotFoundException("Custodian with "+id + " does not exist");
			});

		}catch(IllegalArgumentException e )
		{
			return null;
		}
	}

}
