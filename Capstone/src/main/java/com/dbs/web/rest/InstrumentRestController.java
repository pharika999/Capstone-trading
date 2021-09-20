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
import com.dbs.web.beans.Instrument;
import com.dbs.web.services.InstrumentService;
@RestController
@CrossOrigin
@RequestMapping("/instruments")
public class InstrumentRestController {
	
	@Autowired
	private InstrumentService insService;
	
	@GetMapping
	private ResponseEntity<Object> getInstruments()
	{
		try { 
			List<Instrument> instruments= this.insService.getInstruments();
			
			return ResponseEntity.status(HttpStatus.OK)
					.body(instruments);
					
			
		}catch (EntityNotFoundException e) {
			System.out.println(e);
			Map<String,String> map=new HashMap<>();
			map.put("Error", "Instruments Not Found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(map);
		}
	}
	
	@GetMapping("/{instrumentid}")
	private ResponseEntity<Object> findInstrumentById(@PathVariable String instrumentid)
	{
		try { 
			Instrument instrument = this.insService.findInstrumentById(instrumentid);
			return ResponseEntity.status(HttpStatus.OK)
					.body(instrument);
					
			
		}catch (EntityNotFoundException e) {
			System.out.println("error");
			Map<String,String> map=new HashMap<>();
			map.put("Error", "Instrument with id "+instrumentid+"not found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(map);
		}
	}
}