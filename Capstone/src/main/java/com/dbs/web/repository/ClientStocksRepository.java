package com.dbs.web.repository;

import org.springframework.data.repository.CrudRepository;

import com.dbs.web.beans.ClientStocks;
import com.dbs.web.beans.ClientStocksUtility;

public interface ClientStocksRepository extends CrudRepository<ClientStocks,ClientStocksUtility>{

}
