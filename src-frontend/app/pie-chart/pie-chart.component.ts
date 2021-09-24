import { Component, OnInit } from '@angular/core';

import { DataService } from '../services/data.service';
import { LocalStorageService } from '../services/localStorage.service';

@Component({
  selector: 'app-pie-chart',
  templateUrl: './pie-chart.component.html',
  styleUrls: ['./pie-chart.component.css']
})
export class PieChartComponent implements OnInit {
  clientStocks:Map<string,number>=new Map();
  clients:any;
  totalQuantity:any;
  clientsQuantity=[];

 responseData1:any;
 responseData2:any;
 responseData3:any;
  getQuantity(){
    this.clients.forEach((element:any) => {
      element["Quantity"]=   this.clientStocks.get(element.clientid);   
      element["Percentage"]= (this.clients.Quantity/this.totalQuantity)*100;

    });
    console.log(this.clients);
  }


  constructor(private dataSvc:DataService,private storageService:LocalStorageService) { 
    this.clients=[];
   // this.clientStocks.set("",10);
  }
  
  // getEntries(){
  //   return Array.from(this.clientStocks.entries());
  // }

  ngOnInit(): void {
    

  //         this.getClientStocks();
  //         this.dataSvc.getDataFromApi('http://localhost:8080/clientstocks/totalQuantity')
  //         .subscribe((result: any) => {
  //           console.log("total",result);
  //           this.totalQuantity=result;
  //               // this.dataSvc.getDataFromApi('http://localhost:8080/clientstocks/allClients')
  //               // .subscribe((result: any) => {
  //               //   console.log("all",result);
  //               //   this.clientStocks=result;
  //               // },(err:any)=>{
  //               //   console.log(err);
  //               // })
  //         },(err:any)=>{
  //           console.log(err);
  //         }) 

  //       }
  // getClientStocks(){
  //   this.dataSvc.getDataFromApi('http://localhost:8080/clientstocks/allClients')
  //   .subscribe((result: any) => {
  //     console.log("all",result);
  //     this.clientStocks=result;
  //     this.getClients();
  //   },(err:any)=>{
  //     console.log(err);
  //   })

  // }
  // getClients(){
  //   this.dataSvc.getDataFromApi('http://localhost:8080/clients/custodianid/'+this.storageService.get('custodian'))
  //         .subscribe((result: any) => {
  //           //console.log(result);
  //           console.log(this.clientStocks);
  //           this.clients=result;
  //           console.log(this.clients);
  //           this.clients.forEach((element:any) => {
  //            // console.log(element);
  //             //let isClientID = this.clientStocks.forEach(x=>x.clientid==element.clientid);
  //             if(this.clientStocks.hasOwnProperty(element.clientid) )
  //             {
  //             element["Quantity"]=   this.clientStocks.get(element.clientid);   
  //             console.log("Quantity",element["Quantity"]);
  //             element["Percentage"]= (element["Quantity"]/this.totalQuantity.total)*100;
  //             console.log("Percentage",element["Percentage"]);
  //             }
  //           });
  //           console.log(this.clients);
  //           console.log("Stocks=",this.clientStocks);
            
  //         },(err:any)=>{
  //           console.log(err);
  //         })
          

  }
}
