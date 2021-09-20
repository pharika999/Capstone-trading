package com.dbs.web.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.dbs.web.beans.SellOrder;

public interface SellOrderRepository extends CrudRepository<SellOrder, String> {
	
	  @Query(nativeQuery = true, value="Select * from sell_order so where status <> 'Completed' order by so.orderdate asc")
	  public Optional<List<SellOrder>> findByOrderDate(); 
}
