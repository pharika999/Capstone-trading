import { Injectable } from '@angular/core';
import { LocalStorageService } from './services/localStorage.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationguardService {

  constructor(private storageService: LocalStorageService) { }

  getToken(){
    return !!this.storageService.get('token');
  }
}
