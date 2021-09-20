package com.dbs.web.rest;
import java.util.HashMap;
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
import com.dbs.web.beans.Custodian;
import com.dbs.web.services.CustodianService;

@RestController
@CrossOrigin
@RequestMapping("/custodian")
public class CustodianRestController {
	@Autowired
	private CustodianService custodianService;
	@GetMapping("/{custodianid}")
	private ResponseEntity<Object> findClientById(@PathVariable String custodianid)
	{
		try { 
			Custodian c= this.custodianService.findCustodianById(custodianid);
			return ResponseEntity.status(HttpStatus.OK)
					.body(c);

		}catch (EntityNotFoundException e) {
			System.out.println("error");
			Map<String,String> map=new HashMap<>();
			map.put("Error", "Client with id "+custodianid+"not found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(map);
		}
	}

}
