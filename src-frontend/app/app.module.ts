import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HeaderComponent } from './header/header.component';
import { RouterModule } from '@angular/router';
import { DataService } from './services/data.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DashboardComponent } from './dashboard/dashboard.component';
import { OmsComponent } from './oms/oms.component';
import { LogoutComponent } from './logout/logout.component';
import { TradinghistoryComponent } from './tradinghistory/tradinghistory.component';
import { CommonModule } from '@angular/common';
import { ReportComponent } from './report/report.component';
import { LocalStorageService } from './services/localStorage.service';
import { UniversalAppInterceptor } from './universal-app.interceptor';
import { AuthenticationguardService } from './authenticationguard.service';
import { AuthguardGuard } from './authguard.guard';
import { BarGraphComponent } from './bar-graph/bar-graph.component';
import { GoogleChartsModule } from 'angular-google-charts';
import { PieChartComponent } from './pie-chart/pie-chart.component';
import { OrdersComponent } from './orders/orders.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HeaderComponent,
    DashboardComponent,
    OmsComponent,
    TradinghistoryComponent,
    ReportComponent,
    BarGraphComponent,
    PieChartComponent,
    OrdersComponent
  ],
  imports: [
    BrowserModule,
    CommonModule,
    FormsModule,
    GoogleChartsModule,
    HttpClientModule,
    ReactiveFormsModule,
    RouterModule.forRoot([
      {
        path: '', component: LoginComponent
      },
      {
        path: 'dashboard', component: DashboardComponent, canActivate:[AuthguardGuard]
      },
      {
        path: 'oms', component: OmsComponent,canActivate:[AuthguardGuard]
      },
      {
        path: 'logout', component: LoginComponent
      },
      {
        path: 'tradinghistory', component: TradinghistoryComponent, canActivate:[AuthguardGuard]
      },
      {
        path: 'report', component: ReportComponent, canActivate:[AuthguardGuard]
      },
      {
        path: 'orders', component:OrdersComponent,canActivate:[AuthguardGuard]
      }
    ]),
    BrowserAnimationsModule
  ],
  providers: [DataService,LocalStorageService, AuthenticationguardService,
    { provide: HTTP_INTERCEPTORS, useClass: UniversalAppInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
