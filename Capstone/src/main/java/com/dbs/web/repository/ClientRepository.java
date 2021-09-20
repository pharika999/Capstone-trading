package com.dbs.web.repository;

import org.springframework.data.repository.CrudRepository;

import com.dbs.web.beans.Client;

public interface ClientRepository extends CrudRepository<Client, String> {

	Iterable<Client> findAllByCustodianCustodianid(String custId);

}
