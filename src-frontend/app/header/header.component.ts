import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LocalStorageService } from '../services/localStorage.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  currentDate:any;
  constructor(private storageService:LocalStorageService,private router:Router) { 
    this.currentDate = new Date();
  }
  ngOnInit(): void {
  }
  onClickLogout(){
    this.storageService.remove('token');
    this.storageService.remove('custodian');
    this.router.navigate(['/']);
    //alert('Successfully logged out');
  }

}
