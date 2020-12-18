import { Component, OnInit } from '@angular/core';
import { EmployeeService } from "../employee.service";
import { FileDB } from "../fileDB";
import { Observable } from "rxjs";
@Component({
  selector: 'app-listrequest',
  templateUrl: './listrequest.component.html',
  styleUrls: ['./listrequest.component.css']
})
export class ListrequestComponent implements OnInit {
fileDBs: Observable<FileDB[]>;
  constructor(private employeeService: EmployeeService) { }

  ngOnInit() {
	  this.reloadData();
  }
reloadData() {
    this.fileDBs = this.employeeService.getAllRequest();
  }
}
