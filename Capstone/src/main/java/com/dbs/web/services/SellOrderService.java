package com.dbs.web.services;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dbs.web.beans.BuyOrder;
import com.dbs.web.beans.Client;
import com.dbs.web.beans.ClientStocks;
import com.dbs.web.beans.ClientStocksUtility;
import com.dbs.web.beans.SellOrder;
import com.dbs.web.beans.TradeHistory;
import com.dbs.web.repository.BuyOrderRepository;
import com.dbs.web.repository.ClientStocksRepository;
import com.dbs.web.repository.SellOrderRepository;


@Service
public class SellOrderService {
    
    @Autowired
    private ClientStocksService clientstockService;
    @Autowired
    private SellOrderRepository sellRepo;
    
    @Autowired
    private BuyOrderService buyservice;
    
    @Autowired
    private BuyOrderRepository buyRepo;

 

    @Autowired
    private TradeHistoryService tradeService;
    
    @Autowired
    private ClientStocksRepository  cskrepo;
    
    @Autowired
    private ClientService cliService;

 

    public List<SellOrder> getSellOrderByOrderDate() {
        try { 
            List<SellOrder> sellorder =this.sellRepo.findByOrderDate().get();
    return sellorder; 
    }
        catch(IllegalArgumentException e ) { return null; } 
    }
    
    public String insertSellOrder(SellOrder so)    {
        if(this.sellRepo.findById(so.getSellid()).isPresent())
            return "Sell order already Exists";
        try {
        	so.setSellid(generateId());
            so.setOrderdate(LocalDate.now());
            so.setStatus("Waiting");
            System.out.println(so.getQuantity());
            so.setRemainingquantity(so.getQuantity());
            System.out.println("Rem qua"+so.getRemainingquantity());
            Client bc=this.cliService.findClientById(so.getClientid().getClientid());
            ClientStocksUtility cskid= new ClientStocksUtility(so.getClientid(),so.getInstrumentid());
            ClientStocks csSeller=this.clientstockService.getClientStockById(cskid);
            double amount=so.getPrice()*so.getQuantity();
            if(bc.getRemainingtransactionlimit()-amount<0 )
            	return "Transaction Limit exceeded";
            if(csSeller.getQuantity()<so.getQuantity())
                return "Please check you quantity. you cannot sell more than you hold";
            if(!cskrepo.existsById(cskid))
                return "You don't have existing instruments";
            this.sellRepo.save(so);
            sellmatch(so,cskid);
        }catch(IllegalArgumentException e )
        {
            return "Please check you details and Try again";
        }
        return "sucessfully placed sell order";
    }
    public String generateId() {
        long count=this.sellRepo.count()+1;
        if(count<10)
            return "S00"+count;
        else if(count<100)
            return  "S0"+count;
        else
            return "S"+count;
    }
    public void assign(ClientStocksUtility cskidseller,ClientStocksUtility cskidbuyer,int qty) {
        ClientStocks csSeller=this.clientstockService.getClientStockById(cskidseller);
        if(csSeller.getQuantity()-qty==0)
            this.clientstockService.deleteClientStock(cskidseller);
        else {
            csSeller.setQuantity(csSeller.getQuantity()-qty);
            this.clientstockService.updateClientStock(csSeller);
        }
        if(this.cskrepo.findById(cskidbuyer).isPresent()) {
        ClientStocks csbuyer=this.clientstockService.getClientStockById(cskidbuyer);
        csbuyer.setQuantity(csbuyer.getQuantity()+qty);
        this.clientstockService.updateClientStock(csbuyer);
        }
        else {
            ClientStocks cs=new ClientStocks();
            cs.setCskid(cskidbuyer);
            cs.setQuantity(qty);
            this.clientstockService.insertClientStock(cs);
        }
       
    }
public void sellmatch(SellOrder so,ClientStocksUtility cskid) {
        
        List<BuyOrder> buyod=this.buyservice.getBuyOrderByOrderDate();
        
        for(BuyOrder bo:buyod) {
            if(!so.getClientid().getClientid().equals(bo.getClientid().getClientid())) {
            if(so.getInstrumentid().getInstrumentid().equals(bo.getInstrumentid().getInstrumentid())&&so.getPrice()==bo.getPrice()) {
                double amountbuyer=bo.getPrice()*bo.getQuantity();
                ClientStocksUtility cskidbuyer= new ClientStocksUtility(bo.getClientid(),bo.getInstrumentid());
                int qty;
                if(so.getRemainingquantity()==bo.getRemainingquantity()) {
                    int tempqunatity=bo.getRemainingquantity()-so.getRemainingquantity();
                    qty=bo.getRemainingquantity();
                    bo.setRemainingquantity(tempqunatity);
                    so.setRemainingquantity(tempqunatity);
           
                    bo.setStatus("Completed");
                    so.setStatus("Completed");
                }
                else if(so.getRemainingquantity()>bo.getRemainingquantity()) {
                    int tempqunatity=so.getRemainingquantity()-bo.getRemainingquantity();
                    qty=bo.getRemainingquantity();
                    so.setRemainingquantity(tempqunatity);
                    bo.setRemainingquantity(0);
                   
                    bo.setStatus("Completed");
                    so.setStatus("Partially Completed");
    
                }
                else {
                    int tempqunatity=bo.getRemainingquantity()-so.getRemainingquantity();
                    qty=so.getRemainingquantity();
                  
                    bo.setRemainingquantity(tempqunatity);
                    so.setRemainingquantity(0);
                    bo.setStatus("Partially Completed");
                    so.setStatus("Completed");      
                }
                double amount=qty*so.getPrice();
                Client boClient=this.cliService.findClientById(bo.getClientid().getClientid());
                Client soClient=this.cliService.findClientById(so.getClientid().getClientid());
                boClient.setRemainingtransactionlimit(boClient.getRemainingtransactionlimit()-amount);
                soClient.setRemainingtransactionlimit(soClient.getRemainingtransactionlimit()-amount);
             assign(cskid,cskidbuyer,qty);
             this.buyRepo.save(bo);
             this.sellRepo.save(so);
             this.tradeService.insertTradeHistory(new TradeHistory(bo,so,LocalDate.now(),bo.getPrice()*qty,qty,bo.getPrice()));
             //break;    
            }    
        }
        }
    }
}