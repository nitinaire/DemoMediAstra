import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CreateEmployeeComponent } from './create-employee/create-employee.component';
import { EmployeeDetailsComponent } from './employee-details/employee-details.component';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { HttpClientModule } from '@angular/common/http';
import { UpdateEmployeeComponent } from './update-employee/update-employee.component';
import { SearchEmployeeComponent } from './search-employee/search-employee.component';
import { ReportEmployeeComponent } from './report-employee/report-employee.component';
import { ChartsModule } from 'ng2-charts';
import { HelpingHandComponent } from './helping-hand/helping-hand.component';
import { ListrequestComponent } from './listrequest/listrequest.component';
import { BlogsComponent } from './blogs/blogs.component';
import { MedihelpComponent } from './medihelp/medihelp.component';
@NgModule({
  declarations: [
    AppComponent,
    CreateEmployeeComponent,
    EmployeeDetailsComponent,
    EmployeeListComponent,
    UpdateEmployeeComponent,
    SearchEmployeeComponent,
	ReportEmployeeComponent,
	HelpingHandComponent,
	ListrequestComponent,
	BlogsComponent,
	MedihelpComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
	ChartsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }