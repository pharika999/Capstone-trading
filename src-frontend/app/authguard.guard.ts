import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthenticationguardService } from './authenticationguard.service';

@Injectable({
  providedIn: 'root'
})
export class AuthguardGuard implements CanActivate {

  constructor(private authService:AuthenticationguardService,private router:Router){}
  canActivate(){
    if(!this.authService.getToken())
    {
    this.router.navigate(['/']);
    }
    return this.authService.getToken();
  } 
}
