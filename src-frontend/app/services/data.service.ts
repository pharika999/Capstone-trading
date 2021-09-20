import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";

@Injectable()
export class DataService {
    custodianData: any;

    constructor(private http: HttpClient) {
        this.custodianData = [];
    }

    getDataFromApi(url: string) {
       return this.http.get(url);
    }

    getCustodianData() {
        return this.custodianData;
    }
    setCustodianData(custodianData: any) {
        this.custodianData = custodianData;
    }
    postApi(url:string,payload:any){
        return this.http.post(url,payload);
    }
}