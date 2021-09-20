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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dbs.web.beans.Client;
import com.dbs.web.services.ClientService;
@RestController
@CrossOrigin
@RequestMapping("/clients")
public class ClientRestConroller
{
	@Autowired
	private ClientService cliService;
	
	
	@GetMapping("/clientid/{clientid}")
	private ResponseEntity<Object> findClientById(@PathVariable String clientid)
	{
		try { 
			Client c= this.cliService.findClientById(clientid);
			return ResponseEntity.status(HttpStatus.OK)
					.body(c);
					
			
		}catch (EntityNotFoundException e) {
			System.out.println("error");
			Map<String,String> map=new HashMap<>();
			map.put("Error", "Client with id "+clientid+"not found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(map);
		}
	}
	
	
	@GetMapping("/custodianid/{custid}")
	private ResponseEntity<Object> getClientsByCustodianId(@PathVariable String custid)
	{
		try { 
			List<Client> c= this.cliService.getClientsByCustodianId(custid);
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(c);
					
			
		}catch (EntityNotFoundException e) {
			System.out.println(e);
			Map<String,String> map=new HashMap<>();
			map.put("Error", "Clients with id "+custid+"not found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(map);
		}
	}
	
	
}