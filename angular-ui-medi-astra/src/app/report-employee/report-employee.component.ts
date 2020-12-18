import { EmployeeService } from '../employee.service';
import { Component, OnInit } from '@angular/core';
import { Employee } from '../employee';
import { Router } from '@angular/router';

@Component({
  selector: 'app-report-employee',
  templateUrl: './report-employee.component.html',
  styleUrls: ['./report-employee.component.css']
})
export class ReportEmployeeComponent implements OnInit {

	stat1: any;
	data0:any;
	data1:any;

	constructor(private employeeService: EmployeeService,
	private router: Router) {
		}

	ngOnInit(): void {
		this.employeeService.getAllEmployeesReport().subscribe(result => {
		this.stat1 = result;
		this.data0 = [ this.stat1[0]];
		this.data1 = [ this.stat1[1]];
		this.pieChartData= [parseInt(this.data0), parseInt(this.data1)];
		});
	}
	
	pieChartOptions: any = {
					responsive: true,
					legend: {
							position: 'top',
						},
					tooltips: {
							enabled: true,
							mode: 'single',
						},
					};

	pieChartLabels: String[] = ['Vaccine taken count of employee', 'Vaccine not taken count of employee'];

	pieChartData: number[] = [0,0];

	pieChartType: String = 'pie';

	pieChartLegend = true;

	pieChartPlugins = [];

	pieChartColors = [{backgroundColor: ['rgba(0,255,0,3)', 'rgba(250,0,0)'],},];


}