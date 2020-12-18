import { Component, OnInit } from '@angular/core';
import { Employee } from '../employee';
import { Router, ActivatedRoute } from '@angular/router';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-search-employee',
  templateUrl: './search-employee.component.html',
  styleUrls: ['./search-employee.component.css']
})
export class SearchEmployeeComponent implements OnInit {

  city:string;
	 selectedCity: string = '';
employee: Employee = new Employee();
  submitted = false;
  showAll= true;  
  
  constructor(private route: ActivatedRoute,private router: Router,
    private employeeService: EmployeeService) { }

	  ngOnInit() {
  }
  
   newEmployee(): void {
    this.submitted = false;
    this.employee = new Employee();
  }


onSubmit() {
	this.submitted = true;
    this.save();  
	 //this.router.navigate(['..']);
	 this.submitted = false;
      
  }
  
  onAllSubmit() {
	this.submitted = true;
   
      
  }

 

   selectChangeHandler (event: any) {
    //update the ui
    this.selectedCity = event.target.value;
	//console.log(this.selectedDay);
  }
  
  selectIntake(event: any){
	  this.showAll=false;

  }
 
 save() {     
       console.log(this.employee.city);
	   console.log(this.employee.isIntake);
	   if(this.showAll==true){
		    console.log("searchAll");
		       this.employeeService.getVaccineIntake("All").subscribe(res => {
      const fileURL = URL.createObjectURL(res);
	  console.log("searchAll")
      window.open(fileURL, '_blank');
	    var a         = document.createElement('a');
          a.href        = fileURL; 
          a.target      = '_blank';
          a.download    = 'AllData.pdf';
          document.body.appendChild(a);
          a.click();
    });
		   
	   } 
	
	  else if(this.employee.city==undefined && this.employee.isIntake=='1'){
		  console.log("search YES  No CITY")
		  // var inputValue = (<HTMLInputElement>document.getElementById('city')).value;   
		  
   	 this.employeeService.notTakenList("1").subscribe(res => {
      const fileURL = URL.createObjectURL(res);
      window.open(fileURL, '_blank');
	    var a         = document.createElement('a');
          a.href        = fileURL; 
          a.target      = '_blank';
          a.download    = 'AllTakenData.pdf';
          document.body.appendChild(a);
          a.click();
		
    });  
	  }
	  
	  
	  	  else if(this.employee.city !="" && this.employee.isIntake=='1'){
		  console.log("search YES  With CITY")
		   var inputValue = (<HTMLInputElement>document.getElementById('city')).value;   
    this.employeeService.getVaccineIntake(inputValue).subscribe(res => {
      const fileURL = URL.createObjectURL(res);
      window.open(fileURL, '_blank');
	    var a         = document.createElement('a');
          a.href        = fileURL; 
          a.target      = '_blank';
          a.download    = 'Dataof'+inputValue+'.pdf';
          document.body.appendChild(a);
          a.click();
	
	     
		   });
	  }
	else if(this.employee.isIntake=='0' ){
		
		 this.employeeService.notTakenList("0").subscribe(res => {
      const fileURL = URL.createObjectURL(res);
      window.open(fileURL, '_blank');
	    var a         = document.createElement('a');
          a.href        = fileURL; 
          a.target      = '_blank';
          a.download    = 'RemainingData.pdf';
          document.body.appendChild(a);
          a.click();
		
    });         
  }
   
 
   }
  

}
