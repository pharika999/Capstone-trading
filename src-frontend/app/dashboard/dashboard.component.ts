import { Component, OnInit } from '@angular/core';
import { DataService } from '../services/data.service';
import { LocalStorageService } from '../services/localStorage.service';

@Component({
  selector: 'app-dashboard,mat-drawer-content',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  custodian={
    custodianid:'',
    custodianname:''
  }
  clients:any;
  currentDate:any;
  constructor(private storageService:LocalStorageService,private dataSvc:DataService) { 
    this.currentDate = new Date();
  }
  ngOnInit(): void {
    this.dataSvc.getDataFromApi('http://localhost:8080/custodian/'+this.storageService.get('custodian'))
        .subscribe((result: any) => {
          this.custodian=result;
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
}

