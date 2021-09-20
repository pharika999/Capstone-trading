import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { DataService } from '../services/data.service';
import { LocalStorageService } from '../services/localStorage.service';

 

@Component({
  selector: 'app-oms',
  templateUrl: './oms.component.html',
  styleUrls: ['./oms.component.css']
})

 

export class OmsComponent implements OnInit {

 

  submitb: any = ' Trade ';
  direction: any = '';
  omsData: any = [];
  price: any;
  quantity: any;
  client:any;
  instrument:any;
  constructor(private formBuilder: FormBuilder,private datasvc:DataService,private storageService:LocalStorageService) {
    this.omsData = {
      clients: [],
      clientname: '',
      instruments: [],
      instrumentname: '',
      facevalue: '',
      expirydate: '',
      direction: ''
    }

 

  }

 

  onClientChange(selected:any){
    this.omsData.clients.forEach((element:any) => {
     // console.log(selected)
      if(element.clientid==selected.value){
        this.omsData.clientname=element.clientname;
        this.client=element
      }
    });
  }
  onInstrumentChange(selected:any){
    this.omsData.instruments.forEach((element:any) => {
    //  console.log(selected)
      if(element.instrumentid==selected.value){
      //  console.log(element)
        this.instrument=element
        this.omsData.instrumentname=element.instrumentname
        this.omsData.facevalue=element.facevalue
        this.omsData.expirydate=element.date
        //console.log(element.date)
      }
    });
  }
  ngOnInit(): void {
      this.datasvc.getDataFromApi('http://localhost:8080/clients/custodianid/'+this.storageService.get('custodian'))
        .subscribe((result: any) => {
          this.omsData.clients = result.map((x: any) => {
            return { ...x }
          });
        },(err:any)=>{
          console.log(err);
        })

 

        this.datasvc.getDataFromApi('http://localhost:8080/instruments')
        .subscribe((result: any) => {
          this.omsData.instruments = result.map((x: any) => {
            return { ...x }
          });
        },(err:any)=>{
          console.log(err);
        })
  }

 

  directionValue() {
    this.direction = this.direction;
    if (this.direction == 1) {
      this.submitb = 'Buy';
    }
    else if (this.direction == 2) this.submitb = 'Sell'
    else {
      this.submitb = ' Trade ';
    }
  }
  handleSubmit() {
    if(this.quantity==0||this.quantity%25!=0||this.quantity>100)
    alert("Check quantity. Please try again!");
    else
    {
    console.log(this.submitb)
    if(this.submitb=="Buy")
  {
    let url="http://localhost:8080/buyOrder";
    let payload={
      "bid":"",
      "price":this.price,
      "clientid":this.client,
      "instrumentid":this.instrument,
      "quantity":this.quantity,
    }
    this.datasvc.postApi(url, payload)
    .subscribe((result:any) => {
      alert(result.message);
    }, err => {
      console.log("Hi",err.message);
    })
  }
  else if(this.submitb=="Sell")
  {
    let url="http://localhost:8080/sellOrder";
    let payload={
      "sellid":"",
      "price":this.price,
      "clientid":this.client,
      "instrumentid":this.instrument,
      "quantity":this.quantity,
    }
    this.datasvc.postApi(url, payload)
    .subscribe((result:any) => {
      console.log(result.message);
    }, (err:any) => {
      console.log("Hi",err.message);
    })
  }
  else
    alert("select either Buy or sell")
}
}
  checkPrice() {
    if((this.price > (1.12 * this.omsData.facevalue)) || (this.price < (0.88*this.omsData.facevalue))){
      return 0;
    }
    else return 1;

 

  }

 

  checkQuantity() {
    if (this.quantity % 25 != 0 && this.quantity <= 100) {
      return 1;
    }
    if (this.quantity > 100) {
      return 2;
    }
    else return 3;
  }

 

}