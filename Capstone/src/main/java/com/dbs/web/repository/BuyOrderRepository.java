package com.dbs.web.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.dbs.web.beans.BuyOrder;

public interface BuyOrderRepository extends CrudRepository<BuyOrder, String>{
	
	
  @Query(nativeQuery = true, value="Select * from buy_order bo where status <> 'Completed' order by bo.orderdate asc")
	  public Optional<List<BuyOrder>> findByOrderDate(); 
}
