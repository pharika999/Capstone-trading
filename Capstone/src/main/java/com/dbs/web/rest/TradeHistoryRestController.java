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

 

import com.dbs.web.beans.TradeHistory;
import com.dbs.web.services.TradeHistoryService;

 

 

 

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/tradehistory")
public class TradeHistoryRestController {
    
    @Autowired
    private TradeHistoryService tradeService;
    
     @PostMapping
        public ResponseEntity<Map<String, String>> insertTradeHistory(@RequestBody TradeHistory tradeHistory)
        {
            boolean b=this.tradeService.insertTradeHistory(tradeHistory);
            Map<String,String> map=new HashMap();
            if(b==true)
            {
                
                //System.out.println("if block");
                map.put("message", "success");
                return  ResponseEntity
                        .status(HttpStatus.ACCEPTED)
                        .body(map);
            }
            //System.out.println("outside");
            map.put("message","unsucessful");
            return  ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(map);
        }
     
     @GetMapping("/clientid/{clientid}")
        private ResponseEntity<Object> findTradeHistoryByClientId(@PathVariable String clientid)
        {
            try { 
                List<TradeHistory> t= this.tradeService.getTradeHistoryByClientId(clientid);
                System.out.println(t);
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
                List<TradeHistory> t= this.tradeService.getTradeHistoryOfAllCustomers(custid);
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