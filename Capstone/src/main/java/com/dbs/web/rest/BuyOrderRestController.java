package com.dbs.web.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.web.beans.BuyOrder;
import com.dbs.web.services.BuyOrderService;

@RestController
@CrossOrigin
@RequestMapping("/buyOrder")
public class BuyOrderRestController {
	
	@Autowired
	private BuyOrderService buyService;
	
	@PostMapping
    public ResponseEntity<Map<String, String>> insertBuyOrder(@RequestBody BuyOrder buyOrder)
    {
		String s="";
		try {
        s=this.buyService.insertBuyOrder(buyOrder);
        Map<String,String> map=new HashMap<>();
            map.put("message", s);
            return  ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(map);
        
		}
		catch(Exception e) {
			 Map<String,String> map=new HashMap<>();
	            map.put("message", s);
	            return  ResponseEntity
	                    .status(HttpStatus.ACCEPTED)
	                    .body(map);
		}
    }
	@GetMapping("/clientid/{clientid}")
    private ResponseEntity<Object> findTradeHistoryByClientId(@PathVariable String clientid)
    {
        try {
            List<BuyOrder> t= this.buyService.getBuyordersByClientId(clientid);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(t);
                   
           
        }catch (EntityNotFoundException e) {
            System.out.println("error");
            Map<String,String> map=new HashMap<>();
            map.put("message","Client with id "+clientid+"not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(map);
        }
    }

 @GetMapping("/custodianid/{custid}")

 private ResponseEntity<Object> findAllTradeHistory(@PathVariable String custid)
    {
        try {
            List<BuyOrder> t= this.buyService.getBuyordersOfAllCustomers(custid);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(t);
                   
           
        }catch (EntityNotFoundException e) {
            System.out.println("error");
            Map<String,String> map=new HashMap<>();
            map.put("message","Custodian with id "+custid+"not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(map);
        }
    }
}
