import { Component, OnInit } from '@angular/core';
import { DataService } from '../services/data.service';
import { LocalStorageService } from '../services/localStorage.service';

@Component({
  selector: 'app-tradinghistory',
  templateUrl: './tradinghistory.component.html',
  styleUrls: ['./tradinghistory.component.css']
})
export class TradinghistoryComponent implements OnInit {
  trade:any;
  selectedClientTrade:any;
  clients:any;
  selectedClient:any="All"
  constructor(private dataSvc:DataService,private storageService:LocalStorageService) { }

  
  ngOnInit(): void {
    this.dataSvc.getDataFromApi('http://localhost:8080/tradehistory/custodianid/'+this.storageService.get('custodian'))
        .subscribe((result: any) => {
          //console.log(result);
           this.trade=result;
           this.selectedClientTrade=result;
           //console.log(result);
          },(err:any)=>{
            console.log(err);
          })
          this.dataSvc.getDataFromApi('http://localhost:8080/clients/custodianid/'+this.storageService.get('custodian'))
          .subscribe((result: any) => {
            console.log(result);
            this.clients=result;
          },(err:any)=>{
            console.log(err);
          }) 
  }
  onChangeClient(event:any){
    this.selectedClient=event.target.value;
    console.log("selected",event.target.value);
    if(this.selectedClient=="All")
    this.selectedClientTrade=this.trade;
    else {
      this.dataSvc.getDataFromApi('http://localhost:8080/tradehistory/clientid/'+this.selectedClient)
      .subscribe((result: any) => {
        console.log(result);
         this.selectedClientTrade=result;
         console.log(result);
        },(err:any)=>{
          console.log(err);
        })
    }
  }



}
