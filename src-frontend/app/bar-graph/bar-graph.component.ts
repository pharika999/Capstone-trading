import { Component, OnInit } from '@angular/core';
import { ElementRef, ViewChild } from '@angular/core';
import { GoogleChartComponent } from 'angular-google-charts';
import { ChartType, Row } from "angular-google-charts";
import { Observable } from 'rxjs';
import { DataService } from '../services/data.service';
import { LocalStorageService } from '../services/localStorage.service';

@Component({
  selector: 'app-bar-graph',
  templateUrl: './bar-graph.component.html',
  styleUrls: ['./bar-graph.component.css']
})
export class BarGraphComponent implements OnInit {
  // myType = ChartType.PieChart;
  // title = 'Pie Chart representing percentage of number of transactions done by clients';
  // type = 'PieChart';
  // data = [
  //   ['DBS001', 5.0],
  //   ['DBS002', 36.8],
  //   ['DBS003', 42.8],
  //   ['DBS004', 18.5],
  //   ['DBS005', 16.2]
  // ];
  // data:any[];
  // columnNames = ['Client ID', 'Percentage'];
  // options = {is3D: true,
  // };
  // width = 1350;
  // height = 1100;
  // clients:any;
   constructor(private dataSvc:DataService,private storageService:LocalStorageService) { 
    this.data=[];
  }
  // getClients():Observable<any>{
  //   this.dataSvc.getDataFromApi('http://localhost:8080/clients/custodianid/'+this.storageService.get('custodian'))
  //   .subscribe((result: any) => {
  //     //console.log(result);
  //     result.forEach((element:any) => {
  //       element["percentage"]=(element.remainingtransactionlimit/element.transactionlimit)*100
  //     });
  //     this.clients=result;
  //     //console.log(this.clients);
  //     this.clients.forEach((element:any) => {
  //       let temp:any[];
  //       temp=[element.clientid,element.percentage]
  //       //console.log(temp)
  //       this.data.push(temp);
  //       return this.clients;
  //     });
  //     console.log("data",this.data);
  //   },(err:any)=>{
  //     console.log(err);
  //   });
  //   return this.clients;
  // }
  // public async fetchClients(){
  //   this.clients=await this.getClients().pipe().toPromise();
  // }
  
  ngOnInit() {
    this.dataSvc.getDataFromApi('http://localhost:8080/clients/custodianid/'+this.storageService.get('custodian'))
    .subscribe((result: any) => {
      //console.log(result);
      result.forEach((element:any) => {
        element["percentage"]=(element.remainingtransactionlimit/element.transactionlimit)*100
      });
      this.clients=result;
      //console.log(this.clients);
      this.clients.forEach((element:any) => {
        let temp:any[];
        temp=[element.clientid,element.percentage]
        //console.log(temp)
        this.data.push(temp);
      });
      console.log("data",this.data);
    },(err:any)=>{
      console.log(err);
    });
  }

  data:any[];
  columnNames = ['Client ID', 'Percentage'];
  options = {is3D: true,
  };
  width = 1350;
  height = 1100;
  clients:any;

  myType = ChartType.PieChart;
  title = 'Pie Chart representing percentage of number of transactions done by clients';
  type = 'PieChart';

 
}