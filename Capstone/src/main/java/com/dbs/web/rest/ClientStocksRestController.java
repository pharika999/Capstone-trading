package com.dbs.web.rest;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dbs.web.beans.Client;
import com.dbs.web.beans.ClientStocks;
import com.dbs.web.beans.ClientStocksUtility;
import com.dbs.web.beans.Custodian;
import com.dbs.web.services.ClientStocksService;

@RestController
@CrossOrigin
@RequestMapping("/clientstocks")
public class ClientStocksRestController {
	
	@Autowired
	private ClientStocksService service;
	
	@PostMapping
    public ResponseEntity<Map<String, String>> insertClientStock(@RequestBody ClientStocks clientstocks)
    {
        boolean b=this.service.insertClientStock(clientstocks);
        Map<String,String> map=new HashMap<>();
        if(b==true)
        {
            
            //System.out.println("if block");
            map.put("message", "success");
            return  ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(map);
        }
        //System.out.println("outside");
        map.put("message","Unsuccessful");
        return  ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(map);
    }
	@PutMapping
    public ResponseEntity<Map<String, String>> updateClientStock(@RequestBody ClientStocks clientstocks)
    {
        boolean b=this.service.updateClientStock(clientstocks);
        Map<String,String> map=new HashMap<>();
        if(b==true)
        {
            
            //System.out.println("if block");
            map.put("message", "success");
            return  ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(map);
        }
        //System.out.println("outside");
        map.put("message","Unsuccessful");
        return  ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(map);
    }
	@DeleteMapping
    public ResponseEntity<Map<String, String>> deleteClientStock(@RequestBody ClientStocksUtility clientstockid)
    {
        boolean b=this.service.deleteClientStock(clientstockid);
        Map<String,String> map=new HashMap<>();
        if(b==true)
        {
            
            //System.out.println("if block");
            map.put("message", "success");
            return  ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(map);
        }
        //System.out.println("outside");
        map.put("message","Unsuccessful");
        return  ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(map);
    }
	
	@GetMapping("/allClients")
	public ResponseEntity<Map<String,Integer>> getAllClientsAndQuantity(){
		try { 
			Map<String,Integer> map=this.service.getAllClientsAndQuantity();
			System.out.println(map);
			return ResponseEntity.status(HttpStatus.OK)
					.body(map);

		}catch (EntityNotFoundException e) {
			System.out.println("error");
			Map<String,Integer> map=new HashMap<>();
			map.put("Error", -1);
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(map);
		}
	}
	
	@GetMapping("/totalQuantity")
	public ResponseEntity<Map<String,Integer>> getTotalQuantity(){
		try { 
			int quantity=this.service.getTotalQuantity();
			Map<String,Integer> map=new HashMap<>();
			map.put("total", quantity);
			System.out.println(map);
			return ResponseEntity.status(HttpStatus.OK)
					.body(map);

		}catch (EntityNotFoundException e) {
			System.out.println("error");
			Map<String,Integer> map=new HashMap<>();
			map.put("Error", -1);
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(map);
		}
	}
}
