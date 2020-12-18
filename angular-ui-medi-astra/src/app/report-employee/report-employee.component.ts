import { EmployeeService } from '../employee.service';
import { Component, OnInit } from '@angular/core';
import { Employee } from '../employee';
import { Router } from '@angular/router';
import {Chart} from 'chart.js';

@Component({
  selector: 'app-report-employee',
  templateUrl: './report-employee.component.html',
  styleUrls: ['./report-employee.component.css']
})
export class ReportEmployeeComponent implements OnInit {

	Employee = [];
		stat1: any;
		Linechart =[];
	
		
  constructor(private employeeService: EmployeeService,
    private router: Router) {
}



  ngOnInit(): void {
	
	this.employeeService.getAllEmployeesReport().subscribe(result => {
      console.log(result);
	  this.stat1 = result;
	  const data0 = [ this.stat1[0]];
	const data1 = [ this.stat1[1]];
	console.log(data0);
	  
this.Employee = new Chart('canvas', {
          type: 'pie',
          data: {  
          labels: ["intake","No"], 
​
          datasets: [{
            data: data0,
            backgroundColor: 'rgba(28,181, 284, 0.6)',
			borderColor: 'rgba(220,220,220,2)',
			borderWidth: 2,
            },{
			data: data1,
            backgroundColor: 'rgba(255, 99, 132, 0.5)',
			borderColor: 'rgba(151,187,205,1)',
			borderWidth: 2,

		}] 
        },  
        options: { 
			title:{
				text:"PIECHART",
				display:true
			},
		}
	});	
	});	
    }
	
	public chartOptions: any = {
    responsive: true
  };
}