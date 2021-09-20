import { Component, OnInit } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { DataService } from '../services/data.service';
import { LocalStorageService } from '../services/localStorage.service';
import { Router, RouterModule } from '@angular/router';

 

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  custodian={
    custodianid:'',
    password:''
  }
  constructor(private http: HttpClient,
    private router:Router, private dataSvc: DataService,private storageService:LocalStorageService) {
      this.custodian={
        custodianid:'',
        password:''
      }
 

  }


  apiResult={
    success:false,
    error:false
  }

 

  ngOnInit(): void {
  }
  getData(url:string){
    //return this.dataSvc.getDataFromApi('');
  }
  handleLogin(){
    let url = 'http://localhost:8080/login/authenticate';
    let payLoad = {
      "username":this.custodian.custodianid,
      "password":this.custodian.password
    }
    this.dataSvc.postApi(url, payLoad).subscribe((result:any) => {
      console.log(result);
      this.apiResult.success=true;
      this.apiResult.error =false;
      console.log("Successsful");
      console.log(result);
      //this.jwtService.setToken(result);
      this.storageService.set('token',result.jwt);
      //console.log("Token in local storage",this.storageService.get('token'));
       if (this.dataSvc.getCustodianData.length == 0) {
        this.dataSvc.getDataFromApi('http://localhost:8080/custodian/'+this.custodian.custodianid)
        .subscribe((result: any) => {
          console.log(result);
            this. custodian=result;
            this.storageService.set('custodian',result.custodianid);
            this.dataSvc.setCustodianData(result);
            //console.log("login  ",this.custodian);
            this.router.navigate(['/dashboard']);
          },(err:any)=>{
            console.log(err);
          })
      }
    }, (err:any) => {
      alert("Invalid credentials");
      this.apiResult.success=false;
      this.apiResult.error =true;
    })
    //console.log("Button clicked")
    //this.router.navigate(['/customer']);
}
}