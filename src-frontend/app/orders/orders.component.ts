import { Component, OnInit } from '@angular/core';
import { Data } from '@angular/router';
import { DataService } from '../services/data.service';
import { LocalStorageService } from '../services/localStorage.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {

  clients:any;
  selectedBuyClient:any;
  selectedClientBuyOrders:any;
  buyOrders:any;
  selectedSellClient:any;
  selectedClientSellOrders:any;
  sellOrders:any;

  constructor(private dataSvc:DataService,private storageService:LocalStorageService) { }

  ngOnInit(): void {
    this.dataSvc.getDataFromApi('http://localhost:8080/buyOrder/custodianid/'+this.storageService.get('custodian'))
        .subscribe((result: any) => {
          //console.log(result);
           this.buyOrders=result;
           this.selectedClientBuyOrders=result;
           console.log(this.buyOrders);
           //console.log(result);
          },(err:any)=>{
            console.log(err);
          })

          this.dataSvc.getDataFromApi('http://localhost:8080/sellOrder/custodianid/'+this.storageService.get('custodian'))
        .subscribe((result: any) => {
          //console.log(result);
           this.sellOrders=result;
           this.selectedClientSellOrders=result;
           console.log(this.sellOrders);
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
  onChangeBuyClient(event:any){
    this.selectedBuyClient=event.target.value;
    console.log("selected",event.target.value);
    if(this.selectedBuyClient=="All")
    this.selectedClientBuyOrders=this.buyOrders;
    else {
      this.dataSvc.getDataFromApi('http://localhost:8080/buyOrder/clientid/'+this.selectedBuyClient)
      .subscribe((result: any) => {
        console.log(result);
         this.selectedClientBuyOrders=result;
         console.log(result);
        },(err:any)=>{
          console.log(err);
        })
    }
  }
  onChangeSellClient(event:any){
    this.selectedSellClient=event.target.value;
    console.log("selected",event.target.value);
    if(this.selectedSellClient=="All")
    this.selectedClientBuyOrders=this.buyOrders;
    else {
      this.dataSvc.getDataFromApi('http://localhost:8080/sellOrder/clientid/'+this.selectedSellClient)
      .subscribe((result: any) => {
        console.log(result);
         this.selectedClientSellOrders=result;
         console.log(result);
        },(err:any)=>{
          console.log(err);
        })
      }

  }
}
