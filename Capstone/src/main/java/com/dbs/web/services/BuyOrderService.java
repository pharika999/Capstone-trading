package com.dbs.web.services;
import java.time.LocalDate;
import java.util.ArrayList;
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
public class BuyOrderService {

	@Autowired
	private ClientStocksRepository cskrepo;

	@Autowired
	private TradeHistoryService tradeService;

	@Autowired
	private ClientStocksService clientstockService;

	@Autowired
	private BuyOrderRepository buyRepo;

	@Autowired
	private SellOrderService sellservice;

	@Autowired
	private ClientService cliService;
	@Autowired
	private SellOrderRepository sellRepo;

	public List<BuyOrder> getBuyOrderByOrderDate() {
		try { 
			List<BuyOrder> buyorder =this.buyRepo.findByOrderDate().get();
			return buyorder; 
		}
		catch(IllegalArgumentException e ) { 
			return null; 
		} 
	}
	public List<BuyOrder> getBuyordersOfAllCustomers(String custId)
	{
	List<BuyOrder> buyorders = new ArrayList<BuyOrder>();
	this.buyRepo.findAll().forEach(t->{
	if(t.getClientid().getCustodian().getCustodianid().equals(custId))
	buyorders.add(t);
	});
	System.out.println(buyorders.get(0).getStatus());
	return buyorders;
	}
	public List<BuyOrder> getBuyordersByClientId(String clientId)
	{
	List<BuyOrder> buyorders = new ArrayList<BuyOrder>();
	this.buyRepo.findAll().forEach(t->{
	if(t.getClientid().getClientid().equals(clientId))
	buyorders.add(t);

	});
	return buyorders;
	}

	public String insertBuyOrder(BuyOrder bo)    {
		if(this.buyRepo.findById(bo.getBid()).isPresent())
			return "BuyOrder Already Exists";
		try {
			
			Client bc=this.cliService.findClientById(bo.getClientid().getClientid());
			//System.out.println(bc);
			double amount=bo.getPrice()*bo.getQuantity();
			//System.out.println("Amount: "+amount+"Price"+bo.getPrice()+"Quantity"+bo.getQuantity()+" Trans"+bc.getTransactionlimit());
			if(bc.getRemainingtransactionlimit()-amount<0)
			{
				//System.out.println("Trans limit exceeded");
				return "Transaction limit exceeded";
			}
			bo.setBid(generateId());
			bo.setStatus("Waiting");
			bo.setOrderdate(LocalDate.now());
			bo.setRemainingquantity(bo.getQuantity());
			this.buyRepo.save(bo);
			 buymatch(bo);
		}catch(IllegalArgumentException e)
		{
			return "Please Check you details and Try again. ";
		}
	
		return "Sucessfully Placed Buy Order";
	}
	public String generateId() {
		long count=this.buyRepo.count()+1;
		if(count<10)
			return "B00"+count;
		else if(count<100)
			return  "B0"+count;
		else
			return "B"+count;
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
	public void buymatch(BuyOrder bo) {

		ClientStocksUtility cskidbuyer= new ClientStocksUtility(bo.getClientid(),bo.getInstrumentid());
		List<SellOrder> sellod=this.sellservice.getSellOrderByOrderDate();
		//  System.out.println("Method called");
		for(SellOrder so:sellod) {
			if(!so.getClientid().getClientid().equals(bo.getClientid().getClientid())) {
				if(so.getInstrumentid().getInstrumentid().equals(bo.getInstrumentid().getInstrumentid())&&so.getPrice()==bo.getPrice()) {
					int qty;
					ClientStocksUtility cskidseller= new ClientStocksUtility(so.getClientid(),so.getInstrumentid());
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
					assign(cskidseller,cskidbuyer,qty);
					this.sellRepo.save(so);
					this.buyRepo.save(bo);
					//System.out.println("Method called");
					this.tradeService.insertTradeHistory(new TradeHistory(bo,so,LocalDate.now(),bo.getPrice()*qty,qty,bo.getPrice()));
					// break;
				}
				
			}
			
		}

	}
}