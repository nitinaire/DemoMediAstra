import { EmployeeDetailsComponent } from './employee-details/employee-details.component';
import { CreateEmployeeComponent } from './create-employee/create-employee.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { UpdateEmployeeComponent } from './update-employee/update-employee.component';
import { SearchEmployeeComponent } from './search-employee/search-employee.component';
import { ReportEmployeeComponent } from './report-employee/report-employee.component';
import { HelpingHandComponent } from './helping-hand/helping-hand.component';
import { ListrequestComponent } from './listrequest/listrequest.component';
import { BlogsComponent } from './blogs/blogs.component';
import { MedihelpComponent } from './medihelp/medihelp.component';
const routes: Routes = [
  { path: '', redirectTo: 'employee', pathMatch: 'full' },
  { path: 'employees', component: EmployeeListComponent },
  { path: 'add', component: CreateEmployeeComponent },
  { path: 'update/:id', component: UpdateEmployeeComponent },
  { path: 'details/:id', component: EmployeeDetailsComponent },
  { path: 'search', component: SearchEmployeeComponent },
  { path: 'report', component: ReportEmployeeComponent },
  { path: 'helpinghand', component: HelpingHandComponent },
  { path: 'helpinghandrequestList', component: ListrequestComponent },
  {path: 'blogs', component: BlogsComponent },
  { path: 'medihelp', component: MedihelpComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }