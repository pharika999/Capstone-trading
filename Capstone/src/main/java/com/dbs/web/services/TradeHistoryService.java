package com.dbs.web.services;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dbs.web.beans.TradeHistory;
import com.dbs.web.repository.TradeHistoryRepository;

 

 

 

@Service
public class TradeHistoryService {

 

 

 

    @Autowired
    private TradeHistoryRepository tradeRepo;

 

 

 

    public boolean insertTradeHistory(TradeHistory trade )
    {
        if(this.tradeRepo.findById(trade.getTradeid()).isPresent())
            return false;
        try {
            this.tradeRepo.save(trade);
        }catch(IllegalArgumentException e )
        {
            return false;
        }
        return true;
    }

 

 

 

    public List<TradeHistory> getTradeHistoryOfAllCustomers(String custId)
    {
        List<TradeHistory> tradeHistory = new ArrayList<TradeHistory>();
        this.tradeRepo.findAll().forEach(t->{
            if(t.getBid().getClientid().getCustodian().getCustodianid().equals(custId))
                tradeHistory.add(t);
            else if(t.getSid().getClientid().getCustodian().getCustodianid().equals(custId))
                tradeHistory.add(t);
            });
        return tradeHistory;
    }
    
    public List<TradeHistory> getTradeHistoryByClientId(String clientId)
    {
        List<TradeHistory> tradeHistory = new ArrayList<TradeHistory>();
        this.tradeRepo.findAll().forEach(t->{
            if(t.getBid().getClientid().getClientid().equals(clientId))
                tradeHistory.add(t);
            else if(t.getSid().getClientid().getClientid().equals(clientId))
                tradeHistory.add(t);
            });
        return tradeHistory;
    }

 

 

 


}