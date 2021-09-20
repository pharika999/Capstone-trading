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
  selectedClient:any;
  selectedClientBuyOrders:any;
  buyOrders:any;

  constructor(private dataSvc:DataService,private storageService:LocalStorageService) { }

  ngOnInit(): void {
    this.dataSvc.getDataFromApi('http://localhost:8080/buyOrders/custodianid/'+this.storageService.get('custodian'))
        .subscribe((result: any) => {
          //console.log(result);
           this.buyOrders=result;
           this.selectedClientBuyOrders=result;
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
    this.selectedClientBuyOrders=this.buyOrders;
    else {
      this.dataSvc.getDataFromApi('http://localhost:8080/buyOrder/clientid/'+this.selectedClient)
      .subscribe((result: any) => {
        console.log(result);
         this.selectedClientBuyOrders=result;
         console.log(result);
        },(err:any)=>{
          console.log(err);
        })
    }
  }
}
